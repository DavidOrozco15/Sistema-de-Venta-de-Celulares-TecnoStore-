package com.tecnostore.controller;

import com.tecnostore.model.Celular;
import com.tecnostore.model.Gama;
import com.tecnostore.persistencia.ICelularDAO;
import com.tecnostore.persistencia.CelularDAOImpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CelularController {

    // El controlador se comunica directamente con la capa de persistencia (DAO)
    private final ICelularDAO celularDAO;

    // Constructor donde inicializamos la implementación del DAO
    public CelularController() {
        this.celularDAO = new CelularDAOImpl();
    }

    /**
     * Lógica para registrar un nuevo celular.
     */
    public String registrarCelular(String marca, String modelo, double precio, int stock, String sistemaOperativo, Gama gama) {
        try {
            // Creamos el objeto con los datos recibidos de la vista
            Celular nuevo = new Celular(0, marca, modelo, precio, stock, sistemaOperativo, gama);
            Celular registrado = celularDAO.registrar(nuevo);
            return "✅ Celular registrado con éxito. ID asignado: " + registrado.getId_celular();
        } catch (SQLException e) {
            return "❌ Error en la base de datos al registrar: " + e.getMessage();
        }
    }

    /**
     * Retorna la lista completa de celulares para que la vista los imprima.
     */
    public List<Celular> listarCelulares() {
        try {
            return celularDAO.listar();
        } catch (SQLException e) {
            System.out.println("❌ Error al listar celulares: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Busca un celular específico usando su ID.
     */
    public Celular buscarCelular(int id) {
        try {
            return celularDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar el celular con ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Lógica para actualizar un celular existente.
     */
    public String actualizarCelular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, Gama gama) {
        try {
            // 1. Validamos primero si el celular realmente existe en la BD usando nuestro 5° método
            Celular existente = celularDAO.buscarPorId(id);
            if (existente == null) {
                return "❌ Error: No existe ningún celular con el ID: " + id;
            }

            // 2. Si existe, actualizamos sus datos
            existente.setMarca(marca);
            existente.setModelo(modelo);
            existente.setPrecio(precio);
            existente.setStock(stock);
            existente.setSistema_operativo(sistemaOperativo);
            existente.setGama(gama);

            celularDAO.actualizar(existente);
            return "🔄 Celular con ID " + id + " actualizado correctamente.";
        } catch (SQLException e) {
            return "❌ Error en la base de datos al actualizar: " + e.getMessage();
        }
    }

    /**
     * Lógica para eliminar un celular del sistema.
     */
    public String eliminarCelular(int id) {
        try {
            // Validamos si existe antes de intentar borrarlo de las tablas
            Celular existente = celularDAO.buscarPorId(id);
            if (existente == null) {
                return "❌ Error: No se encontró ningún celular con el ID: " + id;
            }

            celularDAO.eliminar(id);
            return "🗑️ Celular con ID " + id + " eliminado permanentemente.";
        } catch (SQLException e) {
            return "❌ Error en la base de datos al eliminar: " + e.getMessage();
        }
    }
}