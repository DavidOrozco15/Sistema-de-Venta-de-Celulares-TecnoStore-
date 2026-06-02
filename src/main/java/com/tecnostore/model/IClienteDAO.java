package com.tecnostore.model;
import java.sql.SQLException;

public interface IClienteDAO {
    Cliente registrar(Cliente cliente)throws SQLException;
    boolean existeIdentificacion(String identificacion) throws SQLException;
}
