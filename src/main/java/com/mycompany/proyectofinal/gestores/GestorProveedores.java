package com.mycompany.proyectofinal.gestores;

import com.mycompany.proyectofinal.catalogo.Proveedor;
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
public class GestorProveedores implements Serializable {

    private static final String ARCHIVO = "Archivos/gestorProveedores.txt";
    private static GestorProveedores instancia;
    private ArrayList<Proveedor> proveedores;
    private int siguienteCodigo = 1;

    private GestorProveedores() {
        proveedores = new ArrayList<>();
        cargarDatos();
    }

    public static GestorProveedores getInstancia() {
        if (instancia == null) {
            instancia = new GestorProveedores();
        }
        return instancia;
    }

    public void agregarProveedor(String nombre, String contacto) {
        String codigo = String.format("PRV-%03d", siguienteCodigo);
        proveedores.add(new Proveedor(codigo, nombre, contacto));
        siguienteCodigo++;
        guardarDatos();
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
        guardarDatos();
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
        guardarDatos();
    }

    public void reactivarProveedor(String codigo) {
        Proveedor p = buscarProveedor(codigo);
        if (p == null) {
            throw new IllegalArgumentException("El proveedor con ese codigo no existe");
        }
        p.reactivar();
        guardarDatos();
    }

    public ArrayList<Proveedor> busquedaPorNombre(String busqueda) {
        busqueda = busqueda.trim().toLowerCase();
        ArrayList<Proveedor> resultados = new ArrayList<>();
        for (Proveedor p : proveedores) {
            if (p.getNombre().toLowerCase().contains(busqueda)) {
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(proveedores);
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
            proveedores = (ArrayList<Proveedor>) ois.readObject();
            siguienteCodigo = ois.readInt();
            System.out.println("Datos cargados");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error cargando los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
