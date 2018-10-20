package src.sample.Modelo;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persona_Puede_Poseer_Telefono {
    
    private Integer Cedula;
    private int id;
    
    public Persona_Puede_Poseer_Telefono ( Integer cedula ) {
        
        this.Cedula = cedula;
    }
    
    public Persona_Puede_Poseer_Telefono ( ) {
    
    }
    
    public Integer getCedula ( ) {
        return Cedula;
    }
    
    public void setCedula ( Integer cedula ) {
        Cedula = cedula;
    }
    
    public int getId ( ) {
        return id;
    }
    
    public void setId ( int id ) {
        this.id = id;
    }
    
    public int Buscar_Telefono( Connection Conexion, ObservableList<Integer> ids){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT id_telefono FROM persona_puede_poseer_telefono WHERE cedula = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cedula );
            res = ps.executeQuery ();
            
            while (res.next ()){
                
                ids.add (  res.getInt ( 1 ));
                
            }
        } catch ( SQLException e ) {
            
            e.printStackTrace ();
            
        }
        return 0;
    }
    
    public int Buscar_Ultimo_Registro ( Connection Conexion ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = "SELECT max(id_telefono) FROM telefono";
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return res.getInt ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public void Buscar_Numero ( Connection Conexion , Integer cedula , ObservableList < String > prefijo_telefonico , ObservableList numero , ComboBox < String > prefijo_telefonico1 , ComboBox < String > prefijo_telefonico2 , TextField numero1 , TextField numero2 ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT A3.prefijo_telefonico, A3.numero FROM persona A1 INNER JOIN persona_puede_poseer_telefono A2 ON A1.cedula = A2.cedula INNER JOIN telefono A3 ON A2.id_telefono = A3.id_telefono WHERE A2.cedula = ?" );
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , cedula );
            res = ps.executeQuery ( );
            
            while ( res.next ( ) ) {
                
                prefijo_telefonico.add ( res.getString ( 1 ) );
                numero.add ( res.getString ( 2 ) );
                
            }
            
            prefijo_telefonico1.setValue ( prefijo_telefonico.get ( 0 ) );
            prefijo_telefonico2.setValue ( prefijo_telefonico.get ( 1 ) );
            numero1.setText ( String.valueOf ( numero.get ( 0 ) ) );
            numero2.setText ( String.valueOf ( numero.get ( 1 ) ) );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public int Registrar ( Connection Conexion ) {
        
        
        PreparedStatement ps;
        
        String sql = "INSERT INTO persona_puede_poseer_telefono(cedula,id_telefono) VALUES (?,?)";
        
        try {
            
            id = Buscar_Ultimo_Registro ( Conexion );
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula );
            ps.setInt ( 2 , id );
            
            return ps.executeUpdate ( );
            
        } catch ( SQLException e ) {
            System.out.println ( e );
            return 0;
        }
    }
    
}

