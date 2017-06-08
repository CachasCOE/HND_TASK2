package com.alumnos.hnd_task2.beans;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.io.StringReader;

/**
 * Created by ALUMNOS on 10/03/2017.
 */

public class UsuarioBean implements Serializable {
    @SerializedName("email")
    private String nombre;
    @SerializedName("password")
    private String pass;
    private String imgPerfil, token;

    public UsuarioBean(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public UsuarioBean() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // getters y setters del usuario
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

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
//cambia los datos de usuario a json
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
