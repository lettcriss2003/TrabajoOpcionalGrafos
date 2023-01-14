/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;

/**
 *
 * @author lettcriss
 */
public abstract class Grafo {

    // se tiene vertices y aristas
    // para unir se necesita la adyacencia 
    // asbtrac es una coleccion de metodos -> se asemeja a una interfaz
    // interfaz es una coleccion de datos
    /**
     * es el numero de vertices del grafo
     *
     * @return
     */
    public abstract Integer numVertices();

    public abstract Integer numAristas();

    public abstract Boolean existeArista(Integer o, Integer d) throws Exception; // me permite ver como estan conectadas si tiene un vertice de origen y otro de destino

    public abstract Double pesoArista(Integer o, Integer d);

    public abstract void insertarArista(Integer o, Integer d) throws Exception;

    public abstract void insertarArista(Integer o, Integer d, Double peso) throws Exception;

    public abstract ListaEnlazada<Adyacencia> adyacentes(Integer v);  // para saber con quienes estan conectadas

    // implementar distract y floyd
   private Boolean pintado(ListaEnlazada<Integer> lista, Integer vertice) throws  PosicionNoEncontradaException, ListaNullException{
        Boolean b = false;
        for (int i = 0; i < lista.getSize(); i++) {
            if (lista.obtener(i).intValue() == vertice.intValue()) {
                b = true;
                break;
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuffer grafo = new StringBuffer("");
        try {
            for (int i = 1; i <= numVertices(); i++) {
                grafo.append("Vertice " + String.valueOf(i));
                ListaEnlazada<Adyacencia> lista = adyacentes(i);
                for (int j = 0; j < lista.getSize(); j++) {
                    Adyacencia a = lista.obtener(j);
                    if (a.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN))) {
                        grafo.append("--------Vertice destino " + a.getDestino() + "---SP");
                    } else {
                        grafo.append("--------Vertice destino " + a.getDestino() + "---Peso" + a.getPeso());

                    }
                }
                grafo.append("\n");
            }
        } catch (Exception e) {
            grafo.append(e.getMessage());
        }

        return grafo.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
