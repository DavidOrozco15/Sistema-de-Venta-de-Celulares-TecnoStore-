package com.tecnostore.view;

import com.tecnostore.config.ScannerSingleton;
import com.tecnostore.controller.CelularController;
import com.tecnostore.controller.ClienteController;

public class MenuPrincipal {

    private final CelularController celularController;
    private final ClienteController clienteController;
    private final ScannerSingleton teclado;

    // El constructor recibe ambos controladores desde el Main (Inyección de Dependencias)
    public MenuPrincipal(CelularController celularController, ClienteController clienteController) {
        this.celularController = celularController;
        this.clienteController = clienteController;
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
                    // Le pasamos el controlador que vino desde el Main a la vista SOLID
                    MenuCelular menuCelular = new MenuCelular(celularController);
                    menuCelular.iniciarMenu();
                }
                case 2 -> {
                    System.out.println("Abriendo módulo de clientes...");
                    // Aquí harías lo mismo con clientes cuando lo tengan listo:
                    // MenuCliente menuCliente = new MenuCliente(clienteController);
                    // menuCliente.iniciarMenu();
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