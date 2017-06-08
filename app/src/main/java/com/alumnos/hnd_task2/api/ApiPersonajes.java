package com.alumnos.hnd_task2.api;

import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.alumnos.hnd_task2.response.ResponsePersonaje;
import com.alumnos.hnd_task2.response.ResponsePersonajes;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ApiPersonajes {
    private final String URL = "http://androiddiego.esy.es/APIandroid/personajes/";
    private OkHttpClient client;

    public ApiPersonajes() {
        client = new OkHttpClient();
    }

    public ArrayList<PersonajeBean> getPersonajes(String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponsePersonajes responsePersonajes =ResponsePersonajes.fromJson(json);
            return responsePersonajes.getPersonajes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PersonajeBean getPersonajes(int id, String token) {
        try {
            Request request = new Request.Builder()
                    .url(URL+id)
                    .addHeader("Token", token)
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponsePersonaje responsePersonaje =ResponsePersonaje.fromJson(json);
            return responsePersonaje.getPersonaje();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
