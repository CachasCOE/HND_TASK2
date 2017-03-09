package com.alumnos.hnd_task2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.alumnos.hnd_task2.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editNombre, editContraseña;
    private Button btnIniciar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editContraseña = (EditText) findViewById(R.id.editContraseña);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
    }
}
