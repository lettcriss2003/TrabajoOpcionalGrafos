/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.pilas.Excepciones;

/**
 *
 * @author DEEPIN
 */
public class TopeException extends Exception{

    public TopeException(String msg) {
        super(msg);
    }

    public TopeException() {
        super("La pila se encuentra llena, pendejo");
    }
    
    
    
    
    
}
