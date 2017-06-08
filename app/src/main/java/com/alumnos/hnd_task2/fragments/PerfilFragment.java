package com.alumnos.hnd_task2.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;
import static  android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.alumnos.hnd_task2.MainActivity;
import com.alumnos.hnd_task2.Preferencias;
import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.activities.LoginActivity;
import com.alumnos.hnd_task2.activities.PersonajeActivity;
import com.alumnos.hnd_task2.activities.cambiarPass;
import com.alumnos.hnd_task2.api.ApiUsuarios;
import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.fragments.Dialog.DialogDarBajaFragment;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static android.support.v7.appcompat.R.id.checkbox;
import static android.support.v7.appcompat.R.id.image;


public class PerfilFragment extends Fragment implements View.OnClickListener{

    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;
    private String TEMPORAL_PICTURE_NAME = "ejemplo.jpg";

    private TextView txtUsuario;
    private ImageView imgPerfil;
    private Button btnCambiar, btnCambiarPass, btnBaja;
    private LinearLayout LView;

    private String mPath;

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
        btnCambiarPass = (Button) view.findViewById(R.id.btnCambiarPass);
        btnBaja = (Button) view.findViewById(R.id.btnBaja);
        LView = (LinearLayout) view.findViewById(R.id.LView);

        btnCambiar.setOnClickListener(this);
        btnCambiarPass.setOnClickListener(this);
        btnBaja.setOnClickListener(this);

        Preferencias preferencias = new Preferencias(getActivity());
        UsuarioBean usuario = preferencias.getUsuario();
        Log.d("PerfilFragment","---->"+usuario.toJson());
        txtUsuario.setText("Usuario: "+ usuario.getNombre());
        if(usuario.getImgPerfil()!=null && !usuario.getImgPerfil().isEmpty()) {
            imgPerfil.setImageURI(Uri.parse(usuario.getImgPerfil()));
        }

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
        //switch para elegir entre camara y galeria para cambiar la imagen de perfil
        switch (view.getId()) {
            case R.id.btnCambiar:
                final CharSequence[] options = {getString(R.string.foto), getString(R.string.galeria), getString(R.string.cancelar)};
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.opcion));
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (options[i] == getString(R.string.foto)) {
                            openCamera();
                        } else if (options[i] == getString(R.string.galeria)) {
                            Intent intent = new Intent
                                    (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, getString(R.string.appImg)),
                                    SELECT_PICTURE);
                        } else if (options[i] == getString(R.string.cancelar)) {
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.show();
                break;
            case R.id.btnCambiarPass:
                Intent intent = new Intent(getActivity(), cambiarPass.class);
                startActivity(intent);
                break;
            case R.id.btnBaja:
                DialogDarBajaFragment dialogDarBajaFragment = DialogDarBajaFragment.newInstance();
                dialogDarBajaFragment.show(getFragmentManager(), "DialogBaja");
                break;
            }

    }
//abrir la camara
    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }
//cambia la imagen de perfil
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if(resultCode == RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    Uri path = data.getData();
                    Log.d("PerfilFragment","------->"+path.toString());
                    imgPerfil.setImageURI(path);
                    Preferencias preferencias = new Preferencias(getActivity());
                    UsuarioBean usuariobean = preferencias.getUsuario();
                    usuariobean.setImgPerfil(path.toString());
                    preferencias.setUsuario(usuariobean);
                }
        }
    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);

        imgPerfil.setImageBitmap(bitmap);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}

