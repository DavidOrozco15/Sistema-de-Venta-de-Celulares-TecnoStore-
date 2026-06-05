package com.tecnostore.view;

import com.tecnostore.config.ScannerSingleton;
import com.tecnostore.controller.CelularController;
import com.tecnostore.controller.ClienteController;
import com.tecnostore.controller.VentaController;

public class MenuPrincipal {

    private final CelularController celularController;
    private final ClienteController clienteController;
    private final VentaController ventaController;
    private final ScannerSingleton teclado;

    // El constructor recibe ambos controladores desde el Main (Inyección de Dependencias)
    public MenuPrincipal(CelularController celularController,
                         ClienteController clienteController,
                         VentaController ventaController) {
        this.celularController = celularController;
        this.clienteController = clienteController;
        this.ventaController = ventaController;
        this.teclado = ScannerSingleton.getInstancia();
    }

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║       Bienvenido a TecnoStore        ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Gestión de Clientes              ║");
            System.out.println("║  2. Gestión de Celulares  [pendiente]║");
            System.out.println("║  3. Gestión de Ventas     [pendiente]║");
            System.out.println("║  4. Reportes              [pendiente]║");
            System.out.println("║  0. Salir                            ║");
            System.out.println("╚══════════════════════════════════════╝");

            opcion = teclado.leerEntero("Seleccione un módulo: ");

            switch (opcion) {
                case 1 -> {
                    MenuCliente menuCliente = new MenuCliente(clienteController);
                    menuCliente.mostrar();
                                    }
                case 2 -> {
                    MenuCelular menuCelular = new MenuCelular(celularController);
                    menuCelular.iniciarMenu();

                }
                case 0 -> {
                    System.out.println("👋 ¡Gracias por usar TecnoStore! Cerrando recursos...");
                    teclado.cerrar(); // Cerramos el scanner limpiamente al salir de todo el programa
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }
}