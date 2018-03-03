package com.example.empresasypracticas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmpresasActivityFragment extends Fragment {
    FirebaseListAdapter<Empresa> adapter;
    Button newEmpresa;
    Button buscar;
    EditText etbuscar;
    public static Empresa empresa;

    public EmpresasActivityFragment() {
    }
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
        View view =  inflater.inflate(R.layout.fragment_empresas, container, false);
        getActivity().setTitle("Empresas");

        ListView lvempreses = (ListView) view.findViewById(R.id.lvempresas);

        DatabaseReference query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Empresas");



        FirebaseListOptions<Empresa> options = new FirebaseListOptions.Builder<Empresa>()
                .setQuery(query,Empresa.class)
                .setLayout(R.layout.lv_empresas)
                .build();

        adapter = new FirebaseListAdapter<Empresa>(options){
            @Override
            protected void populateView(View view, Empresa model, int position) {
                TextView tvName = view.findViewById(R.id.tvempresas);
                tvName.setText(model.getNombre());
                TextView tvTipo = view.findViewById(R.id.tvTipo);
                tvTipo.setText(model.getTipo());

                //empresa=new Empresa(model.getNombre(),model.getTipo(),model.getTelefono(),model.getPersonaDeContacto(),model.getCorreoElectronico(),model.getWebpage(),model.getLlamadas());
                empresa=new Empresa(model.getNombre(),model.getTipo(),model.getTelefono(),model.getPersonaDeContacto(),model.getCorreoElectronico(),model.getWebpage());
            }
        };
        lvempreses.setAdapter(adapter);


        lvempreses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapterView, View view, int position, long id) {
                Empresa empresa=(Empresa)adapterView.getItemAtPosition(position);

                Intent intent=new Intent(getContext(),DetallesEmpresaBottomNavigation.class);
                intent.putExtra("empresa",empresa);
                startActivity(intent);

            }
        });


        newEmpresa = view.findViewById(R.id.newEmpresa);
        buscar = view.findViewById(R.id.bttbuscar);
        etbuscar = view.findViewById(R.id.tvbuscar);


        newEmpresa.setOnClickListener(listener);
        buscar.setOnClickListener(listener);

        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.newEmpresa:

                    Intent addEmpresa = new Intent(view.getContext(), addEmpresaActivity.class);
                    startActivityForResult(addEmpresa, 0);
                    break;
                case R.id.bttbuscar:
                    String empresab = String.valueOf(etbuscar.getText());
                    DatabaseReference empresa = FirebaseDatabase.getInstance().getReference().child("Empresas");

                    break;
            }
        }
    };
}
