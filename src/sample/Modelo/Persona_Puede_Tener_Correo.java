package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persona_Puede_Tener_Correo{

    private IntegerProperty Cedula;
    private int Id_Correo;

    public Persona_Puede_Tener_Correo(Integer cedula) {

        this.Cedula = new SimpleIntegerProperty(cedula);


    }
    
    public Persona_Puede_Tener_Correo ( ) {
    
    }
    
    public int getCedula() {
        return Cedula.get();
    }

    public IntegerProperty cedulaProperty() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        this.Cedula.set(cedula);
    }

    public int getId_Correo() {
        return Id_Correo;
    }

    public void setId_Correo(int id_Correo) {
        this.Id_Correo = id_Correo++;
    }
    
    
    public int Buscar_Correo( Connection Conexion, ObservableList<Integer> ids){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT id_correo FROM persona_puede_tener_correo WHERE cedula = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cedula.get () );
            res = ps.executeQuery ();
            
            while (res.next ()){
                
                ids.add (  res.getInt ( 1 ));
                
            }
        } catch ( SQLException e ) {
            
            e.printStackTrace ();
            
        }
        return 0;
    }
    
    public void Buscar_Ultimo_Registro(Connection Conexion) {

        PreparedStatement ps; //variable que almacenara la ejecucion de la consulta
        ResultSet res;//variable que almacenara el resultado de la consulta una vez obtenido

        int id = 0; //variable para almacenar el valor que devuelve la consulta en este caso es un valor entero
        String sql1 = "SELECT max(id_correo) FROM correo";//variable que almacena la consulta

        try {

            ps = Conexion.prepareStatement(sql1);
            res = ps.executeQuery();

            if (res.next()) {//se verifica si la consulta obtuvo un valor, de ser asi:

                id = res.getInt(1);//id1 almacena el valor obtenido(en este caso es un entero)
                setId_Correo(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void BuscarCorreo( Connection Conexion, Integer cedula, ObservableList<String> usuarios , TextField usuario1 , TextField usuario2 ){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.correo FROM persona A1 INNER JOIN persona_puede_tener_correo A2 ON A1.cedula = A2.cedula INNER JOIN correo A3 ON A2.id_correo = A3.id_correo WHERE A2.cedula = ?");
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, cedula );
            res = ps.executeQuery ();
            
            while(res.next ()){
                
                usuarios.add ( res.getString ( 1 ) );
                
            }
            
            usuario1.setText ( usuarios.get ( 0 ) );
            usuario2.setText (usuarios.get ( 1 ) );
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public int Registrar(Connection Conexion) {

        PreparedStatement ps;

        String sql = "INSERT INTO persona_puede_tener_correo(cedula,id_correo) VALUES (?,?)";

        try {

            Buscar_Ultimo_Registro(Conexion);
            ps = Conexion.prepareStatement(sql);
            ps.setInt(1,Cedula.get());
            ps.setInt(2,getId_Correo());

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
}
