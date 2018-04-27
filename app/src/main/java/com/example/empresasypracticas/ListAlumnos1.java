package com.example.empresasypracticas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListAlumnos1 extends Fragment {
    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    DatabaseReference query;
    ListView lvalumnes;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lv_alumnos, container, false);
        lvalumnes = (ListView) view.findViewById(R.id.lvalumnos1);


        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes");

        options = new FirebaseListOptions.Builder<Estudiante>()
                .setQuery(query,Estudiante.class)
                .setLayout(R.layout.lv_alumno)
                .build();

        adapter = new FirebaseListAdapter<Estudiante>(options){
            @Override
            protected void populateView(View view, Estudiante model, int position) {
                TextView tvName = view.findViewById(R.id.tvname);
                tvName.setText(model.getNom()+" "+model.getCognom());
                TextView empresa = view.findViewById(R.id.tvEmpresa);
                empresa.setText(model.getEmpresa());
                TextView practica = view.findViewById(R.id.tvpracticas);
                try {
                    Date fechafin = sdf.parse(model.getFin_practicas());
                    if (currentTime.after(fechafin)){
                        practica.setText("Practicas terminadas");
                    }
                    else if (fechafin.after(currentTime)){
                        practica.setText("Practicas en curso");
                        practica.setTextColor(Color.parseColor("#0eae20"));
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
               // progressBar.setVisibility(View.GONE);
            }
        };
        lvalumnes.setAdapter(adapter);

        lvalumnes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Estudiante estudiante = (Estudiante) adapterView.getItemAtPosition(i);
                try {
                    Date fechafin = sdf.parse(estudiante.getFin_practicas());

                    if (currentTime.after(fechafin)){
                        Intent intent = new Intent(getContext(), DetailEstudent2Activity.class);
                        intent.putExtra("estudiante", estudiante);
                        startActivity(intent);}
                    else if (fechafin.after(currentTime)){
                        Intent intent = new Intent(getContext(), DetailEstudentActivity.class);
                        intent.putExtra("estudiante", estudiante);
                        startActivity(intent);
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

            }
        });
        return view;
    }
}