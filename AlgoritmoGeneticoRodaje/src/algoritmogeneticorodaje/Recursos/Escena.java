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
public class Escena {
    
    private final SimpleStringProperty Nombre;
    private final SimpleStringProperty Paginas;
    private int paginas;
    public Escena(String nomb,String paginas){
        this.Nombre = new SimpleStringProperty(nomb);
        this.Paginas = new SimpleStringProperty(paginas);
        this.paginas = Integer.valueOf(paginas);
    }
    public void setNombre(String nomb){
        Nombre.set(nomb);
    }  
    public String getNombre(){
        return Nombre.get();
    }
    public void setPaginas(String nomb){
        Paginas.set(nomb);
    }
    
    public String getPaginas(){
        return Paginas.get();
    }
    public int getPaginasInt(){
        return this.paginas;
    }
    
}
