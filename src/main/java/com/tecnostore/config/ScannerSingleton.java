package com.tecnostore.config;

import java.util.Scanner;

public class ScannerSingleton {


    private static ScannerSingleton instancia;


    private final Scanner scanner;


    private ScannerSingleton() {
        this.scanner = new Scanner(System.in);
    }


    public static ScannerSingleton getInstancia() {
        if (instancia == null) {
            instancia = new ScannerSingleton();
        }
        return instancia;
    }


    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    public void cerrar() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
