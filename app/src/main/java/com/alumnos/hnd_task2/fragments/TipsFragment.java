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
import com.alumnos.hnd_task2.activities.TipActivity;
import com.alumnos.hnd_task2.adapters.ObjetosAdapter;
import com.alumnos.hnd_task2.adapters.TipsAdapter;
import com.alumnos.hnd_task2.api.ApiTips;
import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.beans.TipsBean;
import com.alumnos.hnd_task2.test.ModeloObjeto;
import com.alumnos.hnd_task2.test.TestTips;

import java.util.ArrayList;


public class TipsFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView listTips;
    private ArrayList<TipsBean> tips;
    public static final String TIPS_KEY="TIPS_KEY";

    public TipsFragment() {
        // Required empty public constructor
    }

    public static TipsFragment newInstance(){
        return new TipsFragment();
    }
//inicializa el list y setea el adpater
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        listTips = (ListView) view.findViewById(R.id.listTips);
        //tips = TestTips.getTips();
        tips = new ArrayList<>();
        TipsAdapter adapter = new TipsAdapter(getActivity(), R.layout.item_tips, tips);
        listTips.setAdapter(adapter);
        listTips.setOnItemClickListener(this);
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
// abre el detalle de tip seleccionado
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        TipsBean tipsBean = tips.get(posicion);
        Intent intent = new Intent(getActivity(), TipActivity.class);
        intent.putExtra(TIPS_KEY, tipsBean);

        startActivity(intent);
    }

    private class Hilo extends AsyncTask<Void, Void, ArrayList<TipsBean>> {

        @Override
        protected ArrayList<TipsBean> doInBackground(Void... voids) {
            ApiTips apiTips = new ApiTips();
            Preferencias preferencias = new Preferencias(getActivity());
            String token = preferencias.getUsuario().getToken();
            return apiTips.getTips(token);
        }

        @Override
        protected void onPostExecute(ArrayList<TipsBean> tipsBeen) {
            super.onPostExecute(tipsBeen);

            if(tipsBeen != null) {
                tips.clear();
                tips.addAll(tipsBeen);

                TipsAdapter adapter = (TipsAdapter) listTips.getAdapter();
                adapter.notifyDataSetChanged();
            }else{
                Snackbar.make(getView(), "No se ha realizado la peticion", Snackbar.LENGTH_LONG)
                        .show();
            }
        }
    }

}
