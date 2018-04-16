package com.example.empresasypracticas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class DetallesEmpresaBottomNavigation extends AppCompatActivity {
    Empresa empresa;
    TextView nombreEmpresa,tipoEmpresa,telfEmpresa,correoEmpresa,webEmpresa;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {

                case R.id.navigation_editar:
                    transaction.replace(R.id.container, new editarFragment()).commit();
                    return true;
                case R.id.navigation_llamar:
                    transaction.replace(R.id.container, new llamarFragment()).commit();
                    return true;
                case R.id.navigation_verLlamadas:
                    transaction.replace(R.id.container, new verLlamadas()).commit();
                    return true;
                case R.id.navigation_verAlumnos:
                    transaction.replace(R.id.container, new verLlamadas()).commit();
                    return true;
                case R.id.navigation_sendMail:
                    transaction.replace(R.id.container, new SendFormularioEmpresa()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_empresa_bottom_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, new editarFragment()).commit();

        //recogemos el objecto empresa desde la actividad anterior
        Intent i = getIntent();
        if (i != null) {
            empresa = (Empresa) i.getSerializableExtra("empresa");
        }

    }
}

