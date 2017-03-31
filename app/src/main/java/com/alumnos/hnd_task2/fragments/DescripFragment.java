package com.alumnos.hnd_task2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alumnos.hnd_task2.R;


public class DescripFragment extends Fragment implements View.OnClickListener {

    private TextView txtDescrip;
    private Button btnComp;

    private OnFragmentInteractionListener mListener;

    public DescripFragment() {
        // Required empty public constructor
    }

    public static DescripFragment newInstance(){
        return new DescripFragment();
    }
    //inicializa el boton y el texto
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_descrip, container, false);

        btnComp = (Button) view.findViewById(R.id.btnComp);
        txtDescrip = (TextView) view.findViewById(R.id.txtDescrip);
         btnComp.setOnClickListener(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
//compartes la descripcion
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(txtDescrip));

        startActivity(intent);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
