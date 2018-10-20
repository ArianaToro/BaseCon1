package src.sample.Modelo;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.*;

public class Usuario extends Persona{
    
    private String Nombre_de_Usuario;
    private String Contraseña;
    private String Cargo;
    private String Pregunta_de_Seguridad;
    private String Respuesta_de_Seguridad;
   
    
    public Usuario ( String Nacionalidad, Integer Cedula, String Primer_Nombre, String Segundo_Nombre, String Primer_Apellido, String Segundo_Apellido, Date Fecha_de_Nacimiento, String Sexo, String Estado_Civil, String Nombre_de_Usuario, String Contraseña, String Cargo , String Pregunta_de_Seguridad, String Respuesta_de_Seguridad){
        
        super(Nacionalidad,Cedula,Primer_Nombre,Segundo_Nombre,Primer_Apellido,Segundo_Apellido,Fecha_de_Nacimiento,Sexo,Estado_Civil);
        this.Nombre_de_Usuario = Nombre_de_Usuario;
        this.Contraseña = Contraseña;
        this.Cargo = Cargo;
        this.Pregunta_de_Seguridad = Pregunta_de_Seguridad;
        this.Respuesta_de_Seguridad = Respuesta_de_Seguridad;
        
    }
    
    public Usuario ( int cedula ) {
    
        this.Cedula = cedula;
        
    }
    
    public Usuario ( ) {
    
    }
    
    public Usuario ( String nombre_de_Usuario ) {
        
        this.Nombre_de_Usuario = nombre_de_Usuario;
        
    }
    
    public String Privilegio_Acceso( Connection Conexion, String nombre, String contraseña){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT nombre_usuario, contraseña, cargo FROM usuario WHERE nombre_usuario = ? AND contraseña = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, nombre );
            ps.setString ( 2, contraseña );
            res = ps.executeQuery ();
            
            if(res.next()){
                
                return res.getString ( 3 );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return null;
    }
    
    public int Verificacion_existencia_nombre_de_usuario(Connection Conexion){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT cedula FROM usuario WHERE nombre_de_usuario = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, Nombre_de_Usuario );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return 1;
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
        return 0;
    }
    
    public String Nombre_de_Usuario(Connection Conexion, String Cargo){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("select concat(A1.primer_nombre,\" \", A1.primer_apellido) as Nombre from persona A1 inner join usuario A2 on A1.cedula = A2.cedula where A2.cargo = ? ");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1,Cargo );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getString ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
        return null;
    }
    
    public int Busqueda_Usuario ( Connection Conexion , TextField primer_nombre , TextField segundo_nombre , TextField primer_apellido , TextField segundo_apellido , ComboBox sexo , ComboBox nacionalidad , ComboBox estado_civil , DatePicker fecha_de_nacimiento ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, sexo, nacionalidad, estado_civil, fecha_de_nacimiento FROM persona WHERE cedula = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula );
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
                
                return 1;
                
            }else{
                
                return 0;
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
    
    public int Llenar_Campos_Usuario ( Connection Conexion , TextField primer_nombre , TextField segundo_nombre , TextField primer_apellido , TextField segundo_apellido , ComboBox sexo , ComboBox nacionalidad , ComboBox estado_civil , DatePicker fecha_de_nacimiento, TextField nombre_de_usuario, TextField contraseña_de_usuario, ComboBox<String> cargo, ComboBox<String> pregunta_de_seguridad, TextField respuesta_de_seguridad ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT A1.primer_nombre, A1.segundo_nombre, A1.primer_apellido, A1.segundo_apellido, A1.sexo, A1.nacionalidad, A1.estado_civil, A1.fecha_de_nacimiento, A2.nombre_usuario, A2.contraseña, A2.cargo, A2.pregunta_de_seguridad, A2.respuesta_de_seguridad FROM persona A1 INNER JOIN usuario A2 on A1.cedula = A2.cedula WHERE A2.cedula = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula );
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
                nombre_de_usuario.setText (  res.getString ( 9 ) );
                contraseña_de_usuario.setText ( res.getString ( 10 ) );
                cargo.setValue ( res.getString ( 11 ) );
                pregunta_de_seguridad.setValue ( res.getString ( 12 ) );
                respuesta_de_seguridad.setText ( res.getString ( 13 ) );
                
                return 1;
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
    
    public int Verificar_Existencia_de_Usuario(Connection Conexion) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT cedula FROM usuario WHERE cargo = ?");
        
        try {
            
            ps = Conexion.prepareStatement(sql);
            ps.setString ( 1, Cargo );
            res = ps.executeQuery();
            
            if (res.next()) {
                
                return 1;
                
            } else {
                
                return 0;
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        return 0;
    }
    
    public int Registrar( Connection Conexion ){
    
        PreparedStatement ps;
        
        String sql = ("INSERT INTO usuario(cedula,nombre_usuario,contraseña,cargo,pregunta_de_seguridad,respuesta_de_seguridad, codigo_c) VALUES (?,?,?,?,?,?,?)");
        
        try{
            
            int Verificacion = Verificar_Existencia_de_Persona ( Conexion );
            
            if(Verificacion == 1 ){
    
                ps = Conexion.prepareStatement ( sql );
                ps.setInt ( 1, Cedula );
                ps.setString (2, Nombre_de_Usuario );
                ps.setString ( 3, Contraseña );
                ps.setString ( 4, Cargo );
                ps.setString ( 5, Pregunta_de_Seguridad );
                ps.setString ( 6, Respuesta_de_Seguridad );
                ps.setInt ( 7, 1 );
    
                return ps.executeUpdate ( );
                
            }else {
    
                ps = Conexion.prepareStatement ( sql );
                Registrar_Datos ( Conexion );
                ps.setInt ( 1 , Cedula );
                ps.setString ( 2 , Nombre_de_Usuario );
                ps.setString ( 3 , Contraseña );
                ps.setString ( 4 , Cargo );
                ps.setString ( 5 , Pregunta_de_Seguridad );
                ps.setString ( 6 , Respuesta_de_Seguridad );
                ps.setInt ( 7, 1 );
                
                return ps.executeUpdate ( );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public int Modificar(Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE usuario SET nombre_usuario = ? , contraseña = ?, cargo = ? , pregunta_de_seguridad = ? , respuesta_de_seguridad = ? WHERE cedula = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            Modificar_Datos ( Conexion );
            ps.setString ( 1, Nombre_de_Usuario );
            ps.setString ( 2, Contraseña );
            ps.setString ( 3, Cargo );
            ps.setString ( 4, Pregunta_de_Seguridad );
            ps.setString ( 5, Respuesta_de_Seguridad );
            ps.setInt ( 6, Cedula );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public boolean Eliminar( Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("DELETE FROM usuario WHERE cedula = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cedula );
            
            return ps.execute();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return false;
    }
    
}
