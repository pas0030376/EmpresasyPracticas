package com.example.empresasypracticas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link verAlumnos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link verAlumnos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class verAlumnos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Empresa empresa=new Empresa();
    FirebaseListAdapter<Estudiante> adapter;
    FirebaseListOptions<Estudiante> options;
    Query query;
    ListView lvAlumnosEmpresa;
    net.bohush.geometricprogressview.GeometricProgressView progressBar;
    DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date currentTime = Calendar.getInstance().getTime();

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public verAlumnos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment verAlumnos.
     */
    // TODO: Rename and change types and number of parameters
    public static verAlumnos newInstance(String param1, String param2) {
        verAlumnos fragment = new verAlumnos();
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
        //recogemos el objecto empresa desde la actividad anterior
        Intent i = getActivity().getIntent();
        empresa = (Empresa) i.getSerializableExtra("empresa");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_alumnos, container, false);
        getActivity().setTitle("Alumnos en "+empresa.getNombre());
        lvAlumnosEmpresa = (ListView) view.findViewById(R.id.lv_alumnes_empresa);

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Estudiantes").orderByChild("empresa").equalTo((empresa.getNombre()));

        options = new FirebaseListOptions.Builder<Estudiante>()
                .setQuery(query,Estudiante.class)
                .setLayout(R.layout.lv_alumnos_en_cada_empresa)
                .build();

        adapter = new FirebaseListAdapter<Estudiante>(options){
            @Override
            protected void populateView(View view, Estudiante model, int position) {
                TextView tvName = view.findViewById(R.id.tvNombreYApellidos);
                tvName.setText(model.getNom()+" "+model.getCognom());
            }
        };
        lvAlumnosEmpresa.setAdapter(adapter);
        lvAlumnosEmpresa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Estudiante estudiante = (Estudiante) adapterView.getItemAtPosition(i);

                try {
                    Date fechafin = sdf.parse(estudiante.getFin_practicas());

                    if (currentTime.after(fechafin)){
                        Intent intent = new Intent(getContext(), DetailEmpresaActivity.class);
                        intent.putExtra("estudiante", estudiante);
                        startActivity(intent);}
                    else if (fechafin.after(currentTime)){
                        Intent intent = new Intent(getContext(), DetailEmpresa2Activity.class);
                        intent.putExtra("estudiante", estudiante);
                        startActivity(intent);
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });


        return view;
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
