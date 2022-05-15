/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtech
 */
public class InvocacionAutomaticaMenu {

    public InvocacionAutomaticaMenu() {
    }
    
    
    
    private List<ActionListener> listaListeners = new ArrayList();

    public void addEventoOpcionMenuListener(ActionListener actionListener) {      
        listaListeners.add(actionListener);
        
    }

    public void fireEventoOpcionMenu(String command) {
        for (int i = 0; i < listaListeners.size(); i++){
            listaListeners.get(i).actionPerformed(new ActionEvent(this, 1, command));
        
        }
        
    }
    
}
