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
    
}
