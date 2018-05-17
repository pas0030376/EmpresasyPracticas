package com.example.empresasypracticas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link editarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link editarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class editarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView nombreEmpresa,cif,adreca,municipi,cp,telefon,responsable,dniResponsable,cargoResponsable,telfResponsable,emailResponsable,nomTutor,dniTutor,carrecTutor,telefonTutor,emailTutor,tvHomologada;
    Empresa empresa;
    CheckBox homologada;



    public editarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment editarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static editarFragment newInstance(String param1, String param2) {
        editarFragment fragment = new editarFragment();
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

        /*private String adreca;
        private String municipi;
        private String cp;
        private String telefon;
        private String nombrepersonaDeContacto;
        private String dnipersonaDeContacto;
        private String cargopersonaDeContacto;
        private String telefonPersonaDeContacto;
        private String emailPersonaDeContacto;*/
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_editar, container, false);

        cif=view.findViewById(R.id.tvCIF);
        adreca=view.findViewById(R.id.tvAdreca);
        cp=view.findViewById(R.id.tvCP);
        municipi=view.findViewById(R.id.tvMunicipi);
        telefon=view.findViewById(R.id.tvTelefon);
        responsable=view.findViewById(R.id.tvResponsable);
        dniResponsable=view.findViewById(R.id.tvDniResponsable);
        cargoResponsable=view.findViewById(R.id.tvCarrecResponsable);
        telfResponsable=view.findViewById(R.id.tvTelefonResponsable);
        emailResponsable=view.findViewById(R.id.tvEmailResponsable);
        nomTutor=view.findViewById(R.id.tvNomTutor);
        dniTutor=view.findViewById(R.id.tvDniTutor);
        carrecTutor=view.findViewById(R.id.tvCarrecTutor);
        telefonTutor=view.findViewById(R.id.tvTelefonTutor);
        emailTutor=view.findViewById(R.id.tvEmailTutor);
        tvHomologada=view.findViewById(R.id.tvHomologada);
        homologada=view.findViewById(R.id.checkBoxHomolo);







        //recogemos el objecto empresa desde la actividad anterior
        Intent i = getActivity().getIntent();
        if (i != null) {
            empresa = (Empresa) i.getSerializableExtra("empresa");
            if (empresa != null) {
                String nomEmpresa= empresa.getNombre();
                getActivity().setTitle(nomEmpresa);
                //nombreEmpresa.setText(empresa.getNombre());
                cif.setText(empresa.getCif());
                adreca.setText(empresa.getAdreca());
                municipi.setText(empresa.getMunicipi());
                cp.setText(empresa.getCp());
                telefon.setText(empresa.getTelefon());
                responsable.setText(empresa.getNombrepersonaDeContacto());
                dniResponsable.setText(empresa.getDnipersonaDeContacto());
                cargoResponsable.setText(empresa.getCargopersonaDeContacto());
                telfResponsable.setText(empresa.getTelefonPersonaDeContacto());
                emailResponsable.setText(empresa.getEmailPersonaDeContacto());
                nomTutor.setText(empresa.getNombreTutor());
                dniTutor.setText(empresa.getDniTutor());
                carrecTutor.setText(empresa.getCargoTutor());
                telefonTutor.setText(empresa.getTelefonTutor());
                emailTutor.setText(empresa.getEmailTutor());

                String isHomologada=empresa.getHomologada();
                if(empresa.getHomologada()==null||empresa.getHomologada().equals("No")){
                    homologada.setChecked(false);
                    tvHomologada.setText("Empresa no homologada");
                }else if(isHomologada.equals("Si")){
                    homologada.setChecked(true);
                    tvHomologada.setText("Empresa homologada");
                }



               /* telfEmpresa.setText(empresa.getTelefono());
                personaDeContacto.setText(empresa.getPersonaDeContacto());
                correoEmpresa.setText(empresa.getCorreoElectronico());*/


            }
        }

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
