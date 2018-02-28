package com.example.empresasypracticas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DetailEstudent2ActivityFragment extends Fragment {
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    TextView nom;
    TextView empresa;
    TextView inicio;
    TextView motivo;
    TextView comentarios;
    Button sendEmail;

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
        String[] CC = {"zapejustine@gmail.com"};
        String link="https://empresasypracticas.firebaseapp.com/formularioEstudiante.html";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulario");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hola. Good job for finishing FCT under "+estudiante.getEmpresa()+". Please rellenar this enquesta. Formulario link: "+link);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
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

    private String CommentsShow(String nie){

        return "hola";
    }
}
