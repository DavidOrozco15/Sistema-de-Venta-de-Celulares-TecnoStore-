package com.tecnostore.view;

import com.tecnostore.config.ScannerSingleton;
import com.tecnostore.controller.CelularController;
import com.tecnostore.controller.ClienteController;
<<<<<<< HEAD
import com.tecnostore.controller.ReporteController;
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
import com.tecnostore.controller.VentaController;

public class MenuPrincipal {

    private final CelularController celularController;
    private final ClienteController clienteController;
    private final VentaController   ventaController;
<<<<<<< HEAD
    private final ReporteController reporteController;
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
    private final ScannerSingleton  teclado;

    public MenuPrincipal(CelularController celularController,
                         ClienteController clienteController,
<<<<<<< HEAD
                         VentaController   ventaController, ReporteController reporteController) {
        this.celularController = celularController;
        this.clienteController = clienteController;
        this.ventaController   = ventaController;
        this.reporteController = reporteController;
=======
                         VentaController   ventaController) {
        this.celularController = celularController;
        this.clienteController = clienteController;
        this.ventaController   = ventaController;
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
        this.teclado = ScannerSingleton.getInstancia();
    }

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║       Bienvenido a TecnoStore        ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Gestión de Clientes              ║");
            System.out.println("║  2. Gestión de Celulares             ║");
            System.out.println("║  3. Gestión de Ventas                ║");
<<<<<<< HEAD
            System.out.println("║  4. Gestión de Reportes              ║");
=======
            System.out.println("║  4. Reportes              [pendiente]║");
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
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
                case 3 -> {
                    MenuVenta menuVenta = new MenuVenta(ventaController);
                    menuVenta.mostrar();
                }
<<<<<<< HEAD
                case 4 ->  {
                    MenuReportes menuReporte = new MenuReportes(reporteController);
                    menuReporte.iniciarMenu();

                }
=======
                case 4 -> System.out.println("  [Módulo pendiente]");
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
                case 0 -> {
                    System.out.println("👋 ¡Gracias por usar TecnoStore!");
                    teclado.cerrar();
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
    }
}