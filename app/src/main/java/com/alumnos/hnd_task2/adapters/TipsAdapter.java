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
import com.alumnos.hnd_task2.beans.TipsBean;

import java.util.List;

/**
 * Created by ALUMNOS on 10/03/2017.
 */

public class TipsAdapter extends ArrayAdapter<TipsBean> {
    private final List<TipsBean> objects;
    private Context context;
    private int resource;

    public TipsAdapter(Context context, int resource, List<TipsBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects=objects;
    }

    class ViewHolder{
        TextView txtTitulo;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        TipsAdapter.ViewHolder viewHolder;

        if(view==null){
            viewHolder = new TipsAdapter.ViewHolder();
            view = LayoutInflater.from(context).inflate(resource,null);
            viewHolder.txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
            view.setTag(viewHolder);
        }else{
            viewHolder = (TipsAdapter.ViewHolder) view.getTag();
        }

        TipsBean tipsBean = objects.get(position);

        viewHolder.txtTitulo.setText(tipsBean.getDescripcion());
        return view;
    }
}
