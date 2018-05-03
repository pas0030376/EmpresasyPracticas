package com.example.empresasypracticas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jackandphantom.circularimageview.CircleImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListAlumnos3 extends Fragment {
    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    Query query;
    ListView lvalumnes;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    CircleImage photo;
    StorageReference storageRef = storage.getReferenceFromUrl("gs://empresasypracticas.appspot.com/");

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
        View view = inflater.inflate(R.layout.lv_alumnos3, container, false);
        lvalumnes = (ListView) view.findViewById(R.id.lv_alumnes3);

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes").orderByChild("estadoPracticas").equalTo("Acabades");

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
                practica.setText("Practicas Terminadas");
                // progressBar.setVisibility(View.GONE);
                //SetImageforStudent
                photo = view.findViewById(R.id.stdphoto);
                storageRef.child(model.getNIE()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
            }
        };
        lvalumnes.setAdapter(adapter);

        lvalumnes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Estudiante estudiante = (Estudiante) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), DetailEstudent2Activity.class);
                intent.putExtra("estudiante", estudiante);
                startActivity(intent);
            }
        });
        return view;
    }
}