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
import com.alumnos.hnd_task2.activities.ObjetoActivity;
import com.alumnos.hnd_task2.activities.PersonajeActivity;
import com.alumnos.hnd_task2.adapters.ObjetosAdapter;
import com.alumnos.hnd_task2.adapters.PersonajesAdapter;
import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.test.Modelo;
import com.alumnos.hnd_task2.test.ModeloObjeto;

import java.util.ArrayList;

import static com.alumnos.hnd_task2.R.id.listObjetos;
import static com.alumnos.hnd_task2.R.id.listPersonajes;


public class ListObjetosFragment extends Fragment
        implements AdapterView.OnItemClickListener{

    private ListView listObjetos;
    private ArrayList<ObjetoBean> objetos;
    public static final String OBJETO_KEY="OBJETO_KEY";

    public ListObjetosFragment() {
        // Required empty public constructor
    }

    public static ListObjetosFragment newInstance(){
        return new ListObjetosFragment();
    }
//inicializas los objetos y seteas el adapter de objetos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_objetos, container, false);
        listObjetos = (ListView) view.findViewById(R.id.listObjetos);
        objetos = ModeloObjeto.getObjetos();
        ObjetosAdapter adapter = new ObjetosAdapter(getActivity(), R.layout.item_objeto, objetos);
        listObjetos.setAdapter(adapter);
        listObjetos.setOnItemClickListener(this);
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
//te lleva al detalle del objeto seleccionado en el listview
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        ObjetoBean objetoBean = objetos.get(posicion);
        Intent intent = new Intent(getActivity(), ObjetoActivity.class);
        intent.putExtra(OBJETO_KEY, objetoBean);

        startActivity(intent);
    }
}

