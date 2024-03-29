/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import javax.swing.JPanel;

/**
 *
 * @author jtech
 */
public abstract class PantallaOpcion extends JPanel {
    
    protected Controller controller;
    protected Object[] componentesJPanel = new Object[20];
    
    public abstract void responderAController(String actionCommand) throws Exception;
    public abstract void inicializarPostInstanciar(Controller controller) throws Exception;

    
    public void inicializarPantalla() throws Exception {
    }

    public Object getComponentesJPanel(int component) {
        return componentesJPanel[component];
    }

    public void setComponentesJPanel(Object object, int component) {
        this.componentesJPanel[component] = object;
    }
    
    
    
    
}
