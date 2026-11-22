/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.eventos;

import java.io.Serializable;

/**
 *
 * @author axelher67
 */
public abstract class TipoCobro implements Serializable{
    public abstract double calcularMonto(DatosCobro datos,double costoUnitario);
    public abstract String getUnidad();
}
