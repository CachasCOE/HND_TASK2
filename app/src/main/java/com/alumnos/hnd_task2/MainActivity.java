package com.alumnos.hnd_task2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.alumnos.hnd_task2.activities.LoginActivity;
import com.alumnos.hnd_task2.api.ApiUsuarios;
import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.fragments.AjustesFragment;
import com.alumnos.hnd_task2.fragments.DescripFragment;
import com.alumnos.hnd_task2.fragments.Dialog.DialogDarBajaFragment;
import com.alumnos.hnd_task2.fragments.Dialog.DialogSalirFragment;
import com.alumnos.hnd_task2.fragments.ListObjetosFragment;
import com.alumnos.hnd_task2.fragments.ListPersonajesFragment;
import com.alumnos.hnd_task2.fragments.PerfilFragment;
import com.alumnos.hnd_task2.fragments.TipsFragment;
import com.alumnos.hnd_task2.response.ResponseUsuario;

public class MainActivity extends AppCompatActivity        implements NavigationView.OnNavigationItemSelectedListener,
        DialogSalirFragment.MyDialogListener, DialogDarBajaFragment.MyDialogListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navView;
    private FragmentManager fm;
//inicializa el toolbar y el menulateral
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        navView = (NavigationView) findViewById(R.id.navView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(this);

        fm = getSupportFragmentManager();
        fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
//objetos del menu lateral y a donde te llevan
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_personajes:
                ListPersonajesFragment listpersonajesFragment = ListPersonajesFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,listpersonajesFragment).commit();
                break;
            case R.id.item_ajustes:
                AjustesFragment ajustesFragment = AjustesFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,ajustesFragment).commit();
                break;
            case R.id.item_descrip:
                DescripFragment descripFragment = DescripFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,descripFragment).commit();
                break;
            case R.id.item_tips:
                TipsFragment tipsFragment = TipsFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,tipsFragment).commit();
                break;
            case R.id.item_perfil:
                PerfilFragment perfilFragment = PerfilFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,perfilFragment).commit();
                break;
            case R.id.item_objetos:
                ListObjetosFragment listObjetosFragment = ListObjetosFragment.newInstance();
                fm.beginTransaction().replace(R.id.container,listObjetosFragment).commit();
                break;
            case R.id.item_salir:
                DialogSalirFragment fragmentListener = DialogSalirFragment.newInstance();
                fragmentListener.show(getSupportFragmentManager(),"Dialog Listener");
                break;
        }
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        drawer.closeDrawers();

        return true;
    }


    @Override
    public void onPositiveClick(DialogFragment dialogFragment) {
        Snackbar.make(getCurrentFocus(), "Ok", Snackbar.LENGTH_LONG)
                .show();
        Preferencias preferencias = new Preferencias(this);
        String token = preferencias.getUsuario().getToken();
        HiloLogout hiloLogout = new HiloLogout();
        hiloLogout.execute(token);
        /*Preferencias preferencias = new Preferencias(MainActivity.this);
        preferencias.setLogin(false);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void onNegativeClick(DialogFragment dialogFragment) {
        Snackbar.make(getCurrentFocus(), "cancelar", Snackbar.LENGTH_LONG)
                .show();
        dialogFragment.getDialog().cancel();
    }

    @Override
    public void onPositiveClickBaja(DialogFragment dialogFragment) {
        Preferencias preferencias = new Preferencias(MainActivity.this);
        UsuarioBean usuarioBean = preferencias.getUsuario();

        HiloBaja hiloBaja = new HiloBaja();
        hiloBaja.execute(usuarioBean.getNombre(), usuarioBean.getPass(), usuarioBean.getToken());
    }

    @Override
    public void onNegativeClickBaja(DialogFragment dialogFragment) {

    }

    private class HiloLogout extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args) {
            String token = args[0];
            ApiUsuarios usuarioApi = new ApiUsuarios();
            return usuarioApi.logout(token);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ResponseUsuario usuarioResponse = ResponseUsuario.fromJson(s);
            if(usuarioResponse.getResultado()!=200){
                Snackbar.make(getCurrentFocus(), "No se realizo la peticion", Snackbar.LENGTH_LONG)
                        .show();
            }
            if(s!=null) {
                Preferencias preferencias = new Preferencias(MainActivity.this);
                preferencias.setLogin(false);
                preferencias.removeUsuario();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }


        }
    }

    private class HiloBaja extends AsyncTask<String, Void, Integer> {


        @Override
        protected Integer doInBackground(String... args) {

            String nombre = args[0];
            String password = args[1];
            String token = args[2];

            ApiUsuarios apiUsuario = new ApiUsuarios();

            return apiUsuario.bajaUsuario(nombre,password, token);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if(integer == 200){

                Preferencias preferencias = new Preferencias(MainActivity.this);
                preferencias.setLogin(false);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        }


    }

}