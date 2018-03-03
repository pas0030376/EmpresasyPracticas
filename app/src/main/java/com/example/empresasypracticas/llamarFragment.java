package com.example.empresasypracticas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link llamarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link llamarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class llamarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button btnLlamar;
    EditText tvFecha,tvHora,tvMotivo,tvPersonaContactada;
    Empresa empresa=new Empresa();
    Llamada llamada;
    String fechaActual,horaActual;
    private DatabaseReference mRef;
    private Task<Void> mDatabase;

    public llamarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment llamarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static llamarFragment newInstance(String param1, String param2) {
        llamarFragment fragment = new llamarFragment();
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
        View view= inflater.inflate(R.layout.fragment_llamar, container, false);
        Button btnGuardar=view.findViewById(R.id.btnGuardar);
        btnLlamar=view.findViewById(R.id.btnLlamar);
        tvFecha=view.findViewById(R.id.etFecha);
        tvHora=view.findViewById(R.id.etHora);
        tvMotivo=view.findViewById(R.id.etMotivo);
        tvPersonaContactada=view.findViewById(R.id.etPersonaContactada);

        //recogemos la fecha actual del sistema dándole formato
        Date date =new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual=dateFormat.format(date);

        //recogemos la hora actual del sistema dándole formato
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        horaActual=hourFormat.format(date);

        //recogemos el objecto empresa desde la actividad anterior
        Intent i = getActivity().getIntent();
        if (i != null) {
            empresa = (Empresa) i.getSerializableExtra("empresa");
            if (empresa != null) {
                MostrarEmpresa(empresa);
            }
        }

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoConfirmar(); //mostramos un cuadro de diálogo con las opciones llamar o cancelar
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLlamada();
            }
        });

        return view;
    }
    private void dialogoConfirmar() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
        dialogo1.setTitle("Atención:");
        dialogo1.setMessage("¿Desea realizar una llamada a "+empresa.getNombre()+" ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }

    public void aceptar() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+empresa.getTelefono()));
        startActivity(intent);

        //mostramos en el tvFecha la fecha actual
        tvFecha.setText(fechaActual);
        //mostramos en el tvHora la hora actual
        tvHora.setText(horaActual);
    }

    private void MostrarEmpresa(Empresa empresa) {
        String nomEmpresa=empresa.getNombre();
        getActivity().setTitle(nomEmpresa);
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
    private void guardarLlamada(){ //guardamos los datos de la llamada en la empresa correpondiente.
        String nombreEmpresa="",fechaLlamada="",horaLlamada="",motivoLlamada="",personaContactadaLlamada="";
        nombreEmpresa=empresa.getNombre();
        fechaLlamada=tvFecha.getText().toString().trim();
        horaLlamada=tvHora.getText().toString().trim();
        motivoLlamada=tvMotivo.getText().toString().trim();
        personaContactadaLlamada=tvPersonaContactada.getText().toString().trim();
        llamada=new Llamada(nombreEmpresa,fechaLlamada,horaLlamada,motivoLlamada,personaContactadaLlamada);

        mRef =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://empresasypracticas.firebaseio.com/");
        String mId = mRef.push().getKey();
        mDatabase = mRef.child("Llamadas").child(mId).setValue(llamada);

        Intent intent = new Intent(getContext(),DetallesEmpresaBottomNavigation.class);
        startActivityForResult(intent, 0);


    }

}

