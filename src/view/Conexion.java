/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import controller.InvocacionAutomaticaMenu;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Contexto;
import persistence.BaseDatos;
import persistence.UsuariosBibliotecaNegocio;

/**
 *
 * @author jtech
 */
public class Conexion extends PantallaOpcion {
    
    private JLabel jLabelUsuario;
    private JTextField jTextFieldUsuario;
    private JLabel jLabelPassword;    
    private JPasswordField jPasswordFieldPassword; 

    public Conexion() {
        ubicarComponentes();
    }
    
    private void ubicarComponentes(){
        
        setLayout(null);
        
        jLabelUsuario = new JLabel("Usuario");
        jLabelUsuario.setBounds(400, 370, 150, 20);
        add(jLabelUsuario);
        
        jTextFieldUsuario = new JTextField();
        jTextFieldUsuario.setBounds(480, 370, 150, 20);
        add(jTextFieldUsuario);
        
        jLabelPassword = new JLabel("Password");
        jLabelPassword.setBounds(400, 395, 150, 20);
        add(jLabelPassword);       
        
        jPasswordFieldPassword = new JPasswordField();
        jPasswordFieldPassword.setBounds(480, 395, 150, 20);
        add(jPasswordFieldPassword);     
        jPasswordFieldPassword.setActionCommand("autenticacion");
    
    }
    
    

    @Override
    public void inicializarPostInstanciar(Controller controller) throws Exception {
        this.controller = controller;
        jPasswordFieldPassword.addActionListener(controller);
    }
    
    @Override
    public void responderAController(String actionCommand) throws Exception {
          switch(actionCommand)
          {   
               case "autenticacion" : 
                      Contexto contexto = new Contexto(jTextFieldUsuario.getText(), new String(jPasswordFieldPassword.getPassword()), obtenerIP());                         
                      new UsuariosBibliotecaNegocio().autenticar((BaseDatos)controller.getRepositorio()[0], contexto); 
                      controller.setUsuarioAutenticado(contexto);
                      InvocacionAutomaticaMenu invocacionAutomaticaMenu = new InvocacionAutomaticaMenu();  
                      invocacionAutomaticaMenu.addEventoOpcionMenuListener(controller);
                      invocacionAutomaticaMenu.fireEventoOpcionMenu("ConexionEfectuada");
                      break;       
          }
    }
    
    
    private String obtenerIP() throws UnknownHostException {
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
    
    
    
}
