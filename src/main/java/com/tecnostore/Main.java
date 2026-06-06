package com.tecnostore;

import com.tecnostore.config.ConexionDB;
import com.tecnostore.controller.CelularController;
import com.tecnostore.controller.ClienteController;
<<<<<<< HEAD
import com.tecnostore.controller.ReporteController;
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
import com.tecnostore.controller.VentaController;
import com.tecnostore.persistencia.*;
import com.tecnostore.service.GestorCelulares;
import com.tecnostore.service.GestorClientes;
<<<<<<< HEAD
import com.tecnostore.service.GestorReporte;
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
import com.tecnostore.service.GestorVentas;
import com.tecnostore.view.MenuPrincipal;

public class Main {

    public static void main(String[] args) {

        // ── DAOs ──────────────────────────────────────────────────────────
        IClienteDAO clienteDAO = new ClienteDAOimpl();
        ICelularDAO celularDAO = new CelularDAOImpl();
        IVentaDAO   ventaDAO   = new VentaDAOimpl();

<<<<<<< HEAD

=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
        // ── Servicios ─────────────────────────────────────────────────────
        GestorClientes  gestorClientes  = new GestorClientes(clienteDAO);
        GestorCelulares gestorCelulares = new GestorCelulares(celularDAO);
        GestorVentas    gestorVentas    = new GestorVentas(ventaDAO, celularDAO, clienteDAO);
<<<<<<< HEAD
        GestorReporte   gestorReporte = new GestorReporte(celularDAO,ventaDAO);
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129

        // ── Controladores ─────────────────────────────────────────────────
        ClienteController clienteController = new ClienteController(gestorClientes);
        CelularController celularController = new CelularController(gestorCelulares);
        VentaController   ventaController   = new VentaController(gestorVentas);
<<<<<<< HEAD
        ReporteController  reporteController = new ReporteController(gestorReporte);
=======
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129

        // ── Arrancar ──────────────────────────────────────────────────────
        MenuPrincipal menu = new MenuPrincipal(
                celularController,
                clienteController,
<<<<<<< HEAD
                ventaController,
                reporteController
=======
                ventaController
>>>>>>> 5bd7acd3c5dc39ed0d378af5185101e17a3f0129
        );
        menu.iniciarMenu();

        ConexionDB.getInstancia().cerrar();
    }
}