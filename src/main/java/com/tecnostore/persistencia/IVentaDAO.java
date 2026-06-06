package com.tecnostore.persistencia;

import com.tecnostore.model.Venta;
import java.sql.SQLException;
import java.util.List;

public interface IVentaDAO {
    Venta registrar(Venta venta) throws SQLException;
    List<Venta> listarTodas() throws SQLException;
<<<<<<< HEAD


    /**
     * Trae las ventas básicas (id, id_cliente, fecha, total).
     * Útil para el reporte de recaudación por mes.
     */
    List<Venta> listar() throws SQLException;

    /**
     * Trae las ventas incluyendo internamente su lista de detalles cargada.
     * Crucial para el reporte analítico del Top 3 de productos más vendidos con Streams.
     */
    List<Venta> listarConDetalles() throws SQLException;
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
}
