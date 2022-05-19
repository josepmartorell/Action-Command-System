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
        List<Vehiculo> listaLibros = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                              
            listaLibros = new VehiculosDatos().consultarTodos(connection, criterioOrdenacion, genero, limitesListado);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return listaLibros;                                 
     } 



    
}
