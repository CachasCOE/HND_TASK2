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

public class DialogDarBajaFragment extends DialogFragment {


    public DialogDarBajaFragment() {
        // Required empty public constructor
    }

    public static DialogDarBajaFragment newInstance() {
        DialogDarBajaFragment fragment = new DialogDarBajaFragment();

        return fragment;
    }

    public interface MyDialogListener{
        public void onPositiveClickBaja(DialogFragment dialogFragment);
        public void onNegativeClickBaja(DialogFragment dialogFragment);
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

        builder.setTitle("Dar de baja");
        builder.setMessage("Se va a borrar tu perfil\n"+
                "¿Estas seguro?");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onPositiveClickBaja(DialogDarBajaFragment.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onNegativeClickBaja(DialogDarBajaFragment.this);
            }
        });

        return builder.create();
    }
}

