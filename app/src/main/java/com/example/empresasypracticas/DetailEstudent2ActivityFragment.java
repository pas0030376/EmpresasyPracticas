package com.example.empresasypracticas;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

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

    private void sendEmailToStudent(Estudiante estudiante) {
        // Recipient's email ID needs to be mentioned.
        String email = estudiante.getCorreo();

        // Sender's email ID needs to be mentioned
        String from = "jhazape@yahoo.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            String link="https://empresasypracticas.firebaseapp.com/formularioEstudiante.html";
            message.setText("Formulario link: "+"<a href=\"" + link + "\">" + link+ "</a>");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

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
