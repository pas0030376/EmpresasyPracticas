package com.example.empresasypracticas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailEstudent2ActivityFragment extends Fragment {
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    TextView nom;
    TextView empresa;
    TextView inicio;
    TextView motivo;
    TextView comentarios;

    public DetailEstudent2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_estudent2, container, false);
        nom = view.findViewById(R.id.tvnombreEF);
        empresa = view.findViewById(R.id.tvempresaEF);
        inicio = view.findViewById(R.id.tvfechasEF);
        motivo = view.findViewById(R.id.tvmotivos);
        comentarios = view.findViewById(R.id.tvcomments);


        Intent i = getActivity().getIntent();
        if (i != null) {
            Estudiante estudiante = (Estudiante) i.getSerializableExtra("estudiante");
            if (estudiante != null) {
                MostrarEstudiante(estudiante);
            }
        }
        return view;
    }

    private void MostrarEstudiante(Estudiante estudiante) {
        String fullname = estudiante.getNom().concat(" "+estudiante.getCognom());
        getActivity().setTitle(fullname);
        String fechas = "Inicio: "+estudiante.getInicio_practicas().concat("   Fin: "+estudiante.getFin_practicas());

        String nie = estudiante.getNIE();

        nom.setText(fullname);
        empresa.setText(estudiante.getEmpresa());
        inicio.setText(fechas);
        motivo.setText("Motivo: Finalizacion del convenio");
        comentarios.setText("Buen estudiante, Trabajador, Resposable, Trabajo en equipo, lo mejor de lo mejor in the world");

        items = new ArrayList<>(estudiante.getTareas());
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_tareas,
                R.id.tvtareaname,
                items
        );
    }
}
