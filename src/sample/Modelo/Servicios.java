package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.awt.*;
import java.sql.*;

public class Servicios {

      private IntegerProperty Id_Servicio;
      private StringProperty Tipo_de_Servicio;
      private StringProperty Descripcion;
      private IntegerProperty Costo;

      public Servicios(Integer ID_Servicios, String Tipo_de_Servicio, String Descripcion, Integer Costo){

          this.Id_Servicio = new SimpleIntegerProperty(ID_Servicios);
          this.Tipo_de_Servicio = new SimpleStringProperty(Tipo_de_Servicio);
          this.Descripcion = new SimpleStringProperty(Descripcion);
          this.Costo = new SimpleIntegerProperty(Costo);

      }
    
    public Servicios ( int parseInt ) {
          
          Id_Servicio = new SimpleIntegerProperty ( parseInt);
    }
    
    public Servicios ( ) {
    
    }
    
    public Servicios ( Integer Id , String tipo , Integer costo ) {
          
          this.Id_Servicio = new SimpleIntegerProperty ( Id );
          this.Tipo_de_Servicio = new SimpleStringProperty ( tipo );
          this.Costo = new SimpleIntegerProperty ( costo );
    }
    
    public String getTipo_de_Servicio() {
        return Tipo_de_Servicio.get();
    }

    public StringProperty tipo_de_ServicioProperty() {
        return Tipo_de_Servicio;
    }

    public void setTipo_de_Servicio(String tipo_de_Servicio) {
        this.Tipo_de_Servicio.set(tipo_de_Servicio);
    }

    public int getId_Servicio() {
        return Id_Servicio.get();
    }

    public IntegerProperty id_ServicioProperty() {
        return Id_Servicio;
    }

    public void setId_Servicio(int id_Servicio) {
        this.Id_Servicio.set(id_Servicio);
    }

    public String getDescripcion() {
        return Descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion.set(descripcion);
    }

    public int getCosto() {
        return Costo.get();
    }

    public IntegerProperty costoProperty() {
        return Costo;
    }

    public void setCosto(int costo) {
        this.Costo.set(costo);
    }


   
    public int BuscarUltimoIdDeServicio(Connection Conexion){

        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT max(id_servicio) FROM servicios");

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
    

    public int Agregar(Connection Conexion) {

        PreparedStatement ps;

        String sql = ("INSERT INTO servicios(id_servicio,tipo_de_servicio,descripcion,costo,estado) VALUES (?,?,?,?,?)");

        try {

            Id_Servicio.set(BuscarUltimoIdDeServicio(Conexion));
            int Id = getId_Servicio();
            Id++;
            ps = Conexion.prepareStatement(sql);
            ps.setInt(1, Id++);
            ps.setString(2, Tipo_de_Servicio.get().toUpperCase ());
            ps.setString(3, Descripcion.get().toUpperCase ());
            ps.setInt(4, Costo.get());
            ps.setInt( 5, 1 );
            

            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int Modificar( Connection Conexion){

        PreparedStatement ps;

        String sql = ("UPDATE servicios SET tipo_de_servicio = ? ,descripcion = ? , costo = ? WHERE id_servicio = ?");

        try {
            
            ps = Conexion.prepareStatement(sql);
            ps.setString(1, Tipo_de_Servicio.get());
            ps.setString(2, Descripcion.get());
            ps.setInt(3, Costo.get());
            ps.setInt(4, getId_Servicio ());

            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int Eliminar(Connection Conexion){

        PreparedStatement ps;

        String sql = ("UPDATE servicios SET estado = ? WHERE id_servicio= ? ");

        try {
            
            ps = Conexion.prepareStatement(sql);
            ps.setInt ( 1, 0 );
            ps.setInt(2, getId_Servicio () );
    
            ps.executeUpdate ();
            
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int CalcularTotal(Connection Conexion){

         PreparedStatement ps;
         ResultSet res;

         try{

             ps = Conexion.prepareStatement("SELECT SUM(costo) FROM servicios WHERE estado = ?" );
             ps.setInt ( 1, 1 );
             res  = ps.executeQuery();

             if(res.next()){

                 return res.getInt(1);

             }

         } catch (SQLException e) {

             e.printStackTrace();
             
             return 0;
         }

         return 0;
    }

    public  void LLenarTablaDeServicios(Connection Conexion, ObservableList Servicio){

          try{

              Statement ps = Conexion.createStatement();
              ResultSet res = ps.executeQuery("SELECT id_servicio, tipo_de_servicio, descripcion, costo  FROM servicios WHERE estado = 1 ");

              while(res.next()){

                  Servicio.add(new Servicios(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4)));
                  
              }

          } catch (SQLException e) {
              e.printStackTrace();
          }
    }
    
    public void LLenarTablaCuota_Servicios( Connection Conexion, ObservableList Cuota_Servicios, int ID_Cuota ){
        
        try{
            
            PreparedStatement pes = Conexion.prepareStatement ( "SELECT A1.id_servicio,A1.tipo_de_servicio,A1.costo FROM servicios A1 INNER JOIN cuota_ordinaria_corresponde_a_servicios A2 on A1.id_servicio = A2.id_servicio INNER JOIN cuota_ordinaria A3 on A2.id_cuota = A3.id_cuota WHERE A3.id_cuota = ?");
            pes.setInt ( 1, ID_Cuota);
            ResultSet res = pes.executeQuery();
            
            while(res.next()){
                
                Cuota_Servicios.add(new Servicios (res.getInt(1),res.getString(2),res.getInt(3)));
           
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
    }
}
