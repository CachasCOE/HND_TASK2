package com.alumnos.hnd_task2.beans;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 10/03/2017.
 */

public class TipsBean implements Serializable {
    private String descripcion, titulo;


    public TipsBean(String descripcion, String titulo) {
        this.descripcion = descripcion;
        this.titulo = titulo;
    }


    // getters y setters de cada tip
    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}