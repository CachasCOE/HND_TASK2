package com.alumnos.hnd_task2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.ObjetoBean;


import java.util.List;

/**
 * Created by ALUMNOS on 09/03/2017.
 */

public class ObjetosAdapter extends ArrayAdapter<ObjetoBean> {
    private Context context;
    private int resource;
    private List<ObjetoBean> objects;

    public ObjetosAdapter(Context context, int resource, List<ObjetoBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects=objects;
    }

    class ViewHolder{
        TextView txtDescripcion;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ObjetosAdapter.ViewHolder viewHolder;

        if(view==null){
            viewHolder = new ObjetosAdapter.ViewHolder();
            view = LayoutInflater.from(context).inflate(resource,null);
            viewHolder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ObjetosAdapter.ViewHolder) view.getTag();
        }

        ObjetoBean objetoBean = objects.get(position);

        viewHolder.txtDescripcion.setText(objetoBean.getDescripcion());
        return view;
    }
}
