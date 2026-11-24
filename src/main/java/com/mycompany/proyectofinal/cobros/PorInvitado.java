/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.cobros;


/**
 *
 * @author axelher67
 */
public class PorInvitado extends TipoCobro{

    @Override
    public double calcularMonto(int cantidad, double costoUnitario) {
        return costoUnitario*cantidad;
    }

    @Override
    public String getUnidad() {
        return "Invitado";
    }
    
    @Override
    public int obtenerCantidad(DatosCobro datos) {
        return datos.getInvitados();
    }
}
