package com.alumnos.hnd_task2.response;

import com.alumnos.hnd_task2.beans.UsuarioBean;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 05/06/2017.
 */

public class ResponseUsuario implements Serializable {
    private int resultado;
    private UsuarioBean usuario;

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public static ResponseUsuario fromJson(String json){
        if(json!=null && !json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json, ResponseUsuario.class);

        }else{
            return new ResponseUsuario();
        }
    }
}
