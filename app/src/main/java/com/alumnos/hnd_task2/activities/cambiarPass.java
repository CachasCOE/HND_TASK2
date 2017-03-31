package com.alumnos.hnd_task2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alumnos.hnd_task2.R;

public class cambiarPass extends AppCompatActivity implements View.OnClickListener {

    private Button btnConfirmar;
    private EditText editContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        editContra = (EditText) findViewById(R.id.editContra);

        btnConfirmar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
