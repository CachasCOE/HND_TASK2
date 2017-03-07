package com.alumnos.hnd_task2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.activities.PersonajeActivity;
import com.alumnos.hnd_task2.adapters.PersonajesAdapter;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_personajes, container, false);
        listPersonajes = (ListView) view.findViewById(R.id.listPersonajes);
        personajes = Modelo.getPersonajes();
        PersonajesAdapter adapter = new PersonajesAdapter(getActivity(), R.layout.item_personaje, personajes);
        listPersonajes.setAdapter(adapter);
        listPersonajes.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        PersonajeBean personajeBean = personajes.get(posicion);
        Intent intent = new Intent(getActivity(), PersonajeActivity.class);
        intent.putExtra(PERSONAJE_KEY, personajeBean);

        startActivity(intent);
    }
}
