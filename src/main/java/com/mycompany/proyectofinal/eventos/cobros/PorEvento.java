/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.eventos.cobros;


/**
 *
 * @author axelher67
 */
public class PorEvento extends TipoCobro{

    @Override
    public double calcularMonto(DatosCobro datos, double costoUnitario) {
        return costoUnitario;
    }

    @Override
    public String getUnidad() {
        return "Evento";
    }
    
    @Override
    public int obtenerCantidad(DatosCobro datos) {
        return 1;
    }
}
