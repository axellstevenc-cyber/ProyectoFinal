/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyectofinal.catalogo;

/**
 *
 * @author axelher67
 */
public enum CategoriaProducto {
    ALIMENTOS("Alimentos"),
    MOBILIARIO("Mobiliario"),
    AUDIO_E_ILUMINACION("Audio e iluminación"),
    FOTOGRAFIA("Fotografía");

    private final String nombre;

    CategoriaProducto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
