package com.tecnostore.controller;

import com.tecnostore.model.Cliente;
import com.tecnostore.persistencia.ClienteDAO;
import com.tecnostore.service.GestorClientes;
import com.tecnostore.config.ScannerSingleton;

public class ClienteController {

    private final GestorClientes gestorClientes;
    private final ScannerSingleton entrada;

    public ClienteController() {
        this.gestorClientes = new GestorClientes(new ClienteDAO());
        this.entrada = ScannerSingleton.getInstancia();
    }

    public void mostrarMenuCliente() {
        int opcion;
        do {
            System.out.println("\n═══════ GESTIÓN DE CLIENTES ═══════");
            System.out.println("  1. Registrar cliente");
            System.out.println("  0. Volver");
            System.out.println("═══════════════════════════════════");
            opcion = entrada.leerEntero("Seleccione: ");

            switch (opcion) {
                case 1  -> registrar();
                case 0  -> System.out.println("  Volviendo...");
                default -> System.out.println("  Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrar() {
        System.out.println("\n── Registrar nuevo cliente ──");
        String nombre         = entrada.leerTexto("Nombre:         ");
        String identificacion = entrada.leerTexto("Identificación: ");
        String correo         = entrada.leerTexto("Correo:         ");
        String telefono       = entrada.leerTexto("Teléfono:       ");

        try {
            Cliente c = gestorClientes.registrarCliente(nombre, identificacion,
                    correo, telefono);
            System.out.println("\n✔ Cliente registrado con ID: " + c.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("\n✘ Validación: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("\n✘ Error: " + e.getMessage());
        }
    }
}
