/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje.Recursos;

/**
 *
 * @author Valenzuela
 */
public class Cromosoma {
    
    // totales
    int Nescenas = 5; //e  total de escenas
    int Nrecursos = 4; //r total de recursos
    int Nlocalizaciones= 3; //l total de locaciones
    int Njornadas = 5; //j  total de jornadas
    int NpaginasJornada = 5; // numero maximo de paginas que se pueden grabar por jornada
    
    //indices
    // e escena
    // r recurso
    // l localizacion
    // j jornada
    
    //parametros
    
    int A[][] = new int[Nrecursos][Nescenas]; // si el recurso debe participar en la escene
    int Y[][] = new int[Nescenas][Nlocalizaciones]; // si la escena debe ser gravada en la localizacion
    int B[][]= new int[Nlocalizaciones][Njornadas]; // si la localizacion esta disponible en la jornada
    int O[][]= new int[Nrecursos][Njornadas]; // si el recurso esta disponible en la jornada
    int P[]= new int[Nescenas]; // si la escena se filma de dia
    int D[]= new int[Nescenas]; // duracion de la escena 
    int C[]= new int[Nrecursos]; // costo del recurso en moneda/jornada
    
    //Variables
    int Z[][] = new int[Nescenas][Njornadas];// si la escena se programa en la jornada
    int W[][] = new int[Nrecursos][Njornadas]; // si el recurso sera utilizado en la jornada
    int F[] = new int[Nrecursos];// primera jornada del programa en el que el recurso r se utiliza
    int L[] = new int[Nrecursos];// ultima jornada del programa en el que el recurso r se utiliza
    
 
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
