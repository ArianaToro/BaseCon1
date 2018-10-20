package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Propietario_Tiene_Inmueble {
    
    private Integer Cedula;
    private int id;
    private java.sql.Date Fecha_de_Adquisicion;
    private IntegerProperty n_casa;
    private Integer id_terraza;
    
    public Propietario_Tiene_Inmueble ( Integer Cedula , Date Fecha_de_Adqusicion , Integer n_casa , Integer id_terraza ) {
        
        this.Cedula = Cedula;
        this.Fecha_de_Adquisicion = ( java.sql.Date ) Fecha_de_Adqusicion;
        this.n_casa = new SimpleIntegerProperty ( n_casa );
        this.id_terraza = id_terraza ;
        
    }
    
    public Propietario_Tiene_Inmueble ( int id , Integer casa , Integer terraza ) {
    
        this.id = id;
        this.n_casa = new SimpleIntegerProperty (casa);
        this.id_terraza =  terraza ;
        
    }
    
    public Propietario_Tiene_Inmueble ( Integer cedula , Integer id ) {
    
        this.Cedula = cedula;
        this.id = id;
        
    }
    
    public Propietario_Tiene_Inmueble ( TextField cedula , String text ) {
    }
    
    public Propietario_Tiene_Inmueble ( ) {
    
    }
    
    public Propietario_Tiene_Inmueble(Integer numero_casa, String id_terraza) {
        
        this.n_casa=new SimpleIntegerProperty(numero_casa);
        this.id_terraza= Integer.valueOf(id_terraza);
        
    }
    
    public Integer getCedula ( ) {
        return Cedula;
    }
    
    public void setCedula ( Integer cedula ) {
        Cedula = cedula;
    }
    
    public void setFecha_de_Adquisicion ( java.sql.Date fecha_de_Adquisicion ) {
        Fecha_de_Adquisicion = fecha_de_Adquisicion;
    }
    
    public int getId ( ) {
        return id;
    }
    
    public void setId ( int id ) {
        this.id = id;
    }
    
    public Date getFecha_de_Adquisicion ( ) {
        return Fecha_de_Adquisicion;
    }
    
    public void setFecha_de_Adquisicion ( Date fecha_de_Adquisicion ) {
        Fecha_de_Adquisicion = ( java.sql.Date ) fecha_de_Adquisicion;
    }
    
    public int getN_casa ( ) {
        return n_casa.get ( );
    }
    
    public IntegerProperty n_casaProperty ( ) {
        return n_casa;
    }
    
    public void setN_casa ( int n_casa ) {
        this.n_casa.set ( n_casa );
    }
    
    public Integer getId_terraza ( ) {
        return id_terraza;
    }
    
    public void setId_terraza ( Integer id_terraza ) {
        this.id_terraza = id_terraza;
    }
    
    public void Direccion_de_las_propiedades ( Connection Conexion , String Cedula , ObservableList Direccion, ObservableList id_Casa ) {
        
        PreparedStatement ps;
        PreparedStatement ps1;
        ResultSet res;
        ResultSet res1;
        
        String sql = ( "SELECT id_casa FROM propietario_tiene_inmueble WHERE cedula_propietario = ? AND habilitado = 'SI'" );
        String sql1 = ( "SELECT A1.id_casa, A1.n_casa FROM inmueble A1 INNER JOIN inmueble_esta_ubicado_en_terraza A2 on A2.id_casa = A1.id_casa INNER JOIN terraza A3 on A3.id_terraza = A2.id_terraza WHERE A2.id_casa = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1 , Cedula );
            res = ps.executeQuery ( );
            
            while ( res.next ( ) ) {
                
                int id_casa = res.getInt ( 1 );
                ps1 = Conexion.prepareStatement ( sql1 );
                ps1.setInt ( 1 , id_casa );
                res1 = ps1.executeQuery ( );
                while ( res1.next ( ) ) {
                    
                    int casa = res1.getInt ( 2 );
                    Direccion.add ( casa );
                    id_Casa.add ( res1.getInt ( 1 ) );
                    
                }
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public int Verificar_si_el_inmueble_ingresado_ya_tiene_dueño ( Connection Conexion ) {
    
        PreparedStatement ps;
        ResultSet res;
    
        String sql = ( "SELECT A2.id_casa, A1.n_casa, A2.id_terraza, A5.cedula_persona FROM inmueble A1 inner join inmueble_esta_ubicado_en_terraza A2 on A1.id_casa = A2.id_casa inner join terraza A3 on A2.id_terraza = A3.id_terraza  inner join propietario_tiene_inmueble A4 on A2.id_casa = A4.id_casa inner join propietario A5 on A5.cedula_persona = A4.cedula_propietario WHERE A1.n_casa = ? AND A3.id_terraza = ? AND A4.habilitado = ?" );
    
        try {
        
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , n_casa.get ( ) );
            ps.setInt ( 2 , id_terraza );
            ps.setString ( 3, "SI" );
            res = ps.executeQuery ( );
        
            if ( res.next ( ) ) {
            
                return 1;
            
            }
        
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public int Buscar_ID_del_Inmueble ( Connection Conexion ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        try {
            
            ps = Conexion.prepareStatement ( "SELECT A2.id_casa FROM inmueble A1 inner join inmueble_esta_ubicado_en_terraza A2 on A1.id_casa = A2.id_casa inner join terraza A3 on A2.id_terraza = A3.id_terraza  WHERE A1.n_casa = ? AND A3.id_terraza = ?" );
            ps.setInt ( 1 , n_casa.get ( ) );
            ps.setInt ( 2 , id_terraza );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                int id = res.getInt ( 1 );
                JOptionPane.showMessageDialog ( null , " ID: " + id );
                return id;
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public java.sql.Date Fecha_De_Adquisicion ( Connection Conexion , Integer cedula , Integer Id_casa ) {
    
        PreparedStatement ps;
        ResultSet res;
    
        String sql = ( "SELECT fecha_de_adquisicion FROM propietario_tiene_inmueble WHERE cedula_propietario = ? AND id_casa = ?" );
    
        try {
        
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , cedula );
            ps.setInt ( 2 , Id_casa );
            res = ps.executeQuery ( );
        
            if ( res.next ( ) ) {
            
                return res.getDate ( 1 );
            
            }
        
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return null;
    }
    
    public java.sql.Date Fecha_De_Adquisicion_Para_Cuota_Extraordinaria ( Connection Conexion , Integer cedula , Integer Id_casa ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT fecha_de_adquisicion FROM propietario_tiene_inmueble WHERE cedula_propietario = ? AND id_casa = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , cedula );
            ps.setInt ( 2 , Id_casa );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return res.getDate ( 1 );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return null;
    }
    
    public int Llenar_Campos ( Connection Conexion , Integer cedula , TextField primer_nombre , TextField segundo_nombre , TextField primer_apellido , TextField segundo_apellido , ComboBox sexo , ComboBox nacionalidad , ComboBox estado_civil , DatePicker fecha_de_nacimiento ) {
    
        PreparedStatement ps;
        ResultSet res;
    
        String sql = ( "SELECT \n" +
                "  \n" +
                "    A1.primer_nombre,\n" +
                "    A1.segundo_nombre,\n" +
                "    A1.primer_apellido,\n" +
                "    A1.segundo_Apellido,\n" +
                "    A1.sexo,\n" +
                "    A1.nacionalidad,\n" +
                "    A1.estado_civil,\n" +
                "    A1.fecha_de_nacimiento\n" +
                
                "FROM\n" +
                "    persona A1\n" +
                "        INNER JOIN\n" +
                "    propietario A2 ON A1.cedula = A2.cedula_persona\n" +
                "        INNER JOIN\n" +
                "    propietario_tiene_inmueble A3 ON A2.cedula_persona = A3.cedula_propietario\n" +
                "        INNER JOIN\n" +
                "    inmueble A4 ON A3.id_casa = A4.id_casa\n" +
                "        INNER JOIN\n" +
                "    inmueble_esta_ubicado_en_terraza A5 ON A4.id_casa = A5.id_casa\n" +
                "        INNER JOIN\n" +
                "    terraza A6 ON A5.id_terraza = A6.id_terraza\n" +
                "WHERE\n" +
                "\n" +
                "    A1.cedula = ? " );
        
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
            
                return 1;
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public int Registrar ( Connection Conexion ) {
    
        PreparedStatement ps;
    
        try {
        
            int id = Buscar_ID_del_Inmueble ( Conexion );
            ps = Conexion.prepareStatement ( "INSERT INTO propietario_tiene_inmueble(cedula_propietario,id_casa,fecha_de_adquisicion, habilitado) VALUES (?,?,?,?)" );
            ps.setInt ( 1 , Cedula );
            ps.setInt ( 2 , id );
            ps.setDate ( 3 , Fecha_de_Adquisicion );
            ps.setString ( 4, "SI" );
        
            return ps.executeUpdate ( );
        
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public int Deshabilitar_Propietario ( Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE propietario_tiene_inmueble SET habilitado = ? WHERE cedula_propietario = ? AND id_casa = ?");
        
        try{
        
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, "NO" );
            ps.setInt (2, Cedula  );
            ps.setInt ( 3, id );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public void Llenar_Tabla_Propiedades ( Connection Conexion, Integer cedula, ObservableList lista ) {
    
        try{
        
            PreparedStatement ps = Conexion.prepareStatement ("SELECT A3.id_casa, A4.n_casa, A6.id_terraza FROM persona A1 INNER JOIN  propietario A2 ON A1.cedula = A2.cedula_persona INNER JOIN propietario_tiene_inmueble A3 ON A2.cedula_persona = A3.cedula_propietario INNER JOIN inmueble A4 ON A3.id_casa = A4.id_casa INNER JOIN inmueble_esta_ubicado_en_terraza A5 ON A4.id_casa = A5.id_casa INNER JOIN terraza A6 ON A5.id_terraza = A6.id_terraza WHERE A1.cedula = ? AND A3.habilitado = ?");
            
            ps.setInt ( 1, cedula );
            ps.setString ( 2, "SI" );
            ResultSet res = ps.executeQuery();
        
            while(res.next()){
            
                lista.add(new Propietario_Tiene_Inmueble (res.getInt(1),res.getInt (2),res.getInt(3)));
            
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    }
