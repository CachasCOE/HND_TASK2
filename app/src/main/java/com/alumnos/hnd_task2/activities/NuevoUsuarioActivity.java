package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.UsuarioBean;

public class NuevoUsuarioActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editNombre, editPass, editPass2;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editPass = (EditText) findViewById(R.id.editPass);
        editPass2 = (EditText) findViewById(R.id.editPass2);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String nombre = editNombre.getText().toString();
        String pass = editPass.getText().toString();
        String pass2 = editPass2.getText().toString();

        if(nombre!=null&& pass!=null && pass2!=null &&
                !nombre.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()){
            //email formato correcto, pass y pass2 iguales
            UsuarioBean usuarioBean = new UsuarioBean(nombre, pass);


            Preferencias preferencias = new Preferencias(NuevoUsuarioActivity.this);
            preferencias.setUsuario(usuarioBean);

            Toast.makeText(NuevoUsuarioActivity.this,
                    "El usuario se ha guardado correctamente",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(NuevoUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(NuevoUsuarioActivity.this,
                    "Todos los datos son obligatorios",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
