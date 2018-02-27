package com.example.empresasypracticas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailEmpresaActivityFragment extends Fragment {
    TextView nombreEmpresa,tipoEmpresa,telfEmpresa,correoEmpresa,webEmpresa;
    Button btnLlamar;
    Empresa empresa=null;
    public DetailEmpresaActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail_empresa, container, false);
        nombreEmpresa = view.findViewById(R.id.tvNombre);
        tipoEmpresa=view.findViewById(R.id.tvTipo);
        telfEmpresa=view.findViewById(R.id.tvPersonaDeContacto);
        correoEmpresa=view.findViewById(R.id.tvPersonaDeContacto);
        webEmpresa=view.findViewById(R.id.tvWeb);
        btnLlamar=view.findViewById(R.id.btnLlamar);


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
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+empresa.getTelefono()));
                startActivity(intent);
            }
        });
        return  view;
    }

    private void MostrarEmpresa(Empresa empresa) {
        /*
        String fullname = estudiante.getNom().concat(" "+estudiante.getCognom());
        getActivity().setTitle(fullname);
        String fechas = "Inicio: "+estudiante.getInicio_practicas().concat("   Fin: "+estudiante.getFin_practicas());

        nom.setText(fullname);
        empresa.setText(estudiante.getEmpresa());
         */
        String nomEmpresa=empresa.getNombre();
        getActivity().setTitle(nomEmpresa);
        nombreEmpresa.setText(empresa.getNombre());
        tipoEmpresa.setText(empresa.getTipo());
        telfEmpresa.setText(empresa.getTelefono());
        correoEmpresa.setText(empresa.getCorreoElectronico());
        webEmpresa.setText(empresa.getWebpage());

    }

}
