package com.example.empresasypracticas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class addEmpresaActivityFragment extends Fragment{
        private View view;
        private DatabaseReference mRef;
        private Task<Void> mDatabase;
        Button addempresa;

    public addEmpresaActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_empresa, container, false);

        getActivity().setTitle("Añadir Empresa");


        addempresa = view.findViewById(R.id.addempresa);
        addempresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewEmpresa();
            }
        });

        return view;
    }

    private void writeNewEmpresa() {
        EditText nom =  view.findViewById(R.id.etnomempresa);
        String fname = nom.getText().toString();
        EditText tipo = view.findViewById(R.id.etTipoempresa);
        String ftipo = tipo.getText().toString();
        EditText correo = view.findViewById(R.id.etCorreo);
        String fcorreo = correo.getText().toString();
        EditText telefono = view.findViewById(R.id.etTelefono);
        String fteleono = telefono.getText().toString();
        EditText webpage = view.findViewById(R.id.etWebpage);
        String fwebpage = webpage.getText().toString();
        EditText personaDeContacto = view.findViewById(R.id.etPersonaDeContacto);
        String fpersonacontacto = personaDeContacto.getText().toString();


        //Empresa empresa = new Empresa(fname,ftipo,fteleono,fpersonacontacto,fcorreo,fwebpage);
        mRef =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://empresasypracticas.firebaseio.com/");
        String mId = fname;
       // mDatabase = mRef.child("Empresas").child(mId).setValue(empresa);
    }
}
