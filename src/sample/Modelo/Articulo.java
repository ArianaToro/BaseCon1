package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class Articulo {
    
    private Integer Id_Articulo;
    private StringProperty Nombre_de_Articulo;
    private IntegerProperty Precio_Articulo;
    private IntegerProperty Cantidad;
    
    public Articulo(  String Nombre_Articulo, Integer Precio_Articulo, Integer Cantidad){
        
        this.Nombre_de_Articulo = new SimpleStringProperty ( Nombre_Articulo );
        this.Precio_Articulo = new SimpleIntegerProperty ( Precio_Articulo );
        this.Cantidad = new SimpleIntegerProperty ( Cantidad );
        
    }
    
    public Articulo ( String Nombre) {
        
        this.Nombre_de_Articulo = new SimpleStringProperty ( Nombre );
        
    }
    
    public Articulo(Integer id){
        
        this.Id_Articulo = id;
        
    }
    
    public Articulo ( ) {
    
    }
    
    public Articulo ( int id , String nombre , int costo , int cantidad ) {
        
        this.Id_Articulo =  id ;
        this.Nombre_de_Articulo = new SimpleStringProperty ( nombre);
        this.Precio_Articulo = new SimpleIntegerProperty ( costo );
        this.Cantidad = new SimpleIntegerProperty ( cantidad );
        
    }
    
    public Articulo(Integer Cantidad, Integer Id) {
    
        this.Cantidad = new SimpleIntegerProperty ( Cantidad );
        this.Id_Articulo = Id;
    
    }
    
    public Articulo(Integer Precio, Integer Cantidad, Integer Id){
        
        this.Precio_Articulo = new SimpleIntegerProperty ( Precio );
        this.Cantidad = new SimpleIntegerProperty ( Cantidad );
        this.Id_Articulo = Id;
        
    }
    
    public Integer getId_Articulo ( ) {
        return Id_Articulo;
    }
    
    public void setId_Articulo ( Integer id_Articulo ) {
        Id_Articulo = id_Articulo;
    }
    
    public String getNombre_de_Articulo ( ) {
        return Nombre_de_Articulo.get ( );
    }
    
    public StringProperty nombre_de_ArticuloProperty ( ) {
        return Nombre_de_Articulo;
    }
    
    public void setNombre_de_Articulo ( String nombre_de_Articulo ) {
        this.Nombre_de_Articulo.set ( nombre_de_Articulo );
    }
    
    public int getPrecio_Articulo ( ) {
        return Precio_Articulo.get ( );
    }
    
    public IntegerProperty precio_ArticuloProperty ( ) {
        return Precio_Articulo;
    }
    
    public void setPrecio_Articulo ( int precio_Articulo ) {
        this.Precio_Articulo.set ( precio_Articulo );
    }
    
    public int getCantidad ( ) {
        return Cantidad.get ( );
    }
    
    public IntegerProperty cantidadProperty ( ) {
        return Cantidad;
    }
    
    public void setCantidad ( int cantidad ) {
        this.Cantidad.set ( cantidad );
    }
    
    public void Articulos_Existentes( Connection Conexion, ComboBox ListaArticulos ){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT tipo_de_articulo FROM articulo WHERE cantidad_existente > 0");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery ();
            
            while(res.next ()){
                
                ListaArticulos.getItems().addAll ( res.getString ( 1 ) );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
    }
    
    public int Cantidad_existente(Connection Conexion){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT cantidad_existente FROM articulo WHERE tipo_de_articulo = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, Nombre_de_Articulo.get () );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getInt ( 1 );
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
        
    }
    
    public int Precio_Articulo( Connection Conexion){
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT monto_articulo FROM articulo WHERE id_articulo = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Id_Articulo );
            res = ps.executeQuery ();
            
            if(res.next ()) {
                
                return res.getInt ( 1 );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
        return 0;
        
    }
    public int Buscar_Ultimo_Id( Connection Conexion){
    
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT max(id_articulo) FROM articulo");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery ();
            
            if(res.next ()){
                
                return res.getInt (  1);
                
            }
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public int Registrar(Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("INSERT INTO articulo(id_articulo, tipo_de_articulo, monto_articulo, cantidad_existente, habilitado,codigo_c) VALUE (?,?,?,?,?,?)");
        
        Id_Articulo = Buscar_Ultimo_Id ( Conexion );
        Id_Articulo++;
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Id_Articulo );
            ps.setString ( 2, getNombre_de_Articulo () );
            ps.setInt ( 3, getPrecio_Articulo () );
            ps.setInt ( 4, getCantidad () );
            ps.setString ( 5, "SI" );
            ps.setInt(6, 1);
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public int Modificar_Cantidad(Connection Conexion){
    
        PreparedStatement ps;
        
        String sql = ("UPDATE articulo SET cantidad_existente = ? WHERE id_articulo = ? ");
    
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cantidad.get () );
            ps.setInt ( 2, Id_Articulo );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return 0;
    }
    
    public int Modificar(Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE articulo SET monto_articulo = ? , cantidad_existente = ? WHERE id_articulo = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Precio_Articulo.get () );
            ps.setInt ( 2, Cantidad.get () );
            ps.setInt ( 3, Id_Articulo );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public int Eliminar(Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("UPDATE articulo SET  habilitado = ? WHERE id_articulo = ? ");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1, "NO" );
            ps.setInt ( 2, Id_Articulo );
            
            return ps.executeUpdate ();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return 0;
    }
    
    public void LLenarTablaInventario(Connection Conexion, ObservableList disponibles){
        
        try{
            
            Statement ps = Conexion.createStatement();
            ResultSet res = ps.executeQuery("SELECT id_articulo, tipo_de_articulo , monto_articulo,cantidad_existente  FROM articulo WHERE habilitado = 'SI' ");
            
            while(res.next()){
                
                disponibles.add(new Articulo (res.getInt(1),res.getString (2),res.getInt(3), res.getInt ( 4 )));
                
            }
        }
        catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
}
