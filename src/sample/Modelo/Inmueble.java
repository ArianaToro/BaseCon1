package src.sample.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inmueble {

      private int id_casa;
      private IntegerProperty n_casa;

      public Inmueble(Integer n_casa){

          this.n_casa = new SimpleIntegerProperty(n_casa);

      }
}
