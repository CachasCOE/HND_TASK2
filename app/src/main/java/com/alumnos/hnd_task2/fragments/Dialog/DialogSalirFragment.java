package com.alumnos.hnd_task2.fragments.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

/**
 * Created by ALUMNOS on 02/06/2017.
 */

public class DialogSalirFragment extends android.support.v4.app.DialogFragment {

    public DialogSalirFragment() {
        // Required empty public constructor
    }

    public static DialogSalirFragment newInstance() {
        DialogSalirFragment fragment = new DialogSalirFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface MyDialogListener{
        public void onPositiveClick(android.support.v4.app.DialogFragment dialogFragment);
        public void onNegativeClick(android.support.v4.app.DialogFragment dialogFragment);
    }

    private MyDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MyDialogListener) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Alert Dialog");
        builder.setMessage("¿Seguro que quieres salir?");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositiveClick(DialogSalirFragment.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegativeClick(DialogSalirFragment.this);
            }
        });

        return builder.create();
    }

}

