/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import persistence.RepositorioXML;

/**
 *
 * @author jtech
 */
public class RepositorioNegocio {
    
    //clase auxiliar con método que permite cargar repositorio.xml desde el driver (actuando como un intermediario entre Controller y RepositorioXML)
    public Object[] cargarRepositorio() throws Exception 
    {
        return new RepositorioXML().cargar();
    }
    
    
}
