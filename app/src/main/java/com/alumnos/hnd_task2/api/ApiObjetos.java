package com.alumnos.hnd_task2.api;

import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.alumnos.hnd_task2.response.ResponseObjeto;
import com.alumnos.hnd_task2.response.ResponseObjetos;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ApiObjetos {
    private final String URL = "http://androiddiego.esy.es/APIandroid/objetos/";
    private OkHttpClient client;

    public ApiObjetos() {
        client = new OkHttpClient();
    }

    public ArrayList<ObjetoBean> getObjetos(String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseObjetos responseObjetos =ResponseObjetos.fromJson(json);
            return responseObjetos.getObjetos();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObjetoBean getObjetos(int id, String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL+id)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseObjeto responseObjeto =ResponseObjeto.fromJson(json);
            return responseObjeto.getObjeto();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
