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
    
}
