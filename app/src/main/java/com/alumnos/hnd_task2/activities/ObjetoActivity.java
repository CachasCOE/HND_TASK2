package com.alumnos.hnd_task2.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.fragments.ListObjetosFragment;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;

public class ObjetoActivity extends AppCompatActivity {

    private ImageView imgObjeto;
    private TextView txtDescripcionObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto);

        ObjetoBean objetoBean = (ObjetoBean) getIntent().getSerializableExtra(ListObjetosFragment.OBJETO_KEY);

        imgObjeto = (ImageView) findViewById(R.id.imgObjeto);


        txtDescripcionObj = (TextView) findViewById(R.id.txtDescripcionObj);


        txtDescripcionObj.setText(objetoBean.getDescripcion());

        imgObjeto.setImageDrawable(ContextCompat.getDrawable(this,objetoBean.getFoto()));
    }
}
