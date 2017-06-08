package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;
import com.squareup.picasso.Picasso;

public class PersonajeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgPersonaje;
    private TextView txtDescripcion;
    private Button btnComp;
    //muestra los datos dle objeto clikeado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        PersonajeBean personajeBean = (PersonajeBean) getIntent().getSerializableExtra(ListPersonajesFragment.PERSONAJE_KEY);

        imgPersonaje = (ImageView) findViewById(R.id.imgPersonaje);
        btnComp = (Button) findViewById(R.id.btnComp);

        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);


        txtDescripcion.setText(personajeBean.getDescripcion());

        Picasso.with(this)
                .load(personajeBean.getImagen())
                .resize(400,400)                        // optional
                .into(imgPersonaje);

        btnComp.setOnClickListener(this);
    }
// comparte los datos del detalle
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(txtDescripcion));

        startActivity(intent);
    }
}
