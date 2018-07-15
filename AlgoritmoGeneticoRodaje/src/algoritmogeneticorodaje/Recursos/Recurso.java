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
public class Recurso {
    private final SimpleStringProperty Nombre;
    private final SimpleStringProperty Costo;
    private int costo;
    public Recurso(String nomb, String costo) {
        this.Nombre = new SimpleStringProperty(nomb);
        this.Costo = new SimpleStringProperty(costo);
        this.costo = Integer.valueOf(costo);
    }
    public int getCostoInt(){
        return this.costo;
    }
    public void setNombre(String nomb){
        Nombre.set(nomb);
    }
    
    public String getNombre(){
        return Nombre.get();
    }
    
    public void setCosto(String apell){
        Costo.set(apell);
    }
    
    public String getCosto(){
        return Costo.get();
    }
}
