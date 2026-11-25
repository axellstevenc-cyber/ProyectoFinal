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
    private String contacto;
    private boolean estado;

    public Proveedor(String codigoProveedor, String nombre, String contacto) {
        if (codigoProveedor == null || codigoProveedor.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        this.codigoProveedor = codigoProveedor;
        setNombre(nombre);
        setContacto(contacto);
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        if (contacto == null || contacto.isBlank()) {
            throw new IllegalArgumentException("El teléfono o correo de contacto es obligatorio.");
        }
        contacto = contacto.trim();
        if (contacto.contains("@")) {
            int arroba = contacto.indexOf('@');
            int punto = contacto.lastIndexOf('.');
            if (arroba < 1 || punto < arroba + 2 || punto == contacto.length() - 1 || contacto.contains(" ")) {
                throw new IllegalArgumentException("El correo no tiene un formato válido.");
            }
        } else {
            if (contacto.length() != 8) {
                throw new IllegalArgumentException("El teléfono debe tener 8 dígitos.");
            }
            for (int i = 0; i < contacto.length(); i++) {
                if (!Character.isDigit(contacto.charAt(i))) {
                    throw new IllegalArgumentException("El teléfono solo puede contener números.");
                }
            }
        }
        this.contacto = contacto;
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
        return "Proveedor [Codigo: " + codigoProveedor + " | Nombre: " + nombre + " | Contacto: " + contacto + "]";
    }

}
