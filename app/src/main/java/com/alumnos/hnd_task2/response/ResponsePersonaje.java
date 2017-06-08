package com.alumnos.hnd_task2.response;

import com.alumnos.hnd_task2.beans.PersonajeBean;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponsePersonaje implements Serializable {

    private int resultado;
    private PersonajeBean personaje;

    public ResponsePersonaje() {
    }

    public PersonajeBean getPersonaje() {
        return personaje;
    }

    public void setPersonaje(PersonajeBean personaje) {
        this.personaje = personaje;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public static ResponsePersonaje fromJson (String json){
        if(json!=null && !json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json, ResponsePersonaje.class);
        }else{
            return new ResponsePersonaje();
        }
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
