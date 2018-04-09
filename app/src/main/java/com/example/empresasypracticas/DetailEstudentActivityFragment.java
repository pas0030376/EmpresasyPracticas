package com.example.empresasypracticas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailEstudentActivityFragment extends Fragment {
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    TextView nom;
    TextView empresa;
    TextView inicio;

    public DetailEstudentActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_detail_estudent, container, false);

       nom = view.findViewById(R.id.tvnombreEF);
       empresa = view.findViewById(R.id.tvempresaEF);
       inicio = view.findViewById(R.id.tvinicio);

        ListView lvTareas = view.findViewById(R.id.lvtareas);

        Intent i = getActivity().getIntent();
        if (i != null) {
            Estudiante estudiante = (Estudiante) i.getSerializableExtra("estudiante");
            if (estudiante != null) {
                MostrarEstudiante(estudiante,lvTareas);
            }
        }
        return view;
    }

    private void MostrarEstudiante(Estudiante estudiante,ListView lvTareas) {


        String fullname = estudiante.getNom().concat(" "+estudiante.getCognom());
        getActivity().setTitle("");
        String fechas = "Inicio: "+estudiante.getInicio_practicas().concat("   Fin: "+estudiante.getFin_practicas());

        nom.setText(fullname);
        empresa.setText(estudiante.getEmpresa());
        inicio.setText(fechas);

        items = new ArrayList<>(estudiante.getTareas());
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_tareas,
                R.id.tvtareaname,
                items
        );

        lvTareas.setAdapter(adapter);
    }
}
