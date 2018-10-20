package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;


public class Telefono {

    private int id;
    private StringProperty Prefijo_Telefonico;
    private StringProperty Telefono;

    public Telefono(String Prefijo_Telefonico, String Telefono) {

        this.Prefijo_Telefonico = new SimpleStringProperty(Prefijo_Telefonico);
        this.Telefono = new SimpleStringProperty(Telefono);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return Telefono.get();
    }

    public StringProperty telefonoProperty() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono.set(telefono);
    }

    public String getPrefijo_Telefonico() {
        return Prefijo_Telefonico.get();
    }

    public StringProperty prefijo_TelefonicoProperty() {
        return Prefijo_Telefonico;
    }

    public void setPrefijo_Telefonico(String prefijo_Telefonico) {
        this.Prefijo_Telefonico.set(prefijo_Telefonico);
    }
    

    public int Buscar_Ultimo_Registro(Connection Conexion) {

        PreparedStatement ps; //variable que almacenara la ejecucion de la consulta
        ResultSet res;//variable que almacenara el resultado de la consulta una vez obtenido

        int id = 0; //variable para almacenar el valor que devuelve la consulta en este caso es un valor entero
        String sql1 = "SELECT max(id_telefono) FROM telefono";//variable que almacena la consulta

        try {

            ps = Conexion.prepareStatement(sql1);
            res = ps.executeQuery("SELECT max(id_telefono) FROM telefono");

            if (res.next()) {//se verifica si la consulta obtuvo un valor, de ser asi:

                return res.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

        public int RegistrarTelefono(Connection Conexion) {

        int id;
        PreparedStatement ps;

        String sql = ("INSERT INTO telefono(id_telefono,prefijo_telefonico,numero) VALUES (?,?,?)");

        try {

            id = Buscar_Ultimo_Registro(Conexion);
            id++;
            ps = Conexion.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2, Prefijo_Telefonico.get());
            ps.setString(3, Telefono.get());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
    
    public int Modificar(Connection Conexion, Integer id){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE telefono SET prefijo_telefonico = ? , numero = ? WHERE id_telefono = ? ");
        
        try{
            
            this.id = id;
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, Prefijo_Telefonico.get () );
            ps.setString ( 2, Telefono.get () );
            ps.setInt ( 3, id );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
}
