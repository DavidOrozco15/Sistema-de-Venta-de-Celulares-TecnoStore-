package com.tecnostore.service;

import com.tecnostore.model.Celular;
import com.tecnostore.model.Gama;
import com.tecnostore.persistencia.ICelularDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorCelulares {

    // El servicio depende directamente de la abstracción de la persistencia
    private final ICelularDAO celularDAO;

    /**
     * SOLID D: Inyección de la dependencia de persistencia a través del constructor.
     */
    public GestorCelulares(ICelularDAO celularDAO) {
        this.celularDAO = celularDAO;
    }

    public Celular registrar(String marca, String modelo, double precio, int stock, String sistemaOperativo, Gama gama) throws SQLException {
        Celular nuevo = new Celular(0, marca, modelo, precio, stock, sistemaOperativo, gama);
        return celularDAO.registrar(nuevo);
    }

    public List<Celular> listar() throws SQLException {
        return celularDAO.listar();
    }

    public Celular buscarPorId(int id) throws SQLException {
        return celularDAO.buscarPorId(id);
    }

    public void actualizar(Celular celular) throws SQLException {
        celularDAO.actualizar(celular);
    }

    public void eliminar(int id) throws SQLException {
        celularDAO.eliminar(id);
    }
}