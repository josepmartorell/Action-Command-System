/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import model.Vehiculo;

/**
 *
 * @author jtech
 */
public class ModeloDatos extends AbstractTableModel {
    
    private Object datos[][];
    private String[] nombreColumnas = {"Placa","Modelo","Categoría","Fecha alta","Kilometraje alta","Itv"};
    private Controller controller;
    private int numeroFilas = 0;
    private Object copiaReservaDato;
    private int numeroFilasRecibidas = 5;

    public ModeloDatos(Controller controller, int numeroFilas) throws Exception {
        this.controller = controller;
        this.numeroFilas = numeroFilas;
        instanciarArrayDatos();
        addTableModelListener(controller); 
    }
    
    private void instanciarArrayDatos() throws Exception {
              
      if (numeroFilas == 0)
         numeroFilas=1;
        
      datos = new Object[numeroFilas][6];    
    }
    
    private void inicializarFilas(int filaInicial, int filaFinal) {  
        for (int i=filaInicial; i<=filaFinal; i++)
          { datos[i][0] = "";
            datos[i][1] = "";
            datos[i][2] = "";
            datos[i][3] = "";
            datos[i][4] = 0;
            datos[i][5] = false;
          }                
    }
    
    public void insertarFila() {  
        Object datosAuxiliar[][] = datos;
        numeroFilas++;
        datos = new Object[numeroFilas][6];

        for (int i=0; i<(datos.length)-1; i++)
        {  datos[i][0] = datosAuxiliar[i][0];
           datos[i][1] = datosAuxiliar[i][1];
           datos[i][2] = datosAuxiliar[i][2];
           datos[i][3] = datosAuxiliar[i][3];
           datos[i][4] = datosAuxiliar[i][4];
           datos[i][5] = datosAuxiliar[i][5];
        }       
        inicializarFilas((datos.length)-1, (datos.length)-1);

        ((JTable)controller.getPantallaOpcion().getComponentesJPanel(0)).repaint();     
    } 

  
    public void eliminarFilaInsertada() {

        Object datosAuxiliar[][] = datos;
        numeroFilas--;
        datos = new Object[numeroFilas][6];  

        for (int i=0; i<datos.length; i++)
        {  datos[i][0] = datosAuxiliar[i][0];
           datos[i][1] = datosAuxiliar[i][1];
           datos[i][2] = datosAuxiliar[i][2];
           datos[i][3] = datosAuxiliar[i][3];
           datos[i][4] = datosAuxiliar[i][4];
           datos[i][5] = datosAuxiliar[i][5];
        }       

        ((JTable)controller.getPantallaOpcion().getComponentesJPanel(0)).repaint();  
    }


    public void cargar(List<Vehiculo> listaVehiculos) throws Exception { 

        switch(controller.getPantallaOpcion().getClass().getName())
          {  case "view.VistaUnicaTabla": numeroFilas = listaVehiculos.size();
                     break;
             case "view.VistaPaginadaTabla": numeroFilas = ((ParametrosPaginacion)controller.getRepositorio()[4]).getNumeroFilasPagina(); 
                     break;                     
          }      

        numeroFilasRecibidas = listaVehiculos.size();
        instanciarArrayDatos();

        if (!listaVehiculos.isEmpty())
        {  
           for (int i=0; i<listaVehiculos.size(); i++)
           { 
               Vehiculo vehiculo = listaVehiculos.get(i);         
               datos[i][0] = vehiculo.getIdVehiculo();
               datos[i][1] = vehiculo.getModelo();
               datos[i][2] = vehiculo.getCategoria()+"  -  "+vehiculo.getDescripcion();
               datos[i][3] = new SimpleDateFormat("dd-MM-yyyy").format(vehiculo.getFechaAlta());
               datos[i][4] = vehiculo.getKilometrajeAlta(); 
              if (vehiculo.isItv())
                   datos[i][5] = true;
                 else
                   datos[i][5] = false;
           }
        }

        ((JTable)controller.getPantallaOpcion().getComponentesJPanel(0)).repaint();
   }



    public Object[][] getDatos() {
        return datos;
    }


     public Object getCopiaReservaDato() {
        return copiaReservaDato;
    }


    public int getColumnCount() {
        return(datos[0].length );
    }


    public int getRowCount() {
        return(datos.length );
    }


    public Object getValueAt(int fila, int columna) {
       Object datoADevolver = null;
       if (fila >= 0  &&  fila <= numeroFilas-1)
         {
            datoADevolver = datos[fila][columna];
         }
       return datoADevolver;
    }


    public void setValueAt(Object valor,int fila, int columna ) {  
         if (fila >= 0  &&  fila <= numeroFilas-1)
         {  copiaReservaDato = datos[fila][columna];
            datos[fila][columna] = valor;  
            fireTableCellUpdated(fila, columna);
            //  fireTableDataChanged();
         }
      }


    public boolean isCellEditable(int fila, int columna ) {
        boolean editable = false;

        switch(controller.getPantallaOpcion().getClass().getName())
          {  case "view.VistaUnicaTabla": if (columna >=0) // con primer campo autoincremental sería >0 para impedir su edición 
                                                      editable = true;
                     break;
             case "view.VistaPaginadaTabla": if (fila < numeroFilasRecibidas  &&  columna >0)
                                                         editable = true; 
                     break;                     
          } 

        return editable;
      }


    public String getColumnName(int columna) {
        return nombreColumnas[columna];
    }


    public Class getColumnClass(int columna) {
        Class classADevolver = null;
           try {
                 switch (columna)
                 {
                     case 0: classADevolver = Class.forName("java.lang.String");
                     case 1: classADevolver = Class.forName("java.lang.String");
                     case 2: classADevolver = Class.forName("java.lang.String");
                     case 3: classADevolver = Class.forName("java.lang.String");
                             break;
                     case 4: classADevolver = Class.forName("java.lang.Integer");
                             break;
                     case 5: classADevolver = Class.forName("java.lang.Boolean");
                             break;        
                 }

           } catch (ClassNotFoundException ex) {
               System.out.println("Error debido a clase no encontrada en obtener Class");;
           }

        //     classADevolver = getValueAt(0,columna).getClass();     Se podría devolver directamente en el caso de que el JTable nunca estuviese sin filas.     
        return classADevolver;
    }
    
}
