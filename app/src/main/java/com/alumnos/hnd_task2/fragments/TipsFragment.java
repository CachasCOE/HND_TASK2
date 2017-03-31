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
import com.alumnos.hnd_task2.activities.TipActivity;
import com.alumnos.hnd_task2.adapters.ObjetosAdapter;
import com.alumnos.hnd_task2.adapters.TipsAdapter;
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
        tips = TestTips.getTips();
        TipsAdapter adapter = new TipsAdapter(getActivity(), R.layout.item_tips, tips);
        listTips.setAdapter(adapter);
        listTips.setOnItemClickListener(this);
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
}
