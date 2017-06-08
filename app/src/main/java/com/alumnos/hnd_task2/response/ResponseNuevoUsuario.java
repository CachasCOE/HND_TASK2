package com.alumnos.hnd_task2.response;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 06/06/2017.
 */

public class ResponseNuevoUsuario implements Serializable {

    private int resultado;

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public static ResponseNuevoUsuario fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseNuevoUsuario.class);

        }else{

            return new ResponseNuevoUsuario();

        }

    }
}