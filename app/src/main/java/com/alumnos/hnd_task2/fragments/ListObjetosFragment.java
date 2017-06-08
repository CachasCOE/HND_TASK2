package com.alumnos.hnd_task2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.alumnos.hnd_task2.activities.ObjetoActivity;
import com.alumnos.hnd_task2.activities.PersonajeActivity;
import com.alumnos.hnd_task2.adapters.ObjetosAdapter;
import com.alumnos.hnd_task2.adapters.PersonajesAdapter;
import com.alumnos.hnd_task2.api.ApiObjetos;
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
        //objetos = ModeloObjeto.getObjetos();
        objetos = new ArrayList<>();
        ObjetosAdapter adapter = new ObjetosAdapter(getActivity(), R.layout.item_objeto, objetos);
        listObjetos.setAdapter(adapter);
        listObjetos.setOnItemClickListener(this);

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
//te lleva al detalle del objeto seleccionado en el listview
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        ObjetoBean objetoBean = objetos.get(posicion);
        Intent intent = new Intent(getActivity(), ObjetoActivity.class);
        intent.putExtra(OBJETO_KEY, objetoBean);

        startActivity(intent);
    }

    private class Hilo extends AsyncTask<Void, Void, ArrayList<ObjetoBean>> {

        @Override
        protected ArrayList<ObjetoBean> doInBackground(Void... voids) {
            ApiObjetos apiObjetos = new ApiObjetos();
            Preferencias preferencias = new Preferencias(getActivity());
            String token = preferencias.getUsuario().getToken();
            return apiObjetos.getObjetos(token);
        }

        @Override
        protected void onPostExecute(ArrayList<ObjetoBean> objetoBeen) {
            super.onPostExecute(objetoBeen);

            if(objetoBeen != null) {
                objetos.clear();
                objetos.addAll(objetoBeen);

                ObjetosAdapter adapter = (ObjetosAdapter) listObjetos.getAdapter();
                adapter.notifyDataSetChanged();
            }else{
                Snackbar.make(getView(), "No se ha realizado la peticion", Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }
}

