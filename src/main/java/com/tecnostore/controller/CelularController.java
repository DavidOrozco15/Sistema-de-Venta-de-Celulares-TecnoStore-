package com.tecnostore.controller;

import com.tecnostore.model.Celular;
import com.tecnostore.model.Gama;
import com.tecnostore.service.GestorCelulares;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CelularController {

    // SOLID D: Ahora el controlador depende únicamente de la capa superior de servicio
    private final GestorCelulares gestorCelulares;

    /**
     * SOLID D: Recibe el servicio ya instanciado por inversión de dependencias.
     */
    public CelularController(GestorCelulares gestorCelulares) {
        this.gestorCelulares = gestorCelulares;
    }

    /**
     * Lógica para orquestar el registro de un nuevo celular.
     */
    public String registrarCelular(String marca, String modelo, double precio, int stock, String sistema_operativo, Gama gama) {
        try {
            Celular registrado = gestorCelulares.registrar(marca, modelo, precio, stock, sistema_operativo, gama);
            return "✅ Celular registrado con éxito. ID asignado: " + registrado.getId_celular();
        } catch (SQLException e) {
            return "❌ Error en el servicio al registrar: " + e.getMessage();
        }
    }

    /**
     * Retorna la lista completa de celulares mapeada desde el servicio.
     */
    public List<Celular> listarCelulares() {
        try {
            return gestorCelulares.listar();
        } catch (SQLException e) {
            System.out.println("❌ Error al listar celulares: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Busca un celular específico usando su ID a través del servicio.
     */
    public Celular buscarCelular(int id) {
        try {
            return gestorCelulares.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar el celular con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Lógica para actualizar los datos de un celular existente.
     */
    public String actualizarCelular(int id, String marca, String modelo, double precio, int stock, String sistema_operativo, Gama gama) {
        try {
            Celular existente = gestorCelulares.buscarPorId(id);
            if (existente == null) {
                return "❌ Error: No existe ningún celular con el ID: " + id;
            }

            existente.setMarca(marca);
            existente.setModelo(modelo);
            existente.setPrecio(precio);
            existente.setStock(stock);
            existente.setSistema_operativo(sistema_operativo);
            existente.setGama(gama);

            gestorCelulares.actualizar(existente);
            return "🔄 Celular con ID " + id + " actualizado correctamente.";
        } catch (SQLException e) {
            return "❌ Error en el servicio al actualizar: " + e.getMessage();
        }
    }

    /**
     * Lógica para eliminar un celular del sistema de manera segura.
     */
    public String eliminarCelular(int id) {
        try {
            Celular existente = gestorCelulares.buscarPorId(id);
            if (existente == null) {
                return "❌ Error: No se encontró ningún celular con el ID: " + id;
            }

            gestorCelulares.eliminar(id);
            return "🗑️ Celular con ID " + id + " eliminado permanentemente.";
        } catch (SQLException e) {
            return "❌ Error en el servicio al eliminar: " + e.getMessage();
        }
    }
}