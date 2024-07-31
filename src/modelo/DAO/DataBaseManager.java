/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author karen
 */
public class DataBaseManager {
    Statement statement;
    Connection con;
    
    public void conectar()
    {
        try 
        {
            if(con!=null)
            {
                System.out.println ("ya existe la conexion");
            }
            else
            {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria","root","Km/op2004");
                statement= con.createStatement();
                System.out.println ("conexion exitosa");
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean agregarLibro(int codLibro, String titulo, String fechaPublicacion, double precio, int codGenero)
    {
        conectar();
        try 
        {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("INSERT INTO Libro VALUES (?,?,?,?,?)");
            
            //seteo los parámetros
            sentencia.setInt(1, codLibro);
            sentencia.setString(2, titulo);
            sentencia.setString(3, fechaPublicacion);
            sentencia.setDouble(4, precio);
            sentencia.setInt(5, codGenero);
            
            //ejecuto el query
            sentencia.executeUpdate();
            
            return true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ResultSet listarLibros()
    {
        conectar();
        
        try 
        {
            ResultSet listado=statement.executeQuery("SELECT l.codigoLibro, l.titulo, l.fechaPublicacion, l.precio, g.descripcion FROM Libro l JOIN Genero g ON l.codigoGenero = g.codigoGenero");
            
            return listado;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int eliminarLibro(int codLibro)
    {
        conectar();
        try 
        {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("DELETE FROM Libro WHERE codigoLibro= ?");
            //seteo el parámetro
            sentencia.setInt(1, codLibro);
            //ejecuto el query
            return sentencia.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public boolean modificarLibro(int codLibro, String titulo, String fechaPublicacion, double precio, int codGenero)
    {
        conectar();
        try {
            //creo el query
            PreparedStatement sentencia=con.prepareStatement("UPDATE Libro SET titulo=?, fechaPublicacion=?, precio=?, codigoGenero=? WHERE codigoLibro=?");
            //seteo el parámetro
            sentencia.setString(1, titulo);
            sentencia.setString(2, fechaPublicacion);
            sentencia.setDouble(3, precio);
            sentencia.setInt(4, codGenero);
            sentencia.setInt(5, codLibro);
            //ejecuto el query
            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
            
    }
    
    public ResultSet buscarLibroPorCodigo(int codigoLibro) {
        conectar();
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        String sql = "SELECT l.codigoLibro, l.titulo, l.fechaPublicacion, l.precio, l.codigoGenero, g.descripcion AS genero " +
                     "FROM Libro l " +
                     "JOIN Genero g ON l.codigoGenero = g.codigoGenero " +
                     "WHERE l.codigoLibro = ?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codigoLibro);
            rs = pstmt.executeQuery();

            // No cerramos la conexión ni el statement aquí porque necesitamos mantener el ResultSet abierto

        } catch (SQLException e) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, "Error al buscar libro", e);
            
        }

        return rs;
    }

    
    public ResultSet listarGeneros()
    {
        conectar();
        
        try 
        {
            ResultSet listado=statement.executeQuery("SELECT descripcion FROM Genero");
            return listado;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int buscarGenero(String genero){
        conectar();
        int codigo = -1;
        try (PreparedStatement pstmt = con.prepareStatement("SELECT codigoGenero FROM Genero WHERE descripcion = ?")) {
            pstmt.setString(1, genero);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    codigo = rs.getInt("codigoGenero");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codigo;
    }
}
