/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.catalogo.*;
import com.mycompany.proyectofinal.cobros.TipoCobro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author axelher67
 */
public class GestorProductos implements Serializable {

    private static final String ARCHIVO = "Archivos/gestorProductos.txt";
    private static GestorProductos instancia;
    private ArrayList<Producto> productos;
    private int siguienteCodigo = 1;

    private GestorProductos() {
        productos = new ArrayList<>();
        cargarDatos();
    }

    public Producto agregarProducto(String descripcion, CategoriaProducto categoria,
            double costoUnitario, Proveedor proveedor, TipoCobro tipoCobro) {
        String codigo = String.format("PRO-%03d", siguienteCodigo);
        Producto p = new Producto(codigo, descripcion, categoria, costoUnitario, proveedor, tipoCobro);
        productos.add(p);
        siguienteCodigo++;
        guardarDatos();
        return p;
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigoProducto().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public static GestorProductos getInstancia() {
        if (instancia == null) {
            instancia = new GestorProductos();
        }
        return instancia;
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
        guardarDatos();
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
        guardarDatos();
    }

    public void reactivarProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El producto con ese codigo no existe");
        }
        p.reactivar();
        guardarDatos();
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

    private void guardarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        try (ObjectOutputStream oosCat = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oosCat.writeObject(productos);
            oosCat.writeInt(siguienteCodigo);
            System.out.println("Datos guardados");
        } catch (IOException e) {
            System.err.println("Error guardando los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        if (!new File(ARCHIVO).exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            productos = (ArrayList<Producto>) ois.readObject();
            siguienteCodigo = ois.readInt();
            System.out.println("Datos cargardos");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error guardando los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
