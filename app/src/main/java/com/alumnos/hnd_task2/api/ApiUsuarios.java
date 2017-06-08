package com.alumnos.hnd_task2.api;


import android.util.Log;

import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.response.ResponseDeleteUsuario;
import com.alumnos.hnd_task2.response.ResponseNuevoUsuario;
import com.alumnos.hnd_task2.response.ResponseUsuario;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiUsuarios {
    private OkHttpClient client;
    private final String URL = "http://androiddiego.esy.es/APIandroid/";

    public ApiUsuarios() {
        this.client = new OkHttpClient();
    }

    public String getUsuario(String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL + "usuarios/")
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String logout(String token) {
        try {
            RequestBody body = new FormBody.Builder().build();
            Log.d("ApiUsuarios",URL + "logout/");
            Log.d("ApiUsuarios","Token: "+token);
            Request request = new Request.Builder()
                    .url(URL + "logout/")
                    .addHeader("Token", token)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Log.d("ApiUsuarios","********** JSON: "+json);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsuarioBean login(String nombre, String password) {
        try {
            RequestBody body = new FormBody.Builder()
                    .add("email", nombre)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(URL + "login/")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            String json = response.body().string();
            Log.d("UsuarioApi", "************ JSON: " + json);
            ResponseUsuario responseUsuario = ResponseUsuario.fromJson(json);
            if (responseUsuario.getResultado() == 200) {
                return responseUsuario.getUsuario();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int nuevoUsuario(String  nombre, String password){
        try {

            RequestBody body = new FormBody.Builder()
                    .add("password", password)
                    .add("nombre", nombre)
                    .build();

            Request request = new Request.Builder()
                    .url(URL+"usuarios/new/")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            ResponseNuevoUsuario responseNuevoUsuario = ResponseNuevoUsuario.fromJson(response.body().string());

            return responseNuevoUsuario.getResultado();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;

    }
    public int bajaUsuario (String  nombre, String password, String token){

        try {

            RequestBody body = new FormBody.Builder()
                    .add("nombre", nombre)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(URL+"usuarios/delete/")
                    .addHeader("Token", token)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            ResponseDeleteUsuario responseDeleteUsuario = ResponseDeleteUsuario.fromJson(response.body().string());

            return responseDeleteUsuario.getResultado();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public int updateUsuario (String  nombre, String password, String token){

        try {

            RequestBody body = new FormBody.Builder()
                    .add("nombre", nombre)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder()
                    .url(URL+"usuarios/update/")
                    .addHeader("Token", token)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            ResponseDeleteUsuario responseDeleteUsuario = ResponseDeleteUsuario.fromJson(response.body().string());

            return responseDeleteUsuario.getResultado();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;


    }
}