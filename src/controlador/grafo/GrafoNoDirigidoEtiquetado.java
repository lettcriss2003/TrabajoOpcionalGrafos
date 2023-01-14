/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

import controlador.grafo.exception.VerticeOfSizeException;
import controlador.listas.ListaEnlazada;

/**
 *
 * @author lettcriss
 */
public class GrafoNoDirigidoEtiquetado <E> extends GrafoDirigidoEtiquetado<E> {
    
    public GrafoNoDirigidoEtiquetado(Integer numVert, Class clazz) {
        super(numVert, clazz);
    }

    @Override
    public void insertarArista(Integer o, Integer d, Double peso) throws Exception {

        if (o.intValue() <= getNumVertices() && d.intValue() <= getNumVertices()) {
            if (!existeArista(o, d)) {
                setNumAristas(getNumAristas() + 1);
                getListaAdyacente()[o].insertar(new Adyacencia(d, peso));
                getListaAdyacente()[d].insertar(new Adyacencia(o, peso));
            }
        } else {
            throw new VerticeOfSizeException();

        }
    }

    @Override
    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        super.insertarAristaE(o, d, peso); //To change body of generated methods, choose Tools | Templates.
    }
    
    public E obtenerEtiquetaNd(Integer codigo) {
        return etiquetas[codigo];
    }

    public void etiquetarVerticeNd(Integer codigo, E etiqueta) {
        etiquetas[codigo] = etiqueta;
        dicVertices.put(etiqueta, codigo);
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
    
    

