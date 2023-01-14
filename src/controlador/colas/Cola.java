/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.colas;

import controlador.colas.excepciones.CimaException;
import controlador.colas.excepciones.ColaVaciaExcepcion;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;

/**
 *
 * @author DEEPIN
 */
public class Cola<E> extends ListaEnlazada<E> {

    private Integer cima;

    public Cola(Integer cima) {
        this.cima = cima;
    }

    public Boolean estaLleno() {
        return cima == getSize();

    }

    public void queue(E dato) throws PosicionNoEncontradaException, CimaException {
        if (!estaLleno()) {
            insertarPosicion(dato, getSize() - 1);
        } else {
            throw new CimaException();
        }

    }

    public E dequue() throws ColaVaciaExcepcion, PosicionNoEncontradaException, ListaNullException {
        if (!estaVacia()) {
            E dato = eliminar(0);
            return dato;
        } else {
            throw new ColaVaciaExcepcion();
        }

    }
}
