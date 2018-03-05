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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class DetailEstudent2ActivityFragment extends Fragment {
    FirebaseListAdapter<FormularioEstudiante> adapterForm;
    FirebaseListOptions<FormularioEstudiante> options;
    DatabaseReference query;
    TextView nom;
    TextView empresa;
    TextView inicio;
    Button sendEmail;
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

        ListView lvcomments = view.findViewById(R.id.lvcomments);

        Intent i = getActivity().getIntent();
        final Estudiante estudiante = (Estudiante) i.getSerializableExtra("estudiante");
        if (i != null) {
            if (estudiante != null) {
                MostrarEstudiante(estudiante);
                lvcomments.findViewById(R.id.lvcomments);
                String nie = estudiante.getNIE();

                query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("FormularioEstudiante");

                options = new FirebaseListOptions.Builder<FormularioEstudiante>()
                        .setQuery(query,FormularioEstudiante.class)
                        .setLayout(R.layout.lv_comentariosstudents)
                        .build();

                adapterForm = new FirebaseListAdapter<FormularioEstudiante>(options){
                    @Override
                    protected void populateView(View v, FormularioEstudiante model, int position) {
                        if (model == null){
                            TextView formacionInicial = view.findViewById(R.id.f1);
                            formacionInicial.setText(model.getFormacionInicial());
                        }
                        else {
                            Toast.makeText(getContext(), "Aun no se ha respondido este cuestionario",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                lvcomments.setAdapter(adapterForm);

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
        //String[] CC = {"proyectopoblenou@gmail.com"};
        String link="https://empresasypracticas.firebaseapp.com/formularioEstudiante.html";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulari");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Salutacions.\n Felicitats acabant les teves pràctiques a l'empresa "+estudiante.getEmpresa()+". Si us plau omple aquesta enquesta sobre aquestes pràctiques.\nLink: "+link);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Toast.makeText(getContext(), "Email enviat.", Toast.LENGTH_SHORT).show();

            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
           // Toast.makeText(DetailEstudent2Activity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();

            Toast.makeText(getContext(), "Email no enviat.", Toast.LENGTH_SHORT).show();
        }
    }

    private void MostrarEstudiante(Estudiante estudiante) {
        String fullname = estudiante.getNom().concat(" "+estudiante.getCognom());
        getActivity().setTitle("");
        String fechas = "Inicio: "+estudiante.getInicio_practicas().concat("   Fin: "+estudiante.getFin_practicas());

        nom.setText(fullname);

        empresa.setText(estudiante.getEmpresa());
        inicio.setText(fechas);
    }

}
