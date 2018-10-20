package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Propietario_Puede_Comprar_Articulo {
    
    private IntegerProperty Cedula_Propietario;
    private IntegerProperty Id_Articulo;
    private IntegerProperty Cantidad;
    private IntegerProperty Total;
    private Integer Id_Casa;
    private Integer N_Casa;
    private Integer Id_Terraza;
    
    public Propietario_Puede_Comprar_Articulo(Integer Cedula, Integer Id, Integer Cantidad, Integer Total, Integer Id_casa, Integer n_casa, Integer id_terraza){
        
        this.Cedula_Propietario = new SimpleIntegerProperty ( Cedula );
        this.Id_Articulo = new SimpleIntegerProperty ( Id );
        this.Cantidad = new SimpleIntegerProperty ( Cantidad );
        this.Total = new SimpleIntegerProperty ( Total );
        this.Id_Casa = Id_casa;
        this.N_Casa = n_casa;
        this.Id_Terraza = id_terraza;
        
    }
    
    public Propietario_Puede_Comprar_Articulo ( ) {
    
    }
    
    public Integer getId_Casa ( ) {
        return Id_Casa;
    }
    
    public void setId_Casa ( Integer id_Casa ) {
        Id_Casa = id_Casa;
    }
    
    public Integer getN_Casa ( ) {
        return N_Casa;
    }
    
    public void setN_Casa ( Integer n_Casa ) {
        N_Casa = n_Casa;
    }
    
    public int getTotal ( ) {
        return Total.get ( );
    }
    
    public IntegerProperty totalProperty ( ) {
        return Total;
    }
    
    public void setTotal ( int total ) {
        this.Total.set ( total );
    }
    
    public int getCedula_Propietario ( ) {
        return Cedula_Propietario.get ( );
    }
    
    public IntegerProperty cedula_PropietarioProperty ( ) {
        return Cedula_Propietario;
    }
    
    public void setCedula_Propietario ( int cedula_Propietario ) {
        this.Cedula_Propietario.set ( cedula_Propietario );
    }
    
    public int getId_Articulo ( ) {
        return Id_Articulo.get ( );
    }
    
    public IntegerProperty id_ArticuloProperty ( ) {
        return Id_Articulo;
    }
    
    public void setId_Articulo ( int id_Articulo ) {
        this.Id_Articulo.set ( id_Articulo );
    }
    
    public int getCantidad ( ) {
        return Cantidad.get ( );
    }
    
    public IntegerProperty cantidadProperty ( ) {
        return Cantidad;
    }
    
    public void setCantidad ( int cantidad ) {
        this.Cantidad.set ( cantidad );
    }
    
    public int Buscar_Id_Articulo( Connection Conexion, String nombre_articulo){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT id_articulo FROM articulo WHERE tipo_de_articulo = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, nombre_articulo );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return  res.getInt ( 1 ) ;
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    
    public int Registrar( Connection Conexion ){
    
        PreparedStatement ps;
        
        String sql = ("INSERT INTO propietario_puede_comprar_articulo(cedula, id_articulo, cantidad, total_pagado, id_casa, n_casa, id_terraza) VALUES (?,?,?,?,?,?,?)");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, getCedula_Propietario () );
            ps.setInt ( 2, getId_Articulo () );
            ps.setInt ( 3, getCantidad ());
            ps.setInt ( 4, getTotal () );
            ps.setInt ( 5, Id_Casa );
            ps.setInt ( 6, N_Casa );
            ps.setInt (7, Id_Terraza);
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
}
