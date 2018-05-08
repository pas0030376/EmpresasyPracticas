package com.example.empresasypracticas;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
public class FamHosteleria extends Fragment{
    FirebaseListAdapter<Empresa> adapter;
    FirebaseListOptions<Empresa> options;
    Query query;

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fam_hosteleria, container, false);
        getActivity().setTitle("Empresas");

        ListView lvempreses = view.findViewById(R.id.lv_hosteleria);


        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Empresas").orderByChild("sectorEscolar").equalTo("Hoteleria i turisme");


        options = new FirebaseListOptions.Builder<Empresa>()
                .setQuery(query,Empresa.class)
                .setLayout(R.layout.lv_empresas)
                .build();

        adapter = new FirebaseListAdapter<Empresa>(options){
            @Override
            protected void populateView(View view, Empresa model, int position) {
                TextView tvName = view.findViewById(R.id.tvempresas);
                tvName.setText(model.getNombre());

            }
        };
        lvempreses.setAdapter(adapter);


        lvempreses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?>adapterView, View view, int position, long id) {
                Empresa empresa=(Empresa)adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getContext(),DetallesEmpresaBottomNavigation.class);
                intent.putExtra("empresa",empresa);
                startActivity(intent);

            }
        });
        return view;
    }

}
