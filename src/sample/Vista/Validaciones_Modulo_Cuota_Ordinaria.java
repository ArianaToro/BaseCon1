package src.sample.Vista;

import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Validaciones_Modulo_Cuota_Ordinaria {
    
    public void Permitido( boolean valor1, DatePicker fecha, AnchorPane Mensaje ){
        
        if( valor1 == true){
    
            fecha.setValue ( null );
            Mensaje.setVisible ( true );
            fecha.setPromptText ( "FECHA NO ADMITIDA" );
            
        }
    }
    
    public void Nombre_de_servicio ( TextField nombre_servicio ) {
        
        nombre_servicio.setOnKeyTyped ( new EventHandler< KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                Character c1 = c.charAt ( 0 );
                
                if ( ! Character.isLetter ( c1 ) && ! Character.isWhitespace ( c1 ) ) {
                    
                    event.consume ( );
                    
                }
                
                int l = nombre_servicio.getText ( ).length ( );
                
                if ( l >= 30 ) {
                    
                    event.consume ( );
                    
                }
            }
            
        } );
    }
    
    public void Descripcion_de_servicio ( TextField descripcion_servicio ) {
        
        descripcion_servicio.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                Character c1 = c.charAt ( 0 );
                
                if ( ! Character.isLetter ( c1 ) && ! Character.isWhitespace ( c1 ) ) {
                    
                    event.consume ( );
                    
                }
                
                int l = descripcion_servicio.getText ( ).length ( );
                
                if ( l >= 100 ) {
                    
                    event.consume ( );
                    
                }
            }
            
        } );
    }
    
    public void Costo ( TextField costo ) {
        
        costo.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                char c1 = c.charAt ( 0 );
                
                if ( ! Character.isDigit ( c1 ) ) {
                    
                    event.consume ( );
                    
                }
            }
        } );
    }
}
