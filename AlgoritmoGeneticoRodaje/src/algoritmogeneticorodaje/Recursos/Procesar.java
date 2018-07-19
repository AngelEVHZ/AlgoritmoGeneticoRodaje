/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

import javafx.collections.ObservableList;

/**
 *
 * @author Valenzuela
 */
public class Procesar {
     ObservableList<Recurso> dataRecurso;
     ObservableList<Locacion> dataLocacion;
     ObservableList<Escena> dataEscena;
     int Nescenas;
     int Nrecursos;
     int Nlocalizaciones;
     int Njornadas;
     int paginasJornada;
     
     public Procesar(ObservableList<Recurso> dataRecurso, ObservableList<Locacion> dataLocacion,ObservableList<Escena> dataEscena,int pj){
         this.dataRecurso = dataRecurso;
         this.dataLocacion = dataLocacion;
         this.dataEscena = dataEscena;
         this.paginasJornada = pj;
         this.Nescenas= this.dataEscena.size();
         this.Nrecursos= this.dataRecurso.size();
         this.Nlocalizaciones=this.dataLocacion.size();
         this.Njornadas=10;
         
         
         setIds();
         crearCromosoma();
        
     }
     
    public void crearCromosoma(){
        
        Cromosoma c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.llenarTodo();
    
    }
     
     public void setIds(){
         for(Recurso r: dataRecurso){
             r.setId(dataRecurso.indexOf(r));
         }
         for(Locacion l : dataLocacion){
             l.setId(dataLocacion.indexOf(l));
         }
         for(Escena e: dataEscena){
             e.setId(dataEscena.indexOf(e));
         }
     }
     
     
     public void print(){
         System.out.println("Recursos");
          for(Recurso e: dataRecurso){
              System.out.println(e.getNombre()+" "+e.getId());
         }
         System.out.println("Locaciones");
         for(Locacion l : dataLocacion){
            System.out.println(l.getNombre()+" "+l.getId());
         }
         System.out.println("Escenas");
         for(Escena e: dataEscena){
             System.out.println(e.getNombre()+" "+e.getId());
         }
     
     
     }
    
}
