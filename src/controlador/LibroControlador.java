/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.ResultSet;
import modelo.DAO.DataBaseManager;

/**
 *
 * @author karen
 */
public class LibroControlador {
    DataBaseManager db;
    
    public LibroControlador(){
        db = new DataBaseManager();
    }
    
    public boolean agregarLibro(int codLibro, String titulo, String fechaPublicacion, double precio, int codGenero)
    {
        
        boolean respuesta=db.agregarLibro(codLibro, titulo, fechaPublicacion, precio, codGenero);
        if (respuesta)
            return true;
        else
            return false;
    }
    
    public ResultSet listarLibros()
    {
        ResultSet listado= db.listarLibros();
        return listado;
    }
    
    public boolean eliminarLibro(int codLibro)
    {
        int resultado=db.eliminarLibro(codLibro);
        if(resultado==0)
        {
           return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean modificarLibro(int codLibro, String titulo, String fechaPublicacion, double precio, int codGenero)
    {
        boolean resultado=db.modificarLibro(codLibro, titulo, fechaPublicacion, precio, codGenero);
        if(resultado)
            return true;
        else
            return false;
    }
    
    public ResultSet buscarLibro(int codigoLibro){
        return db.buscarLibroPorCodigo(codigoLibro);
    }
}
