/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Valenzuela
 */
public class Escena {
    
    private final SimpleStringProperty Nombre;
    private final SimpleStringProperty Paginas;
    private int paginas;
    private int tiempo;
    int id;
    int rSize=0;
    
    Locacion locacion=null;
    ArrayList<Recurso> recursos;
    
    public Escena(String nomb,String paginas,int tiempo){
        this.Nombre = new SimpleStringProperty(nomb);
        this.Paginas = new SimpleStringProperty(paginas);
        this.paginas = Integer.valueOf(paginas);
        this.tiempo=tiempo;
        recursos=new ArrayList<Recurso>();
    }
    
    public ArrayList<Recurso> getRecurso(){
        return this.recursos;
    }
    
    public int getRecursoSize(){
        return this.rSize;
    }
  
    public void addRecurso(Recurso recurso){

        if(this.recursos.indexOf(recurso)>=0)
            return;
        rSize++;
        this.recursos.add(recurso);
    }
    public void setLocacion(Locacion locacion){
        this.locacion=locacion;
    }
    public Locacion getLocacion(){
        if(this.locacion!=null)
            return this.locacion;
        return null;
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
    
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public int getTiempo(){
        return this.tiempo;
    }
}
