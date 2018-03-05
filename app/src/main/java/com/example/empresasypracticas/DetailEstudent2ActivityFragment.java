package com.example.empresasypracticas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class DetailEstudent2ActivityFragment extends Fragment {
    FirebaseListAdapter<FormularioEstudiante> adapterForm;
    FirebaseListOptions<FormularioEstudiante> options;
    DatabaseReference query;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    TextView nom;
    TextView empresa;
    TextView inicio;
    Button sendEmail;
    ListView lv_comments;
    View view;

    public DetailEstudent2ActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterForm.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterForm.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_estudent2, container, false);
        nom = view.findViewById(R.id.tvnombreEF);
        empresa = view.findViewById(R.id.tvempresaEF);
        inicio = view.findViewById(R.id.tvfechasEF);
        sendEmail = view.findViewById(R.id.btnSendEmail);


        Intent i = getActivity().getIntent();
        if (i != null) {
            final Estudiante estudiante = (Estudiante) i.getSerializableExtra("estudiante");
            if (estudiante != null) {
                MostrarEstudiante(estudiante);
                sendEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendEmailToStudent(estudiante);
                    }
                });
            }
        }

        return view;
    }

    @SuppressLint("LongLogTag")
    private void sendEmailToStudent(Estudiante estudiante) {
        Log.i("Send email", "");
        String[] TO = {estudiante.getCorreo()};
        //String[] CC = {"zapejustine@gmail.com"};
        String link="https://empresasypracticas.firebaseapp.com/formularioEstudiante.html";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulario");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Salutacions, Felicitats acabant les teves pràctiques a l'empresa "+estudiante.getEmpresa()+". Si us plau omple aquesta enquesta sobre aquestes pràctiques. Link: "+link);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
           // Toast.makeText(DetailEstudent2Activity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();

            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void MostrarEstudiante(Estudiante estudiante) {
        String fullname = estudiante.getNom().concat(" "+estudiante.getCognom());
        getActivity().setTitle("");
        String fechas = "Inicio: "+estudiante.getInicio_practicas().concat("   Fin: "+estudiante.getFin_practicas());

        String nie = estudiante.getNIE();
        CommentsShow(nie);
        nom.setText(fullname);

        empresa.setText(estudiante.getEmpresa());
        inicio.setText(fechas);

        lv_comments.findViewById(R.id.lvcomments);
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("FormularioEstudiante");

        options = new FirebaseListOptions.Builder<FormularioEstudiante>()
                .setQuery(query.equalTo(nie),FormularioEstudiante.class)
                .setLayout(R.layout.lv_comentariosstudents)
                .build();

        adapterForm = new FirebaseListAdapter<FormularioEstudiante>(options){
            @Override
            protected void populateView(View v, FormularioEstudiante model, int position) {
                TextView formacionInicial = view.findViewById(R.id.f1);
                formacionInicial.setText(model.getFormacionInicial());
            }
        };

        lv_comments.setAdapter(adapterForm);
    }

    private void CommentsShow(String nie){
      /*  lv_comments.findViewById(R.id.lvcomments);
        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("FormularioEstudiante");

        options = new FirebaseListOptions.Builder<FormularioEstudiante>()
                .setQuery(query.equalTo(nie),FormularioEstudiante.class)
                .setLayout(R.layout.lv_comentariosstudents)
                .build();

        adapterForm = new FirebaseListAdapter<FormularioEstudiante>(options){
            @Override
            protected void populateView(View v, FormularioEstudiante model, int position) {
                TextView formacionInicial = view.findViewById(R.id.f1);
                formacionInicial.setText(model.getFormacionInicial());
            }
        };

        lv_comments.setAdapter(adapterForm);*/
    }
}
