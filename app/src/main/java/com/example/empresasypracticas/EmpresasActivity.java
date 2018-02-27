package com.example.empresasypracticas;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class EmpresasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class EmpresaGlobal extends Application {
        private Empresa laEmpresa;

        public Empresa getLaEmpresa() {
            return laEmpresa;
        }

        public void setLaEmpresa(Empresa laEmpresa) {
            this.laEmpresa = laEmpresa;
        }
    }
}
