/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author jtech
 */
public class Menu extends JFrame{
    private Controller controller;
    private JMenuBar jMenuBar = new JMenuBar();
    private String[] opcionesMenu;
    private String[][] opciones; 
    private JMenu[] jMenus;
    private JMenuItem[][] jMenuItems;

    public Menu() {
        // inicializamos el driver
        controller = new Controller(); 
        setSize(1200, 900);        
        // carga las opciones del archivo repositorio.xml manejando la clase RepositorioXML a traves del driver del aplicativo 
        opcionesMenu = (String [])(controller.getRepositorio())[2];
        opciones = (String [][]) (controller.getRepositorio())[3];
        jMenus = new JMenu[opcionesMenu.length];
        jMenuItems =  new JMenuItem[opcionesMenu.length][];
        // ubicamos los componentes
        ubicarComponentes();
        setVisible(true);
    }
    
        private void ubicarComponentes() {
               

        for ( int i=0 ; i<opcionesMenu.length; i++)
           { 
             jMenus[i]  = new JMenu(opcionesMenu[i]);
             jMenuItems[i] = new JMenuItem[opciones[i].length];
             for ( int k=0 ; k<opciones[i].length; k++)
                { 
                   jMenuItems[i][k] = new JMenuItem(opciones[i][k]);
                   jMenus[i].add(jMenuItems[i][k]);                                 
                }
             jMenuBar.add(jMenus[i]);
           }
        setJMenuBar(jMenuBar);
    }
    

    
   
}
