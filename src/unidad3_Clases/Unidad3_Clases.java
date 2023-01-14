/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidad3_Clases;

import controlador.colas.Cola;
import controlador.dao.AdaptadorDao;
import controlador.dao.PersonaDao;
import controlador.grafo.GrafoDirigido;
import controlador.grafo.GrafoDirigidoEtiquetado;
import controlador.grafo.GrafoNoDirigido;
import controlador.grafo.GrafoNoDirigidoEtiquetado;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.pilas.Pila;
import controlador.utiles.Utilidades;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Persona;
import modelo.Rol;
import vistas.FrmGrafo;

/**
 *
 * @author DEEPIN
 */
public class Unidad3_Clases {

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args) {
    
    
    GrafoDirigido gd=new GrafoDirigido(4);
                System.out.println(gd);
                System.out.println("----------------");
                try {
                    gd.insertarArista(4, 2);
                    gd.insertarArista(4, 1);
                    gd.insertarArista(4, 3);
                    gd.insertarArista(2, 3);
                       new FrmGrafo(null, true, gd).setVisible(true);
        
                    System.out.println(gd);
        
                } catch (Exception e) {
                    System.out.println("Error"+ e.getMessage());
                }
                
                GrafoDirigidoEtiquetado gde = new GrafoDirigidoEtiquetado(5, String.class);
            gde.etiquetarVertice(1, "Mayuri");
            gde.etiquetarVertice(2, "ALice");
            gde.etiquetarVertice(3, "Letty");
            gde.etiquetarVertice(4, "Vannesa");
            gde.etiquetarVertice(5, "Viviana");
            
            //se debe obtener la clase mediante el identificador
            try {
                
                gde.insertarAristaE(gde.obtenerEtiqueta(1), gde.obtenerEtiqueta(5), 10.0);
                gde.insertarAristaE(gde.obtenerEtiqueta(2), gde.obtenerEtiqueta(3), 1000.0);
                gde.insertarAristaE(gde.obtenerEtiqueta(3), gde.obtenerEtiqueta(2), 0.0);
                gde.insertarAristaE(gde.obtenerEtiqueta(4), gde.obtenerEtiqueta(1), 50.0);
                new FrmGrafo(null, true, gde).setVisible(true);
                
                System.out.println(gde.toString());
                
//                gde.insertarArista(1, 3);
//            gde.insertarArista(1, 5);
//            gde.insertarArista(1, 7);
//            new FrmGrafo(null, true, gde).setVisible(true);
                
        } catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
        }
            
            GrafoNoDirigidoEtiquetado gnde = new GrafoNoDirigidoEtiquetado(5, String.class);
            gnde.etiquetarVerticeNd(1, "Dennys");
            gnde.etiquetarVerticeNd(2, "David");
            gnde.etiquetarVerticeNd(3, "Carlos");
            gnde.etiquetarVerticeNd(4, "Vladimir");
            gnde.etiquetarVerticeNd(5, "Santiago");
            
            try {
                
                gnde.insertarAristaE(gnde.obtenerEtiquetaNd(5), gnde.obtenerEtiquetaNd(2), 20.0);
                gnde.insertarAristaE(gnde.obtenerEtiquetaNd(5), gnde.obtenerEtiquetaNd(4), 5000.0);
                gnde.insertarAristaE(gnde.obtenerEtiquetaNd(5), gnde.obtenerEtiquetaNd(1), 0.56);
                gnde.insertarAristaE(gnde.obtenerEtiquetaNd(5), gnde.obtenerEtiquetaNd(3), 350.0);
                new FrmGrafo(null, true, gnde).setVisible(true);
                
                System.out.println(gnde.toString());
          
      } catch (Exception e) {
          System.out.println("Error"+ e.getMessage());
      }
    }
    
        // TODO code application logic here
/*
        ListaEnlazada<Integer> lista = new ListaEnlazada<>();
        lista.insertar(25);
        lista.insertar(500);
        lista.insertar(100);
        lista.insertarCabecera(300);
        try {
            lista.insertarPosicion(450, 3);
            Integer pos = 2;
            System.out.println("El objeto de la posicion " + pos + " es " + lista.obtener(pos-1));
            lista.imprimir();
            lista.insertarPosicion(9, 0);
            lista.insertarPosicion(450, 3);
            lista.imprimir();
        } catch (PosicionNoEncontradaException | ListaNullException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(lista.getSize());
        lista.imprimir();
        
        
        try {
            lista.modificar(12, 2);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        lista.imprimir();*/
//        System.out.println("\n***************************PILA***************************\n");
//
//        Pila<String> p = new Pila<>(3);
//        Cola<String> c = new Cola<>(4);
//        try {
//            p.push("Juan");
//            p.push("Maria");
//            p.push("Pedro");
//
//            p.imprimir();
//            System.out.println("push " + p.pop());
//            p.imprimir();
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
////
////        System.out.println("\n***************************COLA***************************\n");
////
////        try {
////
////            c.queue("Juan");
////            c.queue("Luis");
////            c.queue("Sauul");
////            c.queue("Denji");
////            c.imprimir();
////
////            c.dequue();
////            c.imprimir();
////        } catch (Exception e) {
////            System.out.println("Error: " + e.getMessage());
////        }
//
//        AdaptadorDao<Rol> ad = new AdaptadorDao<Rol>(Rol.class);
//        Rol aux = new Rol();
//        aux.setId(2);
//        aux.setNombre("Cajero");
//        aux.setDescripcion("Si hace algo xd");
//        try {
//            ad.guardar(aux);
////            ad.guardar(aux);
//            //ad.listar();
//            ad.listar().imprimir();
////            System.out.println(Utilidades.capitalizar("hola"));
//            //Field a = Utilidades.obtenerAtributo(Rol.class, "id");
//            //System.out.println(a.getType().getSimpleName());
//        } catch (Exception e) {
//            System.out.println(e);
//
//        }
//        AdaptadorDao<Persona> ad = new AdaptadorDao<>(Persona.class);
//        try {
//            Persona p = new Persona();
//            p.setApellido("White");
//            p.setNombre("Walter");
//            p.setDireccion("Laboratorio de Ohio");
//            p.setId(3);
//            ad.guardar(p);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

    /*   
    public static void imprimir(Object [] a){  // con burbuja
        System.out.println();
       for(int i = 0; i< a.length -1;i++){
            System.out.println(a[i].toString());
       }
        System.out.println();
    }*/
    //public static void main(String[] args) { //throws Exception {
        /*    try {
        ListaEnlazada<Integer> lista = new ListaEnlazada();
        lista.insertar(6);
        lista.insertar(0);
        lista.insertar(36);
        lista.insertar(40);
        lista.insertar(60);
        lista.insertar(90);
        lista.imprimir();
        //Integer[]a = lista.toArray();
        //lista.burbuja(null, ListaEnlazada.ASCENDENTE); 
        //lista.order_seleccion(null, ListaEnlazada.ASCENDENTE);
        System.out.println("ORDENAR");
        lista.imprimir();
            System.out.println("BUSQUEDA");
            lista.buscar(null, 4).imprimir();
            System.out.println("FIN BUSQUEDA");
        
        
        ListaEnlazada<String> lista1 = new ListaEnlazada();
        lista1.insertar("Sooto");
        lista1.insertar("Cano");
        lista1.insertar("Quinche");
        lista1.insertar("Calva");
        lista1.insertar("Marilyn");
        lista1.imprimir();
        //lista1.burbuja(null, 2);
        //lista1.order_seleccion(null, ListaEnlazada.DESCENDENTE);
        System.out.println("ORDENAR");
        lista1.imprimir();  
        System.out.println("BUSQUEDA");
            lista1.buscar(null, "n").imprimir();
            System.out.println("FIN BUSQUEDA");
        
            ListaEnlazada<Persona> listaP = new PersonaDao().listar();
            //listaP.burbuja("apellido", ListaEnlazada.ASCENDENTE);
            listaP.imprimir();
            //listaP.burbuja("id", ListaEnlazada.DESCENDENTE);
             System.out.println("BUSQUEDA");
            listaP.buscar("apellido", 1).imprimir();
            System.out.println("FIN BUSQUEDA");
            //lista.order_seleccion("identificacion", ListaEnlazada.DESCENDENTE);
            //listaP.imprimir();
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
         */

//        GrafoDirigido gd = new GrafoDirigido(4);
//        System.out.println(gd);
//        System.out.println("------------------------------");
//        try {
//            gd.insertarArista(1, 3);
//            gd.insertarArista(1, 5);
//            gd.insertarArista(1, 7);
//           
//            // gd.insertarArista(3, 7);
//
//            System.out.println(gd);
//        } catch (Exception e) {
//            System.out.println("Error " + e.getMessage());
//        }
//        
//        GrafoDirigido gnd = new GrafoNoDirigido(4);
//        System.out.println(gnd);
//        System.out.println("------------------------------");
//        try {
//            gd.insertarArista(1, 3);
//            gd.insertarArista(1, 5);
//            gd.insertarArista(1, 7);
//            new FrmGrafo(null, true, gnd).setVisible(true);
//            // gd.insertarArista(3, 7);
//
//            System.out.println(gnd);
//        } catch (Exception e) {
//            System.out.println("Error " + e.getMessage());
//        }
//        
//        /*hashMap<INteger, String> maa = new HashMap<>();
//        mapa.put(5, "casa");
//        System.out.println(mapa.get(1));*/


            

}
