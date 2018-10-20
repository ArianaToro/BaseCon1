package src.sample.Modelo;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;

public class Propietario extends Persona {
    
    private int Cantidad_de_casas_que_posee;
    
    public Propietario ( String Nacionalidad , Integer Cedula , String Primer_Nombre , String Segundo_Nombre , String Primer_Apellido , String Segundo_Apellido , Date Fecha_de_Nacimiento , String Sexo , String Estado_Civil , int Cantidad_de_casas ) {
        
        super ( Nacionalidad , Cedula , Primer_Nombre , Segundo_Nombre , Primer_Apellido , Segundo_Apellido , Fecha_de_Nacimiento , Sexo , Estado_Civil );
        this.Cantidad_de_casas_que_posee = Cantidad_de_casas;
        
    }
    
    public Propietario ( ) {
    
    }
    
    public Propietario ( int cedula ) {
        
        this.Cedula = cedula;
        
    }
    
    public int Cantidad_de_casas ( Connection Conexion ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT sum(cantidad_de_casas_que_posee) from propietario where cantidad_de_casas_que_posee > 0" );
        
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
    
    public int Verificar_Existencia_de_Propietario ( Connection Conexion ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT cedula_persona FROM propietario WHERE cedula_persona = ? " );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return 1;
                
            } else {
                
                return 0;
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
            
        }
        
        return 0;
    }
    
    public void Llenar_Campos_Datos_Propietarios ( Connection Conexion , Integer cedula , TextField primer_nombre , TextField segundo_nombre , TextField primer_apellido , TextField segundo_apellido , ComboBox sexo , ComboBox nacionalidad , ComboBox estado_civil , DatePicker fecha_de_nacimiento ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( " SELECT A1.primer_nombre, A1.segundo_nombre, A1.primer_apellido, A1.segundo_Apellido, A1.sexo, A1.nacionalidad, A1.estado_civil, A1.fecha_de_nacimiento FROM persona A1 INNER JOIN propietario A2 ON A1.cedula = A2.cedula_persona WHERE   A1.cedula = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , cedula );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                primer_nombre.setText ( res.getString ( 1 ) );
                segundo_nombre.setText ( res.getString ( 2 ) );
                primer_apellido.setText ( res.getString ( 3 ) );
                segundo_apellido.setText ( res.getString ( 4 ) );
                sexo.setValue ( res.getString ( 5 ) );
                nacionalidad.setValue ( res.getString ( 6 ) );
                estado_civil.setValue ( res.getString ( 7 ) );
                java.sql.Date f = res.getDate ( 8 );
                fecha_de_nacimiento.setValue ( f.toLocalDate ( ) );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public int Registrar ( Connection Conexion ) {
        
        PreparedStatement ps;
        
        String sql = ( "INSERT INTO propietario(cedula_persona,cantidad_de_casas_que_posee) VALUES (?,?)" );
        
        try {
            
            int Verificacion = Verificar_Existencia_de_Persona ( Conexion );
            
            if ( Verificacion == 1 ) {
                
                ps = Conexion.prepareStatement ( sql );
                ps.setInt ( 1 , Cedula );
                ps.setInt ( 2 , 0 );
                
                return ps.executeUpdate ( );
                
            } else {
                
                ps = Conexion.prepareStatement ( sql );
                Registrar_Datos ( Conexion );
                ps.setInt ( 1 , Cedula );
                ps.setInt ( 2 , 0 );
                
                return ps.executeUpdate ( );
                
            }
        } catch ( SQLException e1 ) {
            
            e1.printStackTrace ( );
        }
        
        return 0;
    }
    
    
    public int Cantidad_DeCasas ( Connection Conexion ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        try {
            
            ps = Conexion.prepareStatement ( "SELECT COUNT(id_casa) FROM propietario_tiene_inmueble WHERE cedula_propietario = ? AND habilitado = ?" );
            ps.setInt ( 1 , getCedula ( ) );
            ps.setString ( 2 , "SI" );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return res.getInt ( 1 );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
    
    public int Actualizacion_De_Cantidad_De_Casas ( Connection Conexion ) {
        
        PreparedStatement ps;
        
        try {
            
            Cantidad_de_casas_que_posee = Cantidad_DeCasas ( Conexion );
            ps = Conexion.prepareStatement ( "UPDATE propietario SET cantidad_de_casas_que_posee = ? WHERE cedula_persona = ?" );
            ps.setInt ( 2 , getCedula ( ) );
            ps.setInt ( 1 , Cantidad_de_casas_que_posee );
            
            return ps.executeUpdate ( );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
}