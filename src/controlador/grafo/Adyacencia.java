/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.grafo;

/**
 *
 * @author lettcriss
 */
public class Adyacencia {
    
    // donde inicio y donde termino 
    // grfaos van a ser 1 ... 2... 3
    // solo pueden tener por cada adyacencia un destino, solo un vertice puede tener varios grafos conectados
    // el peso puede ser cualquier cosa, no solo integer
    private Integer destino;
    private Double peso;  // en la prueba sera con object

    public Adyacencia(Integer destino, Double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        
        return "destino=>>" + destino +", peso =>>"+peso;
        
    }
    
    
    
    
    
}
