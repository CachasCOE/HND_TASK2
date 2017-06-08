package com.alumnos.hnd_task2.beans;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 07/03/2017.
 */

public class PersonajeBean implements Serializable {
    private String imagen;
    private String descripcion;
    private int id;

    public PersonajeBean() {
    }

    public PersonajeBean(String imagen, String descripcion) {
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
