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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link verLlamadas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link verLlamadas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class verLlamadas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Empresa empresa;
    Llamada llamada;

    FirebaseListAdapter<Llamada> adapter;
    DatabaseReference query;
    FirebaseListOptions<Llamada> options;


    public verLlamadas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment verLlamadas.
     */
    // TODO: Rename and change types and number of parameters
    public static verLlamadas newInstance(String param1, String param2) {
        verLlamadas fragment = new verLlamadas();
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
        View view = inflater.inflate(R.layout.fragment_ver_llamadas, container, false);
        getActivity().setTitle("Historial llamadas");

        ListView lvllamadas = (ListView) view.findViewById(R.id.lvllamadas);

        //recogemos el objecto empresa desde la actividad anterior
        Intent i = getActivity().getIntent();
        empresa = (Empresa) i.getSerializableExtra("empresa");

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Empresas").child(empresa.getNombre()).child("Llamadas");


        options = new FirebaseListOptions.Builder<Llamada>()
                .setQuery(query, Llamada.class)
                .setLayout(R.layout.lv_llamadas)
                .build();

        adapter = new FirebaseListAdapter<Llamada>(options) {
            @Override
            protected void populateView(View v, Llamada model, int position) {
                TextView tvDia = v.findViewById(R.id.tvDia);
                tvDia.setText("hola");
                TextView tvHora = v.findViewById(R.id.tvHora);
                tvHora.setText(model.getHoraLLamada());

               // llamada = new Llamada(model.getFechaLlamada(), model.getHoraLLamada(), model.getMotivoLlamada(), model.getPersonaContactada());

            }
        };
        lvllamadas.setAdapter(adapter);
        lvllamadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Llamada llamada = (Llamada) parent.getItemAtPosition(position);

                Intent intent = new Intent(getContext(), DetallesEmpresaBottomNavigation.class);
                /*putExtra("empresa",empresa);
                startActivity(intent);*/
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
