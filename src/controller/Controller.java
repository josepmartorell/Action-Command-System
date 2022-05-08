/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jtech
 */
public class Controller {
    
    private Object[] repositorio;
    

    public Controller() {

        try {
            repositorio = new RepositorioNegocio().cargarRepositorio();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    public Object[] getRepositorio() {
        return repositorio;
    }

  
    
}
