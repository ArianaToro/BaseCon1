package src.sample.Vista;

import com.jfoenix.controls.JFXPasswordField;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones_de_Usuario {
    
    public int Id_Usuario ( TextField nombre_de_usuario ) {
        
        Pattern expresion = Pattern.compile ( "([A-Z][a-z]{3,3})([0-9]{3,3})([_*.,/<>][_*.,/<>][_*.,/<>]{1,1})$" );
        
        String nombre = nombre_de_usuario.getText ( );
        
        Matcher verifica = expresion.matcher ( nombre );
        
        if ( verifica.find ( ) ) {
            
            System.out.println ( "Valido" );
            return 1;
            
        } else {
    
            System.out.println ( "Invalido" );
            return 0;
            
        }
        
    }
    
    public void Longitud_Nombre_de_Usuario ( TextField usuario ) {
        
        usuario.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
    
                if ( usuario.getText ( ).length ( ) == 12 ) {
        
                    event.consume ( );
                    
                }
            }
        } );
    }
    public int Contraseña_Usuario( PasswordField contraseña_de_usuario ){
    
        Pattern expresion = Pattern.compile ( "([A-Za-z0-9]{8,8})$" );
    
        String contraseña = contraseña_de_usuario.getText ( );
    
        Matcher verifica = expresion.matcher ( contraseña );
    
        if ( verifica.find ( ) ) {
        
            System.out.println ( "Valido" );
            return 1;
        
        } else {
        
            System.out.println ( "Invalido" );
            return 0;
        
        }
        
    }
    
    public void Longitud_Contraseña_Usuario ( PasswordField contraseña_de_usuario ) {
        
        contraseña_de_usuario.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                int l = contraseña_de_usuario.getText ( ).length ( );
                
                if ( l > 7 ) {
                    
                    event.consume ( );
                    
                }
            }
        } );
    }
    
    public void Validaciones_String_Respuesta ( TextField respuesta ) {
        
        respuesta.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent e ) {
                
                String c = e.getCharacter ( );
                char c1 = c.charAt ( 0 );
                
                if ( ! Character.isLetter ( c1 ) ) {
                    
                    e.consume ( );
                    
                }
            }
        } );
    }
}

