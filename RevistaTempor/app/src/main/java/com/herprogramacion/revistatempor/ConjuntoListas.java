package com.herprogramacion.revistatempor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Creado por Hermosa Programación
 */
public class ConjuntoListas {

    static final Lista LISTAS[] = {
            new Lista("Lista de las 10 mejores herramientas de analiticas", "3022", R.drawable.analytics),
            new Lista("Lista de los mejores servicios de entrega en Cali", "1234", R.drawable.box),
            new Lista("Lista de los navegadores mas rápidos", "2452", R.drawable.browser),
            new Lista("Lista de las aplicaciones mas populares de chat", "4532", R.drawable.bubbles),
            new Lista("Lista de los hábitos que te hacen perder tiempo", "24321", R.drawable.clock),
            new Lista("Lista de las joyas mas deseadas por las mujeres", "9090", R.drawable.diamond),
            new Lista("Lista de los países mas ricos del mundo", "256", R.drawable.graph),
            new Lista("Lista de las computadoras MAC mas caras", "2453", R.drawable.imac),
            new Lista("Lista de los videojuegos mas jugados", "1112", R.drawable.joypad),
            new Lista("Lista de los mejores DJs de Europa", "4512", R.drawable.keyboards),
            new Lista("Lista de los actores mas guapos de Bollywood", "123", R.drawable.man),
            new Lista("Lista de los los lugares turisticos mas hermosos", "4452", R.drawable.map),
            new Lista("Lista de los servicios en la nube con mayor espacio gratis", "2222", R.drawable.open_box),
            new Lista("Lista de los peores regalos que puedas dar", "3849", R.drawable.pack),
            new Lista("Lista de 20 oportunidades de negocio para jovenes", "456", R.drawable.portafolio),
            new Lista("Lista de las 10 herramientas de edición musical mas usadas", "7840", R.drawable.settings),
            new Lista("Lista de los conciertos mas esperados de octubre", "9808", R.drawable.speakers),
            new Lista("Lista de objetivos mas comunes de un Desarrollador Android", "1234", R.drawable.target),
            new Lista("Lista de los 5 peores licores de China", "5556", R.drawable.wine),
            new Lista("Lista de las 10 mujeres mas deseadas del equipo Google", "567", R.drawable.woman)
    };

    /**
     * Este método retorna en una lista aleatoria basada en el
     * atributo LISTAS.
     *
     * El parámetro entero count es el tamaño deseado de la lista
     * resultante
     */
    public static ArrayList<Lista> randomList(int count) {
        Random random = new Random();
        HashSet<Lista> items = new HashSet<Lista>();

        // Restricción de tamaño
        count = Math.min(count, LISTAS.length);

        while (items.size() < count) {
            items.add(LISTAS[random.nextInt(LISTAS.length)]);
        }

        return new ArrayList<Lista>(items);
    }
}
