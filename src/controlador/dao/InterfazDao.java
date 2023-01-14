/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author DEEPIN
 */
public interface InterfazDao<T> {

    public void guardar(T dato) throws FileNotFoundException, JAXBException;
    public void modificar(T dato, Integer pos) throws FileNotFoundException, JAXBException, PosicionNoEncontradaException;
    public ListaEnlazada<T> listar();
    public T obtener (Integer id);

}
