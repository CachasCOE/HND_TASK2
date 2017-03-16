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

public class PersonajeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgPersonaje;
    private TextView txtDescripcion;
    private Button btnComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        PersonajeBean camisaBean = (PersonajeBean) getIntent().getSerializableExtra(ListPersonajesFragment.PERSONAJE_KEY);

        imgPersonaje = (ImageView) findViewById(R.id.imgPersonaje);
        btnComp = (Button) findViewById(R.id.btnComp);

        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);


        txtDescripcion.setText(camisaBean.getDescripcion());

        imgPersonaje.setImageDrawable(ContextCompat.getDrawable(this,camisaBean.getFoto()));

        btnComp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(txtDescripcion));

        startActivity(intent);
    }
}
