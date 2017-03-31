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
import com.alumnos.hnd_task2.beans.TipsBean;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;
import com.alumnos.hnd_task2.fragments.TipsFragment;

import static com.alumnos.hnd_task2.R.id.txtDescripcion;

public class TipActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtDescripcionTips, txtTitulo;

    private Button btnComp;
    //muestra los datos dle objeto clikeado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        TipsBean tipsBean = (TipsBean) getIntent().getSerializableExtra(TipsFragment.TIPS_KEY);

        txtDescripcionTips = (TextView) findViewById(R.id.txtDescripcionTips);

        btnComp = (Button) findViewById(R.id.btnComp);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);


        txtDescripcionTips.setText(tipsBean.getDescripcion());

        txtTitulo.setText(tipsBean.getTitulo());

        btnComp.setOnClickListener(this);
    }
//compartes los contenidos del detalle
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(txtDescripcionTips));

        startActivity(intent);
    }
}
