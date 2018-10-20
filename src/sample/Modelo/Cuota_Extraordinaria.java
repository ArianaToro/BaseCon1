package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;


import java.sql.*;

public class Cuota_Extraordinaria {

       private Integer Id_CuotaExtraordinaria;
       private StringProperty Tipo_de_CuotaExtraordinaria;
       private StringProperty Descripcion_CuotaExtraordinaria;
       private Date Fecha_CuotaExtraordinaria;
       private IntegerProperty Costo_CuotaExtraordinaria;

       public Cuota_Extraordinaria(Integer Id_CuotaExtraordinaria, String Tipo_de_CuotaExtraordinaria, String Descripcion_CuotaExtraordinaria, Date Fecha_CuotaExtraordinaria,Integer Costo_CuotaExtraordinaria){

            this.Id_CuotaExtraordinaria = Id_CuotaExtraordinaria;
            this.Tipo_de_CuotaExtraordinaria = new SimpleStringProperty(Tipo_de_CuotaExtraordinaria);
            this.Descripcion_CuotaExtraordinaria = new SimpleStringProperty(Descripcion_CuotaExtraordinaria);
            this.Fecha_CuotaExtraordinaria = Fecha_CuotaExtraordinaria;
            this.Costo_CuotaExtraordinaria = new SimpleIntegerProperty(Costo_CuotaExtraordinaria);

       }

    public Cuota_Extraordinaria() {

    }

    public Cuota_Extraordinaria(String busquedaCuotaExtraordinaria) {

           this.Tipo_de_CuotaExtraordinaria = new SimpleStringProperty(busquedaCuotaExtraordinaria);
    }


    public Integer getId_CuotaExtraordinaria() {
        return Id_CuotaExtraordinaria;
    }

    public void setId_CuotaExtraordinaria(Integer id_CuotaExtraordinaria) {
        Id_CuotaExtraordinaria = id_CuotaExtraordinaria;
    }

    public String getTipo_de_CuotaExtraordinaria() {
        return Tipo_de_CuotaExtraordinaria.get();
    }

    public StringProperty tipo_de_CuotaExtraordinariaProperty() {
        return Tipo_de_CuotaExtraordinaria;
    }

    public void setTipo_de_CuotaExtraordinaria(String tipo_de_CuotaExtraordinaria) {
        this.Tipo_de_CuotaExtraordinaria.set(tipo_de_CuotaExtraordinaria);
    }

    public String getDescripcion_CuotaExtraordinaria() {
        return Descripcion_CuotaExtraordinaria.get();
    }

    public StringProperty descripcion_CuotaExtraordinariaProperty() {
        return Descripcion_CuotaExtraordinaria;
    }

    public void setDescripcion_CuotaExtraordinaria(String descripcion_CuotaExtraordinaria) {
        this.Descripcion_CuotaExtraordinaria.set(descripcion_CuotaExtraordinaria);
    }

    public Date getFecha_CuotaExtraordinaria() {
        return Fecha_CuotaExtraordinaria;
    }

    public void setFecha_CuotaExtraordinaria(Date fecha_CuotaExtraordinaria) {
        Fecha_CuotaExtraordinaria = fecha_CuotaExtraordinaria;
    }

    public int getCosto_CuotaExtraordinaria() {
        return Costo_CuotaExtraordinaria.get();
    }

    public IntegerProperty costo_CuotaExtraordinariaProperty() {
        return Costo_CuotaExtraordinaria;
    }

    public void setCosto_CuotaExtraordinaria(int costo_CuotaExtraorinaria) {
        this.Costo_CuotaExtraordinaria.set(costo_CuotaExtraorinaria);
    }

    public int BuscarUltimoIddeCuotaExtraordinaria(Connection Conexion){

        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT max(id_cuota_e) FROM cuota_extraordinaria");

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
    
    public java.util.Date Ultima_Cuota_Establecida( Connection Conexion, java.util.Date fecha, DatePicker aviso ){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT max(fecha) FROM cuota_extraordinaria");
        
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
    

    public int Agregar(Connection Conexion) {

        PreparedStatement ps;

        String sql = ("INSERT INTO cuota_extraordinaria(id_cuota_e,tipo_cuota_e,descripcion,fecha,codigo_c,costo) VALUES (?,?,?,?,?,?)");

        try {

            Id_CuotaExtraordinaria = BuscarUltimoIddeCuotaExtraordinaria(Conexion);
            Id_CuotaExtraordinaria++;
            ps = Conexion.prepareStatement(sql);
            ps.setInt(1, Id_CuotaExtraordinaria);
            ps.setString(2,Tipo_de_CuotaExtraordinaria.get());
            ps.setString(3, Descripcion_CuotaExtraordinaria.get());
            ps.setDate(4,Fecha_CuotaExtraordinaria);
            ps.setInt(5,1);
            ps.setInt(6, Costo_CuotaExtraordinaria.get());


            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        }

       }


       public void Busqueda(Connection Conexion, ObservableList cuota){

       }


    public static void LlenarTablaCuotaExtraordinaria(Connection Conexion, ObservableList cuota){


           try{

              Statement ps = Conexion.createStatement();
              ResultSet res = ps.executeQuery("SELECT id_cuota_e,tipo_cuota_e,descripcion,fecha,costo FROM cuota_extraordinaria");

               while(res.next()){

                   cuota.add(new Cuota_Extraordinaria(res.getInt(1),res.getString(2),res.getString(3),res.getDate(4),res.getInt(5)));

               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
}
