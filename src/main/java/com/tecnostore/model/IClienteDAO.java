package com.tecnostore.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IClienteDAO {
    Cliente registrar(Cliente cliente)throws SQLException;
    boolean existeIdentificacion(String identificacion) throws SQLException;
}
