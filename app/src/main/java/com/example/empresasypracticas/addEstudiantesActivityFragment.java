package com.example.empresasypracticas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class addEstudiantesActivityFragment extends Fragment {
    DatabaseReference empr;
    View view;
    private DatabaseReference mRef;
    private Task<Void> mDatabase;
    Button cancel;
    Button addStudent;
    Button selectImage;
    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;
    ProgressDialog pd;
    //referencia para guardar las imagenes en Firebase
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://empresasypracticas.appspot.com/");
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

       /* final Spinner spinnerEmpresas = view.findViewById(R.id.empresa_spinner);
       // ArrayAdapter<CharSequence> adapterEmp = new ArrayAdapter<CharSequence>();
        ArrayList<CharSequence> adapterEmpr = new ArrayList<CharSequence>();
        ConsultaEmpresas(adapterEmpr);
        ArrayAdapter<CharSequence> adapterEmp = new ArrayAdapter<CharSequence>(this.getContext(),android.R.layout.simple_spinner_item, adapterEmpr);
        adapterEmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresas.setAdapter(adapterEmp);*/

        final Spinner spinnerEmpresas = view.findViewById(R.id.empresa_spinner);
        ArrayAdapter<CharSequence> adapterEmp = ArrayAdapter.createFromResource(this.getContext(),R.array.empresa_list, android.R.layout.simple_spinner_item);
        adapterEmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmpresas.setAdapter(adapterEmp);

        //Curso
        final Spinner spinnerCurso = view.findViewById(R.id.curs_spinner);
        ArrayAdapter<CharSequence> adapterCurso = ArrayAdapter.createFromResource(this.getContext(),R.array.curs_list, android.R.layout.simple_spinner_item);
        adapterCurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurso.setAdapter(adapterCurso);

        //tipopract_spinner
        final Spinner spinnertp = view.findViewById(R.id.tipopract_spinner);
        ArrayAdapter<CharSequence> adaptertp = ArrayAdapter.createFromResource(this.getContext(),R.array.tipo_practicas, android.R.layout.simple_spinner_item);
        adaptertp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertp.setAdapter(adaptertp);

       selectImage = view.findViewById(R.id.btnimage);
       addStudent = view.findViewById(R.id.addstudent);
       cancel = view.findViewById(R.id.btncancelar);
       selectImage.setOnClickListener(listener);
       addStudent.setOnClickListener(listener);
       addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    writeNewEstudent(spinnerEstado, spinnerEmpresas, spinnertp, spinnerCurso);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId())
            {
                case R.id.btncancelar:
                    Intent Studiante = new Intent(view.getContext(), AlumnadoActivity.class);
                    startActivityForResult(Studiante, 0);
                    break;
                case R.id.btnimage:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST);
                    break;
            }
        }
    };

    private void ConsultaEmpresas(final ArrayList<CharSequence> adapter) {

        empr = FirebaseDatabase.getInstance()
                .getReference()
                .child("Empresas");

        empr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("EMPRESA", String.valueOf(child.child("nombre").getValue()));
                    adapter.add(String.valueOf(child.child("nombre").getValue()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeNewEstudent(Spinner spinner, Spinner spinnerEmpresas, Spinner spinnertp, Spinner spinnerCurso) throws ParseException {
        int error = 0;

            EditText name =  view.findViewById(R.id.etname);
            String fname = name.getText().toString();

            EditText lastname = view.findViewById(R.id.etapellido);
            String flastname = lastname.getText().toString();

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
                    if (date.after(datefin)) {
                        CharSequence text = "Fechas de inicio y fin incorrectas";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        error++;
                    }
            EditText email = view.findViewById(R.id.etemail);
            String femail = email.getText().toString();
            EditText telefono = view.findViewById(R.id.ettelefono);
            int ftelefono = Integer.parseInt(telefono.getText().toString());
            EditText etTareas = view.findViewById(R.id.etTareas);
            String tareas = etTareas.getText().toString();
            List<String> atareas = new ArrayList<String>(Arrays.asList(tareas.split(",")));
            EditText etNie = view.findViewById(R.id.etnie);
            String nie = etNie.getText().toString();

            String ftipo = spinnertp.getSelectedItem().toString().trim();
            String fempresa = spinnerEmpresas.getSelectedItem().toString().trim();
            String estado = spinner.getSelectedItem().toString().trim();
            String fcurso =spinnerCurso.getSelectedItem().toString().trim();

        if(filePath != null) {
            String nameImage = nie+".jpg";
            StorageReference childRef = storageRef.child(nameImage);
            UploadTask uploadTask = childRef.putFile(filePath);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Carga exitosa", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Carga fallida -> " + e, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getContext(), "Selecciona una imagen", Toast.LENGTH_SHORT).show();
        }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

