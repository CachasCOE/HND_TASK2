package com.alumnos.hnd_task2.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.UsuarioBean;


public class PerfilFragment extends Fragment implements View.OnClickListener {



    private TextView txtUsuario;
    private ImageView imgPerfil;
    private Button btnCambiar;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(){
        return new PerfilFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        txtUsuario = (TextView) view.findViewById(R.id.txtUsuario);
        imgPerfil = (ImageView) view.findViewById(R.id.imgPerfil);
        btnCambiar = (Button) view.findViewById(R.id.btnCambiar);

        btnCambiar.setOnClickListener(this);

        Preferencias preferencias = new Preferencias(getActivity());
        UsuarioBean usuario = preferencias.getUsuario();
        Log.d("PerfilFragment","---->"+usuario.toJson());
        txtUsuario.setText("Usuario: "+ usuario.getNombre());
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

    @Override
    public void onClick(View view) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
