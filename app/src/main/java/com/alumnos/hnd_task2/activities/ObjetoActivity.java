package com.alumnos.hnd_task2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.api.ApiObjetos;
import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.fragments.ListObjetosFragment;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;
import com.squareup.picasso.Picasso;

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

        Picasso.with(this)
                .load(objetoBean.getImagen())
                .resize(400,400)                        // optional
                .into(imgObjeto);

        Hilo hilo = new Hilo();
        hilo.execute(objetoBean.getId());

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

    private class Hilo extends AsyncTask<Integer, Void, ObjetoBean> {

        @Override
        protected ObjetoBean doInBackground(Integer... args) {

            int id = args[0];

            Preferencias preferencias = new Preferencias(ObjetoActivity.this);
            String token = preferencias.getUsuario().getToken();

            ApiObjetos apiObjetos = new ApiObjetos();
            ObjetoBean objetoBean = apiObjetos.getObjetos(id, token);

            return objetoBean;
        }

        @Override
        protected void onPostExecute(ObjetoBean objetoBean) {
            super.onPostExecute(objetoBean);

            if(objetoBean == null){

                Toast.makeText(ObjetoActivity.this, "No se pudo realizar la petición", Toast.LENGTH_SHORT).show();

            }else{

                txtDescripcionObj.setText(objetoBean.getDescripcion());

                Picasso.with(ObjetoActivity.this)
                        .load(objetoBean.getImagen())
                        .fit()
                        .centerInside()
                        .into(imgObjeto);

            }

        }
    }
}
