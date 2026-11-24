/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.proyectofinal.eventos;

/**
 *
 * @author axelher67
 */
public enum TipoEvento {
    BODA("Boda"),
    CUMPLEANOS("Cumpleaños"),
    CORPORATIVO("Corporativo"),
    GRADUACION("Graduación"),
    OTRO("Otro");

    private final String nombre;

    TipoEvento(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() { return nombre; }
}
