package com.tecnostore.model;

public class Celular {
    // Atributos privados requeridos
    private int id_celular;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private String sistema_operativo;
    private Gama gama; // Aquí implementamos tu nuevo Enum

    // Constructor vacío (Muy útil para cuando instancias desde la Base de Datos o la Vista)
    public Celular() {
    }

    // Constructor completo (Para cuando registras un celular nuevo)
    public Celular(int id_celular, String marca, String modelo, double precio, int stock, String sistema_operativo, Gama gama) {
        this.id_celular = id_celular;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
    }

    // Getters y Setters


    public int getId_celular() {
        return id_celular;
    }

    public void setId_celular(int id_celular) {
        this.id_celular = id_celular;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    // Método toString opcional (Te servirá muchísimo en la consola/vista para listar los celulares)
    @Override
    public String toString() {
        return "Celular [" +
                "ID=" + id_celular +
                ", Marca='" + marca + '\'' +
                ", Modelo='" + modelo + '\'' +
                ", Precio=$" + precio +
                ", Stock=" + stock +
                ", S.O.='" + sistema_operativo + '\'' +
                ", Gama=" + gama +
                ']';
    }
}