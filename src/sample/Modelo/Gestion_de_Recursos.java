package src.sample.Modelo;

import javafx.collections.ObservableList;

import java.sql.*;

public class Gestion_de_Recursos {
    
    private Integer N_Casa;
    private String Nombre;
    private long Total_Cuota_Ordinaria;
    private int terraza;
    private int id_casa;
    
    public Gestion_de_Recursos ( Integer N_Casa , String Nombre , long Total_Cuota_Ordinaria  ) {
        
        this.N_Casa = N_Casa;
        this.Nombre = Nombre;
        this.Total_Cuota_Ordinaria = Total_Cuota_Ordinaria;
        
    }
    
    public Gestion_de_Recursos ( ) {
    
    }
    
    public Gestion_de_Recursos ( int n_casa , int terraza , String nombres , int deuda_en_meses ) {
        
        this.N_Casa = n_casa;
        this.terraza = terraza;
        this.Nombre = nombres;
        this.Total_Cuota_Ordinaria = deuda_en_meses;
        
    }
    
    public Gestion_de_Recursos ( int id_casa , int n_Casa , int n_terraza , String nombre , int deuda) {
        
        this.id_casa = id_casa;
        this.N_Casa = n_Casa;
        this.terraza = n_terraza;
        this.Nombre = nombre;
        this.Total_Cuota_Ordinaria = deuda;
        
    }
    
    public Integer getN_Casa ( ) {
        return N_Casa;
    }
    
    public void setN_Casa ( Integer n_Casa ) {
        N_Casa = n_Casa;
    }
    
    public String getNombre ( ) {
        return Nombre;
    }
    
    public void setNombre ( String nombre ) {
        Nombre = nombre;
    }
    
    public long getTotal_Cuota_Ordinaria ( ) {
        return Total_Cuota_Ordinaria;
    }
    
    public void setTotal_Cuota_Ordinaria ( long total_Cuota_Ordinaria ) {
        Total_Cuota_Ordinaria = total_Cuota_Ordinaria;
    }
    
    public ObservableList Llenar_Gestion_de_recursos_lista_morosos_cuota ( Connection Conexion , ObservableList morosos ) {//fecha adquisicion
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.id_casa, A4.n_casa, A6.id_terraza, concat(A1.primer_nombre,\" \", A1.primer_Apellido) as nombre, datediff((max(A5.fecha_en_que_pago)),(max(A8.fecha_establecida)))*0.0329 FROM persona A1 inner join propietario A2 on A1.cedula = A2.cedula_persona inner join propietario_tiene_inmueble A3 on A2.cedula_persona = A3.cedula_propietario inner join inmueble A4 on A3.id_casa = A4.id_casa inner join propietario_cancela_cuota_ordinaria A5 on A4.id_casa = A5.id_casa inner join inmueble_esta_ubicado_en_terraza A6 on A4.id_casa = A6.id_casa inner join terraza A7 on A6.id_terraza = A7.id_terraza inner join cuota_ordinaria A8 on A5.id_cuota = A8.id_cuota group by A4.id_casa having datediff((max(A8.fecha_establecida)),max(A5.fecha_en_que_pago))*0.0329 > 1");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                morosos.add ( new Gestion_de_Recursos ( res.getInt ( 1 ), res.getInt ( 2 ) , res.getInt ( 3 ) , res.getString ( 4 ), res.getInt ( 5 ) ));
                
            }
    
            return morosos;
            
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return null;
    }
    
    public void Llenar_Gestion_de_recursos_lista_morosos_cuota_ordinaria ( Connection Conexion , ObservableList morosos, ObservableList morosos1 , ObservableList morosos2 ) {//ultima fecha que pago
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.id_casa,A4.n_casa, A6.id_terraza, concat(A1.primer_nombre,\" \", A1.primer_Apellido) as nombre, datediff((max(A7.fecha_establecida)),max(A3.fecha_de_adquisicion))*0.0329 FROM persona A1 inner join propietario A2 on A1.cedula = A2.cedula_persona inner join propietario_tiene_inmueble A3 on A2.cedula_persona = A3.cedula_propietario inner join inmueble A4 on A3.id_casa = A4.id_casa inner join inmueble_esta_ubicado_en_terraza A5 on A4.id_casa = A5.id_casa inner join terraza A6 on A5.id_terraza = A6.id_terraza inner join cuota_ordinaria A7 where A3.habilitado = 'si' group by A3.id_casa having datediff((max(A7.fecha_establecida)),max(A3.fecha_de_adquisicion))*0.0329 > 1");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
               // if(!(morosos.equals ( morosos1 ) )) {
                    
                    morosos.add ( new Gestion_de_Recursos ( res.getInt ( 1) , res.getInt ( 2 ) , res.getInt ( 3 ), res.getString ( 4 ) , res.getInt ( 5 ) ) );
                    morosos2.add( new Gestion_de_Recursos (  res.getInt ( 1 ) , res.getInt ( 2 ) , res.getInt ( 3 ), res.getString ( 4) , res.getInt ( 5 ) ));
               
               // }
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public void Llenar_Gestion_de_recursos_ordinaria ( Connection Conexion , ObservableList Totales ) {
    
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.monto)as Total_cuota_ordinaria FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_cancela_cuota_ordinaria A3 on A2.cedula_persona = A3.cedula_propietario INNER JOIN cuota_ordinaria A4 on A3.id_cuota = A4.id_cuota  GROUP BY A3.id_casa ");
        
        try {
    
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 ) ));
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
     public void Busqueda_por_terraza ( Connection Conexion , ObservableList Totales , Integer id_terraza) {
    
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.monto)as Total_cuota_ordinaria FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_cancela_cuota_ordinaria A3 on A2.cedula_persona = A3.cedula_propietario INNER JOIN cuota_ordinaria A4 on A3.id_cuota = A4.id_cuota  WHERE A3.id_terraza = ? GROUP BY A3.id_casa ");
        
        try {
    
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, id_terraza );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 )) );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    
    public void Llenar_Gestion_de_recursos_extraordinaria ( Connection Conexion , ObservableList Totales ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.monto)as Total_cuota_extraordinaria FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_puede_pagar_cuota_extraordinaria A3 on A2.cedula_persona = A3.cedula_propietario INNER JOIN cuota_extraordinaria A4 on A3.id_cuota_e = A4.id_cuota_e GROUP BY A3.id_casa ");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 ) ));
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public void Busqueda_por_terrazaExtra ( Connection Conexion , ObservableList Totales , Integer id_terraza) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.monto)as Total_cuota_ordinaria FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_puede_pagar_cuota_extraordinaria A3 on A2.cedula_persona = A3.cedula_propietario INNER JOIN cuota_extraordinaria A4 on A3.id_cuota_e = A4.id_cuota_e  WHERE A3.n_terraza = ? GROUP BY A3.id_casa ");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, id_terraza );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 )) );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public void Llenar_Gestion_de_recursos_Articulo ( Connection Conexion , ObservableList Totales ) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.total_pagado) as Total_Articulos FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_puede_comprar_articulo A3 on A2.cedula_persona = A3.cedula INNER JOIN articulo A4 on A3.id_articulo = A4.id_articulo GROUP BY A3.id_casa");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 ) ));
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
    
    public void Busqueda_por_terrazaArticulo ( Connection Conexion , ObservableList Totales , Integer id_terraza) {
        
        PreparedStatement ps;
        ResultSet res;
        
        String sql = ("SELECT A3.n_casa,concat(A1.primer_nombre,\"  \", A1.primer_apellido) as nombre_y_apellido ,sum(A3.total_pagado)as Total_Articulos FROM persona A1 INNER JOIN propietario A2 on A1.cedula = A2.cedula_persona INNER JOIN propietario_puede_comprar_articulo A3 on A2.cedula_persona = A3.cedula INNER JOIN articulo A4 on A3.id_articulo = A4.id_articulo  WHERE A3.id_terraza = ? GROUP BY A3.id_casa ");
        
        try {
            
            ps = Conexion.prepareStatement ( sql );
            ps.setInt ( 1, id_terraza );
            res = ps.executeQuery (  );
            
            while ( res.next ( ) ) {
                
                Totales.add ( new Gestion_de_Recursos ( res.getInt ( 1 ) , res.getString ( 2 ) , res.getLong ( 3 )) );
                
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
}