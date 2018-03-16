package com.example.empresasypracticas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.bohush.geometricprogressview.GeometricProgressView;
import net.bohush.geometricprogressview.TYPE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;

public class AlumnadoActivityFragment extends Fragment {
    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    Button newStudent;
    DatabaseReference query;
    Button encurso;
    Button todos;
    Button Terminadas;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();
    ListView lvalumnes;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;

    public AlumnadoActivityFragment() {
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
        View view = inflater.inflate(R.layout.fragment_alumnado, container, false);
        getActivity().setTitle("Alumnado");

        lvalumnes = (ListView) view.findViewById(R.id.lvalumnos);
        progressBar = view.findViewById(R.id.progressView);

       query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes");

        options = new FirebaseListOptions.Builder<Estudiante>()
                .setQuery(query,Estudiante.class)
                .setLayout(R.layout.lv_alumnos)
                .build();

        AdapterALL();

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

        encurso = view.findViewById(R.id.btencurso);
        todos = view.findViewById(R.id.btall);
        Terminadas = view.findViewById(R.id.btfinish);
        newStudent = view.findViewById(R.id.newStudent);

        newStudent.setOnClickListener(listener);
        encurso.setOnClickListener(listener);
        Terminadas.setOnClickListener(listener);
        todos.setOnClickListener(listener);

        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.btencurso:
                    progressBar.setVisibility(View.VISIBLE);
                    adapter = new FirebaseListAdapter<Estudiante>(options){
                        @Override
                        protected void populateView(View view, Estudiante model, int position) {
                            progressBar.setVisibility(View.GONE);
                            try {
                               Date fechafin = sdf.parse(model.getFin_practicas());
                               if (fechafin.after(currentTime)){
                                   TextView tvName = view.findViewById(R.id.tvname);
                                   tvName.setText(model.getNom()+" "+model.getCognom());
                                   TextView empresa = view.findViewById(R.id.tvEmpresa);
                                   empresa.setText(model.getEmpresa());
                                   TextView practica = view.findViewById(R.id.tvpracticas);
                                   practica.setText("Practicas en curso");
                                   practica.setTextColor(Color.parseColor("#0eae20"));
                                }
                                else{
                                   TextView tvName = view.findViewById(R.id.tvname);
                                   tvName.setVisibility(View.GONE);
                                   TextView empresa = view.findViewById(R.id.tvEmpresa);
                                   empresa.setVisibility(View.GONE);
                                   TextView practica = view.findViewById(R.id.tvpracticas);
                                   practica.setVisibility(View.GONE);
                               }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                    };
                    lvalumnes.setAdapter(adapter);
                    break;
                case R.id.btfinish:
                    progressBar.setVisibility(View.VISIBLE);
                    adapter = new FirebaseListAdapter<Estudiante>(options){
                        @Override
                        protected void populateView(View view, Estudiante model, int position) {
                            progressBar.setVisibility(View.GONE);
                            try {
                                Date fechafin = sdf.parse(model.getFin_practicas());
                                if (fechafin.before(currentTime)){
                                    TextView tvName = view.findViewById(R.id.tvname);
                                    tvName.setText(model.getNom()+" "+model.getCognom());
                                    TextView empresa = view.findViewById(R.id.tvEmpresa);
                                    empresa.setText(model.getEmpresa());
                                    TextView practica = view.findViewById(R.id.tvpracticas);
                                    practica.setText("Practicas Terminadas");}
                                else{
                                    TextView tvName = view.findViewById(R.id.tvname);
                                    tvName.setVisibility(View.GONE);
                                    TextView empresa = view.findViewById(R.id.tvEmpresa);
                                    empresa.setVisibility(View.GONE);
                                    TextView practica = view.findViewById(R.id.tvpracticas);
                                    practica.setVisibility(View.GONE);

                                }
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                    };
                    lvalumnes.setAdapter(adapter);
                    break;
                case R.id.newStudent:
                    Intent addStudiante = new Intent(view.getContext(), addEstudiantesActivity.class);
                    startActivityForResult(addStudiante, 0);
                    break;
                case R.id.btall:
                    progressBar.setVisibility(View.VISIBLE);
                    AdapterALL();
                    break;
            }
        }
    };

    public void AdapterALL(){
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
                progressBar.setVisibility(View.GONE);
            }
        };
        lvalumnes.setAdapter(adapter);
    }
}