package src.sample.Modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Correo {

    private int id;
    private StringProperty Usuario;

    public Correo( String usuario) {

        this.Usuario = new SimpleStringProperty(usuario);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario.get();
    }

    public StringProperty usuarioProperty() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario.set(usuario);
    }

    public int Buscar_Ultimo_Registro(Connection Conexion) {

        PreparedStatement ps; //variable que almacenara la ejecucion de la consulta
        ResultSet res;//variable que almacenara el resultado de la consulta una vez obtenido

        String sql1 = "SELECT max(id_correo) FROM correo";//variable que almacena la consulta

        try {

            ps = Conexion.prepareStatement(sql1);
            res = ps.executeQuery("SELECT max(id_correo) FROM correo");

            if (res.next()) {//se verifica si la consulta obtuvo un valor, de ser asi:

                return res.getInt(1);//id1 almacena el valor obtenido(en este caso es un entero)

            }
            return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int RegistrarCorreo(Connection Conexion){


        PreparedStatement ps;

        String sql = ("INSERT INTO correo(id_correo,correo) VALUES (?,?)");

        try {

            id = Buscar_Ultimo_Registro(Conexion);
            id++;
            ps = Conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, Usuario.get());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int Modificar(Connection Conexion, Integer id){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE correo SET  correo  = ? WHERE id_correo = ? ");
        
        try{
            
            this.id = id;
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, Usuario.get () );
            ps.setInt ( 2, id );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
}
