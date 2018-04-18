package com.example.empresasypracticas;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * A placeholder fragment containing a simple view.
 */
public class addEstudiantesActivityFragment extends Fragment {
    View view;
    private DatabaseReference mRef;
    private Task<Void> mDatabase;
    Button cancel;
    Button addStudent;
    public addEstudiantesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_estudiantes, container, false);

        getActivity().setTitle("Afegir Alumne");

        final Spinner spinnerEstado = view.findViewById(R.id.estado_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.practica_estado, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        final Spinner spinnerEmpresas = view.findViewById(R.id.empresa_spinner);
        ArrayAdapter<CharSequence> adapterEmp = ArrayAdapter.createFromResource(this.getContext(),R.array.empresa_list, android.R.layout.simple_spinner_item);
        adapterEmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresas.setAdapter(adapterEmp);



       addStudent = view.findViewById(R.id.addstudent);
       cancel = view.findViewById(R.id.btncancelar);
       cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Studiante = new Intent(view.getContext(), AlumnadoActivity.class);
                startActivityForResult(Studiante, 0);
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    writeNewEstudent(spinnerEstado, spinnerEmpresas);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private void writeNewEstudent(Spinner spinner, Spinner spinnerEmpresas) throws ParseException {
        int error = 0;

            EditText name =  view.findViewById(R.id.etname);
            String fname = name.getText().toString();
            EditText lastname = view.findViewById(R.id.etapellido);
            String flastname = lastname.getText().toString();
            EditText curso = view.findViewById(R.id.etcurso);
            String fcurso = curso.getText().toString();
            String fempresa = spinnerEmpresas.getSelectedItem().toString().trim();
            Context context = getContext();
                            EditText inicio_practicas = view.findViewById(R.id.etinicio);
                            Date date = new Date();
                            String finicio = inicio_practicas.getText().toString();
                            try {
                                date = Fechas(finicio);
                            }catch (DatoErroneo e) {
                                String respuesta=e.getMessage();
                                CharSequence text = respuesta+" dd-MM-yyyy";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                error++;
                            }

                            EditText fin_practicas = view.findViewById(R.id.etfin);
                            Date datefin = new Date();
                            String ffin = fin_practicas.getText().toString();
                            try{
                                datefin = Fechas(ffin);
                            } catch (DatoErroneo e) {
                                String respuesta=e.getMessage();
                                CharSequence text = respuesta+" dd-MM-yyyy";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                error++;
                                }
                    if (date.after(datefin)){
                        CharSequence text = "Fechas de inicio y fin incorrectas";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        error++;
                    }
            EditText tipo_practicas = view.findViewById(R.id.ettipopracticas);
            String ftipo = tipo_practicas.getText().toString();
            EditText email = view.findViewById(R.id.etemail);
            String femail = email.getText().toString();
            EditText telefono = view.findViewById(R.id.ettelefono);
            int ftelefono = Integer.parseInt(telefono.getText().toString());
            EditText etTareas = view.findViewById(R.id.etTareas);
            String tareas = etTareas.getText().toString();
            List<String> atareas = new ArrayList<String>(Arrays.asList(tareas.split(",")));
            EditText etNie = view.findViewById(R.id.etnie);
            String nie = etNie.getText().toString();
            String estado = spinner.getSelectedItem().toString().trim();


            if (error==0){
                Estudiante estudiante = new Estudiante(fname,flastname,nie,ftipo,finicio,ffin,femail,fcurso,ftelefono,fempresa,atareas,estado);

                mRef =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://empresasypracticas.firebaseio.com/");
                String mId = nie;
                mDatabase = mRef.child("Estudiantes").child(mId).setValue(estudiante);
                CharSequence text = "Estudiante añadido correctamente";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent Studiante = new Intent(view.getContext(), AlumnadoActivity.class);
                startActivityForResult(Studiante, 0);
            }
        else{
                CharSequence text = "Vuelve a introducir correctamente los datos";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
    }

    static Date Fechas(String birthday) throws DatoErroneo, ParseException {
        Date date;
        String format = "dd-MM-yyyy";
        SimpleDateFormat ft = new SimpleDateFormat( format);
        date = ft.parse(birthday);
        if (!birthday.equals(ft.format(date))) {
            throw new DatoErroneo("Formato de fecha erroneo");
        }
        else{
            Date nacimiento = ft.parse(birthday);
            return nacimiento;
        }
    }

}

