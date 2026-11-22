/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.catalogo;

import com.mycompany.proyectofinal.eventos.cobros.TipoCobro;
import java.io.Serializable;

/**
 *
 * @author axelher67
 */
public class Producto implements Serializable {

    private final String codigoProducto;
    private String descripcion;
    private CategoriaProducto categoria;
    private double costoUnitario;
    private Proveedor proveedor;
    private TipoCobro tipoCobro;

    public Producto(String codigoProducto, String descripcion, CategoriaProducto categoria, double costoUnitario, Proveedor proveedor, TipoCobro tipoCobro) {
        if (codigoProducto == null || codigoProducto.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        this.codigoProducto = codigoProducto;
        setDescripcion(descripcion);
        setCategoria(categoria);
        setCostoUnitario(costoUnitario);
        setProveedor(proveedor);
        setTipoCobro(tipoCobro);
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria.");
        }
        this.descripcion = descripcion;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("El producto debe tener una categoria.");
        }
        this.categoria = categoria;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        if (costoUnitario <= 0) {
            throw new IllegalArgumentException("El costo unitario del producto debe ser mayor a 0.");
        }
        this.costoUnitario = costoUnitario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El producto debe tener un proveedor.");
        }
        this.proveedor = proveedor;
    }

    public TipoCobro getTipoCobro() {
        return tipoCobro;
    }

    public void setTipoCobro(TipoCobro tipoCobro) {
        if (tipoCobro == null) {
            throw new IllegalArgumentException("El producto debe tener una forma de cobro.");
        }
        this.tipoCobro = tipoCobro;
    }
    
    @Override
    public String toString() {
        return "Producto [Codigo: " + codigoProducto+ " | Descripcion: " + descripcion
                + " | Categoria: " + categoria+ " | Costo: " + costoUnitario
                + " | Proveedor: " + proveedor.getNombre() + " | Cobro: " + tipoCobro.getUnidad()+ "]";
    }

}
