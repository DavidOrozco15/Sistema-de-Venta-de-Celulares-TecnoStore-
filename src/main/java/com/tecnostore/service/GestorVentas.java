package com.tecnostore.service;

import com.tecnostore.model.*;
import com.tecnostore.persistencia.ICelularDAO;
import com.tecnostore.persistencia.IClienteDAO;
import com.tecnostore.persistencia.IVentaDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GestorVentas {

    private static final double IVA = 0.19;

    private final IVentaDAO   ventaDAO;
    private final ICelularDAO celularDAO;
    private final IClienteDAO clienteDAO;

    public GestorVentas(IVentaDAO ventaDAO, ICelularDAO celularDAO,
                        IClienteDAO clienteDAO) {
        this.ventaDAO   = ventaDAO;
        this.celularDAO = celularDAO;
        this.clienteDAO = clienteDAO;
    }

    // ── Buscar cliente para asociar a la venta ───────────────────────────
    public Cliente buscarCliente(String identificacion) {
        try {
            return clienteDAO.buscarPorIdentificacion(identificacion);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente: " + e.getMessage());
        }
    }

    // ── Buscar celular para agregar a la venta ───────────────────────────
    public Celular buscarCelular(int id) {
        try {
            return celularDAO.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar celular: " + e.getMessage());
        }
    }

    // ── Registrar la venta completa ──────────────────────────────────────
    public Venta registrarVenta(Cliente cliente, List<ItemVenta> items) {

        if (items == null || items.isEmpty())
            throw new IllegalArgumentException("Debe agregar al menos un celular.");

        // Validar stock de cada ítem
        for (ItemVenta item : items) {
            if (item.getCantidad() <= 0)
                throw new IllegalArgumentException(
                        "La cantidad debe ser mayor a 0.");
            if (item.getCelular().getStock() < item.getCantidad())
                throw new IllegalArgumentException(
                        "Stock insuficiente para: "
                                + item.getCelular().getMarca() + " "
                                + item.getCelular().getModelo()
                                + " (disponible: " + item.getCelular().getStock() + ")");
        }

        // Calcular total con IVA usando Stream API ← requerido por el enunciado
        double subtotalSum = items.stream()
                .mapToDouble(ItemVenta::getSubtotal)
                .sum();
        double total = Math.round(subtotalSum * (1 + IVA) * 100.0) / 100.0;

        try {
            // Crear y guardar la venta
            // Le pasamos "items" directamente al constructor
            Venta venta = new Venta(cliente, items, LocalDate.now(), total);
            venta.setItems(items);
            ventaDAO.registrar(venta);

            return venta;

        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar la venta: " + e.getMessage());
        }
    }

    // ── Listar todas las ventas (para reportes) ──────────────────────────
    public List<Venta> listarVentas() {
        try {
            return ventaDAO.listarTodas();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar ventas: " + e.getMessage());
        }
    }
}