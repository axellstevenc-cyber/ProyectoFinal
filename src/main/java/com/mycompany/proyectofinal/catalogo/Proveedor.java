/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.catalogo;

import java.io.Serializable;

/**
 *
 * @author axelher67
 */
public class Proveedor implements Serializable {

    private final String codigoProveedor;
    private String nombre;
    private String telefono;
    private boolean estado;

    public Proveedor(String codigoProveedor, String nombre, String telefono) {
        if (codigoProveedor == null || codigoProveedor.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        this.codigoProveedor = codigoProveedor;
        setNombre(nombre);
        setTelefono(telefono);
        this.estado = true;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }
        if (telefono.length() != 8) {
            throw new IllegalArgumentException("El teléfono debe tener 8 dígitos.");
        }
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                throw new IllegalArgumentException("El telefono solo puede contener numeros.");
            }
        }
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return estado;
    }

    public void darDeBaja() {
        this.estado = false;
    }

    public void reactivar() {
        this.estado = true;
    }

    @Override
    public String toString() {
        return "Proveedor [Codigo: " + codigoProveedor + " | Nombre: " + nombre + " | Teléfono: " + telefono + "]";
    }

}
