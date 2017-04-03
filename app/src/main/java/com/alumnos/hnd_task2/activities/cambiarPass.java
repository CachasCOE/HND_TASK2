package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alumnos.hnd_task2.MainActivity;
import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.UsuarioBean;

public class cambiarPass extends AppCompatActivity implements View.OnClickListener {

    private Button btnConfirmar;
    private EditText editContra, editNombre;
    private String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

        Preferencias preferencias = new Preferencias(cambiarPass.this);
        UsuarioBean usuarioBean = preferencias.getUsuario();

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        editContra = (EditText) findViewById(R.id.editContra);
        editNombre = (EditText) findViewById(R.id.editNombre);


        editContra.setText(usuarioBean.getPass());
        editNombre.setText(usuarioBean.getNombre());

        foto = usuarioBean.getImgPerfil();

        btnConfirmar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String pass = editContra.getText().toString();
        String nombre = editNombre.getText().toString();

        if(!nombre.isEmpty() && nombre!=null && pass!=null && !pass.isEmpty()){


            //coge el usuario ya guardado y permite cambiarlo
            Preferencias preferencias = new Preferencias(cambiarPass.this);
            UsuarioBean usuarioBean = preferencias.getUsuario();
            usuarioBean.setNombre(nombre);
            usuarioBean.setPass(pass);

            preferencias.setUsuario(usuarioBean);


            //mensaje de guardado
            Toast.makeText(cambiarPass.this,
                    getString(R.string.guardado),
                    Toast.LENGTH_SHORT).show();
            //abre main activity despues del registro
            Intent intent = new Intent(cambiarPass.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
