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
public class GeneroControlador {
    DataBaseManager db;
    
    public GeneroControlador (){
        db = new DataBaseManager();
    }
    
    public ResultSet listarGeneros()
    {
        ResultSet listado= db.listarGeneros();
        return listado;
    }
    
    public int devolverCodigoGenero(String genero)
    {
        return db.buscarGenero(genero);
    }
}
