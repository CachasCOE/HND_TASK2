package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alumnos.hnd_task2.MainActivity;
import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.UsuarioBean;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editNombre, editContraseña;
    private Button btnIniciar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Preferencias preferencias = new Preferencias(LoginActivity.this);
        if (preferencias.isLogin() == false) {

            editNombre = (EditText) findViewById(R.id.editNombre);
            editContraseña = (EditText) findViewById(R.id.editContraseña);

            btnIniciar = (Button) findViewById(R.id.btnIniciar);
            btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

            btnIniciar.setOnClickListener(this);
            btnRegistrar.setOnClickListener(this);
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnIniciar: //login
                //validar datos
                String nombre = editNombre.getText().toString();
                String pass = editContraseña.getText().toString();
                if (nombre != null && pass != null && !nombre.isEmpty() && !pass.isEmpty()) {
                    Preferencias preferencias = new Preferencias(LoginActivity.this);
                    UsuarioBean usuarioBean = preferencias.getUsuario();
                    if (usuarioBean.getNombre() != null) {
                        if (nombre.equals(usuarioBean.getNombre()) && pass.equals(usuarioBean.getPass())) {

                            preferencias.setLogin(true);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Datos obligatorios", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnRegistrar:
                Intent intent = new Intent(LoginActivity.this, NuevoUsuarioActivity.class);
                startActivity(intent);
                break;
        }


    }
}
