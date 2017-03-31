package com.alumnos.hnd_task2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.PersonajeBean;

import java.util.List;

/**
 * Created by ALUMNOS on 07/03/2017.
 */

public class PersonajesAdapter extends ArrayAdapter<PersonajeBean> {
    private Context context;
    private int resource;
    private List<PersonajeBean> objects;

    public PersonajesAdapter(Context context, int resource, List<PersonajeBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects=objects;
    }

    class ViewHolder{
        TextView txtDescripcion;
    }
    //muestra en la lista el nombre del personaje
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if(view==null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(resource,null);
            viewHolder.txtDescripcion = (TextView) view.findViewById(R.id.txtDescripcion);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        PersonajeBean personajeBean = objects.get(position);

        viewHolder.txtDescripcion.setText(personajeBean.getDescripcion());
        return view;
    }
}
