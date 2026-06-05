package com.tecnostore.view;

import com.tecnostore.config.ScannerSingleton;
import com.tecnostore.controller.VentaController;

public class MenuVenta {

    private final VentaController ventaController;
    private final ScannerSingleton entrada;

    public MenuVenta(VentaController ventaController) {
        this.ventaController = ventaController;
        this.entrada = ScannerSingleton.getInstancia();
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n═══════ GESTIÓN DE VENTAS ═══════");
            System.out.println("  1. Registrar venta");
            System.out.println("  0. Volver");
            System.out.println("══════════════════════════════════");
            opcion = entrada.leerEntero("Seleccione: ");

            switch (opcion) {
                case 1  -> ventaController.registrarVenta();
                case 0  -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opción no válida.");
            }
        } while (opcion != 0);
    }
}