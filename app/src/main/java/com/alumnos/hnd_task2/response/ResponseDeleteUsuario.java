package com.alumnos.hnd_task2.response;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 08/06/2017.
 */

public class ResponseDeleteUsuario implements Serializable {

    private int resultado;

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public static ResponseDeleteUsuario fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseDeleteUsuario.class);

        }else{

            return new ResponseDeleteUsuario();

        }

    }

}
