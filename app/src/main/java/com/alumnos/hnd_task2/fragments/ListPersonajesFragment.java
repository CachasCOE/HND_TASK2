package com.alumnos.hnd_task2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.activities.PersonajeActivity;
import com.alumnos.hnd_task2.adapters.PersonajesAdapter;
import com.alumnos.hnd_task2.api.ApiPersonajes;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.test.Modelo;

import java.util.ArrayList;


public class ListPersonajesFragment extends Fragment
        implements AdapterView.OnItemClickListener{

    private ListView listPersonajes;
    private ArrayList<PersonajeBean> personajes;
    public static final String PERSONAJE_KEY="PERSONAJE_KEY";

    public ListPersonajesFragment() {
        // Required empty public constructor
    }

    public static ListPersonajesFragment newInstance(){
        return new ListPersonajesFragment();
    }
//inicializa el list view y setea el adapter
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_personajes, container, false);
        listPersonajes = (ListView) view.findViewById(R.id.listPersonajes);
        //personajes = Modelo.getPersonajes();
        personajes = new ArrayList<>();
        PersonajesAdapter adapter = new PersonajesAdapter(getActivity(), R.layout.item_personaje, personajes);
        listPersonajes.setAdapter(adapter);
        listPersonajes.setOnItemClickListener(this);

        Hilo hilo = new Hilo();
        hilo.execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //te lleva al detalle del personaje seleccionado en el listview
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        PersonajeBean personajeBean = personajes.get(posicion);
        Intent intent = new Intent(getActivity(), PersonajeActivity.class);
        intent.putExtra(PERSONAJE_KEY, personajeBean);

        startActivity(intent);
    }

    private class Hilo extends AsyncTask<Void, Void, ArrayList<PersonajeBean>> {

        @Override
        protected ArrayList<PersonajeBean> doInBackground(Void... voids) {
            ApiPersonajes apiPersonajes = new ApiPersonajes();
            Preferencias preferencias = new Preferencias(getActivity());
            String token = preferencias.getUsuario().getToken();
            return apiPersonajes.getPersonajes(token);
        }

        @Override
        protected void onPostExecute(ArrayList<PersonajeBean> personajeBeen) {
            super.onPostExecute(personajeBeen);

            if(personajeBeen != null) {
                personajes.clear();
                personajes.addAll(personajeBeen);

                PersonajesAdapter adapter = (PersonajesAdapter) listPersonajes.getAdapter();
                adapter.notifyDataSetChanged();
            }else{
                Snackbar.make(getView(), "No se ha realizado la peticion", Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }
}
