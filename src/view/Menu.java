/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import controller.InvocacionAutomaticaMenu;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.CardLayout;

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
    private JPanel jPanelPrincipal; 
    private CardLayout cardLayout;

    public Menu() {
        // inicializamos el driver
        controller = new Controller();
        controller.setMenu(this); // almacenamos la instancia de la vista menú en el driver del aplicativo
        setSize(1200, 900);        
        // carga las opciones del archivo repositorio.xml manejando la clase RepositorioXML a traves del driver del aplicativo 
        opcionesMenu = (String [])(controller.getRepositorio())[2];
        opciones = (String [][]) (controller.getRepositorio())[3];
        jMenus = new JMenu[opcionesMenu.length];
        jMenuItems =  new JMenuItem[opcionesMenu.length][];
        // ubicamos los componentes
        ubicarComponentes();
        setVisible(true);
        //   PRESENTA PANTALLA DE CONEXION
        InvocacionAutomaticaMenu invocacionAutomaticaMenu = new InvocacionAutomaticaMenu();  
        invocacionAutomaticaMenu.addEventoOpcionMenuListener(controller);// la clase Controller implementa la interfaz actionlistener
        invocacionAutomaticaMenu.fireEventoOpcionMenu("Conexion");
    }
    
        private void ubicarComponentes() {
            
        addWindowListener(controller); /* REGISTRO DE ESCUCHA DE EVENTOS DE VENTANA (metodo public void windowClosing(WindowEvent e) {...} en el driver del aplicactivo) */

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
        
    public void responderAController(String actionCommand) throws Exception {
        
        String textoTitulo = "Gestión taller     -     "+actionCommand;
        if (actionCommand.compareTo("Conexion") != 0)
            textoTitulo+="     -     Conectado como usuario  "+controller.getUsuarioAutenticado().getIdentificadorUsuario();
        setTitle(textoTitulo);  
        
        boolean usuarioAutenticado = false;  
                
        if (controller.getUsuarioAutenticado().getIdentificadorUsuario() != null)
            usuarioAutenticado = true;
              
        for ( int i=0 ; i<opcionesMenu.length; i++)
           { 
             for ( int k=0 ; k<opciones[i].length; k++)
                {  if (jMenuItems[i][k].getActionCommand().compareTo("Conexion") == 0  ||  jMenuItems[i][k].getActionCommand().compareTo(actionCommand) == 0)                        
                         jMenuItems[i][k].setEnabled(false);
                      else
                         jMenuItems[i][k].setEnabled(usuarioAutenticado);
                }
           }
          
        if (jPanelPrincipal != null)
            this.getContentPane().remove(jPanelPrincipal);
        jPanelPrincipal = new JPanel(new CardLayout()); 
        PantallaOpcion pantallaOpcion = (PantallaOpcion)Class.forName("view."+  eliminarEspaciosEnBlanco(actionCommand)).getDeclaredConstructor().newInstance();  
        controller.setPantallaOpcion(pantallaOpcion); 
        pantallaOpcion.inicializarPostInstanciar(controller);  
        pantallaOpcion.inicializarPantalla();
        jPanelPrincipal.add(eliminarEspaciosEnBlanco(actionCommand), pantallaOpcion); 
        this.getContentPane().add(jPanelPrincipal);     
        setVisible(true);
        ((CardLayout)jPanelPrincipal.getLayout()).show(jPanelPrincipal, eliminarEspaciosEnBlanco(actionCommand));       
    }
 
    
    private String eliminarEspaciosEnBlanco(String cadenaRecibida) {
        String nombreClase = "";
        for ( int i=0 ; i<cadenaRecibida.length(); i++)
           if (cadenaRecibida.charAt(i)!=' ')
              nombreClase = nombreClase + cadenaRecibida.charAt(i);
                     
        return nombreClase;
    }
    

    
   
}
