package com.tecnostore.model;

public enum CategoriaGama {

    ALTA,
    MEDIA,
    BAJA;

    public static CategoriaGama fromString(String valor) {
        return switch (valor.toLowerCase()) {
            case "alta"  -> ALTA;
            case "media" -> MEDIA;
            case "baja"  -> BAJA;
            default -> throw new IllegalArgumentException("Gama no válida: " + valor);
        };
    }
}
