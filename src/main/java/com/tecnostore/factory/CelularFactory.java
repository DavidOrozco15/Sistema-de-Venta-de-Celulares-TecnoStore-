package com.tecnostore.factory;

import com.tecnostore.model.Celular;
import com.tecnostore.model.emuns.Gama;
import com.tecnostore.model.emuns.SistemaOperativo;

public class CelularFactory {

    /**
     * Patrón Factory: Centraliza la creación de objetos Celular.
     */
    public static Celular crearCelular(int id, String marca, String modelo, double precio,
                                       int stock, SistemaOperativo so, Gama gama) {

        // Aquí podrías agregar validaciones o lógica extra de creación en el futuro
        // sin tocar el Gestor ni los controladores.
        return new Celular(id, marca, modelo, precio, stock, so, gama);
    }
}
