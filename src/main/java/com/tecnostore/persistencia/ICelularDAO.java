package com.tecnostore.persistencia;

import com.tecnostore.model.Celular;
import java.sql.SQLException;
import java.util.List;

public interface ICelularDAO {

    Celular registrar(Celular celular) throws SQLException;

    void actualizar(Celular celular) throws SQLException;

    void eliminar(int id) throws SQLException;

    List<Celular> listar() throws SQLException;

    /**
     * Busca un celular en la base de datos por su identificador único.
     * @param id El ID del celular a buscar.
     * @return El objeto Celular si se encuentra; null si no existe en la BD.
     * @throws SQLException Si ocurre un error al ejecutar el SELECT.
     */
    Celular buscarPorId(int id) throws SQLException;
}