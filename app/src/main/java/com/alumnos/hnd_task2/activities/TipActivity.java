package com.alumnos.hnd_task2.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.beans.TipsBean;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;
import com.alumnos.hnd_task2.fragments.TipsFragment;

import static com.alumnos.hnd_task2.R.id.txtDescripcion;

public class TipActivity extends AppCompatActivity {

    private TextView txtDescripcionTips, txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        TipsBean tipsBean = (TipsBean) getIntent().getSerializableExtra(TipsFragment.TIPS_KEY);

        txtDescripcionTips = (TextView) findViewById(R.id.txtDescripcionTips);


        txtTitulo = (TextView) findViewById(R.id.txtTitulo);


        txtDescripcionTips.setText(tipsBean.getDescripcion());

        txtTitulo.setText(tipsBean.getTitulo());
    }
}
