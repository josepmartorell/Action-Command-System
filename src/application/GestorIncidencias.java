/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.io.IOException;
import javax.swing.JOptionPane;
import model.Contexto;

/**
 *
 * @author jtech
 */
public class GestorIncidencias {
    
    public void gestionarExcepcion(Exception excepcion, Contexto contexto) {
       
        int codigoError = 0;
        String mensajeError = "";
        
        if (excepcion instanceof GenericaExcepcion)      
         {
              GenericaExcepcion genericaExcepcion = (GenericaExcepcion)excepcion;
              codigoError = genericaExcepcion.getCodigoError();
              
              String mensajesError[] = {"formato fecha erróneo", "año erróneo", "mes erróneo", "día erróneo", "kilometraje erróneo"};
     
              for (int i=1, contadorMensajes=0; i<=16; i*=2, contadorMensajes++)
              {  int codigoErrorFechaParcial = codigoError & i;
                 if (codigoErrorFechaParcial > 0)     
                      mensajeError += mensajesError[contadorMensajes]+"   ";                
              }
                    
              switch (genericaExcepcion.getCodigoError())
              {    
                  case 17: mensajeError = "No puede dejar ningún dato nulo";      
                            break;    
                  case 18: mensajeError = "Debe seleccionar categoría";      
                            break;                               
                  case 20: mensajeError = "No se ha podido conectar: No existe un usuario con este identificador";      
                            break; 
                  case 21: mensajeError = "No se ha podido conectar: Password introducido incorrecto";      
                            break;  
                  case 25: mensajeError = "Se ha producido una situación de error como consecuencia de problemas con la conexión a la BD";
                            break;
                  case 30: mensajeError = "Se ha producido una situación de error en la lectura del fichero XML del repositorio";      
                            break;     
                  case 31: mensajeError = "Se ha producido una situación de error de E/S al intentar acceder al fichero XML del repositorio";       
                            break;  
                  case 40: mensajeError = "Se ha producido un error al intentar insertar actividad de usuario";
                            break; 
                  case 41: mensajeError = "Se ha producido un error al intentar consulatar relación de usuarios con actividad";
                            break; 
                  case 42: mensajeError = "Se ha producido un error al intentar consultar relación de fechas en que se ha producido actividad";
                            break; 
                  case 43: mensajeError = "Se ha producido un error al intentar consultar estadística de actividad";
                            break;               
                  case 50: mensajeError = "Se ha producido un error al intentar insertar vehículo";
                            break; 
                  case 51: mensajeError = "Se ha producido un error al intentar eliminar vehículo";
                            break;  
                  case 52: mensajeError = "Se ha producido un error al intentar actualizar un dato de vehículo";
                            break; 
                  case 53: mensajeError = "Se ha producido un error al intentar actualizar datos de vehículo";
                            break;                                                         
                  case 54: mensajeError = "Se ha producido un error al intentar consultar por identificador de vehículo";
                            break; 
                  case 55: mensajeError = "Se ha producido un error al intentar consultar listado de vehículos";
                            break;  
                  case 56: mensajeError = "Se ha producido un error al intentar página de listado de vehículos";
                            break; 
                  case 57: mensajeError = "Se ha producido un error al intentar consultar número de vehículos registrados";
                            break;                            
                  case 58: mensajeError = "Se ha producido un error al intentar consultar categorías";
                            break;     
                  case 60: mensajeError = "Se ha producido un error al intentar consultar por identificador de usuario";
                            break;
                  case 70: mensajeError = "No se puede generar el documento por ausencia de filas dados los límites establecidos";
                            break;  
                  case 100: mensajeError = "Se ha producido una situación de error en la BD al intentar obtener valor de secuencia vehículos";      
                            break;                             
              }        
         }
        else
         {  if (excepcion instanceof IOException)      
             {
                 mensajeError = "Se ha producido una situación de error de E/S"; 
             }
            else
             {
                 mensajeError = excepcion.getMessage();                
             }
         }
        
        
        JOptionPane.showMessageDialog(null, mensajeError, "ERROR", JOptionPane.ERROR_MESSAGE);  
      
    }
    
}
