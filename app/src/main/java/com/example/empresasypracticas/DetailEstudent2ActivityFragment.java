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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class DetailEstudent2ActivityFragment extends Fragment {
    FirebaseListAdapter<FormularioEstudiante> adapter;
    FirebaseListOptions<FormularioEstudiante> options;
    Query query;
    TextView nom;
    TextView empresa;
    TextView inicio;
    Button sendEmail;
    TextView questionari;
    View view;

    public DetailEstudent2ActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();}

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_estudent2, container, false);
        nom = view.findViewById(R.id.tvnombreEF);
        empresa = view.findViewById(R.id.tvempresaEF);
        inicio = view.findViewById(R.id.tvfechasEF);
        sendEmail = view.findViewById(R.id.btnSendEmail);
        questionari = view.findViewById(R.id.tvIsnot);

        ListView lvcomments = view.findViewById(R.id.lvcomments);

        Intent i = getActivity().getIntent();

        if (i != null) {
            final Estudiante estudiante = (Estudiante) i.getSerializableExtra("estudiante");
            if (estudiante != null) {
                MostrarEstudiante(estudiante);
                lvcomments.findViewById(R.id.lvcomments);
                String email = estudiante.getCorreo();

                query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("FormularioEstudiante").orderByChild("EstudenEmail").equalTo(email);

                options = new FirebaseListOptions.Builder<FormularioEstudiante>()
                        .setQuery(query,FormularioEstudiante.class)
                        .setLayout(R.layout.lv_comentariosstudents)
                        .build();

                adapter = new FirebaseListAdapter<FormularioEstudiante>(options){
                    @Override
                    protected void populateView(View v, FormularioEstudiante model, int position) {
                        questionari.setVisibility(View.GONE);
                            TextView formacionInicial = v.findViewById(R.id.f1);
                            formacionInicial.setText("Valoració de la formació inicial de l'estudiant: "+model.getFormacionInicial());
                            TextView valoracionGlobal = v.findViewById(R.id.vg);
                            valoracionGlobal.setText("Valoració global de les pràctiques: "+model.getValoracionGlobal());
                            TextView valoracionestudiante = v.findViewById(R.id.ve);
                            valoracionestudiante.setText("Valoració general de l'estudiant: "+model.getValoracionEstudiante());
                            TextView repetirColab = v.findViewById(R.id.rc);
                            repetirColab.setText("¿Es repetiria una col·laboració amb "+model.getEstudentFullname()+"? "+model.getRepitirColaboracion());
                            TextView comentarios = v.findViewById(R.id.comments);
                            comentarios.setText("Comentaris: "+model.getComentarios());
                            TextView motivo = v.findViewById(R.id.motivo);
                            motivo.setText("Motiu de la finalització: "+model.getMotivoFin());
                            sendEmail.setVisibility(View.GONE);
                    }
                };

                lvcomments.setAdapter(adapter);

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
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Salutacions.\n Felicitats acabant les teves pràctiques a l'empresa "+estudiante.getEmpresa()+". Si us plau omple aquesta enquesta sobre aquestes pràctiques.\nLink: "+link+"\n\n"
        +"Moltes gràcies.");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

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
