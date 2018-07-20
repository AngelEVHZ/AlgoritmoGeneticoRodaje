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
public class Cromosoma {
    
    ObservableList<Escena> dataEscena;
    ObservableList<Recurso> dataRecurso;
    ArrayList<Jornada> jornadas;
 
    public Cromosoma( int Nescenas,int Nrecursos,int Nlocalizaciones,int Njornadas,int NpaginasJornada,ObservableList<Escena> dataEscena,ObservableList<Recurso> dataRecurso){
            this.jornadas = new ArrayList<Jornada>();
            this.Nescenas=Nescenas;
            this.Nrecursos=Nrecursos;
            this.Nlocalizaciones=Nlocalizaciones;
            this.Njornadas=Njornadas;
            this.NpaginasJornada=NpaginasJornada;
            this.dataEscena = dataEscena;
            this.dataRecurso=dataRecurso;
            this.NtotalPaginas=0;
            declarar(1);
           
           
    }
    public void reiniciar(){
         this.jornadas = new ArrayList<Jornada>();
            this.NtotalPaginas=0;
            declarar(1);
    
    
    }
    
    public void setOrdenFilmacion(int []orden){
        this.ordenFilmacion = orden;
    }
    public void setRandom(){
        ordenFilmacion = new int[this.Nescenas];
        ArrayList<Integer> ids = new ArrayList<Integer>();
        int numero;
        for(int i=0; i<this.Nescenas;i++){
            ids.add(i);
        }
        int j=0;
        while(ids.size()>0){
            numero = (int) (Math.random() * ids.size());
            ordenFilmacion[j]=ids.get(numero);
            j++;
            ids.remove(numero);
        }
        
    }
    public void setOrden(int [] ordenFilmacion){
        this.ordenFilmacion = ordenFilmacion;
    }
    public int[] getOrdenFilmacion(){
        return this.ordenFilmacion;
    }
    public void llenarTodo(){
         Escena e;
        for(int i=0; i<this.Nescenas;i++){       
            e = dataEscena.get(ordenFilmacion[i]);
            NtotalPaginas+=e.getPaginasInt();
        //    System.out.println(e.getNombre());
            
            for(Recurso r: e.getRecurso()){
                 A[r.getId()][ordenFilmacion[i]]=1;//si el recurso debe participar en la escene
            }
             Y[ordenFilmacion[i]][e.getLocacion().getId()]= 1;// si la escena debe ser gravada en la localizacion
             P[i] = e.getTiempo();
             D[i]= e.getPaginasInt();
        }
        
        for(Recurso r:dataRecurso){
            C[r.getId()] = r.getCostoInt();
        }
        
        for(int i=0; i< this.Nrecursos;i++){ F[i]=1000;L[i]=-1;}
        
        for(Jornada j:this.jornadas){
            if(j.getSize()>0){
                for(Escena e2: j.getEscenas() ){
                    Z[e2.getId()][j.getId()]=1;
                    B[e2.getLocacion().getId()][j.getId()]=1;
                    if(e2.getRecursoSize()>0){
                        for(Recurso r: e2.getRecurso()){
                            O[r.getId()][j.getId()]=1;
                            W[r.getId()][j.getId()]=1;
                            
                            if(F[r.getId()] > j.getId()  ){
                                F[r.getId()] =  j.getId(); 
                            }
                            if(L[r.getId()]< j.getId() ){
                                L[r.getId()] = j.getId();
                            }
                        }
                    }
                }
            }
        
        }
       /* System.out.println("");
         for(int i=0; i< this.Nrecursos;i++){ 
             System.out.println("Primera vez del recurso "+ i + " j" +F[i]);
             System.out.println("ultima vez del recurso "+ i + " j" +L[i]);
            
         }
       */
    }
    public void generarJornadas(){
        int tiempoActual;
        int tiempoAnterior;
        int paginasActuales=0;
        int Njornadas=0;
        Escena e;
        Jornada j = new Jornada();
        tiempoActual = dataEscena.get(ordenFilmacion[0]).getTiempo();
        tiempoAnterior = tiempoActual;
        for(int i=0; i< this.ordenFilmacion.length; i++){
            
            e = dataEscena.get(ordenFilmacion[i]);
            //System.out.println("Escena "+ e.getNombre());
            paginasActuales+=e.getPaginasInt();
            tiempoAnterior = tiempoActual;
            tiempoActual=e.getTiempo();
            
            //System.out.println("paginas actuales " +paginasActuales);
            if(tiempoActual!= tiempoAnterior){
              //  System.out.println("Cambio de tiempo");
                j.setId(Njornadas);
                Njornadas+=2;
                paginasActuales=e.getPaginasInt();
                this.jornadas.add(j);
                this.jornadas.add(new Jornada(Njornadas-1));
                j = new Jornada();
              
            }else if(paginasActuales > this.NpaginasJornada){
              
               j.setId(Njornadas);
               jornadas.add(j);
               j = new Jornada();
               Njornadas++;
               paginasActuales=e.getPaginasInt();
            }
             //System.out.println("se añadio a j "+Njornadas);
            j.addEscena(e);
        }
        jornadas.add(j);
        j.setId(Njornadas);
        
      //  System.out.println(Njornadas);
       // System.out.println(jornadas.size());
       this.Njornadas = Njornadas+1;
       declarar(2);
       llenarTodo();
   
    }
    public void prinOrden(){
        System.out.println("");
        for(int i=0; i< this.Nescenas;i++){
            System.out.print(this.ordenFilmacion[i]+" ");   
        
        }
        System.out.println("");
    
    }
    
    public String printJornadas(){
        String texto="";
        String t="";
        System.out.println("/**************/");
        for(Jornada j: this.jornadas){
           //System.out.println("id "+j.getId()+ " tam "+j.getSize());
           
            System.out.println("Jornada "+ j.getId());
            texto+="Dia "+ (j.getId()+1)+"\n";
            if(j.getSize()>0){
                for(Escena e : j.getEscenas()){
                    if( e.getTiempo()==1){t="Dia";}
                    else t="Noche";
                    System.out.println("    -"+e.getNombre()+ " Tiempo " + t );
                    texto+="\t-"+e.getNombre()+ " Tiempo " + e.getTiempo()+"\n";
                }
            }else{
               
                System.out.println("    -Libre ");
                texto+="\t-Libre\n";
            
            }
        
        }
       return texto;
    }
    
    
    int ordenFilmacion[];
    
    // totales
    int Nescenas; //e  total de escenas
    int Nrecursos; //r total de recursos
    int Nlocalizaciones; //l total de locaciones
    int Njornadas; //j  total de jornadas
    int NpaginasJornada; // numero maximo de paginas que se pueden grabar por jornada
    int NtotalPaginas;//total de paginas
    //indices
    // e escena
    // r recurso
    // l localizacion
    // j jornada
    
    //parametros
    
    int A[][]; // si el recurso debe participar en la escene
    int Y[][]; // si la escena debe ser gravada en la localizacion
    int B[][]; // si la localizacion esta disponible en la jornada
    int O[][]; // si el recurso esta disponible en la jornada
    int P[]; // si la escena se filma de dia
    int D[]; // duracion de la escena 
    int C[]; // costo del recurso en moneda/jornada
    
    //Variables
    int Z[][];// si la escena se programa en la jornada
    int W[][]; // si el recurso sera utilizado en la jornada
    int F[];// primera jornada del programa en el que el recurso r se utiliza
    int L[];// ultima jornada del programa en el que el recurso r se utiliza
    
    public int [][] getA(){
        return this.A;
    }
    public int [][] getW(){
        return this.W;
    }
    public int getNJornadas(){
        return this.Njornadas;
    }
    
    public void declarar(int parte){
        if(parte==1){
            A = new int[Nrecursos][Nescenas]; // si el recurso debe participar en la escene
            Y = new int[Nescenas][Nlocalizaciones]; // si la escena debe ser gravada en la localizacion
           
            P = new int[Nescenas]; // si la escena se filma de dia
            D = new int[Nescenas]; // duracion de la escena 
            C = new int[Nrecursos]; // costo del recurso en moneda/jornada
         
        }else{    
            B = new int[Nlocalizaciones][Njornadas]; // si la localizacion esta disponible en la jornada
            O = new int[Nrecursos][Njornadas]; // si el recurso esta disponible en la jornada
            
            
            //Variables
            Z = new int[Nescenas][Njornadas];// si la escena se programa en la jornada
            W = new int[Nrecursos][Njornadas]; // si el recurso sera utilizado en la jornada
            F = new int[Nrecursos];// primera jornada del programa en el que el recurso r se utiliza
            L = new int[Nrecursos];// ultima jornada del programa en el que el recurso r se utiliza
        }
    }
    
    
    public int funcionObjetivo(){
        //minimo
        int total=0;
        for(int r=0;r<Nrecursos;r++){
            total+= C[r] * (L[r] - F[r] + 1);
        }
        return total;
    }
    // un recurso sera utilizado en una jornada solo si existe una escena programada para esa jornada que lo requiera
    public boolean restriccion1(int r, int j, int e){
        return (W[r][j] >= (A[r][e] * Z[e][j]) );
    }
    // un recurso no debe ser asignado a una jornada si no es necesario
    public boolean restriccion2(int r, int j){
        int total=0;
        for(int e=0;e<Nescenas;e++){
            total+= A[r][e] * Z[e][j];
        }
        return (W[r][j]<= total);
    }
    // la primera jornada en que un recurso es requerido debe ser menor que todas las jornadas en las cuales el recurso
    // es requerido
    public boolean restriccion3(int r, int j){
        return ( F[r] <= ( j * W[r][j] + Njornadas * (1 - W[r][j]) )   );
    }
    // la ultima jornada en que un recurso es utilizado debe ser mayor que todas las jornadas en que un recurso es requerido
    public boolean restriccion4(int r, int j){
        return ( L[r] >= ( j * W[r][j] ) ) ;
    }
    // la duracion total de las escenas programadas en una jornada no puede superar el limite establecido
    public boolean restriccion5( int j){
        int total=0;
        for(int e=0; e < Nescenas;e++){
            total+=Z[e][j] * D[e];
        }
        return total<=NpaginasJornada;
    }
    // una escena debe ser programada solo en una jornada
    public boolean restriccion6( int e){
        int total=0;
        for(int j=0; j < Njornadas;j++){
            total+=Z[e][j];
        }
        return total == 1;
    }
    // todas las escenas que se filman en una jornada deben ser del mismo tipo
    public boolean restriccion7( int j){
       int min=Nescenas;
       boolean flag=true;
        for(int e=0; e < Nescenas;e++){
            if(Z[e][j]== 1 ){
                if(min>e)min=e;
                
                if(P[min] != P[e]){
                    flag=false;
                    e=Nescenas;
                }
            }
        
        }
       return flag; 
    }
    //solo se puede programar jornadas del mismo tipo de forma consecutiva
    //hacer un cambio requiere dejar un dia libre
    public boolean restriccion8( int j){
        if( j >= (Njornadas-1)  )return true;
        return (tipoJornada(j) == tipoJornada(j+1)  );
       
    }
    
    public int tipoJornada( int j){
       int tipo=0;
        for(int e=0; e < Nescenas;e++){
            if(Z[e][j] == 1 ){
                tipo=P[e];
                e=Nescenas;
            }
        }
       return tipo; 
    }

}
