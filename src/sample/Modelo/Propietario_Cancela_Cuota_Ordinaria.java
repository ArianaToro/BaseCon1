package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import java.sql.*;

public class Propietario_Cancela_Cuota_Ordinaria {

     private Integer Cedula_Propietario;
     private Integer Id_Cuota;
     private Date Fecha_en_que_pago;
     private Long Monto;
     private IntegerProperty N_Casa;
     private IntegerProperty Id_Terraza;
     private IntegerProperty Id_Casa;
     
     public Propietario_Cancela_Cuota_Ordinaria( Integer Cedula_Propietario, Integer N_Casa, Integer Id_Terraza, Integer Id_Casa ){
         
         this.Cedula_Propietario = Cedula_Propietario;
         this.N_Casa = new SimpleIntegerProperty ( N_Casa );
         this.Id_Terraza = new SimpleIntegerProperty ( Id_Terraza );
         this.Id_Casa = new SimpleIntegerProperty ( Id_Casa );
         
     }
    
    public Propietario_Cancela_Cuota_Ordinaria ( ) {
    
    }
    
    public Propietario_Cancela_Cuota_Ordinaria ( String text ) {
    }
    
    public Propietario_Cancela_Cuota_Ordinaria ( Date fecha_cuota , long ValorAlicuota ) {
         
         this.Fecha_en_que_pago = fecha_cuota;
         this.Monto = ValorAlicuota ;
         
    }
    
    public Integer getCedula_Propietario ( ) {
        return Cedula_Propietario;
    }
    
    public void setCedula_Propietario ( Integer cedula_Propietario ) {
        Cedula_Propietario = cedula_Propietario;
    }
    
    public Integer getId_Cuota ( ) {
        return Id_Cuota;
    }
    
    public void setId_Cuota ( Integer id_Cuota ) {
        Id_Cuota = id_Cuota;
    }
    
    public Date getFecha_en_que_pago ( ) {
        return Fecha_en_que_pago;
    }
    
    public void setFecha_en_que_pago ( Date fecha_en_que_pago ) {
        Fecha_en_que_pago = fecha_en_que_pago;
    }
    
    public Long getMonto ( ) {
        return Monto;
    }
    
    public void setMonto ( Long monto ) {
        Monto = monto;
    }
    
    public int getN_Casa ( ) {
        return N_Casa.get ( );
    }
    
    public IntegerProperty n_CasaProperty ( ) {
        return N_Casa;
    }
    
    public void setN_Casa ( int n_Casa ) {
        this.N_Casa.set ( n_Casa );
    }
    
    public int getId_Terraza ( ) {
        return Id_Terraza.get ( );
    }
    
    public IntegerProperty id_TerrazaProperty ( ) {
        return Id_Terraza;
    }
    
    public void setId_Terraza ( int id_Terraza ) {
        this.Id_Terraza.set ( id_Terraza );
    }
    
    public int getId_Casa ( ) {
        return Id_Casa.get ( );
    }
    
    public IntegerProperty id_CasaProperty ( ) {
        return Id_Casa;
    }
    
    public void setId_Casa ( int id_Casa ) {
        this.Id_Casa.set ( id_Casa );
    }
    
    public void cbxCuotasAPagarCuandoFechaNula ( Connection Conexion , Integer Cedula , Date maxfecha_en_que_pago , ObservableList < Integer > id_cuota , ObservableList < Date > fechas , ObservableList < Long > montos , ObservableList < Long > total , ObservableList cuotas , int Cantidad_propietarios , Label deuda ){
     
         PreparedStatement ps;
         ResultSet res;
         int Contador = 0;
         long Total = 0;
         
          String sql = ("SELECT id_cuota,fecha_establecida, monto_establecido FROM cuota_ordinaria  WHERE fecha_establecida = ? OR fecha_establecida > ?");
          
          try {
    
              Cedula_Propietario = Cedula;
              ps = Conexion.prepareStatement ( sql );
              ps.setDate ( 1 , maxfecha_en_que_pago );
              ps.setDate ( 2, maxfecha_en_que_pago );
              res = ps.executeQuery ( );
              
              while(res.next ()) {
    
                                  cuotas.addAll ( "         " + res.getDate ( 2 ) + "                " + res.getInt ( 3 ) + " bs" );
                                  fechas.add ( res.getDate ( 2 ) );
                                  montos.add ( res.getLong ( 3 )/Cantidad_propietarios);
                                  id_cuota.add ( res.getInt ( 1 ) );
                                  Contador++;
                                  Total += (res.getLong ( 3 )/Cantidad_propietarios);
                                  deuda.setText ( "  DEBE " + String.valueOf ( Contador ) + " CUOTAS." );
              }
    
              total.add ( Total);
              
     } catch ( SQLException e ) {
              e.printStackTrace ( );
              }
          }
    
    public void cbxCuotasAPagar ( Connection Conexion , Integer Cedula , Date maxfecha_en_que_pago , ObservableList < Integer > id_cuota , ObservableList < Date > fechas , ObservableList < Long > montos , ObservableList < Long > total , ObservableList cuotas, int deuda1 , Label deuda ){
        
        PreparedStatement ps;
        ResultSet res;
        long Total = 0;
        int Contador = 0;
        
        String sql = ("SELECT id_cuota, fecha_establecida, monto_establecido FROM cuota_ordinaria WHERE fecha_establecida > ?");
        
        try {
            
            Cedula_Propietario = Cedula;
            
            ps = Conexion.prepareStatement ( sql );
            ps.setDate ( 1 , maxfecha_en_que_pago );
            res = ps.executeQuery ( );
                
                while(res.next ()) {
    
                    cuotas.addAll ( "         " + res.getDate ( 2 ) + "                " + res.getInt ( 3 ) + " bs" );
                    fechas.add ( res.getDate ( 2 ) );
                    montos.add ( res.getLong ( 3 )/deuda1);
                    id_cuota.add ( res.getInt ( 1 ) );
                    Contador++;
                    Total += (res.getLong ( 3 )/deuda1);
                    deuda.setText ( "  DEBE " + String.valueOf ( Contador ) + " CUOTAS." );
                    
                }
                
                total.add ( Total );
                
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
         public int Deuda( Connection Conexion, Date maxCuotaPagada){
     
         PreparedStatement ps;
         ResultSet res;
         
         String sql = ("SELECT sum(monto_establecido) FROM cuota_ordinaria  WHERE fecha_establecida > ?");
         
         try{
             
             ps = Conexion.prepareStatement ( sql );
             ps.setDate ( 1, maxCuotaPagada );
             res = ps.executeQuery ();
             
             if(res.next ()){
                 
                 return res.getInt ( 1 );
                 
             }
             
         } catch ( SQLException e ) {
             e.printStackTrace ( );
         }
    return 0;
     }
     
     public Date UltimaCuotaPagada( Connection Conexion, Integer Cedula, Integer n_Casa){
          
          PreparedStatement ps;
          ResultSet res;
          
          String sql = ("SELECT max(fecha_en_que_pago) FROM propietario_cancela_cuota_ordinaria WHERE cedula_propietario = ? AND n_casa = ?");
          
          try{
               
               ps = Conexion.prepareStatement ( sql );
               ps.setInt ( 1 , Cedula );
               ps.setInt ( 2 , n_Casa );
               res = ps.executeQuery ();
               
               if(res.next ()){
                    
                    return res.getDate ( 1 );
                    
               }
          } catch ( SQLException e ) {
               e.printStackTrace ( );
          }
          
          return null;
     }
    
    public void HistorialAlicuotaOrdinaria(Connection Conexion, ObservableList ListaHistorial, int n_casa, int id_terraza){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("select fecha_en_que_pago, monto from propietario_cancela_cuota_ordinaria where n_casa = ? AND id_terraza = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt (1, n_casa );
            ps.setInt ( 2, id_terraza);
            res = ps.executeQuery ();
            
            while(res.next ()){
                
                ListaHistorial.add ( new Propietario_Cancela_Cuota_Ordinaria ( res.getDate ( 1 ), res.getLong ( 2 ) ) );
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        
    }
     
     public int Registrar( Connection Conexion, Integer id_cuota, Date listaDeCuotas, Long montos) {
    
         PreparedStatement ps;
         
    
         String sql = ("INSERT INTO propietario_cancela_cuota_ordinaria(cedula_propietario,id_cuota,fecha_en_que_pago,monto,n_casa,id_terraza,id_casa) VALUES (?,?,?,?,?,?,?)");
         
         
         try {
             
             Id_Cuota = id_cuota;
             Fecha_en_que_pago = listaDeCuotas;
             Monto = montos;
             
                        ps = Conexion.prepareStatement ( sql );
                        ps.setInt ( 1 , Cedula_Propietario );
                        ps.setInt ( 2 , Id_Cuota );
                        ps.setDate ( 3 , Fecha_en_que_pago );
                        ps.setLong ( 4 , Monto );
                        ps.setInt ( 5 , N_Casa.get ( ) );
                        ps.setInt ( 6 , Id_Terraza.get ( ) );
                        ps.setInt ( 7 , Id_Casa.get ( ) );
    
                        return ps.executeUpdate ( );
                
         } catch ( SQLException e ) {
             e.printStackTrace ( );
         }
         return 0;
     }
}
