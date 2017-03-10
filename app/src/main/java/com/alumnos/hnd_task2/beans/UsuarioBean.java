package com.alumnos.hnd_task2.beans;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 10/03/2017.
 */

public class UsuarioBean implements Serializable {
    private String nombre, pass;

    public UsuarioBean(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public UsuarioBean() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static UsuarioBean fromJson(String json){
        if(json!=null && !json.isEmpty()) {
            Gson gson = new Gson();
            return gson.fromJson(json, UsuarioBean.class);
        }else{
            return new UsuarioBean();
        }
    }
}
