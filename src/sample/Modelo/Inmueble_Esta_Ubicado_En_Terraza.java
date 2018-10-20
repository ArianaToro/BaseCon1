package src.sample.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Inmueble_Esta_Ubicado_En_Terraza {

    private int id_casa;
    private int id_terraza;

    public Inmueble_Esta_Ubicado_En_Terraza(Integer id_casa,Integer id_terraza){

           this.id_casa = id_casa;
           this.id_terraza = id_terraza;

    }
    
    public Inmueble_Esta_Ubicado_En_Terraza ( ) {
    
    }
    
    public int IDCasa(Connection Conexion, Integer n_casa, Integer id_terraza){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A1.id_casa FROM inmueble A1 INNER JOIN inmueble_esta_ubicado_en_terraza A2 ON A1.id_casa = A2.id_casa INNER JOIN  terraza A3 ON A2.id_terraza = A3.id_terraza WHERE A1.n_casa = ? AND A3.id_terraza = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, n_casa );
            ps.setInt ( 2, id_terraza );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getInt ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
    return 0;
    }
    public int Verificacion_Inmueble_Existe_En_Terraza ( Connection Conexion){

        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT A1.n_casa FROM Inmueble A1 INNER JOIN inmueble_esta_ubicado_en_terraza A2 on A2.id_casa = A1.id_casa INNER JOIN Terraza A3 on A3.id_terraza = A2.id_terraza WHERE A1.id_casa = ? AND A3.id_terraza = ?");

        try{

            ps = Conexion.prepareStatement(sql);
            ps.setInt(1, id_casa);
            ps.setInt(2, id_terraza);

            res = ps.executeQuery();

            if(res.next()){

                return 1;

            }else{

                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
