package com.tecnostore;

import com.tecnostore.controller.CelularController;
import com.tecnostore.controller.ClienteController;
import com.tecnostore.view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Sistema TecnoStore...");

        // 1. Instanciamos los controladores (Módulos de negocio)
        CelularController celularController = new CelularController();
        ClienteController clienteController = new ClienteController(); // Por si ya lo tienen listo

        // 2. Inyectamos los controladores al Menú Principal aplicando SOLID
        MenuPrincipal menuSistema = new MenuPrincipal(celularController, clienteController);

        // 3. Encendemos la interfaz de la consola
        menuSistema.iniciarMenu();
    }
}