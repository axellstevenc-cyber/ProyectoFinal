/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.eventos;

/**
 *
 * @author axelher67
 */
public class PorInvitado extends TipoCobro{

    @Override
    public double calcularMonto(DatosCobro datos, double costoUnitario) {
        return costoUnitario*datos.getInvitados();
    }

    @Override
    public String getUnidad() {
        return "Invitado";
    }
}
