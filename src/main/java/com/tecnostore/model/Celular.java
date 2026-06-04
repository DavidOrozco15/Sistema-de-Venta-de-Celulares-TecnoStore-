package com.tecnostore.model;

public class Celular {
    // Atributos privados requeridos
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private String sistemaOperativo;
    private Gama gama; // Aquí implementamos tu nuevo Enum

    // Constructor vacío (Muy útil para cuando instancias desde la Base de Datos o la Vista)
    public Celular() {
    }

    // Constructor completo (Para cuando registras un celular nuevo)
    public Celular(int id, String marca, String modelo, double precio, int stock, String sistemaOperativo, Gama gama) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.stock = stock;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
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
                "ID=" + id +
                ", Marca='" + marca + '\'' +
                ", Modelo='" + modelo + '\'' +
                ", Precio=$" + precio +
                ", Stock=" + stock +
                ", S.O.='" + sistemaOperativo + '\'' +
                ", Gama=" + gama +
                ']';
    }
}