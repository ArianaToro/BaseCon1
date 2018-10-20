package src.sample.Modelo;


import javafx.collections.ObservableList;

import java.sql.*;


public class Cuota_Ordinaria_Corresponde_A_Servicios {

    private int Id_CuotaOrdinaria;
    private int Id_Servicio;


    public  Cuota_Ordinaria_Corresponde_A_Servicios(int id_CuotaOrdinaria){

         this.Id_CuotaOrdinaria = id_CuotaOrdinaria;
         this.Id_Servicio = Id_Servicio;

    }

    public Cuota_Ordinaria_Corresponde_A_Servicios() {

    }

    public int getId_CuotaOrdinaria() {
        return Id_CuotaOrdinaria;
    }

    public void setId_CuotaOrdinaria(int id_CuotaOrdinaria) {
        Id_CuotaOrdinaria = id_CuotaOrdinaria;
    }

    public int getId_Servicio() {
        return Id_Servicio;
    }

    public void setId_Servicio(int id_Servicio) {
        Id_Servicio = id_Servicio;
    }

    public void ServiciosQueCorrespondenACadaCuota(Connection  Conexion){
        
        PreparedStatement ps;
        ResultSet res;
        
        
        
        
        
    }
    public int ConteoDeServiciosPorInmueble(Connection Conexion, int id_cuota){
        
        PreparedStatement ps;
        ResultSet res;
        
         try{
             
             ps = Conexion.prepareStatement ( "SELECT COUNT(id_servicio) FROM cuota_ordinaria_corresponde_a_servicios WHERE id_cuota = ?;" );
             ps.setInt ( 1, id_cuota );
             res = ps.executeQuery ();
             
             if(res.next ()){
                 
                 return res.getInt ( 1 );
                 
             }else{
                 
                 return 0;
                 
             }
             
         } catch ( SQLException e ) {
             e.printStackTrace ( );
             return 0;
         }
    }
    public int Registrar(Connection Conexion){

        PreparedStatement ps;
        PreparedStatement ps1;
        ResultSet res;

        try{

            ps1 = Conexion.prepareStatement("SELECT id_servicio FROM servicios WHERE estado = ?");
            ps1.setInt(1, 1);
            ps = Conexion.prepareStatement("INSERT INTO cuota_ordinaria_corresponde_a_servicios(id_cuota,id_servicio) VALUES (?,?)");
            res = ps1.executeQuery();

            while(res.next()){

                    ps.setInt(1, Id_CuotaOrdinaria);
                    ps.setInt(2, res.getInt(1));

                    ps.executeUpdate();

                    }

        } catch (SQLException e) {
            e.printStackTrace();

            return 0;

        }
        return 0;
    }

    
public int Eliminar(Connection Conexion, int id_CuotaOrdinaria, int id_Servicio){
    
    PreparedStatement ps;
    
    try {
        
        ps = Conexion.prepareStatement ( "DELETE FROM cuota_ordinaria_corresponde_a_servicios WHERE id_cuota = ? AND id_servicio = ?;" );
        ps.setInt ( 1, id_CuotaOrdinaria);
        ps.setInt ( 2, id_Servicio);
        
        ps.executeUpdate ();
        return 1;
        
    } catch ( SQLException e ) {
        e.printStackTrace ( );
    }
    return 0;
   }
}