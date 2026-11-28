package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.eventos.EstadoEvento;
import com.mycompany.proyectofinal.eventos.Evento;
import com.mycompany.proyectofinal.eventos.TipoEvento;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;

public class GestorEventos implements Serializable {
    
    private static GestorEventos instancia;
    private ArrayList<Evento> eventos;
    private int siguienteCodigo = 1;

    public GestorEventos() {
        eventos = new ArrayList<>();
    }

    public Evento agregarEvento(String nombreEvento, String nombreCliente, String contactoCliente,
            TipoEvento tipoEvento, LocalDateTime fechaHora, int duracionHoras, String lugar,
            int cantidadInvitados, EstadoEvento estadoEvento) {
        validarFechaNueva(fechaHora);
        String codigo = String.format("EVE-%03d", siguienteCodigo);
        Evento e = new Evento(codigo, nombreEvento, nombreCliente, contactoCliente, tipoEvento,
                fechaHora, duracionHoras, lugar, cantidadInvitados, estadoEvento);
        eventos.add(e);
        siguienteCodigo++;
        return e;
    }

    public Evento buscarEvento(String codigo) {
        for (Evento e : eventos) {
            if (e.getCodigoEvento().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    public void modificarEvento(String codigo, String nombreEvento, String nombreCliente,
            String contactoCliente, TipoEvento tipoEvento, LocalDateTime fechaHora,
            int duracionHoras, String lugar, int cantidadInvitados, EstadoEvento estadoEvento) {
        Evento e = obtenerEvento(codigo);

        if (!fechaHora.toLocalDate().equals(e.getFechaHora().toLocalDate())) {
            validarFechaNueva(fechaHora);
        }

        e.setNombreEvento(nombreEvento);
        e.setNombreCliente(nombreCliente);
        e.setContactoCliente(contactoCliente);
        e.setTipoEvento(tipoEvento);
        e.setFechaHora(fechaHora);
        e.setDuracionHoras(duracionHoras);
        e.setLugar(lugar);
        e.setCantidadInvitados(cantidadInvitados);
        e.setEstadoEvento(estadoEvento);
    }
    
    public static GestorEventos getInstancia() {
        if (instancia == null) {
            instancia = new GestorEventos();
        }
        return instancia;
    }

    public void eliminarEvento(String codigo) {
        eventos.remove(obtenerEvento(codigo));
    }

    public ArrayList<Evento> listarEventos() {
        return new ArrayList<>(eventos);
    }

    public ArrayList<Evento> listarPorMesaYAnio(YearMonth mes) {
        ArrayList<Evento> resultados = new ArrayList<>();
        for (Evento e : eventos) {
            if (YearMonth.from(e.getFechaHora()).equals(mes)) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    public ArrayList<Evento> listarParaReporte(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null || fin.isBefore(inicio)) {
            throw new IllegalArgumentException("El rango de fechas no es valido");
        }
        ArrayList<Evento> resultados = new ArrayList<>();
        for (Evento e : eventos) {
            LocalDate fecha = e.getFechaHora().toLocalDate();
            if (!fecha.isBefore(inicio) && !fecha.isAfter(fin)
                    && e.getEstadoEvento() != EstadoEvento.CANCELADO) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    public ArrayList<Evento> busquedaPorNombre(String busqueda) {
        busqueda = busqueda.trim().toLowerCase();
        ArrayList<Evento> resultados = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getNombreEvento().toLowerCase().contains(busqueda)) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    public ArrayList<Evento> busquedaPorCliente(String busqueda) {
        busqueda = busqueda.trim().toLowerCase();
        ArrayList<Evento> resultados = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getNombreCliente().toLowerCase().contains(busqueda)) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    public ArrayList<Evento> busquedaPorFecha(LocalDate fecha) {
        ArrayList<Evento> resultados = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getFechaHora().toLocalDate().equals(fecha)) {
                resultados.add(e);
            }
        }
        return resultados;
    }

    private Evento obtenerEvento(String codigo) {
        Evento e = buscarEvento(codigo);
        if (e == null) {
            throw new IllegalArgumentException("El evento con ese codigo no existe");
        }
        return e;
    }

    private void validarFechaNueva(LocalDateTime fechaHora) {
        if (fechaHora == null || fechaHora.toLocalDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a hoy");
        }
    }
}
