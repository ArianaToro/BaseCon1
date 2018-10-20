package src.sample.Modelo;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.sql.*;

public class Propietario_Paga_Cuota_Extraordinaria {
    
    private Integer Cedula_Propietario;
    private Integer Id_CuotaExtraordinaria;
    private Date Fecha_de_CuotaExtraordinaria;
    private Long Monto;
    private Integer Id_casa;
    private Integer n_casa;
    private Integer Id_Terraza;
    
    public Propietario_Paga_Cuota_Extraordinaria ( int cedula_Propietario , Integer n_casa , int n_terraza , Integer id_casa ) {
        
        this.Cedula_Propietario = cedula_Propietario;
        this.n_casa = n_casa;
        this.Id_Terraza = n_terraza;
        this.Id_casa = id_casa;
        
    }
    
    public Propietario_Paga_Cuota_Extraordinaria ( ) {
    
    }
    
    public Propietario_Paga_Cuota_Extraordinaria ( Date fechaAlicuota , long MontoAlicuota ) {
        
        this.Fecha_de_CuotaExtraordinaria = fechaAlicuota;
        this.Monto = MontoAlicuota = MontoAlicuota;
        
    }
    
    public Integer getId_CuotaExtraordinaria ( ) {
        return Id_CuotaExtraordinaria;
    }
    
    public void setId_CuotaExtraordinaria ( Integer id_CuotaExtraordinaria ) {
        Id_CuotaExtraordinaria = id_CuotaExtraordinaria;
    }
    
    public Integer getCedula_Propietario ( ) {
        return Cedula_Propietario;
    }
    
    public void setCedula_Propietario ( Integer cedula_Propietario ) {
        Cedula_Propietario = cedula_Propietario;
    }
    
    public Date getFecha_de_CuotaExtraordinaria ( ) {
        return Fecha_de_CuotaExtraordinaria;
    }
    
    public void setFecha_de_CuotaExtraordinaria ( Date fecha_de_CuotaExtraordinaria ) {
        Fecha_de_CuotaExtraordinaria = fecha_de_CuotaExtraordinaria;
    }
    
    public Long getMonto ( ) {
        return Monto;
    }
    
    public void setMonto ( Long monto ) {
        Monto = monto;
    }
    
    public Integer getId_casa ( ) {
        return Id_casa;
    }
    
    public void setId_casa ( Integer id_casa ) {
        Id_casa = id_casa;
    }
    
    public Integer getN_casa ( ) {
        return n_casa;
    }
    
    public void setN_casa ( Integer n_casa ) {
        this.n_casa = n_casa;
    }
    
    public Integer getId_Terraza ( ) {
        return Id_Terraza;
    }
    
    public void setId_Terraza ( Integer id_Terraza ) {
        Id_Terraza = id_Terraza;
    }
    
    
    
    public void CuotasAPagar ( Connection Conexion , Date maxfecha_en_que_pago , ObservableList < Integer > id_cuota , ObservableList < Date > fechas , ObservableList < Long > montos , ObservableList cuotas_extraordinarias , int i , Label deuda , ObservableList total ) {
        
        PreparedStatement ps;
        ResultSet res;
        long Total = 0;
        int Contador = 0;
        
        String sql = ( "SELECT id_cuota_e, fecha, costo FROM cuota_extraordinaria WHERE fecha > ?" );
        
        try {
            
            deuda.setText ( " " );
            ps = Conexion.prepareStatement ( sql );
            ps.setDate ( 1 , maxfecha_en_que_pago );
            res = ps.executeQuery ( );
            
            while ( res.next ( ) ) {
                
                cuotas_extraordinarias.addAll ( res.getDate ( 2 ) + "         " + res.getInt ( 3 ) + " bs" );
                id_cuota.add ( res.getInt ( 1 ) );
                fechas.add ( res.getDate ( 2 ));
                montos.add ( res.getLong ( 3 )/i );
                Contador++;
                Total += (res.getLong ( 3 )/i);
                deuda.setText ( "  DEBE " + String.valueOf ( Contador ) + " CUOTAS." );
                
            }
            
            total.add ( Total );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public void CuotasAPagarSIUltimaFechaNula ( Connection Conexion , Date maxfecha_en_que_pago , ObservableList < Integer > id_cuota , ObservableList < Date > fechas , ObservableList < Long > montos , ObservableList cuotas_extraordinarias , int i , Label deuda , ObservableList total ) {
        
        PreparedStatement ps;
        ResultSet res;
        int Contador = 0;
        long Total = 0;
        
        String sql = ( "SELECT id_cuota_e, fecha , costo FROM cuota_extraordinaria WHERE fecha = ? OR fecha > ?" );
        
        try {
            
            deuda.setText ( " " );
            ps = Conexion.prepareStatement ( sql );
            ps.setDate ( 1 , maxfecha_en_que_pago );
            ps.setDate ( 2 , maxfecha_en_que_pago );
            res = ps.executeQuery ( );
            
            while ( res.next ( ) ) {
                
                cuotas_extraordinarias.addAll ( res.getDate ( 2 ) + "         " + res.getInt ( 3 ) + " bs" );
                id_cuota.add ( res.getInt ( 1 ) );
                fechas.add ( res.getDate ( 2 ) );
                montos.add ( res.getLong ( 3 )/i );
                Contador++;
                Total += (res.getLong ( 3 )/i);
                deuda.setText ( "  DEBE " + String.valueOf ( Contador ) + " CUOTAS." );
            }
            
            total.add ( Total );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public int Deuda ( Connection Conexion , Date maxCuotaPagada ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT sum(costo) FROM cuota_extraordinaria  WHERE fecha = ? OR fecha > ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setDate ( 1 , maxCuotaPagada );
            ps.setDate ( 2 , maxCuotaPagada );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return res.getInt ( 1 );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public void HistorialAlicuotaExtraordinaria(Connection Conexion, ObservableList ListaHistorial, int n_casa, int id_terraza){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("select fecha_pago, monto from propietario_puede_pagar_cuota_extraordinaria where n_casa = ? AND n_terraza = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt (1, n_casa );
            ps.setInt ( 2, id_terraza);
            res = ps.executeQuery ();
            
            while(res.next ()){
                
                ListaHistorial.add ( new Propietario_Paga_Cuota_Extraordinaria ( res.getDate ( 1 ), res.getLong ( 2 ) ) );
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public Date UltimaCuotaPagada ( Connection Conexion , Integer Cedula , Integer n_Casa, Integer id_terraza ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ( "SELECT max(fecha_pago) FROM propietario_puede_pagar_cuota_extraordinaria WHERE cedula_propietario = ? AND n_casa = ? AND n_terraza = ?" );
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula );
            ps.setInt ( 2 , n_Casa );
            ps.setInt ( 3, id_terraza );
            res = ps.executeQuery ( );
            
            if ( res.next ( ) ) {
                
                return res.getDate ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return null;
    }
    
    public int Registrar ( Connection Conexion , Integer id_cuota , Date listaDeCuotas , Long montos ) {
        
        PreparedStatement ps;
        
        
        String sql = ( "INSERT INTO propietario_puede_pagar_cuota_extraordinaria(cedula_propietario,id_cuota_e,fecha_pago,monto,id_casa, n_casa,n_terraza) VALUES (?,?,?,?,?,?,?)" );
        
        try {
            
            Id_CuotaExtraordinaria = id_cuota;
            Fecha_de_CuotaExtraordinaria = listaDeCuotas;
            Monto = montos;
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1 , Cedula_Propietario );
            ps.setInt ( 2 , Id_CuotaExtraordinaria );
            ps.setDate ( 3 , Fecha_de_CuotaExtraordinaria );
            ps.setLong ( 4 , Monto );
            ps.setInt ( 5 , Id_casa );
            ps.setInt ( 6 , n_casa );
            ps.setInt ( 7 , Id_Terraza );
            
            
            return ps.executeUpdate ( );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
}
