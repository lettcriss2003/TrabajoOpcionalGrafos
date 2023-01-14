/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.pilas;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.pilas.Excepciones.PilaVaciaException;
import controlador.pilas.Excepciones.TopeException;

/**
 *
 * @author DEEPIN
 */
public class Pila<E> extends ListaEnlazada<E> {

    private Integer tope;

    public Pila(Integer tope) {
        this.tope = tope;
    }

    public boolean estaLleno() {
        return tope == getSize();
    }

    public void push(E dato) throws TopeException {
        if (!estaLleno()) {
            insertarCabecera(dato);
        } else {
            throw new TopeException();
        }
    }

    public E pop() throws PilaVaciaException, ListaNullException, PosicionNoEncontradaException {
        if (!estaVacia()) {
            E dato = eliminar(0);
            return dato;
        } else throw new PilaVaciaException();
        
    }

    public Integer getTope() {
        return tope;
    }

    public void setTope(Integer tope) {
        this.tope = tope;
    }

}
