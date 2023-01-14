/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.dao;

import com.sun.xml.internal.bind.v2.schemagen.Util;
import controlador.listas.ListaEnlazada;
import controlador.listas.NodoLista;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import controlador.utiles.Utilidades;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author DEEPIN
 */
public class AdaptadorDao<T> implements InterfazDao<T> {

    private String URL = "data" + File.separatorChar;
    private Class<T> clazz;

    public AdaptadorDao(Class<T> clazz) {
        this.clazz = clazz;
        URL += this.clazz.getSimpleName() + ".xml";
    }

    @Override
    public void guardar(T dato) throws FileNotFoundException, JAXBException {
        ListaEnlazada<T> lista = listar();
        lista.insertar(dato);

        FileOutputStream file = new FileOutputStream(URL);
        JAXBContext jaxbc = JAXBContext.newInstance(new Class[]{ListaEnlazada.class, this.clazz});
        Marshaller marshaller = jaxbc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(lista, file);

    }

    @Override
    public void modificar(T dato, Integer pos) throws FileNotFoundException, JAXBException, PosicionNoEncontradaException {
        // 7 lineas de codigo xd
        ListaEnlazada<T> lista = listar();
        lista.modificar(dato,pos);
        FileOutputStream file = new FileOutputStream(URL);
        JAXBContext jAXBContext = JAXBContext.newInstance(new Class[]{ListaEnlazada.class, this.clazz});
        Marshaller marshaller = jAXBContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(lista, file);

    }

    @Override
    public ListaEnlazada<T> listar() {
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(URL);
            NodeList datos = doc.getElementsByTagName(this.clazz.getSimpleName().toLowerCase());
            for (int i = 0; i < datos.getLength(); i++) {
//                System.out.println(datos.item(i));
                Node n1 = datos.item(i);
                NodeList nodo1 = n1.getChildNodes();

                T obj = this.clazz.newInstance();

                for (int j = 0; j < nodo1.getLength(); j++) {
                    Node dato = nodo1.item(j);
                    if (dato.getNodeName() != null && !dato.getNodeName().equalsIgnoreCase("")
                            && dato.getTextContent() != null && !dato.getTextContent().equalsIgnoreCase("")
                            && !dato.getNodeName().equalsIgnoreCase("#text")) {
                        Method metodo = null;
                        for (Method met : this.clazz.getMethods()) {
                            if (met.getName().equalsIgnoreCase(("set" + Utilidades.capitalizar(dato.getNodeName())))) {
                                metodo = met;
                                break;
                            }
                        }
                        metodo.invoke(obj,
                                Utilidades.transformarDato(Utilidades.obtenerAtributo(clazz, dato.getNodeName()), dato.getTextContent()));

                    }
                }
                lista.insertar(obj);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public T obtener(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
