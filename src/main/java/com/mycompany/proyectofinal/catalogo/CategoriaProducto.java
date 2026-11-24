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
    ALIMENTACION("Alimentación"),
    MOBILIARIO("Mobiliario"),
    DECORACION("Decoración"),
    SONIDO_Y_LUCES("Sonido y luces"),
    FOTOGRAFIA("Fotografía"),
    TRANSPORTE("Transporte"),
    SEGURIDAD("Seguridad"),
    OTRA("Otra");
 
    private final String nombre;
 
    CategoriaProducto(String nombre) {
        this.nombre = nombre;
    }
 
    @Override
    public String toString() {
        return nombre;
    }
}
 