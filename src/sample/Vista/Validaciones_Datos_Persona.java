package src.sample.Vista;


import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones_Datos_Persona {
    
    public void Validaciones_Int_cedula ( TextField cedula ) {
        
        
        cedula.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                char c1 = c.charAt ( 0 );
                
                if ( ! Character.isDigit ( c1 ) ) {
                    
                    event.consume ( );
                    
                }
                
                int l = cedula.getText ( ).length ( );
                
                if ( l > 7 ) {
                    
                    event.consume ( );
                    
                }
                
            }
            
        } );
        
    }
    
    public void Validaciones_Int_Numero_Casa ( TextField Casa ) {
        
        
        Casa.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                char c1 = c.charAt ( 0 );
                
                if ( ! Character.isDigit ( c1 ) ) {
                    
                    event.consume ( );
                    
                }
                
                int l = Casa.getText ( ).length ( );
                
                if ( l > 1 ) {
                    
                    event.consume ( );
                    
                }
                
            }
            
        } );
//
    }
    
    public void Validaciones_String_nombreObligatorio ( TextField nombres ) {
        
        nombres.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
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
    
    public void Validaciones_String_nombre ( TextField nombres ) {
        
        nombres.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
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
    
    public void longitud_Telefono ( TextField numero_telefono ) {
        
        numero_telefono.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                if ( numero_telefono.getText ( ).length ( ) > 6 ) {
                    
                    event.consume ( );
                    
                }
            }
        } );
    }
    
    public void Validaciones_Int_Telefono ( TextField numero_telefono ) {
        
        numero_telefono.setOnKeyTyped ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                String c = event.getCharacter ( );
                char c1 = c.charAt ( 0 );
                
                if ( ! Character.isDigit ( c1 ) ) {
                    
                    event.consume ( );
                    
                }else if ( numero_telefono.getText ( ).length ( ) > 6 ) {
    
                    event.consume ( );
                    
                }
            }
        } );
    }
    
    public int Correo( TextField Correo){
        
            Pattern expresion = Pattern.compile ( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            
            String correo = Correo.getText ();
            
            Matcher mather = expresion.matcher ( correo );
            
            if(mather.find () == true){
                
                return 1;
                
            }else{
                
                return 0;
                
            }
}

    
    public void Permitido ( Integer años , DatePicker fecha) {
        if ( años < 18 || años > 90 ) {
            
            fecha.setValue ( null );
            fecha.setPromptText ( " NO ADMITIDA" );
            
        }
    }
    
    public void Permitido_Fecha_Adquisicion ( boolean valor , boolean valor1 , DatePicker fecha_adquisicion ) {
        
        if ( valor == true ) {
            
            fecha_adquisicion.setValue ( null );
            fecha_adquisicion.setPromptText ( "FECHA NO ADMITIDA" );
            
        } else if ( valor1 == true ) {
            
            fecha_adquisicion.setValue ( null );
            fecha_adquisicion.setPromptText ( "FECHA NO ADMITIDA" );
            
        }
        
    }
    
}
    
    
    
    
