package com.tecnostore.controller;

import com.tecnostore.model.Celular;
import com.tecnostore.service.GestorReporte;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class ReporteController {

    private final GestorReporte reporteService;

    public ReporteController(GestorReporte reporteService) {
        this.reporteService = reporteService;
    }

    public List<Celular> listarStockBajo() {
        try {
            return reporteService.obtenerCelularesStockBajo();
        } catch (SQLException e) {
            System.err.println("❌ Error al procesar stock bajo: " + e.getMessage());
            return List.of();
        }
    }

    public List<Map.Entry<Celular, Integer>> listarTop3MasVendidos() {
        try {
            return reporteService.obtenerTop3CelularesMasVendidos();
        } catch (SQLException e) {
            System.err.println("❌ Error al procesar Top 3: " + e.getMessage());
            return List.of();
        }
    }

    public Map<Month, Double> listarVentasPorMes() {
        try {
            return reporteService.obtenerVentasTotalesPorMes();
        } catch (SQLException e) {
            System.err.println("❌ Error al procesar ventas mensuales: " + e.getMessage());
            return Map.of();
        }
    }
}