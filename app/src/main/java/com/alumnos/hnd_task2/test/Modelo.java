package com.alumnos.hnd_task2.test;

import com.alumnos.hnd_task2.R;
import com.alumnos.hnd_task2.beans.PersonajeBean;

import java.util.ArrayList;

/**
 * Created by ALUMNOS on 07/03/2017.
 */

public class Modelo {
    public static ArrayList<PersonajeBean> getPersonajes(){

        ArrayList<PersonajeBean> personajes = new ArrayList<>();

        personajes.add(new PersonajeBean("Personaje bueno", R.drawable.buena));
        personajes.add(new PersonajeBean("Personaje malo", R.drawable.mala));
        personajes.add(new PersonajeBean("Personaje malo pequeño", R.drawable.neutral));

        return personajes;

    }
}
