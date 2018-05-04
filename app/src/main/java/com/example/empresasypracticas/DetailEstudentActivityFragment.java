package com.example.empresasypracticas;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jackandphantom.circularimageview.CircleImage;

import java.util.ArrayList;

public class DetailEstudentActivityFragment extends Fragment {
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    TextView nom;
    TextView empresa;
    TextView inicio;
    TextView nie;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    CircleImage photo;
    StorageReference storageRef = storage.getReferenceFromUrl("gs://empresasypracticas.appspot.com/");


    public DetailEstudentActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_detail_estudent, container, false);

       nom = view.findViewById(R.id.tvnombreEF);
       empresa = view.findViewById(R.id.tvempresaEF);
       inicio = view.findViewById(R.id.tvinicio);
       photo = view.findViewById(R.id.stdphoto);
       nie = view.findViewById(R.id.tvsnie);

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
        nie.setText(estudiante.getNIE());

        //SetImageforStudent
        storageRef.child(estudiante.getNIE()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.w("Storage", "uri: " + uri.toString());
                Glide.with(getContext()).load(uri).into(photo);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });

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
