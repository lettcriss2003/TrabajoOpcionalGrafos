/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.Tabla;

import controlador.listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;

/**
 *
 * @author DEEPIN
 */
public class ModeloTablaPersona extends AbstractTableModel {

    private ListaEnlazada<Persona> lista = new ListaEnlazada<>();

    public ListaEnlazada<Persona> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Persona> lista) {
        this.lista = lista;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return lista.getSize();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Tipo Identificacion";
            case 2:
                return "Identificacion";
            case 3:
                return "Cliente";
            case 4:
                return "Direccion";
            case 5:
                return "Correo";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = null;
        try {
            p = lista.obtener(rowIndex);
        } catch (Exception e) {
        }
        switch (columnIndex) {
            case 0:
                return (rowIndex + 1);
            case 1:
                return (p != null) ? p.getTipoIdentificacion().toString() : "NO DEFINIDO";
            case 2:
                return (p != null) ? p.getIndetifiacion().toString() : "NO DEFINIDO";
            case 3:
                return (p != null) ? (p.getApellido() + " " + p.getNombre()) : "NO DEFINIDO";
            case 4:
                return (p != null) ? p.getDireccion() : "NO DEFINIDO";
            case 5:
                return (p != null) ? p.getCorreo() : "NO DEFINIDO";
            default:
                return null;
        }
    }

}
