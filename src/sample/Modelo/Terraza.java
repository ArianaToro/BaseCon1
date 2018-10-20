package src.sample.Modelo;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.sql.*;

public class Terraza {

    private int Id_Terraza;
    private int Cantidad_de_Casas;

    public Terraza(Integer Id_Terraza){

        this.Id_Terraza = Id_Terraza;

    }

    public Terraza() {

    }

    public int getId_Terraza() {
        return Id_Terraza;
    }

    public void setId_Terraza(int id_Terraza) {
        Id_Terraza = id_Terraza;
    }

    public void setCantidad_de_Casas(int cantidad) {

        Cantidad_de_Casas = cantidad++;
    }

    public int getCantidad_de_Casas() {
        return Cantidad_de_Casas;
    }

    public void Conteo_de_Casas_por_Terraza(Connection Conexion){

        PreparedStatement ps;
        ResultSet res;
        int Cantidad = 0;

        String sql = ("SELECT COUNT(id_casa) FROM inmueble_esta_ubicado_en_terraza WHERE id_terraza = ?");

        try{

            ps = Conexion.prepareStatement(sql);
            ps.setInt(1,Id_Terraza);
            res = ps.executeQuery();

            if(res.next()){

                Cantidad = res.getInt(1);
                setCantidad_de_Casas(Cantidad);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void Validacion_de_Cantidad_de_Casas(Connection Conexion){

        int Cantidad = 0;
        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT cantidad_casas FROM terraza WHERE id_terraza = ?");

        try{

            ps  = Conexion.prepareStatement(sql);
            ps.setInt(1,getId_Terraza());
            res = ps.executeQuery();

            if(res.next()) {

                Cantidad = res.getInt(1);
                Conteo_de_Casas_por_Terraza(Conexion);

                if(getCantidad_de_Casas() < Cantidad){

                    JOptionPane.showMessageDialog(null,"Aun faltan propietarios por registrarse");

                }else if(getCantidad_de_Casas() == Cantidad){

                    JOptionPane.showMessageDialog(null,"Todos las casas de esta terraza ya se encuentran registradas.");

                }

            }

        }catch(SQLException e){

            e.printStackTrace();
        }
    }
    
    public int TerrazaCorrespondienteACasa(Connection Conexion,Integer Cedula_Propietario, Integer n_Casa , Integer id_casa ){
    
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT id_terraza FROM propietario A1 INNER JOIN propietario_tiene_inmueble A2 ON A2.cedula_propietario = A1.cedula_persona INNER JOIN inmueble A3 ON A2.id_casa = A3.id_casa INNER JOIN inmueble_esta_ubicado_en_terraza A4 ON A3.id_casa = A4.id_casa  WHERE A1.cedula_persona = ? AND A3.n_casa = ? AND A3.id_casa = ? ");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cedula_Propietario );
            ps.setInt ( 2, n_Casa );
            ps.setInt ( 3, id_casa );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getInt ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }

}

