package com.example.empresasypracticas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesLlamada extends AppCompatActivity {
    TextView fechaLlamada,horaLlamada,motivoLlamada,personaDeContacto;
    Llamada llamada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_llamada);

        llamada=(Llamada)getIntent().getExtras().getSerializable("llamada");
        this.setTitle("Historial llamadas a "+llamada.getNombreEmpresa());
        fechaLlamada=(TextView)findViewById(R.id.tvFecha);
        horaLlamada=(TextView)findViewById(R.id.tvHora);
        motivoLlamada=(TextView)findViewById(R.id.tvMotivo);
        personaDeContacto=(TextView)findViewById(R.id.tvPersonaDeContacto);

        fechaLlamada.setText(llamada.getFechaLlamada());
        horaLlamada.setText(llamada.getHoraLLamada());
        motivoLlamada.setText(llamada.getMotivoLlamada());
        personaDeContacto.setText(llamada.getPersonaContactada());


    }
}
