package src.sample.Vista;

import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Validaciones_Modulo_Cuota_Extraordinaria {
    
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
    
    public void Permitido( boolean valor1,  DatePicker fecha ){
        
        if( valor1 == true){
            
            fecha.setValue ( null );
            fecha.setPromptText ( "FECHA NO ADMITIDA" );
            
        }
    }
}
