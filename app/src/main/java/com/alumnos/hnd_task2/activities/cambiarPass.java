package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Debug;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
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
import com.alumnos.hnd_task2.api.ApiUsuarios;
import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.fragments.Dialog.DialogPerfilFragment;

import static android.R.attr.password;

public class cambiarPass extends AppCompatActivity implements View.OnClickListener, DialogPerfilFragment.MyDialogListener {

    private Button btnConfirmar;
    private EditText editContra, editNombre;
    private String foto;

    private UsuarioBean usuarioBean;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

        Preferencias preferencias = new Preferencias(cambiarPass.this);
        usuarioBean = preferencias.getUsuario();
        token = usuarioBean.getToken();

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


            usuarioBean = new UsuarioBean(nombre, pass);
            usuarioBean.setToken(token);

            DialogPerfilFragment dialogCorrectFragment = DialogPerfilFragment.newInstance();
            dialogCorrectFragment.show(getSupportFragmentManager(), "Dialog");
        }else{
            Snackbar.make(getCurrentFocus(), "Necesitas introducir los datos", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onPositiveClick(DialogFragment dialogFragment) {
        HiloUpdate hiloUpdate = new HiloUpdate();
        hiloUpdate.execute();
        Intent intent = new Intent(cambiarPass.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onNegativeClick(DialogFragment dialogFragment) {

    }

    private class HiloUpdate extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {

            ApiUsuarios apiUsuario = new ApiUsuarios();

            return apiUsuario.updateUsuario(usuarioBean.getNombre(), usuarioBean.getPass(), usuarioBean.getToken());
        }

        @Override
        protected void onPostExecute(Integer resultado) {
            super.onPostExecute(resultado);

            if(resultado == 200){

                Preferencias preferencias = new Preferencias(cambiarPass.this);
                preferencias.setUsuario(usuarioBean);
                preferencias.setFlag(true);

                finish();

            }else{

                Snackbar.make(getCurrentFocus(), "Ha habido un error al hacer la peticion", Snackbar.LENGTH_LONG)
                        .show();

            }
        }
    }
}
