package com.tecnostore.persistencia;

import com.tecnostore.config.ConexionDB;
import com.tecnostore.model.Cliente;
import com.tecnostore.model.ItemVenta;
import com.tecnostore.model.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOimpl implements IVentaDAO {

    private Connection getConexion() throws SQLException {
        return ConexionDB.getInstancia().getConexion();
    }

    @Override
    public Venta registrar(Venta venta) throws SQLException {
        // Usamos try-with-resources para asegurar que la conexión se cierre/libere al final
        try (Connection con = getConexion()) {
            boolean autoCommitOriginal = con.getAutoCommit();
            con.setAutoCommit(false); // ← Inicio de transacción (Atomicidad)

            try {
                // 1. Insertar la cabecera de la venta
                String sqlVenta = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?)";
                try (PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                    psVenta.setInt(1, venta.getCliente().getId());
                    psVenta.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
                    psVenta.setDouble(3, venta.getTotal());
                    psVenta.executeUpdate();

                    // Obtener el ID autogenerado
                    try (ResultSet keys = psVenta.getGeneratedKeys()) {
                        if (keys.next()) {
                            venta.setId(keys.getInt(1));
                        }
                    }
                }

                // 2. Insertar los detalles de la venta (Usando Batch para mayor rendimiento)
                String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
                try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                    for (ItemVenta item : venta.getItems()) {
                        psDetalle.setInt(1, venta.getId());
                        psDetalle.setInt(2, item.getCelular().getId_celular());
                        psDetalle.setInt(3, item.getCantidad());
                        psDetalle.setDouble(4, item.getSubtotal());
                        psDetalle.addBatch(); // Se encola la instrucción
                    }
                    psDetalle.executeBatch(); // Se ejecutan todas de golpe
                }

                // 3. Descontar el stock de los celulares (También usando Batch)
                String sqlStock = "UPDATE celulares SET stock = stock - ? WHERE id_celular = ?";
                try (PreparedStatement psStock = con.prepareStatement(sqlStock)) {
                    for (ItemVenta item : venta.getItems()) {
                        psStock.setInt(1, item.getCantidad());
                        psStock.setInt(2, item.getCelular().getId_celular());
                        psStock.addBatch(); // Se encola la instrucción
                    }
                    psStock.executeBatch(); // Se ejecutan todas de golpe
                }

                con.commit(); // ← Confirmar toda la transacción si todo salió bien

            } catch (SQLException e) {
                con.rollback(); // ← Revertir todo si ocurrió un error (Integridad de datos)
                throw e;
            } finally {
                con.setAutoCommit(autoCommitOriginal); // ← Restaurar comportamiento por defecto
            }
        }

        return venta;
    }

    @Override
    public List<Venta> listarTodas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT v.id_venta, v.fecha, v.total, " +
                "c.id_cliente, c.nombre, c.identificacion, c.correo, c.telefono " +
                "FROM ventas v " +
                "INNER JOIN clientes c ON v.id_cliente = c.id_cliente " +
                "ORDER BY v.fecha DESC";

        try (Connection con = getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                Venta venta = new Venta(
                        rs.getInt("id_venta"),
                        cliente,
                        new ArrayList<>(), // Se inicia con lista vacía de ítems
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("total")
                );
                ventas.add(venta);
            }
        }
        return ventas;
    }
}