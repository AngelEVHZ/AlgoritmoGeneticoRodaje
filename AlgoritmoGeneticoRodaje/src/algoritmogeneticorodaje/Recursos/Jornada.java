/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

import java.util.ArrayList;

/**
 *
 * @author Valenzuela
 */
public class Jornada {
    private int id;
    int size=0;
    ArrayList<Escena> escenas;
    
    public Jornada(){
        escenas = new ArrayList<Escena>();
    }

    Jornada(int id) {
       this.id = id;
    }
    
    public int getSize(){
        return size;
    }
    public void addEscena(Escena e){
        
        if(this.escenas.indexOf(e)>=0)
            return;
        this.size++;
        this.escenas.add(e);
    }
    
    public ArrayList<Escena> getEscenas(){
        return this.escenas;
    }
    
    
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    
}
