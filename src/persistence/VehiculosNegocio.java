/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.Connection;
import java.util.List;
import model.Categoria;
import model.Vehiculo;

/**
 *
 * @author jtech
 */
public class VehiculosNegocio {
    
 

    public String[] consultarCategorias(BaseDatos baseDatos) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        List<Categoria> listaCategorias = null;
        String[] categorias = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                              
            listaCategorias = new VehiculosDatos().consultarCategorias(connection);
            categorias = new String[listaCategorias.size()];
            for (int i=0; i<listaCategorias.size(); i++)
            {
               Categoria categoria = listaCategorias.get(i);
               categorias[i] = categoria.getCodigo()+"  -  "+categoria.getDescripcion();
            }
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return categorias;                                 
     }
    
    
    public List<Vehiculo> consultarTodos(BaseDatos baseDatos, int criterioOrdenacion, String genero, LimitesListado limitesListado) throws Exception
     { 
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        List<Vehiculo> listaVehiculos = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                              
            listaVehiculos = new VehiculosDatos().consultarTodos(connection, criterioOrdenacion, genero, limitesListado);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return listaVehiculos;                                 
     }
    
    
    public void eliminar(BaseDatos baseDatos, Vehiculo vehiculo) throws Exception{
            Connection connection = null;
            ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
      try {         
            connection = conexionBaseDatos.abrirConexion(baseDatos);  
            new VehiculosDatos().eliminar(connection, vehiculo);
          } catch (Exception excepcion)
              { 
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }                                
        } 
    
    
    public Vehiculo consultarPorIdVehiculo(BaseDatos baseDatos, Vehiculo vehiculo) throws Exception
    {
            Connection connection = null;
            Vehiculo vehiculoObtenido = null;
            ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();      
      try {         
            connection = conexionBaseDatos.abrirConexion(baseDatos);  
            vehiculoObtenido = new VehiculosDatos().consultarPorIdVehiculo(connection, vehiculo);
          } catch (Exception excepcion)
              { 
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }     

        return vehiculoObtenido;
    } 
    
    
    public String insertar(BaseDatos baseDatos, Vehiculo vehiculo) throws Exception{
            Connection connection = null;
            ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
      try {         
            connection = conexionBaseDatos.abrirConexion(baseDatos); 
            new VehiculosDatos().insertar(connection, vehiculo);
          } catch (Exception excepcion)
              { 
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }    

      return vehiculo.getIdVehiculo();
    }
    
    /*actualizar utiliza de la clase vehiculosDatos no solo el metodo actualizar, sino el metodo actualizarColumna, es decir todos los metodos de datos sql relacionados con la actualización*/
    public void actualizar(BaseDatos baseDatos, Vehiculo vehiculo, int actualizaciones) throws Exception{
            Connection connection = null;
            ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
      try {         
            connection = conexionBaseDatos.abrirConexion(baseDatos); 
            if (actualizaciones == -1)
                  new VehiculosDatos().actualizarColumna(connection, vehiculo);
               else
                  new VehiculosDatos().actualizar(connection, vehiculo, actualizaciones);
          } catch (Exception excepcion)
              { 
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }                                
        }
    
    public Integer consultarNumeroFilas(BaseDatos baseDatos) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        Integer numFilas = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                           
            numFilas = new VehiculosDatos().consultarNumeroFilas(connection);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return numFilas;                                 
     }



    
}
