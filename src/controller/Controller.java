/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import application.GenericaExcepcion;
import application.GestorIncidencias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import model.Contexto;
import view.Menu;
import view.ModeloDatos;
import view.PantallaOpcion;

/**
 *
 * @author jtech
 */
public class Controller extends WindowAdapter implements ActionListener, ListSelectionListener, CaretListener, ItemListener, ChangeListener, TableModelListener {
    
    private Object[] repositorio;
    private Menu menu;
    private Contexto usuarioAutenticado = null;
    private PantallaOpcion pantallaOpcion;
    

    public Controller() {

        try {
            repositorio = new RepositorioNegocio().cargarRepositorio();
            usuarioAutenticado = new Contexto(null, null, obtenerIP());
        } catch (Exception exception) {
            { new GestorIncidencias().gestionarExcepcion(exception, usuarioAutenticado); } 
        }

        
    }
    
    public String obtenerIP() throws UnknownHostException {
        String[] cadenasIP = InetAddress.getLocalHost().getHostAddress().split("\\.");
        StringBuffer procesoIP = new StringBuffer(16);
        for (int i=0; i<cadenasIP.length; i++)
        {
           if (i>0)
               procesoIP.append(".");
           procesoIP.append(String.format("%03d", Integer.parseInt(cadenasIP[i])));
        }
    
        return new String(procesoIP); 
    
    }
    
    
    // MÉTODO DE WindowAdapter  
    public void windowClosing(WindowEvent e) { 
          centralizar("op_menu - CierreVentana");

      }
    
    @Override
    public void actionPerformed(ActionEvent e) {
                  //  Los actionCommand han de tener un mínimo de ocho posiciones para tener mayor longitud que "op_menu".
          String componenteFuente = e.getActionCommand();
          if (e.getSource() instanceof JMenuItem) 
              componenteFuente = ((JMenuItem) e.getSource()).getActionCommand();
        
          switch(componenteFuente)
          {                                    
              case "Conexion" :                                               
              case "ConexionEfectuada" :  
              case "Desconexion":
              case "CierreVentana":
              case "Vista Formulario":
              case "Vista Unica Tabla":
                      centralizar("op_menu - "+componenteFuente);                
                      break;                   
              case "autenticacion" :
              case "comboVehiculos" : 
              case "nuevoVehiculo" :  
              case "aplicarCambios" :
              case "eliminarVehiculo" :
              case "insertarFila" :       
              case "cancelarInsercionFila" :    
              case "guardarFilaInsertada" : 
              case "eliminarFilaSeleccionada" :
                      centralizar(componenteFuente);                
                      break; 
              case "ordenarPorIdentificador" :
                     pantallaOpcion.setComponentesJPanel(1, 10);
                     centralizar("reordenar");
                     break;                    
              case "ordenarPorModelo" :   
                     pantallaOpcion.setComponentesJPanel(2, 10);
                     centralizar("reordenar");                  
                     break;                     
              case "ordenarPorCategoria" :
                     pantallaOpcion.setComponentesJPanel(3, 10);
                     centralizar("reordenar");                   
                     break; 
                    
          }

       
    }
    
         // MÉTODO DE ChangeListener  
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == (pantallaOpcion.getComponentesJPanel(6)))
        { 
           centralizar("actualizadoKilometrajeAlta");                           
        }
    
      System.out.println("Dato actualizado :  "+((JSlider)pantallaOpcion.getComponentesJPanel(6)).getValue());
        
    }
    
          // MÉTODO DE ListSelectionListener 
    @Override
    public void valueChanged(ListSelectionEvent e) {
               //   Cuando se efectúa botón del ratón sobre una fila se intercepta el evento. Y cuando se suelta dicho botón, vuelve a interceptarse el evento.
       ListSelectionModel listSelectionModel = (ListSelectionModel)e.getSource(); 
     
      switch(pantallaOpcion.getClass().getName())
       {  case "view.VistaUnicaTabla": 
          case "view.VistaPaginadaTabla": 
                  if (!(e.getValueIsAdjusting()))
                  {
                      pantallaOpcion.setComponentesJPanel(listSelectionModel.getMinSelectionIndex(), 2);  // Modifica valor de fila seleccionada en JTable  
                      System.out.println("fila seleccionada en JTable  "+pantallaOpcion.getComponentesJPanel(2));
                  }
                  break;        
          case "view.VistaFormulario": 
                  if (e.getSource() == (pantallaOpcion.getComponentesJPanel(12)))
                    { 
                       centralizar("actualizadoCategoria");               
                    }             
                  System.out.println("fila seleccionada en JList  "+listSelectionModel.getMinSelectionIndex());
                  System.out.println("dato actualizado  "+((String)((JList)pantallaOpcion.getComponentesJPanel(5)).getSelectedValue()));       
                  break;                          
       }
        
        
    }
               // MÉTODO DE TableModelListener 
        @Override
    public void tableChanged(TableModelEvent e) {
           //   La invocación a estos métodos es efectiva cuando en el método setValueAt(Object valor,int fila, int columna )  
           //   de ModeloDatos se activa el evento mediante la invocación a fireTableCellUpdated(fila, columna);
           pantallaOpcion.setComponentesJPanel(e.getFirstRow(), 13);    //  Fila de celda actualizada en JTable. 
           pantallaOpcion.setComponentesJPanel(e.getColumn(), 14);      //  Columna de celda actualizada en JTable. 
           
           centralizar("actualizadaColumnaJTable"); 
        
    }
          // MÉTODO DE CaretListener 
    @Override
    public void caretUpdate(CaretEvent e) {
        
    }
    

          // MÉTODO DE ItemListener 
    @Override
    public void itemStateChanged(ItemEvent ie) {
        
    }
    
    public void centralizar(String actionCommand) {
       
        try {                             
              if (actionCommand.substring(0, 7).compareTo("op_menu") == 0)   // OPCION DE MENU
                {      
                   switch (actionCommand)
                   {
                       case "op_menu - Conexion":   
                       case "op_menu - ConexionEfectuada": 
                       case "op_menu - Vista Formulario":
                       case "op_menu - Vista Unica Tabla":
                              actionCommand = actionCommand.substring(10);
                              menu.responderAController(actionCommand);                          
                              break;
                       case "op_menu - Desconexion":
                              //  DESCONECTAR SESION DE USUARIO Y PRESENTAR PANTALLA DE CONEXION
                              usuarioAutenticado.setIdentificadorUsuario(null);
                              usuarioAutenticado.setPassword(null);
                              InvocacionAutomaticaMenu invocacionAutomaticaMenu = new InvocacionAutomaticaMenu();  
                              invocacionAutomaticaMenu.addEventoOpcionMenuListener(this);
                              invocacionAutomaticaMenu.fireEventoOpcionMenu("Conexion");   
                              break;
                       case "op_menu - CierreVentana":
                              System.exit(0);
                              break;                              
                   }
                }              
              else    // RESPUESTA A EVENTO DISPARADO POR COMPONENTE U OPCION DE MENU DE Nodo DE VistaArbol
                { 
                   pantallaOpcion.responderAController(actionCommand);
                } 
            
            } catch (Exception exception)
               {
                  usuarioAutenticado.setFechaHora(new java.util.Date());
                  new GestorIncidencias().gestionarExcepcion(exception, usuarioAutenticado);
                  if (actionCommand.compareTo("actualizadaColumnaJTable") == 0)
                  {
                     if (exception instanceof GenericaExcepcion)      
                     {
                        GenericaExcepcion genericaExcepcion = (GenericaExcepcion)exception;
                        if (genericaExcepcion.getCodigoError() <= 16)   //  CODIGOS DE ERROR DE GenericaExcepcion GENERADOS POR LOS METODOS filtrarFecha() O filtrarKilometrajeVehiculo() QUE IMPLICAN RESTAURAR A LA CELDA EL VALOR ANTERIOR
                        {  
                           ((ModeloDatos)pantallaOpcion.getComponentesJPanel(15)).getDatos()[((Integer)pantallaOpcion.getComponentesJPanel(13))][((Integer)pantallaOpcion.getComponentesJPanel(14))] = ((ModeloDatos)pantallaOpcion.getComponentesJPanel(15)).getCopiaReservaDato();
                           ((JTable)pantallaOpcion.getComponentesJPanel(0)).repaint();
                        }

                     }
                  }
                  
               }  
        
        
    }
     

    public Object[] getRepositorio() {
        return repositorio;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Contexto getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Contexto usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public PantallaOpcion getPantallaOpcion() {
        return pantallaOpcion;
    }

    public void setPantallaOpcion(PantallaOpcion pantallaOpcion) {
        this.pantallaOpcion = pantallaOpcion;
    }

 
}
