package src.sample.Vista;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Advertencias {
    
    public void Advertencia_Primer_Nombre( javafx.scene.control.TextField primer_nombre, Pane Advertencia ){
        
        if(primer_nombre.getText ().isEmpty ()){
            
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
        }
    }

    
    public void Advertencia_Primer_Apellido( TextField primer_apellido, Pane Advertencia ){
        
        if(primer_apellido.getText ().isEmpty ()){
            
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Sexo( ComboBox nacionalidad, Pane Advertencia ){
        
        if(nacionalidad.getSelectionModel ().getSelectedItem () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Estado_Civil( ComboBox estado_civil, Pane Advertencia ){
        
        if(estado_civil.getSelectionModel ().getSelectedItem () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Nacionalidad( ComboBox nacionalidad, Pane Advertencia ){
        
        if(nacionalidad.getSelectionModel ().getSelectedItem () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Fecha_de_Nacimiento( DatePicker fecha , Pane Advertencia ){
        
        if(fecha.getValue () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
            
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Telefono(TextField numero , Pane Advertencia){
        
        if(numero.getLength () > 0 && numero.getLength () < 7 ){
                
                Advertencia.setVisible ( true );
                
        }else{
            
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Correo(TextField correo , int validez, Pane Advertencia){
        
        if(correo.getLength () > 0) {
            
            if ( validez == 0 ) {
    
                Advertencia.setVisible ( true );
                
              }else{
    
                Advertencia.setVisible ( false );
                
            }
            } else {
        
                Advertencia.setVisible ( false );
            }
        }
    
    public void Advertencia_Existe_Nombre_Usuario(int verificacion, Pane Advertencia){
        
        if(verificacion == 1){
            
            Advertencia.setVisible(true);
            
        }else{
            
            Advertencia.setVisible ( false );
            
        }
    }
    
        public void Advertencia_Nombre_Usuario(int verificacion, Pane Advertencia){
        
        if(verificacion == 0){
            
            Advertencia.setVisible(true);
            
        }else{
            
            Advertencia.setVisible ( false );
            
        }
    }
    
    public void Advertencia_Contraseña(int verificacion, Pane Advertencia){
        
        if(verificacion == 0){
            
            Advertencia.setVisible(true);
            
        }else{
            
            Advertencia.setVisible ( false );
            
        }
    }
    
    public void Advertencia_Cargo_Usuario( ComboBox cargo, Pane Advertencia ){
        
        if(cargo.getSelectionModel ().getSelectedItem () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
            
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Pregunta_Usuario( ComboBox pregunta, Pane Advertencia ){
        
        if(pregunta.getSelectionModel ().getSelectedItem () == null){
            
            Advertencia.setVisible ( true );
            
        }else{
            
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Prefijo_Telefonico( ComboBox prefijo,TextField numero, Pane Advertencia ){
        
        if(numero.getText ().length () > 1){
        
        if(prefijo.getSelectionModel ().getSelectedItem () == null) {
    
            Advertencia.setVisible ( true );
            
           }
        }else{
            
            Advertencia.setVisible ( false );
        }
    }
    
    public void Advertencia_Direccion_(ComboBox terraza, Pane Advertencia){
        
        if(terraza.getSelectionModel ().getSelectedItem () == null){
    
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
            
        }
    }
    
    public void Advertencia_Direccion_Casa(TextField Casa, Pane Advertencia){
        
        if(Casa.getText ().isEmpty ()){
    
            Advertencia.setVisible ( true );
            
        }else{
    
            Advertencia.setVisible ( false );
            
        }
    }
}

