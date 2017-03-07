package com.alumnos.hnd_task2.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;

public class PersonajeActivity extends AppCompatActivity {

    private ImageView imgPersonaje;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        PersonajeBean camisaBean = (PersonajeBean) getIntent().getSerializableExtra(ListPersonajesFragment.PERSONAJE_KEY);

        imgPersonaje = (ImageView) findViewById(R.id.imgPersonaje);


        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);


        txtDescripcion.setText(camisaBean.getDescripcion());

        imgPersonaje.setImageDrawable(ContextCompat.getDrawable(this,camisaBean.getFoto()));
    }
}
