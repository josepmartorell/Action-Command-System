/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import persistence.BaseDatos;

/**
 *
 * @author jtech
 */
public class VistaFormulario extends PantallaOpcion{
    
    private ListSelectionModel listSelectionModel;
    private JComboBox jComboBoxVehiculos; //libros/vehiculos
    private JLabel jLabelPlaca; // codigo/placa
    private JTextField jTextFieldPlaca;
    private JLabel jLabelModelo;//titulo/modelo
    private JTextField jTextFieldModelo;
    private JLabel jLabelFechaAlta;//fecha edicion/fecha alta
    private JTextField jTextFieldFechaAlta;
    private JCheckBox jCheckBoxItv;//premiado/itv
    private JLabel jLabelCategoria;
    private JList jListCategorias;
    private JSlider jSLiderKilometrajeAlta;
    private JLabel jLabelKilometrajeAlta;
    private JButton jButtonNuevoVehiculo;
    private JButton jButtonAplicarCambios;
    private JButton jButtonEliminar;
    private int actualizaciones;
            
    public VistaFormulario() {   
        ubicarComponentes();
    }
    
      
    private void ubicarComponentes() {
        setLayout(null);
  
        jComboBoxVehiculos = new JComboBox();
        jComboBoxVehiculos.setBounds(20, 100, 450, 20);
        jComboBoxVehiculos.setBackground(Color.white);
        jComboBoxVehiculos.setActionCommand("comboVehiculos");
        add(jComboBoxVehiculos);    
        
        jLabelPlaca = new JLabel("Placa");
        jLabelPlaca.setBounds(550, 100, 45, 20);
        add(jLabelPlaca);
        
        jTextFieldPlaca = new JTextField();
        jTextFieldPlaca.setBounds(600, 100, 70, 20);
        jTextFieldPlaca.setEditable(true);
        add(jTextFieldPlaca);

        jLabelModelo = new JLabel("Marca");
        jLabelModelo.setBounds(550, 140, 45, 20);
        add(jLabelModelo);      
        
        jTextFieldModelo = new JTextField();
        jTextFieldModelo.setBounds(600, 140, 400, 20);
        add(jTextFieldModelo);
 
        jLabelFechaAlta = new JLabel("Fecha alta (dd-mm-aaaa)");
        jLabelFechaAlta.setBounds(550, 180, 170, 20);
        add(jLabelFechaAlta);
        
        jTextFieldFechaAlta = new JTextField();
        jTextFieldFechaAlta.setBounds(730, 180, 80, 20);
        add(jTextFieldFechaAlta);
        
        jCheckBoxItv = new JCheckBox("Itv");
        jCheckBoxItv.setBounds(550, 220, 150, 20);
        add(jCheckBoxItv);
        
        jLabelCategoria = new JLabel("Categoría");
        jLabelCategoria.setBounds(550, 260, 50, 20);
        add(jLabelCategoria);               
        
        jSLiderKilometrajeAlta = new JSlider(JSlider.HORIZONTAL,1,100000,300);
        jSLiderKilometrajeAlta.setBounds(20, 430, 1070, 48);
        jSLiderKilometrajeAlta.setBorder(new TitledBorder("Kilometros alta"));
        jSLiderKilometrajeAlta.setPaintTicks(true);
        jSLiderKilometrajeAlta.setMajorTickSpacing(200);
        jSLiderKilometrajeAlta.setMinorTickSpacing(10);
        add(jSLiderKilometrajeAlta);
        
        jLabelKilometrajeAlta = new JLabel();
        jLabelKilometrajeAlta.setBounds(1095, 450, 80, 20);
        add(jLabelKilometrajeAlta);
       
        jButtonNuevoVehiculo = new JButton("Nuevo vehículo");
        jButtonNuevoVehiculo.setBounds(475, 550, 200, 40);
        jButtonNuevoVehiculo.setActionCommand("nuevoVehiculo");
        add(jButtonNuevoVehiculo);            
        
        jButtonAplicarCambios = new JButton("Aplicar cambios");
        jButtonAplicarCambios.setBounds(475, 630, 200, 40);
        jButtonAplicarCambios.setActionCommand("aplicarCambios");
        add(jButtonAplicarCambios);            
        
        jButtonEliminar = new JButton("Eliminar");
        jButtonEliminar.setBounds(475, 710, 200, 40);
        jButtonEliminar.setActionCommand("eliminarVehiculo");
        add(jButtonEliminar);                   
    }   
    
    
    public void inicializarPostInstanciar(Controller controller) throws Exception {
        
    }
    
    
    public void inicializarPantalla() throws Exception {
        
    }    
    
    
    public void responderAController(String actionCommand) throws Exception {

    }  
    
    
    private void visualizarVehiculoSeleccionado(String idVehiculo) throws Exception {

    }  


    
}
