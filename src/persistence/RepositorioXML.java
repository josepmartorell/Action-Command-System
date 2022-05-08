/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import application.GenericaExcepcion;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author jtech
 */
public class RepositorioXML {
    
        public Object[] cargar() throws Exception {
        
        Object[] repositorio = new Object[6];

        try {
              Document document = new SAXBuilder().build("xml/repositorio.xml");      // xml es un directorio en el raiz del Proyecto
              Element raiz = document.getRootElement();                             
             
             //      LEER OPCIONES MENU
             Element opcionesMenu = raiz.getChild("OPCIONES_MENU");  
             List listaGruposOpciones = opcionesMenu.getChildren("GRUPO_OPCIONES"); 
             repositorio[2] = new String[listaGruposOpciones.size()];  
             repositorio[3] = new String[listaGruposOpciones.size()][];
             for (int i=0; i<listaGruposOpciones.size(); i++)
             {
                 Element grupoOpciones = ((Element) listaGruposOpciones.get(i));
                 ((String [])repositorio[2])[i] = grupoOpciones.getAttributeValue("nombre_grupo");
                 
                 List listaOpciones = grupoOpciones.getChildren("OPCION");
                 String[] opciones = new String[listaOpciones.size()];
                 
                 for (int k=0; k<listaOpciones.size(); k++)
                 {  
                    opciones[k] = ((Element) listaOpciones.get(k)).getText();
                 }   
                 
                 ((String [][]) repositorio[3])[i] = opciones;
             }             
          
             
        } catch(JDOMException excepcion)
          { throw new GenericaExcepcion(30); }
          catch(IOException excepcion)
          { throw new GenericaExcepcion(31); }
                    
       return repositorio;
    }
    
}
