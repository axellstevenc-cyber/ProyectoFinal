/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.eventos;
 
import com.mycompany.proyectofinal.cobros.*;
import com.mycompany.proyectofinal.catalogo.Producto;
import java.io.Serializable;
 
/**
 *
 * @author axelher67
 */
public class ProductoEvento implements Serializable {
 
    private final String codigoProducto;
    private final String descripcion;
    private final double costoUnitario;
    private final String nombreProveedor;
    private final TipoCobro tipoCobro;
    private Integer cantidadManual;
 
    public ProductoEvento(Producto p, Integer cantidadManual) {
        this.codigoProducto = p.getCodigoProducto();
        this.descripcion = p.getDescripcion();
        this.costoUnitario = p.getCostoUnitario();
        this.nombreProveedor = p.getProveedor().getNombre();
        this.tipoCobro = p.getTipoCobro();
        setCantidadManual(cantidadManual);
    }
 
    public Integer getCantidadManual() {
        return cantidadManual;
    }
 
    public void setCantidadManual(Integer cantidadManual) {
        if (cantidadManual != null && cantidadManual < 1) {
            throw new IllegalArgumentException("La cantidad ingresada debe ser mayor a 0");
        }
        this.cantidadManual = cantidadManual;
    }
 
    public String getCodigoProducto() {
        return codigoProducto;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public double getCostoUnitario() {
        return costoUnitario;
    }
 
    public String getNombreProveedor() {
        return nombreProveedor;
    }
 
    public TipoCobro getTipoCobro() {
        return tipoCobro;
    }
 
    public String unidadCobro() {
        return tipoCobro.getUnidad();
    }
 
    public int cantidad(DatosCobro datos) {
        if (cantidadManual != null) {
            return cantidadManual;
        }
        return tipoCobro.obtenerCantidad(datos);
    }
 
    public double calcularSubtotal(DatosCobro datos) {
        return tipoCobro.calcularMonto(cantidad(datos), costoUnitario);
    }
 
}
 