/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.listas;

import controlador.listas.excepciones.AtributoException;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Set;
import vistas.Utilidades.Utilidades;

/**
 *
 * @author DEEPIN
 */

public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size;
    public static Integer DESCENDENTE = 1;
    public static Integer ASCENDENTE = 2;

    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    public Boolean estaVacia() {
        return cabecera == null;
    }

//    private Integer tamanio() {
//        Integer tamanio = 0;
//        NodoLista<E> aux` = cabecera;
//        while (aux != null) {
//            tamanio++;
//            aux = aux.getSiguiente();
//        }
//        return tamanio;
//    }
    public void insertar(E dato) {
        NodoLista<E> nodo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            this.cabecera = nodo;

        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
        size++;

    }

    public void insertarCabecera(E dato) {

        if (estaVacia()) {
            insertar(dato);
        } else {
            NodoLista<E> nodo = new NodoLista<>(dato, null);
            nodo.setSiguiente(cabecera);
            cabecera = nodo;
            size++;

        }
    }

    public void insertarPosicion(E dato, Integer pos) throws PosicionNoEncontradaException {
        if (estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < size) {
            if (pos == (size - 1)) {
                insertar(dato);
            } else if (pos == 0) {
                insertarCabecera(dato);
            } else {
                NodoLista<E> nodo = new NodoLista(dato, null);
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < (pos - 1); i++) {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nodo);
                nodo.setSiguiente(siguiente);
                size++;
            }

        } else {
            throw new PosicionNoEncontradaException();
        }
    }

    public void imprimir() {
        System.out.println("----------------------LISTA ENLAZADA----------------------");
        NodoLista<E> aux = cabecera;
        while (aux != null) {
            System.out.print(aux.getDato().toString() + "\t");
            aux = aux.getSiguiente();

        }
        System.out.println("\n----------------------------------------------------------");
    }

    public E obtener(Integer pos) throws ListaNullException, PosicionNoEncontradaException {

        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos < size) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }
            } else {
                throw new PosicionNoEncontradaException();
            }
            return dato;
        } else {
            throw new ListaNullException();
        }
        //return dato;

    }

    public void modificar(E dato, Integer pos) throws PosicionNoEncontradaException {
        if (!estaVacia()) {
            insertar(dato);

        } else if (pos >= 0 && pos < size) {
            if (pos == 0) {
                cabecera.setDato(dato);
            } else {
                NodoLista<E> aux = cabecera;
                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }
                aux.setDato(dato);
            }

        } else {
            throw new PosicionNoEncontradaException();
        }

    }

    public E eliminar(Integer pos) throws ListaNullException, PosicionNoEncontradaException {

        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos < size) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
            } else {
                throw new PosicionNoEncontradaException();
            }
            return dato;
        } else {
            throw new ListaNullException();
        }
    }
    
    public E[] toArray() { // -> arreglo para cambiar de lista enlazada a arreglo para realizar el metodo burbuja (compar to ) -> pra los to string 
        // to lista es para ingresar un arreglo y qe devuelva una lista 
        //Class<E> clazz = null;
        E[] matriz = null;

        // esta vacia 
        if (this.size > 0) {
            matriz = (E[]) Array.newInstance(cabecera.getDato().getClass(), this.size); // -> casiting 
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getDato();
                aux = aux.getSiguiente();
            }
        }

        return matriz;
    }
    
    // de lista enlazada a arreglo 
    public ListaEnlazada<E> toList(E[] matriz){
        this.vaciar();
        for(int i = 0; i < matriz.length; i++){
             this.insertar(matriz[i]);
        }
        return this;
    
    
    }
    
    public void vaciar(){
       this.cabecera = null;
    }
    
    // metodo burbuja 
    public ListaEnlazada<E> burbuja(String atributo, Integer tipoOrdenacion) throws Exception {
        // ordenar por datos primitivos, la clazz sera algun atributo que yo quisiera
        Class<E> clazz = null;
        E[] matriz = toArray();
        if (size > 0) {
            // para el tipo de dato que querramos el objeto
            clazz = (Class<E>)cabecera.getDato().getClass();
            Boolean isObject = Utilidades.isObject(clazz);  // aqui para ordenar con el string 
            
                for (int i = matriz.length; i > 1; i--) {
                    for (int j = 0; j < i - 1; j++) {
                        if(isObject){
                            intercambioObjeto(j, matriz, clazz, tipoOrdenacion, atributo);
                        
                        }else{
                            intercambioDato(j, matriz, clazz, tipoOrdenacion);
                        }
                    }
                }
            }
        if (matriz != null)
            toList(matriz);
        
        return this;
    }
    
    //metodo shell
    //metodo quicksort
    
    
   
    // metodo para hacer todos 
    //metodo para intercambio pero solo de dato primitivo o simples 
    
     
    private void intercambioDato(int j, E[] matriz, Class clazz, Integer tipoOrdenacion) {
        E auxJ = matriz[j];
        E auxJ1 = matriz[j + 1];
        intercambio(j, matriz, auxJ, auxJ1, tipoOrdenacion);
       /* if (Utilidades.isNumber(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por number 
                if (((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue()) {
                    matriz[j] = auxJ1;
                    matriz[j + 1] = auxJ;
                }
            } else {
                if (((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue()) {
                    matriz[j] = auxJ1;
                    matriz[j + 1] = auxJ;
                }
            }
        }
        if (Utilidades.isString(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por string 
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) < 0) {
                    matriz[j] = auxJ1;
                    matriz[j + 1] = auxJ;
                }
            } else {
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) > 0) {
                    matriz[j] = auxJ1;
                    matriz[j + 1] = auxJ;
                }

            }
        }*/
    }
    
    
    public void intercambio(int j, E[] matriz, Object auxJ, Object auxJ1, Integer tipoOrdenacion) {
        Class clazz = auxJ.getClass();
        E a = matriz[j];
        E b = matriz[j + 1];
        
        if (Utilidades.isNumber(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por number 
                if (((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue()) {
                    matriz[j] = b;
                    matriz[j + 1] = a;
                }
            } else {
                if (((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue()) {
                    matriz[j] = b;
                    matriz[j + 1] = a;
                }
            }
        }
        if (Utilidades.isString(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por string 
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) < 0) {
                    matriz[j] = b;
                    matriz[j + 1] = a;
                }
            } else {
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) > 0) {
                    matriz[j] = b;
                    matriz[j + 1] = a;
                }

            }
        }

    }
    
    private void intercambioObjeto(int j, E[] matriz, Class clazz, Integer tipoOrdenacion, String atributo)throws Exception{
       E auxJ = matriz[j];
       E auxJ1 = matriz[j+1];
       Field field = Utilidades.obtenerAtributo(clazz, atributo);
       if(field == null)
           throw new AtributoException();
       field.setAccessible(true);
       Object a = field.get(auxJ);
       Object b = field.get(auxJ1);
        //System.out.println(a + " " + b);
        intercambio(j, matriz, a, a, tipoOrdenacion);
       
    }
    
    //ordenacion directa -> similar al burbuja 
    public ListaEnlazada<E> order_seleccion(String atributo, Integer tipoOrdenacion) throws Exception {
        // ordenar por datos primitivos, la clazz sera algun atributo que yo quisiera
        Class<E> clazz = null;
        E[] matriz = toArray();
        if (size > 0) {
            // para el tipo de dato que querramos el objeto
            clazz = (Class<E>)cabecera.getDato().getClass();
            Boolean isObject = Utilidades.isObject(clazz);  // aqui para ordenar con el string 
              // algoritmo de seleccion
              Integer i, j, k = 0;
              Integer n = matriz.length;
              E t = null;
                for (i = 0; i < n-1; i++) {
                    k = i;
                    t = matriz[i];
                    for (j = i + i; j < n; j++) {
                        E auxj1 = matriz[j];
                        Object[] aux = null;
                        if(isObject){
                            aux = evaluarCambioObjeto(t, auxj1, j, tipoOrdenacion, clazz, atributo);
                        
                        }else{
                           
                            aux = evaluarCambioDato(t, auxj1, j, tipoOrdenacion);
                                                 
                        }
                        if(aux[0]!=null){
                                 t = (E) aux[0];
                                 k = (Integer)aux[1];
                            }
                    }
                    matriz[k]=matriz[i];
                    matriz[i]=t;
                }
                
            }
        if (matriz != null)
            toList(matriz);
        
        return this;
    }
    
    private Object[] evaluarCambioDato(E auxJ, E auxJ1, Integer j, Integer tipoOrdenacion){  // no es el objecto, son para datos primitivos
       return evaluarCambio(auxJ, auxJ1, auxJ1, j, tipoOrdenacion);
    
    }
    
    private Object[] evaluarCambioObjeto(E auxJ, E auxJ1, Integer j, Integer tipoOrdenacion, Class clazz, String atributo) throws Exception{  
       Field field = Utilidades.obtenerAtributo(clazz, atributo);
       if(field == null)
           throw new AtributoException();
       field.setAccessible(true);
       Object a = field.get(auxJ);
       Object b = field.get(auxJ1);
        return evaluarCambio(a,b,auxJ1, j, tipoOrdenacion);
    
    }
    
    private Object[] evaluarCambio(Object auxJ, Object auxJ1,E dato, Integer j, Integer tipoOrdenacion){
        //Object valor = matriz(j);
        Object [] aux = new Object[2];
        Class clazz = auxJ.getClass();
         if (Utilidades.isNumber(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por number 
                if (((Number) auxJ).doubleValue() < ((Number) auxJ1).doubleValue()) {
                    //aux[0] = auxJ1;
                    aux[0] = dato;
                    aux[1] = j; //-> generico es decir que es para todos
                }
            } else {
                if (((Number) auxJ).doubleValue() > ((Number) auxJ1).doubleValue()) {
                    //aux[0] = auxJ1;
                    aux[0] = dato;
                    aux[1] = j;
                }
            }
        }
        if (Utilidades.isString(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                // ordenamos por string 
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) < 0) {
                    //aux[0] = auxJ1;
                    aux[0] = dato;
                    aux[1] = j;
                }
            } else {
                if (auxJ.toString().toLowerCase().compareTo(auxJ1.toString().toLowerCase()) > 0) {
                   //aux[0] = auxJ1;
                   aux[0] = dato;
                   aux[1] = j;
                }

            }
        }
        return aux;
    
    }
    
    
    // busqueda secuencial -> se devuelven varios objetos
    
    public ListaEnlazada<E> buscar(String atributo, Object dato)throws Exception{
          Class<E> clazz = null;
        
        ListaEnlazada<E> result = new ListaEnlazada<>();
        if (size > 0) {
            // opcional >> cuando tengo arreglos desordenados
            // se ordena
            E[] matriz = toArray();
            clazz = (Class<E>)cabecera.getDato().getClass();
            Boolean isObject = Utilidades.isObject(clazz);  // aqui para ordenar con el string 
              
                for (int i = 0; i < matriz.length; i++) {
                    if (isObject) {
                        // cuando es objeto
                        Boolean band = evaluarBusquedaObjeto(matriz[i], dato, clazz, atributo);
                        if(band){
                            result.insertar(matriz[i]);
                        }
                         
                    } else {
                        // cuando es dato primitivo
                        E aux = matriz[i];
                        // para todos los datos primitivos
                        Boolean band = buscarPosicion(matriz[i], dato);
                        if(band)
                            result.insertar(matriz[i]);
                    }
                }
                
            }
        
        
        return result;
        
    }
    
    // metodo para evaluar busqueda con datos primitivos 
    private Boolean buscarPosicion(Object datoMatriz, Object busqueda) {
        // implementar datos (fechas, enums, characters)
        if (Utilidades.isNumber(busqueda.getClass())) {
            // si es number se realiza esto si es string el else
            if (((Number) datoMatriz).doubleValue() < ((Number) busqueda).doubleValue()) {
                return true;
            }
            // conocer la posicion si esta al principio, final o lo contiene
        } else if (Utilidades.isString(busqueda.getClass())) {
            if (datoMatriz.toString().toLowerCase().startsWith(busqueda.toString().toLowerCase())
                    || datoMatriz.toString().toLowerCase().endsWith(busqueda.toString().toLowerCase())
                    || datoMatriz.toString().toLowerCase().equalsIgnoreCase(busqueda.toString().toLowerCase())
                    || datoMatriz.toString().toLowerCase().contains(busqueda.toString().toLowerCase())) {
                return true;

            }

        }
        return false;
    }
    
     private Boolean evaluarBusquedaObjeto(E aux, Object dato, Class clazz, String atributo) throws Exception{  
       Field field = Utilidades.obtenerAtributo(clazz, atributo);
       if(field == null){
           throw new AtributoException();
       }
       field.setAccessible(true);         
       Object a = field.get(aux);
     
        return buscarPosicion(a, dato);
    
    }
     
     public ListaEnlazada<E> metodoQuickSort(String atributo, Integer tipoOrdenacion) throws Exception {
        Class<E> clazz = null;
        E[] matriz = toArray();
        long iniEje = System.nanoTime();
        if (size > 0) {
            clazz = (Class<E>) cabecera.getDato().getClass();
            Boolean isObject = vistas.Utilidades.Utilidades.isObject(clazz);
            if (isObject) {
                metodoQuickSort(matriz, atributo, clazz, 0, matriz.length - 1, tipoOrdenacion);
            } else {
                metodoQuickSortPrim(matriz, 0, matriz.length - 1, tipoOrdenacion);
            }
        }
        long finEje = System.nanoTime();
        System.out.println("Tiempo de ejecucion: " + (finEje - iniEje));
        return this;
    }

public void metodoQuickSort(E[] matriz, String atributo, Class clazz, Integer posInicial, Integer posFinal, Integer tipoOrdenacion)
            throws Exception {
        Field field = null;
        field = vistas.Utilidades.Utilidades.obtenerAtributo(clazz, atributo);
        if (field == null) {
            throw new AtributoException();
        }
        field.setAccessible(true);
        int i, j;
        E bajo, pivote;
        i = posInicial;
        j = posFinal;
        pivote = matriz[posInicial + posFinal / 2];
        Boolean bandera = true;
        do {
            bandera = false;
            Integer[] indices = intercambioQuickSort(i, j, matriz, field, pivote, tipoOrdenacion);
            i = (int) indices[0];
            j = (int) indices[1];
            if (i <= j) {
                bajo = matriz[i];
                matriz[i] = matriz[j];
                matriz[j] = bajo;
                i++;
                j--;
                bandera = true;
            }
        } while (i <= j && bandera == true);

        if (posInicial < j) {
            metodoQuickSort(matriz, atributo, clazz, posInicial, j, tipoOrdenacion);
        }
        if (i < posFinal) {
            if (tipoOrdenacion == DESCENDENTE) {
                metodoQuickSort(matriz, atributo, clazz, i - 1, posFinal, tipoOrdenacion);
            } else {
                metodoQuickSort(matriz, atributo, clazz, i - 1, posFinal, tipoOrdenacion);
            }
        }
        if (matriz != null) {
            toList(matriz);
        }

    }

public Integer[] intercambioQuickSort(int i, int j, E[] matriz, Field field, E pivote, Integer tipoOrdenacion) throws Exception {
        Class clazz = pivote.getClass();
        Integer[] indices = new Integer[2];
        Object a = null, b = null, piv;
        a = matriz[i];
        b = matriz[j];
        if (field != null) {
            a = field.get(a);
            b = field.get(b);
            pivote = (E) field.get(pivote);
        }

        if (vistas.Utilidades.Utilidades.isNumber(clazz)) {
            if (tipoOrdenacion == DESCENDENTE) {
                System.out.println("tipo de ordenacion : " + tipoOrdenacion);
                while (((Number) matriz[i]).doubleValue() < ((Number) pivote).doubleValue()) {
                    i++;
                    if (field != null) {
                        a = field.get((Object) matriz[i]);
                    } else {
                        a = matriz[i];
                    }
                }
                while (((Number) matriz[j]).doubleValue() > ((Number) pivote).doubleValue()) {
                    j--;
                    if (field != null) {
                        b = field.get((Object) matriz[j]);
                    } else {
                        b = matriz[j];
                    }
                }
            } else {
                while (((Number) matriz[i]).doubleValue() < ((Number) pivote).doubleValue()) {
                    i++;
                    if (field != null) {
                        a = field.get((Object) matriz[i]);
                    } else {
                        a = matriz[i];
                        System.out.println("a  " + a.toString() + " i " + i);

                    }
                }
                while (((Number) matriz[j]).doubleValue() > ((Number) pivote).doubleValue()) {
                    j--;
                    if (field != null) {
                        b = field.get((Object) matriz[j]);
                    } else {
                        b = matriz[j];
                        System.out.println("b  " + a.toString() + " j " + j);
                    }
                }
            }
        }
        if (vistas.Utilidades.Utilidades.isString(clazz) || field != null) {
            if (tipoOrdenacion == DESCENDENTE) {
                while (a.toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) > 0) {
                    i++;
                    if (field != null) {
                        a = field.get((Object) matriz[i]);
                    } else {
                        a = matriz[i];
                    }
                }
                while (b.toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) < 0) {
                    j--;
                    if (field != null) {
                        b = field.get((Object) matriz[j]);
                    } else {
                        b = matriz[j];
                    }
                }
            } else {
                while (a.toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) < 0) {
                    i++;
                    if (field != null) {
                        a = field.get((Object) matriz[i]);
                    } else {
                        a = matriz[i];
                    }
                }
                while (b.toString().toLowerCase().compareTo(pivote.toString().toLowerCase()) > 0) {
                    j--;
                    if (field != null) {
                        b = field.get((Object) matriz[j]);
                    } else {
                        b = matriz[j];
                    }
                }
            }
        }
        indices[0] = i;
        indices[1] = j;
        return indices;
    }

public E[] metodoQuickSortPrim(E[] matriz, Integer posInicial, Integer posFinal, Integer tipoOrdenacion)
            throws Exception {
        if (posInicial >= posFinal) {
            toList(matriz);
            return matriz;
        }
        int i, j, pivote;
        E bajo;
        i = posInicial;
        j = posFinal;
        Class clazz = matriz[posInicial].getClass();
        if (vistas.Utilidades.Utilidades.isNumber(clazz)) {
            if (posInicial != posFinal) {
                pivote = posInicial;
                while (posInicial != posFinal) {
                    if (tipoOrdenacion == ASCENDENTE) {
                        while (((Number) matriz[posFinal]).doubleValue() >= ((Number) matriz[pivote]).doubleValue()
                                && posInicial < posFinal) {
                            posFinal--;
                            while (((Number) matriz[posInicial]).doubleValue() < ((Number) matriz[pivote]).doubleValue()
                                    && posInicial < posFinal) {
                                posInicial++;
                            }
                        }
                    } else {
                        while (((Number) matriz[posFinal]).doubleValue() <= ((Number) matriz[pivote]).doubleValue()
                                && posInicial < posFinal) {
                            posFinal--;
                            while (((Number) matriz[posInicial]).doubleValue() > ((Number) matriz[pivote]).doubleValue()
                                    && posInicial < posFinal) {
                                posInicial++;
                            }
                        }
                    }
                    if (posInicial != posFinal) {
                        bajo = matriz[posFinal];
                        matriz[posFinal] = matriz[posInicial];
                        matriz[posInicial] = bajo;
                    }
                    if (posInicial == posFinal) {
                        metodoQuickSortPrim(matriz, i, posInicial - 1, tipoOrdenacion);
                        metodoQuickSortPrim(matriz, posInicial + 1, j, tipoOrdenacion);
                    }
                }
            } else {
                toList(matriz);
                return matriz;
            }
        }
        if (vistas.Utilidades.Utilidades.isString(clazz)) {
            if (posInicial != posFinal) {
                pivote = posInicial;
                while (posInicial != posFinal) {
                    if (tipoOrdenacion == ASCENDENTE) {
                        while (matriz[posFinal].toString().toLowerCase().compareTo(matriz[pivote].toString().toLowerCase())
                                >= 0 && posInicial < posFinal) {
                            posFinal--;
                            while ((matriz[posInicial]).toString().toLowerCase().compareTo(matriz[pivote].toString().toLowerCase()) < 0
                                    && posInicial < posFinal) {
                                posInicial++;
                            }
                        }
                    } else {
                        while (matriz[posFinal].toString().toLowerCase().compareTo(matriz[pivote].toString().toLowerCase())
                                <= 0 && posInicial < posFinal) {
                            posFinal--;
                            while (matriz[posFinal].toString().toLowerCase().compareTo(matriz[pivote].toString().toLowerCase())
                                    > 0 && posInicial < posFinal) {
                                posInicial++;
                            }
                        }
                    }
                    if (posInicial != posFinal) {
                        bajo = matriz[posFinal];
                        matriz[posFinal] = matriz[posInicial];
                        matriz[posInicial] = bajo;
                    }
                    if (posInicial == posFinal) {
                        metodoQuickSortPrim(matriz, i, posInicial - 1, tipoOrdenacion);
                        metodoQuickSortPrim(matriz, posInicial + 1, j, tipoOrdenacion);
                    }
                }
            } else {
                toList(matriz);
                return matriz;
            }
        }

        toList(matriz);
        return matriz;

    }

public ListaEnlazada busquedaLinealBinaria(String campo, String dato) throws Exception {
        ListaEnlazada<E> resultado = new ListaEnlazada<>();
        long iniEje = System.nanoTime();
        long finEje = (long) 0.00;
        if (size > 0) {
            E[] matriz = toArray();
            Class<E> clazz = (Class<E>) cabecera.getDato().getClass();
            boolean isObject = vistas.Utilidades.Utilidades.isObject(clazz);
            Integer inicio = 0;
            Integer fin = matriz.length - 1;
            Integer posicion = 0;
            Float num = null;
            Field field = null;
            Object valor = null;
            if (campo != null) {
                field = vistas.Utilidades.Utilidades.obtenerAtributo(clazz, campo);
                if (field == null) {
                    System.out.println("error");
                    throw new AtributoException();
                }
                field.setAccessible(true);
            } else {
                num = (float) Float.parseFloat(dato);
                System.out.println(num);
            }
            while (inicio <= fin) {
                posicion = (inicio + fin) / 2;
                if (isObject) {

                    valor = field.get(matriz[posicion]);
                    if (valor.equals(dato)) {
                        resultado.insertar(matriz[posicion]);
                        finEje = System.nanoTime();
                        System.out.println("Tiempo de ejecicion:" + (finEje - iniEje));
                        return resultado;
                    } else if ((matriz[posicion]).toString().compareTo(dato) < 0) {
                        inicio = posicion + 1;
                    } else {
                        fin = posicion - 1;
                    }
                } else {
                    System.out.println("arreglo " + matriz[posicion]);
                    if (((Number) matriz[posicion]).floatValue() == num) {
                        System.out.println("  ");
                        finEje = System.nanoTime();
                        resultado.insertar(matriz[posicion]);
                        System.out.println("Tiempo de ejecicion: " + (finEje - iniEje));
                        return resultado;
                    } else if (((Number) matriz[posicion]).floatValue() < num) {
                        System.out.println("numeroArreglo < numeroBuscado");
                        inicio = posicion + 1;
                    } else {
                        System.out.println("else");
                        fin = posicion - 1;
                    }
                }
            }
        }
        finEje = System.nanoTime();
        System.out.println("Tiempo de ejecicion: " + (finEje - iniEje));
        return resultado;
    }




    //Getter and Setter
    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getSize() {
//        this.size = tamanio();
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
