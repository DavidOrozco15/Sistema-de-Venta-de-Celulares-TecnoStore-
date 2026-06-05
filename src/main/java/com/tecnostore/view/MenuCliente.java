package com.tecnostore.view;

import com.tecnostore.controller.ClienteController;
import com.tecnostore.config.ScannerSingleton;

public class MenuCliente {

    private final ClienteController clienteController;
    private final ScannerSingleton entrada;

    public MenuCliente(ClienteController clienteController) {
        this.clienteController = clienteController;
        this.entrada = ScannerSingleton.getInstancia();
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n═══════ GESTIÓN DE CLIENTES ═══════");
            System.out.println("  1. Registrar cliente");
            System.out.println("  0. Volver");
            System.out.println("═══════════════════════════════════");
            opcion = entrada.leerEntero("Seleccione: ");

            switch (opcion) {
                case 1  -> clienteController.registrar();
                case 0  -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opción no válida.");
            }
        } while (opcion != 0);
    }
}
