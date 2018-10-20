package src.sample.Modelo;

import java.sql.*;

//Clase que tiene acceso a la base de datos

public class Persona {

    protected String  Nacionalidad;
    protected Integer Cedula;
    protected String  Primer_Nombre;
    protected String  Segundo_Nombre;
    protected String  Primer_Apellido;
    protected String  Segundo_Apellido;
    protected Date    Fecha_de_Nacimiento;
    protected String  Sexo;
    protected String  Estado_Civil;

    public Persona(String Nacionalidad, Integer Cedula, String Primer_Nombre, String Segundo_Nombre, String Primer_Apellido, String Segundo_Apellido, Date Fecha_de_Nacimiento, String Sexo, String Estado_Civil) {
        
        this.Nacionalidad = Nacionalidad;
        this.Cedula = Cedula;
        this.Primer_Nombre = Primer_Nombre;
        this.Segundo_Nombre = Segundo_Nombre;
        this.Primer_Apellido = Primer_Apellido;
        this.Segundo_Apellido = Segundo_Apellido;
        this.Fecha_de_Nacimiento = Fecha_de_Nacimiento;
        this.Sexo = Sexo;
        this.Estado_Civil = Estado_Civil;
        
    }
    
    public Persona ( ) {
    
    }
    
    //Metodos asociados a la clase


    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public Integer getCedula() {
        return Cedula;
    }

    public void setCedula(Integer cedula) {
        Cedula = cedula;
    }

    public String getPrimer_Nombre() {
        return Primer_Nombre;
    }

    public void setPrimer_Nombre(String primer_Nombre) {
        Primer_Nombre = primer_Nombre;
    }

    public String getSegundo_Nombre() {
        return Segundo_Nombre;
    }

    public void setSegundo_Nombre(String segundo_Nombre) {
        Segundo_Nombre = segundo_Nombre;
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String primer_Apellido) {
        Primer_Apellido = primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String segundo_Apellido) {
        Segundo_Apellido = segundo_Apellido;
    }

    public Date getFecha_de_Nacimiento() {
        return Fecha_de_Nacimiento;
    }

    public void setFecha_de_Nacimiento(Date fecha_de_Nacimiento) {
        Fecha_de_Nacimiento = fecha_de_Nacimiento;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEstado_Civil() {
        return Estado_Civil;
    }

    public void setEstado_Civil(String estado_Civil) {
        Estado_Civil = estado_Civil;
    }
    
    
    public int Verificar_Existencia_de_Persona(Connection Conexion){

        PreparedStatement ps;
        ResultSet res;

        String sql = ("SELECT cedula FROM persona WHERE cedula = ?");

        try{

            ps = Conexion.prepareStatement(sql);
            ps.setInt(1,getCedula());
            res = ps.executeQuery();

            if(res.next()){

                res.getInt(1);
                return 1;
                
            }else{

                return 0;
                
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return 0;
    }

    public int Registrar_Datos(Connection Conexion) {


        PreparedStatement ps;

        String sql = "INSERT INTO persona(cedula,primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,sexo,nacionalidad,estado_civil,fecha_de_nacimiento) VALUES (?,?,?,?,?,?,?,?,?)";

        try {

            ps = Conexion.prepareStatement(sql);

            ps.setInt(1, Cedula);
            ps.setString(2, Primer_Nombre);
            ps.setString(3, Segundo_Nombre);
            ps.setString(4, Primer_Apellido);
            ps.setString(5, Segundo_Apellido);
            ps.setString(6, Sexo);
            ps.setString(7, Nacionalidad);
            ps.setString(8, Estado_Civil);
            ps.setDate(9, Fecha_de_Nacimiento);

            return ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }


    }
    
    public int Modificar_Datos(Connection Conexion) {
    
        PreparedStatement ps;
    
        String sql = ( "UPDATE persona SET primer_nombre = ? , segundo_nombre = ? , primer_apellido = ? ,segundo_apellido = ?, sexo = ? , nacionalidad = ? , estado_civil = ?  WHERE cedula = ?" );
    
        try {
        
            ps = Conexion.prepareStatement ( sql );
            ps.setString ( 1 , Primer_Nombre );
            ps.setString ( 2 , Segundo_Nombre );
            ps.setString ( 3 , Primer_Apellido );
            ps.setString ( 4 , Segundo_Apellido );
            ps.setString ( 5 , Sexo );
            ps.setString ( 6 , Nacionalidad );
            ps.setString ( 7 , Estado_Civil );
            ps.setInt ( 8, Cedula );
            
            return ps.executeUpdate ( );
        
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    
        return 0;
    }
    
    public boolean Eliminar_Datos( Connection Conexion){
        
        PreparedStatement ps;
        
        String sql = ("DELETE FROM persona WHERE cedula = ?");
        
        try{
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, Cedula );
            
            return ps.execute();
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        
        return false;
    }
}

