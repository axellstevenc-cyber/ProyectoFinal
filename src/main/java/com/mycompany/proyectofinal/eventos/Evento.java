/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.eventos;

import com.mycompany.proyectofinal.cobros.DatosCobro;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author axelher67
 */
public class Evento implements Serializable{

    private static final double PORCENTAJE_ISV= 0.15;
    private String codigoEvento;
    private String nombreEvento;
    private String nombreCliente;
    private String telefonoCliente;
    private TipoEvento tipoEvento;
    private LocalDateTime fechaHora;
    private int duracionHoras;
    private String lugar;
    private int cantidadInvitados;
    private EstadoEvento estadoEvento;
    private ArrayList<ProductoEvento> listaProductosDelEvento;

    public Evento(String codigoEvento, String nombreEvento, String nombreCliente, String telefonoCliente, TipoEvento tipoEvento, LocalDateTime fechaHora, int duracionHoras,
            String lugar, int cantidadInvitados, EstadoEvento estadoEvento) {
        if (codigoEvento == null || codigoEvento.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        this.codigoEvento=codigoEvento;
        setNombreEvento(nombreEvento);
        setNombreCliente(nombreCliente);
        setTelefonoCliente(telefonoCliente);
        setTipoEvento(tipoEvento);
        setFechaHora(fechaHora);
        setDuracionHoras(duracionHoras);
        setLugar(lugar);
        setCantidadInvitados(cantidadInvitados);
        setEstadoEvento(estadoEvento);
        listaProductosDelEvento = new ArrayList<>();
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        if (nombreEvento == null || nombreEvento.isBlank()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio.");
        }
        this.nombreEvento = nombreEvento;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        if (telefonoCliente == null || telefonoCliente.isBlank()) {
            throw new IllegalArgumentException("El telefono del cliente es obligatorio.");
        }
        if (telefonoCliente.length() != 8) {
            throw new IllegalArgumentException("El teléfono debe tener 8 dígitos.");
        }
        for (int i = 0; i < telefonoCliente.length(); i++) {
            if (!Character.isDigit(telefonoCliente.charAt(i))) {
                throw new IllegalArgumentException("El telefono solo puede contener numeros.");
            }
        }
        this.telefonoCliente = telefonoCliente;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        if (tipoEvento == null) {
            throw new IllegalArgumentException("El tipo del evento es obligatorio.");
        }
        this.tipoEvento = tipoEvento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        this.fechaHora = fechaHora;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras < 1) {
            throw new IllegalArgumentException("La duración debe ser de al menos 1 hora.");
        }
        this.duracionHoras = duracionHoras;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        if (lugar == null || lugar.isBlank()) {
            throw new IllegalArgumentException("EL lugar del evento es obligatorio.");
        }
        this.lugar = lugar;
    }

    public int getCantidadInvitados() {
        return cantidadInvitados;
    }

    public void setCantidadInvitados(int cantidadInvitados) {
        if (cantidadInvitados < 1) {
            throw new IllegalArgumentException("Los invitados deben ser mayores a 0.");
        }
        this.cantidadInvitados = cantidadInvitados;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        if (estadoEvento == null) {
            throw new IllegalArgumentException("El estado del evento obligatorias");
        }
        this.estadoEvento = estadoEvento;
    }

    public ArrayList<ProductoEvento> getListaProductosDelEvento() {
        return new ArrayList<>(listaProductosDelEvento);
    }

    public ProductoEvento buscarProductoEvento(String codigo) {
        for (ProductoEvento pe : listaProductosDelEvento) {
            if (pe.getCodigoProducto().equalsIgnoreCase(codigo)) {
                return pe;
            }
        }
        return null;
    }

    public void agregarProductoEvento(ProductoEvento pe) {
        if (pe == null) {
            throw new IllegalArgumentException("El producto del evento es obligatorio.");
        }
        if (buscarProductoEvento(pe.getCodigoProducto()) != null) {
            throw new IllegalArgumentException("Este producto ya está en el evento");
        }
        listaProductosDelEvento.add(pe);
    }
    
    public void quitarProductoEvento(String codigo) {
        ProductoEvento pe = buscarProductoEvento(codigo);

        if (pe == null) {
            throw new IllegalArgumentException("Ese producto no está en el evento.");
        }

        listaProductosDelEvento.remove(pe);

    }
   
    public void modificarCantidad(String codigo, Integer cantidad) {
        ProductoEvento pe = buscarProductoEvento(codigo);
        if (pe == null) {
            throw new IllegalArgumentException("Ese producto no está en el evento.");
        }

        pe.setCantidadManual(cantidad);

    }
    
    public boolean tieneServicios() {
        return !listaProductosDelEvento.isEmpty();
    }
    
    public double calcularSubtotalEvento(){
        DatosCobro datosEvento=generarDatosCobro();
        double subtotalEvento=0;
        for (ProductoEvento pe : listaProductosDelEvento) {
            subtotalEvento+=pe.calcularSubtotal(datosEvento);
        }
        return subtotalEvento;
    }
    
    public double calcularISV(){
        return calcularSubtotalEvento()*PORCENTAJE_ISV;
    }
    
    public double calcularTotal(){
        return calcularSubtotalEvento()+calcularISV();
    }
    
    public DatosCobro generarDatosCobro(){
        DatosCobro datosEvento = new DatosCobro(duracionHoras, cantidadInvitados);
        return datosEvento;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return codigoEvento + " - " + nombreEvento+ " | Cliente: " + nombreCliente+ " | Tipo: " + tipoEvento+ " | Fecha: " + fechaHora.format(formato)
                + " | Lugar: " + lugar+ " | Invitados: " + cantidadInvitados+ " | Estado: " + estadoEvento+ " | Servicios: " + listaProductosDelEvento.size();
    }

}
