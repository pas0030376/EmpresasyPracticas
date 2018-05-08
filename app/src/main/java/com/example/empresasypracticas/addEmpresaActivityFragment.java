package com.example.empresasypracticas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class addEmpresaActivityFragment extends Fragment{
        private View view;
        private DatabaseReference mRef;
        private Task<Void> mDatabase;
        Button addempresa;
        Button cancelar;

    public addEmpresaActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_empresa, container, false);

        getActivity().setTitle("Afegir Empresa");

        final Spinner spinner = view.findViewById(R.id.famempresa_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.fam_empresas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cancelar = view.findViewById(R.id.btnCancelEmpresa);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Empresa = new Intent(view.getContext(), EmpresasActivity.class);
                startActivityForResult(Empresa, 0);
            }
        });

        addempresa = view.findViewById(R.id.addempresa);
        addempresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewEmpresa(spinner);
            }
        });

        return view;
    }

    private void writeNewEmpresa(Spinner spinner) {
        Context context = getContext();
        EditText nom =  view.findViewById(R.id.etnomempresa);
        String fname = nom.getText().toString();
        EditText CIF = view.findViewById(R.id.etCIF);
        String fCIF = CIF.getText().toString();
        EditText adreca = view.findViewById(R.id.etAdreca);
        String fadreca = adreca.getText().toString();
        EditText municipi = view.findViewById(R.id.etMunicipi);
        String fmunicipi = municipi.getText().toString();
        EditText CP = view.findViewById(R.id.etCP);
        String fCP = CP.getText().toString();
        EditText telefon = view.findViewById(R.id.etTelefono);
        String ftelefon = telefon.getText().toString();
        EditText personaDeContacto = view.findViewById(R.id.etPersonaDeContacto);
        String fpersonacontacto = personaDeContacto.getText().toString();
        EditText dniPersonaDeContacto = view.findViewById(R.id.etDNIPersonaDeContacto);
        String fdniPersonaDeContacto = dniPersonaDeContacto.getText().toString();
        EditText cargoPersonaDeContacto = view.findViewById(R.id.cargoPersonaDeContacto);
        String fcargoPersonaDeContacto = cargoPersonaDeContacto.getText().toString();
        EditText telefonPersonaDeContacto = view.findViewById(R.id.etTelefonoPersonaDeContacto);
        String ftelefonPersonaDeContacto = telefonPersonaDeContacto.getText().toString();
        EditText emailPersonaDeContacto = view.findViewById(R.id.emailPersonaDeContacto);
        String femailPersonaDeContacto = emailPersonaDeContacto.getText().toString();

        EditText etNomTutor = view.findViewById(R.id.etTutor);
        String nomTutor = etNomTutor.getText().toString();
        EditText etDniTutor = view.findViewById(R.id.etDNITutor);
        String dniTutor = etDniTutor.getText().toString();
        EditText etCargoTutor = view.findViewById(R.id.cargoTutor);
        String cargoTutor = etCargoTutor.getText().toString();
        EditText etTelefonoTutor = view.findViewById(R.id.etTelefonoTutor);
        String telefonoTutor = etTelefonoTutor.getText().toString();
        EditText etEmailTutor = view.findViewById(R.id.emailTutor);
        String emailTutor = etEmailTutor.getText().toString();





        String fsectorEscolar = spinner.getSelectedItem().toString().trim() ;

        //Controlo que el nombre de la empresa sea la primera letra mayúscula y las siguientes minúsculas
        char c=' ';
        String fnameModificado="";
        for (int i=0;i<fname.length();i++){
            if(i==0){
                c=Character.toUpperCase(fname.charAt(i));
            }else{
                Character.toLowerCase(fname.charAt(i));
                c=Character.toLowerCase(fname.charAt(i));
            }

            fnameModificado=fnameModificado+c;
        }
        System.out.println(fnameModificado);
        //verificamos que no hayan campos vacíos
        if(!fname.equals("")&&!fCIF.equals("")&&!fadreca.equals("")&&!fmunicipi.equals("")&&!fCP.equals("")&&!ftelefon.equals("")&&!fpersonacontacto.equals("")&&!fdniPersonaDeContacto.equals("")&&!fcargoPersonaDeContacto.equals("")&&!ftelefonPersonaDeContacto.equals("")&&!femailPersonaDeContacto.equals("")&&!nomTutor.equals("")&&!dniTutor.equals("")&&!cargoTutor.equals("")&&!telefonoTutor.equals("")&&!emailTutor.equals("")){
            Empresa empresa = new Empresa(fnameModificado,fCIF,fadreca,fmunicipi,fCP,ftelefon,fpersonacontacto,fdniPersonaDeContacto,fcargoPersonaDeContacto,ftelefonPersonaDeContacto,femailPersonaDeContacto,nomTutor,dniTutor,cargoTutor,telefonoTutor,emailTutor,fsectorEscolar);
            mRef =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://empresasypracticas.firebaseio.com/");
            String mId = fnameModificado;
            mDatabase = mRef.child("Empresas").child(mId).setValue(empresa);
            CharSequence text = "Empresa añadida";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent Empresa = new Intent(view.getContext(), EmpresasActivity.class);
            startActivityForResult(Empresa, 0);
        }else{
            CharSequence text = "Rellene todos los campos por favor";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }



    }
}
