package com.tecnostore;

import com.tecnostore.controller.CelularController;

import com.tecnostore.controller.ClienteController;
import com.tecnostore.persistencia.CelularDAOImpl;
import com.tecnostore.persistencia.ICelularDAO;
import com.tecnostore.service.GestorCelulares;
import com.tecnostore.view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Sistema TecnoStore...");

        // 1. Instancias la persistencia (Capa más baja)
        ICelularDAO celularDAO = new CelularDAOImpl();

// 2. Inyectas la persistencia en el servicio de negocio
        GestorCelulares gestorCelulares = new GestorCelulares(celularDAO);

// 3. Inyectas el servicio en el controlador
        CelularController celularController = new CelularController(gestorCelulares);
        ClienteController clienteController = new ClienteController();

// 4. Inyectas el controlador a la vista principal
        MenuPrincipal menuSistema = new MenuPrincipal(celularController, clienteController);
        menuSistema.iniciarMenu();
    }
}