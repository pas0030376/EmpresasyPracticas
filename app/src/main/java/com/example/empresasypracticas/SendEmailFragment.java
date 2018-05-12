package com.example.empresasypracticas;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jackandphantom.circularimageview.CircleImage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SendEmailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SendEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendEmailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Empresa empresa=new Empresa();
    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    Query query;
    ListView lvEnquesta;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    CircleImage photo;
    StorageReference storageRef = storage.getReferenceFromUrl("gs://empresasypracticas.appspot.com/");

    public SendEmailFragment() {
        // Required empty public constructor
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SendEmailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendEmailFragment newInstance(String param1, String param2) {
        SendEmailFragment fragment = new SendEmailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_email, container, false);

        Intent i = getActivity().getIntent();
        empresa = (Empresa) i.getSerializableExtra("empresa");

        lvEnquesta = view.findViewById(R.id.lvEnquesta);

        progressBar = view.findViewById(R.id.progressBarSendEmail);
        progressBar.setVisibility(View.VISIBLE);

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes").orderByChild("estadoPracticas").equalTo("Acabades");

        options = new FirebaseListOptions.Builder<Estudiante>()
                .setQuery(query,Estudiante.class)
                .setLayout(R.layout.lv_alumnos_en_cada_empresa)
                .build();

        adapter = new FirebaseListAdapter<Estudiante>(options){
            @Override
            protected void populateView(View view, Estudiante model, int position) {
                progressBar.setVisibility(View.GONE);
                TextView tvName = view.findViewById(R.id.tvNombreYApellidos);
                tvName.setText(model.getNom()+" "+model.getCognom());
                TextView practica = view.findViewById(R.id.tvpracticas);
                practica.setText("Practicas Terminadas");
                //SetImageforStudent
                photo = view.findViewById(R.id.stdPhoto);
                Glide.with(getContext())
                        .load(storageRef.child(model.getNIE()+".jpg"))
                        .into(photo);



            }
        };
        lvEnquesta.setAdapter(adapter);
        lvEnquesta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estudiante estudiante = (Estudiante) parent.getItemAtPosition(position);
                dialogoConfirmar(estudiante);

            }
        });
        return view;
    }


    private void dialogoConfirmar(final Estudiante estudiante) {

        final PrettyDialog dialog = new PrettyDialog(getContext());
        dialog.setTitle("Atención")
                .setMessage("Voleu enviar un correu electrònic d'enquesta a " + empresa.getNombreTutor() + " ?")
                .addButton(
                        "Enviar",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.pdlg_color_blue,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {
                                sendEmail(empresa, estudiante);
                            }
                        }
                )
                .addButton(
                        "Cancelar",
                        R.color.pdlg_color_white,
                        R.color.pdlg_color_red,
                        new PrettyDialogCallback() {
                            @Override
                            public void onClick() {
                                dialog.dismiss();
                            }
                        }
                );
        dialog.show();
    }

    @SuppressLint("LongLogTag")
    private void sendEmail(Empresa empresa, Estudiante estudiante) {
        Log.i("Send email", "");
        String[] TO = {empresa.getEmailTutor()};
        //String[] CC = {"proyectopoblenou@gmail.com"};
        String link="https://empresasypracticas.firebaseapp.com/formularioEmpresa.html";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String studentName = estudiante.getNom()+" "+estudiante.getCognom();

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulari");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Salutacions.\n L'alumne "+studentName+" ja ha acabat la seva pràctica a l'empresa "+estudiante.getEmpresa()+". Si us plau omple aquesta enquesta sobre aquestes pràctiques.\nLink: "+link+"\n\n"
                +"Moltes gràcies.");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            // Toast.makeText(DetailEstudent2Activity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();

            Toast.makeText(getContext(), "Email no enviat.", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
