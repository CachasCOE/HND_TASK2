package com.alumnos.hnd_task2.beans;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 07/03/2017.
 */

public class PersonajeBean implements Serializable {
    private int foto;
    private String descripcion;


    public PersonajeBean(String descripcion, int foto) {
        this.descripcion = descripcion;
        this.foto = foto;
    }


    // getters y setters de cada personaje
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

}
