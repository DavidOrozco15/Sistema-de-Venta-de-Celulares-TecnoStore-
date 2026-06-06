package com.tecnostore.service;

import com.tecnostore.model.Celular;
import com.tecnostore.model.Venta;
import com.tecnostore.persistencia.ICelularDAO;
import com.tecnostore.persistencia.IVentaDAO;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorReporte {

    private final ICelularDAO celularDAO;
    private final IVentaDAO ventaDAO;

    // SOLID D: Inyección de dependencias
    public GestorReporte(ICelularDAO celularDAO, IVentaDAO ventaDAO) {
        this.celularDAO = celularDAO;
        this.ventaDAO = ventaDAO;
    }

    /**
     * REPORTE 1: Celulares con stock bajo (menor a 5 unidades)
     */
    public List<Celular> obtenerCelularesStockBajo() throws SQLException {
        List<Celular> todos = celularDAO.listar();

        return todos.stream()
                .filter(c -> c.getStock() < 5)
                .collect(Collectors.toList());
    }

    /**
     * REPORTE 2: Top 3 de celulares más vendidos
     * Requiere mapear los detalles de las ventas para consolidar las cantidades por celular.
     */
    public List<Map.Entry<Celular, Integer>> obtenerTop3CelularesMasVendidos() throws SQLException {
        List<Venta> todasLasVentas = ventaDAO.listarConDetalles(); // Método que traiga ventas con sus detalles agregados

        return todasLasVentas.stream()
                .flatMap(v -> v.getItems().stream()) // Aplanamos todos los detalles de todas las ventas
                .collect(Collectors.groupingBy(
                        detalle -> detalle.getCelular(), // Agrupamos por el objeto Celular
                        Collectors.summingInt(detalle -> detalle.getCantidad()) // Sumamos las cantidades vendidas
                ))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Ordenamos de mayor a menor
                .limit(3) // Nos quedamos solo con los 3 primeros
                .collect(Collectors.toList());
    }

    /**
     * REPORTE 3: Ventas totales por mes
     * Agrupa los totales sumados agrupados por el Enum Month de Java Time
     */
    public Map<Month, Double> obtenerVentasTotalesPorMes() throws SQLException {
        List<Venta> todasLasVentas = ventaDAO.listar();

        return todasLasVentas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth(), // Agrupamos por el mes de la fecha de la venta
                        Collectors.summingDouble(v -> v.getTotal()) // Sumamos los totales de las ventas
                ));
    }
}