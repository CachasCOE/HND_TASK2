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
import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.fragments.ListObjetosFragment;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;

public class ObjetoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgObjeto;
    private TextView txtDescripcionObj;
    private Button btnComp;
//muestra los datos dle objeto clikeado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto);

        ObjetoBean objetoBean = (ObjetoBean) getIntent().getSerializableExtra(ListObjetosFragment.OBJETO_KEY);

        imgObjeto = (ImageView) findViewById(R.id.imgObjeto);

        btnComp = (Button) findViewById(R.id.btnComp);

        txtDescripcionObj = (TextView) findViewById(R.id.txtDescripcionObj);


        txtDescripcionObj.setText(objetoBean.getDescripcion());

        imgObjeto.setImageDrawable(ContextCompat.getDrawable(this,objetoBean.getFoto()));

        btnComp.setOnClickListener(this);
    }
// comparte los datos
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(txtDescripcionObj));

        startActivity(intent);
    }
}
