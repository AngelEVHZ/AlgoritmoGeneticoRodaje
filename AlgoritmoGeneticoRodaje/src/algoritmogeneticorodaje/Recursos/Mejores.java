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
public class Mejores {
    ArrayList<Cromosoma> mejores;
    int iteracionGanadora;
    public Mejores(){
        this.mejores = new ArrayList<Cromosoma>();
    }
    public void add(Cromosoma c){
        this.mejores.add(c);
    }
    public void setIteracion(int iter){
        this.iteracionGanadora = iter;
    }
    public int getIteracion(){
        return this.iteracionGanadora;
    }
    public double getFitness(int index){
        return mejores.get(index).funcionObjetivo();
    }
}
