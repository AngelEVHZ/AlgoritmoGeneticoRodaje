/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Valenzuela
 */
public class Locacion {
    private final SimpleStringProperty Nombre;
    int id;
    public Locacion(String nomb){
        this.Nombre = new SimpleStringProperty(nomb);
     
    }
      public void setNombre(String nomb){
        Nombre.set(nomb);
    }
    
    public String getNombre(){
        return Nombre.get();
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    
    
}
