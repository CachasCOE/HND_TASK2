package com.alumnos.hnd_task2.response;

import com.alumnos.hnd_task2.beans.ObjetoBean;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponseObjeto implements Serializable {

    private int resultado;
    private ObjetoBean objeto;

    public ResponseObjeto() {
    }

    public ObjetoBean getObjeto() {
        return objeto;
    }

    public void setObjeto(ObjetoBean objeto) {
        this.objeto = objeto;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public static ResponseObjeto fromJson (String json){
        if(json!=null && !json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json, ResponseObjeto.class);
        }else{
            return new ResponseObjeto();
        }
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}

