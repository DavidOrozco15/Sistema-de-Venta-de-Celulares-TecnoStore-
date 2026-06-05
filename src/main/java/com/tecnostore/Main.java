package com.tecnostore;

import com.tecnostore.controller.*;
import com.tecnostore.persistencia.*;
import com.tecnostore.service.*;
import com.tecnostore.view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Sistema TecnoStore...");

        // 1. Instanciar persistencia (DAOs)
        ICelularDAO celularDAO = new CelularDAOImpl();
        IClienteDAO clienteDAO = new ClienteDAOimpl();
        IVentaDAO   ventaDAO   = new VentaDAOimpl();

        // 2. Instanciar lógica de negocio (Servicios)
        GestorCelulares gestorCelulares = new GestorCelulares(celularDAO);
        GestorClientes  gestorClientes  = new GestorClientes(clienteDAO);
        GestorVentas    gestorVentas    = new GestorVentas(ventaDAO, celularDAO, clienteDAO);

        // 3. Instanciar Controladores
        CelularController celularController = new CelularController(gestorCelulares);
        ClienteController clienteController = new ClienteController(gestorClientes);
        VentaController   ventaController   = new VentaController(gestorVentas);

        // 4. Inyectar todo al Menú Principal y arrancar
        MenuPrincipal menuSistema = new MenuPrincipal(celularController, clienteController, ventaController);
        menuSistema.iniciarMenu();
    }
}