/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Valenzuela
 */
public class Procesar {
     ObservableList<Recurso> dataRecurso;
     ObservableList<Locacion> dataLocacion;
     ObservableList<Escena> dataEscena;
      ArrayList<Cromosoma> cromosomasList;
    ArrayList<Cromosoma> childCromosomasList;
    Cromosoma ganador;
     int Nescenas;
     int Nrecursos;
     int Nlocalizaciones;
     int Njornadas;
     int paginasJornada;
     
     public Procesar(ObservableList<Recurso> dataRecurso, ObservableList<Locacion> dataLocacion,ObservableList<Escena> dataEscena,int pj){
         this.cromosomasList = new ArrayList<Cromosoma>();
         this.dataRecurso = dataRecurso;
         this.dataLocacion = dataLocacion;
         this.dataEscena = dataEscena;
         this.paginasJornada = pj;
         this.Nescenas= this.dataEscena.size();
         this.Nrecursos= this.dataRecurso.size();
         this.Nlocalizaciones=this.dataLocacion.size();
         this.Njornadas=10;
         
         
         setIds();
         addPoblacionInicial();
         search();
        //crearCromosoma();
     }
     
    public void search(){
        int index = 0;
        
        for(int i=0; i<10; i++){
            //System.out.println("ITERACION "+i+ " #HIJOS " + this.cromosomasList.size());
            //System.out.println("SELECTION");
            index = selection();
            //System.out.println("CROSSOVER");
            this.childCromosomasList = crossover(index);
            mutation( this.childCromosomasList);
            this.cromosomasList = this.childCromosomasList;
            limpiar();
        }
         index = selection();
         System.out.println("Ganador");
         //this.cromosomasList.get(index).printJornadas();
         System.out.println("Fitness "+  this.cromosomasList.get(index).funcionObjetivo());
         this.ganador =  this.cromosomasList.get(index);
    }
    public String printJornadas(){
        return ganador.printJornadas();
    }
    public String printCaledario(){
        String texto="\n";
        texto+="Costo Total: $"+ ganador.funcionObjetivo()+" \n";
        int[][] W = ganador.getW();
        int jornadas = ganador.getNJornadas();
        System.out.println("");
        System.out.print("RE|");
        texto+="RE|\t";
        for(int i=0; i< jornadas; i++){
            System.out.print("D"+(i+1)+"|");
             texto+="D"+(i+1)+"|\t";
        }
        System.out.println("");
         texto+="\n";
        for(int i = 0; i < this.Nrecursos;i++){
            System.out.print(this.dataRecurso.get(i).getNombre()+"|");
             texto+=this.dataRecurso.get(i).getNombre()+"|\t";
            for(int j =0; j < jornadas; j++){
                if(W[i][j]!=0){
                     System.out.print("X |");
                    texto+="X |\t";
                
                }else{
                     System.out.print("  |");
                     texto+="   |\t";
                }
            }
            texto+="\n";
            
            System.out.println("");
            
        }
      return texto;
    }
    
     public void limpiar(){
        int size = this.cromosomasList.size();
        if(size>10){
            int index =0;
            double minFitness=0;
            double currentFitness=0;
            for(int i=0; i < cromosomasList.size(); i++){
                currentFitness =  cromosomasList.get(i).funcionObjetivo() ;
                if(currentFitness > minFitness){
                    minFitness = currentFitness;
                    index = i;
                }
            }
          
            this.cromosomasList.remove(index);
            limpiar();
        }
    
    
    }
    public void mutation(ArrayList<Cromosoma> children) {
        int [] m  = new int[this.Nescenas];
        int random;
        Cromosoma c;
        int temp;
        int curr;
        for(int i=0; i< children.size();i++){
            c = children.get(i);
           
            m = c.getOrdenFilmacion();
            random =  (int)(Math.random() * (100+1)); 
            if(random<= 50){
                // System.out.println("before");
                //c.prinOrden();
                for(int j=0;j< m.length; j++){
                     random =  (int)(Math.random() * (100+1)); 
                     if(random<= 50){
                        if( j <(this.Nescenas-1) ){
                            curr = m[j];
                            temp = m[j+1];
                            m[j] = temp;
                            m[j+1] = curr;

                         }
                     }
                }
                c.reiniciar();
                c.setOrden(m);
                c.generarJornadas();
                //c.prinOrden();
            }
        }
   
   
    }
    public void addPoblacionInicial(){
        Cromosoma c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.generarJornadas();
        this.cromosomasList.add(c);
        c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.generarJornadas();
        this.cromosomasList.add(c);
        c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.generarJornadas();
        this.cromosomasList.add(c);
        c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.generarJornadas();
        this.cromosomasList.add(c);
    
        for(Cromosoma cr : this.cromosomasList){
            cr.printJornadas();
            //System.out.println("Fitness " +cr.funcionObjetivo());
        }
    }
    public int selection(){
        int index =0;
        double minFitness=0;
        double currentFitness=0;
        for(int i=0; i < cromosomasList.size(); i++){
            currentFitness =cromosomasList.get(i).funcionObjetivo();
            if(currentFitness < minFitness){
                minFitness = currentFitness;
                index = i;
            }
        }
        return index;
    }
    public ArrayList<Cromosoma> crossover(int index){
        int[] mparent1 = cromosomasList.get(index).getOrdenFilmacion();
        ArrayList<Cromosoma> children = new ArrayList<Cromosoma>();
        for(int i=0; i < cromosomasList.size();i++){
            if(i!= index){
                int[] mparent2 = cromosomasList.get(i).getOrdenFilmacion();
                Cromosoma child = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
                child.setOrdenFilmacion( newChild(mparent1,mparent2));
                child.generarJornadas();
                
                Cromosoma child2 = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
                child2.setOrdenFilmacion( newChild(mparent2,mparent1));
                child2.generarJornadas();
                
               // child.prinOrden();
               // child2.prinOrden();
                
                children.add(child);
                children.add(child2);  
            }
        }
        return children;
    }
    public int [] newChild(int [] mp1, int[] mp2){
        int[] mp1c = mp1.clone();
        int[] mp2c = mp2.clone();
         int cambios = this.Nescenas/3;
         int buscar =0;
        int j=0;

             
        int child[] = new int[this.Nescenas];
        for(int i =0;i < this.Nescenas;i++)child[i]=-1;
        
        for(int i =0;i < cambios;i++){
            buscar = mp1c[i];
            mp1c[i]=-1;
            
            j=0;
            while(buscar != mp2c[j]){
                j++;
            }
            mp2c[j]=-1;
            child[j]=buscar;
         }
        j=0;
        for(int i =0;i < this.Nescenas;i++){
            
            if(mp1c[i]!=-1){
                j=0;
                while(child[j]!=-1){
                    j++;
                }
                child[j]=mp1c[i];
                mp1c[i]=-1;
            }
        
        }
        /*System.out.println("Nuevo orden");
        for(int i=0; i< this.Nescenas;i++){
            System.out.print(child[i]+" ");   
        
        }
        System.out.println("");
        */
       
        return child;
    }
    public void crearCromosoma(){
        
        Cromosoma c = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c.setRandom();
        c.generarJornadas();
        c.prinOrden();
        
        Cromosoma c2 = new Cromosoma(Nescenas,Nrecursos,Nlocalizaciones,Njornadas,paginasJornada,this.dataEscena,this.dataRecurso);
        c2.setRandom();
        c2.generarJornadas();
        c2.prinOrden();
        
        newChild(c.getOrdenFilmacion(),c2.getOrdenFilmacion());

    
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
