package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.api.ApiUsuarios;
import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.fragments.Dialog.DialogRegistroFragment;

public class NuevoUsuarioActivity extends AppCompatActivity implements View.OnClickListener, DialogRegistroFragment.MyDialogListener{

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
            Hilo hilo = new Hilo();
            hilo.execute(nombre, pass);
        }else{
            Snackbar.make(view, "Datos son obligatorios", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    private class Hilo extends AsyncTask<String, Void, Integer> {


        @Override
        protected Integer doInBackground(String... args) {

            String nombre = args[0];
            String pass = args[1];

            ApiUsuarios usuarioApi = new ApiUsuarios();

            return usuarioApi.nuevoUsuario(nombre, pass);        }

        @Override
        protected void onPostExecute(Integer resultado) {
            super.onPostExecute(resultado);

            if(resultado == 200){
                DialogRegistroFragment dialogCorrectFragment = DialogRegistroFragment.newInstance();
                dialogCorrectFragment.show(getSupportFragmentManager(), "Dialog");
            }

            else{
                Log.d("NuevoUsuarioActivity", "RESULTADO:"+resultado);
                Snackbar.make(getCurrentFocus(), "El usuario ya existe", Snackbar.LENGTH_LONG)
                        .show();

            }

        }
    }


    @Override
    public void onPositiveClick(DialogFragment dialogFragment) {
        Preferencias preferencias = new Preferencias(NuevoUsuarioActivity.this);
        preferencias.setFlag(true);

        Intent intent = new Intent(NuevoUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onNegativeClick(DialogFragment dialogFragment) {

    }
}
