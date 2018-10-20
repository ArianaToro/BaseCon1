package src.sample.Vista;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Menu extends Application {
    
    private double xOffset;
    private double yOffset;
    @FXML private Button BotonCerrarPrograma;
    
    src.sample.Vista.Controlador Controlador = new Controlador ();

    @Override
    public void start(Stage primaryStage) throws Exception{
    
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
    
        //Metodos para mover la aplicacion por la pantalla
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            //cuando se cliquea la interfaz obtiene las coordenadas X y Y
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
    
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //establece las nuevas coordenadas
            
                primaryStage.setX(event.getScreenX()-xOffset);
                primaryStage.setY(event.getScreenY()-yOffset);
            }
        });
    
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("BaseCon");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.show();
        
        
    }
    
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
}