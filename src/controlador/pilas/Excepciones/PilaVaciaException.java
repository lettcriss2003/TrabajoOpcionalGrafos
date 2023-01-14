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
public class PilaVaciaException extends Exception {

    public PilaVaciaException(String msg) {
        super(msg);
    }

    public PilaVaciaException() {
        super("La pila se encuentra vacia");
    }
    
    
    
    
    
}
