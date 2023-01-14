/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;
import controlador.listas.ListaEnlazada;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 *
 * @author lettcriss
 */
public class GrafoDirigidoEtiquetado<E> extends GrafoDirigido{ 
// es para la etiqueta la E 
    protected E etiquetas [];
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;
    
    // en un hashMap cuando no se encuentra una etiqueta se 
    public GrafoDirigidoEtiquetado(Integer numVert, Class clazz) {
        super(numVert);
        this.clazz = clazz;
        etiquetas = (E[]) Array.newInstance(clazz, numVert + 1);
        dicVertices = new HashMap(numVert);
    }
    
    public Boolean existeAristaE(E o, E d) throws Exception {
        return this.existeArista(obtenerCodigoE(o), obtenerCodigoE(d));

    }

    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        insertarArista(obtenerCodigoE(o), obtenerCodigoE(d), peso);
    }

    public ListaEnlazada<Adyacencia> adyacentesE(E o) throws Exception{
        return adyacentes(obtenerCodigoE(o));
    }

    /*private Integer obtenerCodigoE(E etiqueta) throws Exception {
        Integer d = dicVertices.get(etiqueta);
        if (d != null) {
            return d;
        } else {
            throw new VerticeOfSizeException("ERROR");
        }
        //return dicVertices.get(etiqueta);
    }*/
    
    private Integer obtenerCodigoE(E etiqueta) throws Exception {
        return dicVertices.get(etiqueta);
    }

    public E obtenerEtiqueta(Integer codigo) {
        return etiquetas[codigo];
    }

    public void etiquetarVertice(Integer codigo, E etiqueta) {
        etiquetas[codigo] = etiqueta;
        dicVertices.put(etiqueta, codigo);
    }
    
    public void insertarAristaE(E o, E d) throws Exception{
        insertarArista(obtenerCodigoE(o), obtenerCodigoE(d));
    }
    
    
    @Override
    public String toString() {
        StringBuffer grafo = new StringBuffer("");
        try {
            for (int i = 1; i <= numVertices(); i++) {
                grafo.append(etiquetas[i] + String.valueOf(i));
                ListaEnlazada<Adyacencia> lista = adyacentes(i);
                for (int j = 0; j < lista.getSize(); j++) {
                    Adyacencia a = lista.obtener(j);
                    if (a.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN))) {
                        grafo.append("----va hacia---- " + obtenerEtiqueta(a.getDestino()) + "---SP");
                    } else {
                        grafo.append("--------Vertice destino " + obtenerEtiqueta(a.getDestino()) + "---Peso" + a.getPeso());

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
