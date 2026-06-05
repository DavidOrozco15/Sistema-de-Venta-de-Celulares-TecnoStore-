package com.tecnostore.controller;

import com.tecnostore.config.ScannerSingleton;
import com.tecnostore.model.*;
import com.tecnostore.persistencia.*;
import com.tecnostore.service.GestorVentas;

import java.util.ArrayList;
import java.util.List;

public class VentaController {

    private final GestorVentas    gestorVentas;
    private final ScannerSingleton entrada;

    public VentaController(GestorVentas gestorVentas) {
        this.gestorVentas = gestorVentas;
        this.entrada = ScannerSingleton.getInstancia();
    }

    public void registrarVenta() {
        System.out.println("\n── Registrar nueva venta ──");

        // 1. Buscar cliente
        String identificacion = entrada.leerTexto("Identificación del cliente: ");
        Cliente cliente = gestorVentas.buscarCliente(identificacion);
        if (cliente == null) {
            System.out.println("✘ No se encontró cliente con esa identificación.");
            return;
        }
        System.out.println("✔ Cliente: " + cliente.getNombre());

        // 2. Agregar celulares
        List<ItemVenta> items = new ArrayList<>();
        System.out.println("\n[Ingrese celulares — ID 0 para terminar]");

        while (true) {
            int idCelular = entrada.leerEntero("ID del celular: ");
            if (idCelular == 0) {
                if (items.isEmpty()) {
                    System.out.println("  Debe agregar al menos un celular.");
                    continue;
                }
                break;
            }

            Celular celular = gestorVentas.buscarCelular(idCelular);
            if (celular == null) {
                System.out.println("  ✘ No existe celular con ID: " + idCelular);
                continue;
            }

            System.out.printf("  ✔ %s %s | Precio: $%.2f | Stock: %d%n",
                    celular.getMarca(), celular.getModelo(),
                    celular.getPrecio(), celular.getStock());

            int cantidad = entrada.leerEntero("  Cantidad: ");
            if (cantidad <= 0) {
                System.out.println("  ✘ La cantidad debe ser mayor a 0.");
                continue;
            }
            if (cantidad > celular.getStock()) {
                System.out.printf("  ✘ Stock insuficiente. Disponible: %d%n",
                        celular.getStock());
                continue;
            }

            double subtotal = celular.getPrecio() * cantidad;
            items.add(new ItemVenta(celular, cantidad, subtotal));
            System.out.printf("  ✔ Subtotal: $%.2f%n", subtotal);
        }

        // 3. Mostrar resumen
        double subtotalTotal = items.stream()
                .mapToDouble(ItemVenta::getSubtotal).sum();
        double iva   = subtotalTotal * 0.19;
        double total = subtotalTotal + iva;

        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  RESUMEN DE VENTA");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (ItemVenta item : items) {
            System.out.printf("  %-28s x%d  $%.2f%n",
                    item.getCelular().getMarca() + " " + item.getCelular().getModelo(),
                    item.getCantidad(), item.getSubtotal());
        }
        System.out.println("──────────────────────────────────────");
        System.out.printf("  Subtotal:       $%.2f%n", subtotalTotal);
        System.out.printf("  IVA (19%%):      $%.2f%n", iva);
        System.out.printf("  TOTAL:          $%.2f%n", total);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String confirm = entrada.leerTexto("¿Confirmar venta? (s/n): ");
        if (!confirm.equalsIgnoreCase("s")) {
            System.out.println("  Venta cancelada.");
            return;
        }

        try {
            Venta venta = gestorVentas.registrarVenta(cliente, items);
            System.out.println("\n✔ Venta registrada con ID: " + venta.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("\n✘ Validación: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("\n✘ Error: " + e.getMessage());
        }
    }
}