/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.catalogo.Proveedor;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author axelher67
 */
public class GestorProveedores implements Serializable {

    private ArrayList<Proveedor> proveedores;
    private int siguienteCodigo = 1;

    public GestorProveedores() {
        proveedores = new ArrayList<>();
    }

    public void agregarProveedor(String nombre, String contacto) {
        String codigo = String.format("PRV-%03d", siguienteCodigo);
        proveedores.add(new Proveedor(codigo, nombre, contacto));
        siguienteCodigo++;
    }

    public Proveedor buscarProveedor(String codigo) {
        for (Proveedor p : proveedores) {
            if (p.getCodigoProveedor().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void modificarProveedor(String codigo, String nombre, String contacto) {
        Proveedor p = buscarProveedor(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El proveedor con ese codigo no existe");
        }
        p.setNombre(nombre);
        p.setContacto(contacto);
    }

    public ArrayList<Proveedor> listarProveedores() {
        return new ArrayList<>(proveedores);
    }

    public ArrayList<Proveedor> listarActivos() {
        ArrayList<Proveedor> activos = new ArrayList<>();
        for (Proveedor p : proveedores) {
            if (p.isActivo()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public void darDeBajaProveedor(String codigo) {
        Proveedor p = buscarProveedor(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El proveedor con ese codigo no existe");
        }
        p.darDeBaja();
    }

    public void reactivarProveedor(String codigo) {
        Proveedor p = buscarProveedor(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El proveedor con ese codigo no existe");
        }
        p.reactivar();
    }

}
