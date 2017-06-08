package com.alumnos.hnd_task2.fragments.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by ALUMNOS on 08/06/2017.
 */

public class DialogPerfilFragment extends DialogFragment {


    public DialogPerfilFragment() {
        // Required empty public constructor
    }

    public static DialogPerfilFragment newInstance() {
        DialogPerfilFragment fragment = new DialogPerfilFragment();

        return fragment;
    }

    public interface MyDialogListener{
        public void onPositiveClick(DialogFragment dialogFragment);
        public void onNegativeClick(DialogFragment dialogFragment);
    }

    private MyDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (MyDialogListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Datos perfil");
        builder.setMessage("Se va a modificar tu perfil.\n" +
                "¿quieres continuar?");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositiveClick(DialogPerfilFragment.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegativeClick(DialogPerfilFragment.this);
            }
        });

        return builder.create();
    }
}
