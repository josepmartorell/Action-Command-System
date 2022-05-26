/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import application.GenericaExcepcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Vehiculo;

/**
 *
 * @author jtech
 */
public class VehiculosDatos {
    

    public List<Categoria> consultarCategorias(Connection connection) throws Exception
    {
        List<Categoria> listaCategorias = new ArrayList();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
                String sql = "SELECT * FROM categorias ORDER BY codigo ";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                   Categoria categoria = new Categoria();
                   categoria.setCodigo(resultSet.getString(1));
                   categoria.setDescripcion(resultSet.getString(2));
                   listaCategorias.add(categoria);
                }
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(58);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (statement != null) statement.close();
            }

        return listaCategorias;
    }
    
        public List<Vehiculo> consultarTodos(Connection connection, int criterioOrdenacion, String categoria, LimitesListado limitesListado) throws Exception
    {
        List<Vehiculo> listaVehiculos = new ArrayList();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
                String sql = "SELECT id_vehiculo, modelo, categorias.codigo, categorias.descripcion, fecha_alta, kilometraje_alta, itv FROM categorias INNER JOIN vehiculos ON categorias.codigo = vehiculos.categoria ";

                if (categoria != null)
                    sql += "WHERE categorias.codigo = ? ";

                if (limitesListado != null)
                {
                    if (categoria == null)
                        sql += "WHERE ";
                      else
                        sql += "AND ";

                    switch (criterioOrdenacion)
                    {
                       case 1 : sql += "id_vehiculo >= ? AND id_vehiculo <= ? ";
                                break;
                       case 2 : sql += "modelo >= ? AND modelo <= ? ";
                                break;
                       case 3 : sql += "categoria >= ? AND categoria <= ? ";
                                break;                        
                    }                  
                }

                switch (criterioOrdenacion)
                {
                   case 1 : sql += "ORDER BY id_vehiculo";
                            break;
                   case 2 : sql += "ORDER BY modelo";
                            break;
                   case 3 : sql += "ORDER BY categoria, modelo";
                            break;                        
                }

                preparedStatement = connection.prepareStatement(sql);

                int contadorParametros = 1;
                if (categoria != null)
                {
                    preparedStatement.setString(contadorParametros, categoria);
                    contadorParametros++;
                }

                if (limitesListado != null)
                {
                    preparedStatement.setString(contadorParametros, limitesListado.getLimiteInferior());
                    contadorParametros++;
                    preparedStatement.setString(contadorParametros, limitesListado.getLimiteSuperior());
                    // contadorParametros++;                
                }            

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                   Vehiculo vehiculo = new Vehiculo();
                   vehiculo.setIdVehiculo(resultSet.getString(1));
                   vehiculo.setModelo(resultSet.getString(2));
                   vehiculo.setCategoria(resultSet.getString(3));
                   vehiculo.setDescripcion(resultSet.getString(4));
                   vehiculo.setFechaAlta(resultSet.getDate(5));
                   vehiculo.setKilometrajeAlta(resultSet.getInt(6));
                   if (resultSet.getInt(7) == 1)
                        vehiculo.setItv(true);
                     else
                        vehiculo.setItv(false);
                   listaVehiculos.add(vehiculo);
                }
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(55);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (preparedStatement != null) preparedStatement.close();
            }

        return listaVehiculos;
    }
        
        
    public void eliminar(Connection connection, Vehiculo vehiculo) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {    
                String sql = "DELETE FROM vehiculos WHERE id_vehiculo = CAST(? AS CHAR(7))"; 
                preparedStatement = connection.prepareStatement(sql);                   
                preparedStatement.setString(1, vehiculo.getIdVehiculo()); 
                preparedStatement.executeUpdate();
            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(51);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }
    
    
    public Vehiculo consultarPorIdVehiculo(Connection connection, Vehiculo vehiculo) throws Exception
    {
        Vehiculo vehiculoObtenido = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
                String sql = "SELECT id_vehiculo, modelo, categorias.codigo, categorias.descripcion, fecha_alta, kilometraje_alta, itv FROM categorias INNER JOIN vehiculos ON categorias.codigo = vehiculos.categoria WHERE id_vehiculo = CAST(? AS CHAR(7))";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, vehiculo.getIdVehiculo());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                   vehiculoObtenido = new Vehiculo();
                   vehiculoObtenido.setIdVehiculo(resultSet.getString(1));
                   vehiculoObtenido.setModelo(resultSet.getString(2));
                   vehiculoObtenido.setCategoria(resultSet.getString(3));
                   vehiculoObtenido.setDescripcion(resultSet.getString(4));
                   vehiculoObtenido.setFechaAlta(resultSet.getDate(5));
                   vehiculoObtenido.setKilometrajeAlta(resultSet.getInt(6));
                   byte itv = resultSet.getByte(7);                  
                   if (itv == 1)
                       vehiculoObtenido.setItv(true);
                   else
                       vehiculoObtenido.setItv(false);                               
                }
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(54);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (preparedStatement != null) preparedStatement.close();
            }

        return vehiculoObtenido;
    }

    
    public void insertar(Connection connection, Vehiculo vehiculo) throws Exception
    {   
        PreparedStatement preparedStatement = null;
        try {    
                String sql = "INSERT INTO vehiculos VALUES(?,?,?,?,?,?)"; 
                preparedStatement = connection.prepareStatement(sql); 
                preparedStatement.setString(1, vehiculo.getIdVehiculo());
                preparedStatement.setString(2, vehiculo.getModelo());
                preparedStatement.setString(3, vehiculo.getCategoria());
                preparedStatement.setDate(4, vehiculo.getFechaAlta());
                preparedStatement.setInt(5, vehiculo.getKilometrajeAlta());
                byte itv = 0;
                if (vehiculo.isItv())
                    itv = 1;               
                preparedStatement.setByte(6, itv);
                preparedStatement.executeUpdate();
            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(50);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }
    
    
    // metodo auxiliar del método actualizar
    public String concatenarSeparador(boolean iniciado) {
        String separador = "";
        if (iniciado)
            separador = ", ";
        return separador;
    }
    
    public void actualizar(Connection connection, Vehiculo vehiculo, int actualizaciones) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {    
                boolean iniciado = false;
                String sql = "UPDATE vehiculos SET ";

                for (int i=1; i<=16384; i*=2)
                 { int pesoDelBit = (int) actualizaciones & i;
                   switch (i)
                     { case 1 : if (pesoDelBit > 0)
                                 {  sql += concatenarSeparador(iniciado);                            
                                    sql += "modelo = ?";
                                    iniciado = true;                                                              
                                 }
                                break;
                       case 2 : if (pesoDelBit > 0)
                                 {  sql += concatenarSeparador(iniciado);                            
                                    sql += "categoria = ?";
                                    iniciado = true;                                                              
                                 }
                                break;
                       case 4 : if (pesoDelBit > 0)
                                 {  sql += concatenarSeparador(iniciado);                            
                                    sql += "fecha_alta = ?";
                                    iniciado = true;                                                              
                                 }
                                break;                            
                       case 8 : if (pesoDelBit > 0)
                                 {  sql += concatenarSeparador(iniciado);                            
                                    sql += "kilometraje_alta = ?";
                                    iniciado = true;                                                              
                                 }
                                break;                            
                       case 16 : if (pesoDelBit > 0)
                                 {  sql += concatenarSeparador(iniciado);                            
                                    sql += "itv = ?";
                                    iniciado = true;                                                              
                                 }
                                break;   
                     }
                 }

                sql += " WHERE id_vehiculo = CAST(? AS CHAR(7))";
                System.out.println(sql);                            
                preparedStatement = connection.prepareStatement(sql);   

                int contadorActualizaciones = 1;

                for (int i=1; i<=16384; i*=2)
                 { int pesoDelBit = (int) actualizaciones & i;
                   switch (i)
                     { case 1 : if (pesoDelBit > 0)
                                 {  preparedStatement.setString(contadorActualizaciones, vehiculo.getModelo());
                                    contadorActualizaciones++;                                                              
                                 }
                                break;
                       case 2 : if (pesoDelBit > 0)
                                 {  preparedStatement.setString(contadorActualizaciones, vehiculo.getCategoria());
                                    contadorActualizaciones++;                                                              
                                 }
                                break;
                       case 4 : if (pesoDelBit > 0)
                                 {  preparedStatement.setDate(contadorActualizaciones, vehiculo.getFechaAlta());
                                    contadorActualizaciones++;                                                              
                                 }
                                break;                            
                       case 8 : if (pesoDelBit > 0)
                                 {  preparedStatement.setInt(contadorActualizaciones, vehiculo.getKilometrajeAlta());
                                    contadorActualizaciones++;                                                              
                                 }
                                break;                            
                       case 16 : if (pesoDelBit > 0)
                                 {  byte itv = 0;
                                    if (vehiculo.isItv())
                                        itv = 1;               
                                    preparedStatement.setByte(contadorActualizaciones, itv);
                                    contadorActualizaciones++;                                                              
                                 }
                                break;   
                     }
                 }

                preparedStatement.setString(contadorActualizaciones, vehiculo.getIdVehiculo());            
                preparedStatement.executeUpdate();  
            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(53);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }
    
    
    public void actualizarColumna(Connection connection, Vehiculo vehiculo) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {    
                String sql = "UPDATE vehiculos SET ";
                switch(vehiculo.getColumnaActualizada())
                {  
                   case 0: sql += "id_vehiculo = ?"; 
                           break; 
                   case 1: sql += "modelo = ?"; 
                           break;
                   case 2: sql += "categoria = ?";
                           break;                       
                   case 3: sql += "fecha_alta = ?";
                           break;                        
                   case 4: sql += "kilometraje_alta = ?";
                           break; 
                   case 5: sql += "itv = ?";
                           break;                        
                }

                sql += " WHERE id_vehiculo = CAST(? AS CHAR(7))";

                preparedStatement = connection.prepareStatement(sql);   

                switch(vehiculo.getColumnaActualizada())
                {  case 0: preparedStatement.setString(1, (String)vehiculo.getDatoActualizado());
                           break;
                   case 1: preparedStatement.setString(1, (String)vehiculo.getDatoActualizado());
                           break;
                   case 2: preparedStatement.setString(1, (String)vehiculo.getDatoActualizado());
                           break;    
                   case 3: preparedStatement.setDate(1, java.sql.Date.valueOf(((String)vehiculo.getDatoActualizado()).substring(6, 10) +"-"+ ((String)vehiculo.getDatoActualizado()).substring(3, 5) +"-"+ ((String)vehiculo.getDatoActualizado()).substring(0, 2)));
                           break;                        
                   case 4: preparedStatement.setInt(1, (Integer)vehiculo.getDatoActualizado());
                           break; 
                   case 5: byte itv = 0;
                           if (((Boolean)vehiculo.getDatoActualizado()))
                               itv = 1;  
                           preparedStatement.setByte(1, itv);                         
                           break;                        
                }

                preparedStatement.setString(2, vehiculo.getIdVehiculo());            
                preparedStatement.executeUpdate();  
            } catch (SQLException excepcion) {           
                throw new GenericaExcepcion(52);
            } finally
            {
                if (preparedStatement != null) preparedStatement.close();
            }    
    }
    
    public Integer consultarNumeroFilas(Connection connection) throws Exception
    {
        Integer numFilas = null;
        ResultSet resultSet = null;
        Statement statement = null; 
        String sql = "SELECT COUNT(*) FROM vehiculos";

        try {
                statement = connection.createStatement(); 
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                   numFilas = resultSet.getInt(1);          
                }   
            } catch (SQLException excepcion) {
                throw new GenericaExcepcion(57);
            } finally
            {
                if (resultSet != null) resultSet.close(); 
                if (statement != null) statement.close();
            }

        return numFilas;
    }
    


    
}
