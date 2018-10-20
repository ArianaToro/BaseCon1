package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.sql.*;
import java.time.LocalDate;


public class Cuota_Ordinaria {

    private int Id_CuotaOrdinaria;
    private Date Fecha_Establecida;
    private Integer Monto_Establecido;
    private Integer Codigo_Condominio = 1;
    private IntegerProperty Dia_de_cada_mes;
    private IntegerProperty Cada_numero_de_mes;
    
    public Cuota_Ordinaria( Integer Id_CuotaOrdinaria, Date Fecha_Establecida, Integer Monto_Establecido){


        this.Id_CuotaOrdinaria = Id_CuotaOrdinaria;
        this.Fecha_Establecida = Fecha_Establecida;
        this.Monto_Establecido = Monto_Establecido;
        
    }
    
    public Cuota_Ordinaria(Integer dia, Integer mes) {
        
        this.Dia_de_cada_mes = new SimpleIntegerProperty(dia);
        this.Cada_numero_de_mes = new SimpleIntegerProperty(mes);
        
    }
    
    public Cuota_Ordinaria ( ) {
    
    }
    
    public int getId_CuotaOrdinaria() {
        return Id_CuotaOrdinaria;
    }

    public void setId_CuotaOrdinaria(Integer id_CuotaOrdinaria) {
        Id_CuotaOrdinaria = id_CuotaOrdinaria;
    }

    public Integer getMonto_Establecido() {
        return Monto_Establecido;
    }

    public void setMonto_Establecido(Integer monto_Establecido) {
        Monto_Establecido = monto_Establecido;
    }

    public Integer getCodigo_Condominio() {
        return Codigo_Condominio;
    }

    public void setCodigo_Condominio(Integer codigo_Condominio) {
        Codigo_Condominio = codigo_Condominio;
    }

    public int getDia_de_cada_mes() {
        return Dia_de_cada_mes.get();
    }

    public IntegerProperty dia_de_cada_mesProperty() {
        return Dia_de_cada_mes;
    }

    public void setDia_de_cada_mes(int dia_de_cada_mes) {
        this.Dia_de_cada_mes.set(dia_de_cada_mes);
    }

    public int getCada_numero_de_mes() {
        return Cada_numero_de_mes.get();
    }

    public IntegerProperty cada_numero_de_mesProperty() {
        return Cada_numero_de_mes;
    }

    public void setCada_numero_de_mes(int cada_numero_de_mes) {
        this.Cada_numero_de_mes.set(cada_numero_de_mes);
    }

    public Date getFecha_Establecida() {
        return Fecha_Establecida;
    }

    public void setFecha_Establecida(Date fecha_Establecida) {
        Fecha_Establecida = fecha_Establecida;
    }
    
   
    
    public int Verificar_Existencia_de_Cuota(Connection Conexion, Date Fecha_Actual) {
        
        PreparedStatement ps;
        ResultSet res;
        
        try {
            
            Fecha_Establecida = Fecha_Actual;
            ps = Conexion.prepareStatement("SELECT id_cuota FROM cuota_ordinaria WHERE fecha_establecida = ?");
            ps.setDate(1, Fecha_Establecida);
            res = ps.executeQuery();
            
            if(res.next ()) {
                
                return res.getInt ( 1 );
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
        
    }
    
        public java.util.Date Ultima_Cuota( Connection Conexion, java.util.Date fecha, DatePicker aviso, AnchorPane Mensaje ){
        
          PreparedStatement ps;
          ResultSet res;
          
          String sql = ("SELECT max(fecha_establecida) FROM cuota_ordinaria");
          
          try{
              
              ps = Conexion.prepareStatement ( sql );
              res = ps.executeQuery ();
              
              if(res.next ()) {
    
                  java.util.Date Ultima_Cuota = res.getDate ( 1 );
                  
                  if ( Ultima_Cuota == null ) {
        
                          return Ultima_Cuota;
        
                      }else{
    
                      if ( fecha.before ( Ultima_Cuota ) ){
                         
                            aviso.setValue ( null );
                            Mensaje.setVisible ( true );
                            aviso.setPromptText ( "FECHA NO ADMITIDA" );
                            
                         }else{
                          
                           return Ultima_Cuota;
                           
                         }
                      }
                  }
          } catch ( SQLException e ) {
              e.printStackTrace ( );
          }
          return null;
        }
    
    public float Diferencia_entre_Fechas(Connection Conexion, Date fecha_Establecida){
        
        PreparedStatement ps;
        ResultSet res;
        
        try{
            
    
            ps = Conexion.prepareStatement("SELECT (datediff(?,max(fecha_establecida))*0.0329) from cuota_ordinaria");
            ps.setDate ( 1, fecha_Establecida );
            res = ps.executeQuery();
            
            if(res.next()){
                
                return res.getFloat (1);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
        
        public int BuscarUltimoIddeCuotaOrdnaria(Connection Conexion){

        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT max(id_cuota) FROM cuota_ordinaria");

        try {

            ps = Conexion.prepareStatement(sql);
            res = ps.executeQuery();

            if(res.next()){

                return res.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public Date UltimaFechaEstablecida( Connection Conexion){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT max(fecha_establecida) FROM cuota_ordinaria");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getDate ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
        return null;
    }
    
    
    public int Registrar(Connection Conexion,Date Fecha_Actual) {
    
        PreparedStatement ps;
    
        try {
            
                Fecha_Establecida = Fecha_Actual;
                Id_CuotaOrdinaria = BuscarUltimoIddeCuotaOrdnaria ( Conexion );
                Id_CuotaOrdinaria++;
    
                Servicios s = new Servicios ( );
                Monto_Establecido = s.CalcularTotal ( Conexion );
    
                ps = Conexion.prepareStatement ( "INSERT INTO cuota_ordinaria(id_cuota,fecha_establecida,monto_establecido,codigo_c) VALUES (?,?,?,?)" );
                ps.setInt ( 1 , Id_CuotaOrdinaria );
                ps.setDate ( 2 , Fecha_Establecida );
                ps.setInt ( 3 , Monto_Establecido );
                ps.setInt ( 4 , Codigo_Condominio );
    
    
                return ps.executeUpdate ( );
                
        } catch ( SQLException e ) {
            e.printStackTrace ( );
            return 0;
        }
        
    }
    
    public  void LLenarTablaDeCuotaOrdinaria(Connection Conexion, ObservableList CuotaOrdinaria){

        try{

            Statement ps = Conexion.createStatement();
            ResultSet res = ps.executeQuery("SELECT id_cuota, fecha_establecida, monto_establecido FROM cuota_ordinaria");

            while(res.next()){
                
                CuotaOrdinaria.add(new Cuota_Ordinaria (res.getInt(1),res.getDate(2),res.getInt(3)));
                
    }
        }
        catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
}
