package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.eventos.EstadoEvento;
import com.mycompany.proyectofinal.eventos.Evento;
import com.mycompany.proyectofinal.eventos.TipoEvento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;

/**
 *
 * @author axelher67
 */

public class GestorEventos implements Serializable {

    private static final String ARCHIVO = "Archivos/gestorEventos.txt";
    private static GestorEventos instancia;
    private ArrayList<Evento> eventos;
    private int siguienteCodigo = 1;

    private GestorEventos() {
        eventos = new ArrayList<>();
        cargarDatos();
    }

    public static GestorEventos getInstancia() {
        if (instancia == null) {
            instancia = new GestorEventos();
        }
        return instancia;
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
        guardarDatos();
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
        guardarDatos();
    }

    public void eliminarEvento(String codigo) {
        eventos.remove(obtenerEvento(codigo));
        guardarDatos();
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
    
    public double calcularTotalMes(YearMonth mes){
        double totalMes=0;
        for (Evento e : eventos) {
            if (YearMonth.from(e.getFechaHora()).equals(mes) && e.getEstadoEvento()!= EstadoEvento.CANCELADO) {
                totalMes+=e.calcularTotal();
            }
        }
        return totalMes;
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

    public void guardarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(eventos);
            oos.writeInt(siguienteCodigo);
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
            eventos = (ArrayList<Evento>) ois.readObject();
            siguienteCodigo = ois.readInt();
            System.out.println("Datos cargados");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error cargando los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}