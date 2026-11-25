/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.catalogo.*;
import com.mycompany.proyectofinal.cobros.TipoCobro;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author axelher67
 */
public class GestorProductos implements Serializable {

    private ArrayList<Producto> productos;
    private int siguienteCodigo = 1;

    public GestorProductos() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(String descripcion, CategoriaProducto categoria,
            double costoUnitario, Proveedor proveedor, TipoCobro tipoCobro) {
        String codigo = String.format("PRO-%03d", siguienteCodigo);
        productos.add(new Producto(codigo, descripcion, categoria, costoUnitario, proveedor, tipoCobro));
        siguienteCodigo++;
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigoProducto().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void modificarProducto(String codigo, String descripcion, CategoriaProducto categoria,
            double costoUnitario, Proveedor proveedor, TipoCobro tipoCobro) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El producto con ese codigo no existe");
        }
        p.setDescripcion(descripcion);
        p.setCategoria(categoria);
        p.setCostoUnitario(costoUnitario);
        p.setProveedor(proveedor);
        p.setTipoCobro(tipoCobro);
    }

    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public ArrayList<Producto> listarDisponibles() {
        ArrayList<Producto> activos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.estaDisponible()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public ArrayList<Producto> listarPorProveedor(Proveedor pe) {
        ArrayList<Producto> listaPorProveedor = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getProveedor().getCodigoProveedor().equalsIgnoreCase(pe.getCodigoProveedor())) {
                listaPorProveedor.add(p);
            }
        }
        return listaPorProveedor;
    }

    public void darDeBajaProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El producto con ese codigo no existe");
        }
        p.darDeBaja();
    }

    public void reactivarProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El producto con ese codigo no existe");
        }
        p.reactivar();
    }

    public ArrayList<Producto> busquedaPorNombre(String busqueda) {
        busqueda = busqueda.trim().toLowerCase();
        ArrayList<Producto> resultados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getDescripcion().toLowerCase().contains(busqueda)) {
                resultados.add(p);
            }
        }
        return resultados;
    }
}
