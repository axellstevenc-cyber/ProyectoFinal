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
public class DatosCobro implements Serializable{
    private final int horas;
    private final int invitados;

    public DatosCobro(int horas, int invitados) {
        this.horas = horas;
        this.invitados = invitados;
    }

    public int getHoras() {
        return horas;
    }

    public int getInvitados() {
        return invitados;
    }
    
    
    
}
