/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje;

import algoritmogeneticorodaje.Recursos.Cromosoma;
import algoritmogeneticorodaje.Recursos.Escena;
import algoritmogeneticorodaje.Recursos.Locacion;
import algoritmogeneticorodaje.Recursos.Mejores;
import algoritmogeneticorodaje.Recursos.Procesar;
import algoritmogeneticorodaje.Recursos.Recurso;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Julieta Escobar
 */
public class RecursosController implements Initializable {
    Stage stage;
    @FXML TextArea calendario, recursoDia;
    @FXML
    MenuButton menuDiaNoche;
    @FXML 
    Label escenalabel,escena2label,recursoslabel,locacionlabel;
    @FXML 
    private TableView<Recurso> tablaRecursos;
    @FXML 
    private TableView<Recurso> tablaRecursos2;
    private TableColumn<Recurso,String> nombreRecurso;
    private TableColumn<Recurso,String> costo;
    ObservableList<Recurso> dataRecurso;
    @FXML 
    private TableView<Locacion> tablaLocacion;
    @FXML 
    private TableView<Locacion> tablaLocacion2;
    private TableColumn<Locacion,String> nombreLocacion;
    ObservableList<Locacion> dataLocacion;
    @FXML 
    private TableView<Escena> tablaEscena;
    @FXML 
    private TableView<Escena> tablaEscena2;//para asignar
    @FXML 
    private TableView<Escena> tablaEscena3;//para asignar
    private TableColumn<Escena,String> nombreEscena;
    private TableColumn<Escena,String> paginas;
    ObservableList<Escena> dataEscena;
    @FXML
    public TextField  nomRecursoTF, costoTF, nomLocacionTF, nomEscenaTF, numPaginasTF,pJornada;
    
    
    Recurso recursoSelected=null;
    Escena escenaSelected=null;
    Escena escena2Selected=null;
    Locacion locacionSelected=null;
    int tiempoEscena=1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iteraciones = new ArrayList<int[]>();
        iteraciones2 = new ArrayList<int[]>();
        menuDiaNoche.setText("Dia");
        this.tiempoEscena=1;
        MenuItem m1 = new MenuItem("Dia");
        MenuItem m2 = new MenuItem("Noche");
        
        m1.setOnAction(event -> {
             menuDiaNoche.setText("Dia");
                this.tiempoEscena=1;
        });
        m2.setOnAction(event -> {
             menuDiaNoche.setText("Noche");
                this.tiempoEscena=0;
        });
        menuDiaNoche.getItems().addAll(m1,m2);
  
        //Recursos*********************************************************************
        nombreRecurso = new TableColumn<>("Recurso");
        costo = new TableColumn<>("Costo");
        this.dataRecurso=FXCollections.observableArrayList();
        
        nombreRecurso.setCellValueFactory(new PropertyValueFactory<Recurso,String>("Nombre"));
        costo.setCellValueFactory(new PropertyValueFactory<Recurso,String>("Costo"));
        tablaRecursos.getColumns().addAll(
            nombreRecurso,
            costo
        );
        tablaRecursos.setItems(dataRecurso);
        tablaRecursos2.getColumns().addAll(
            nombreRecurso
        );
        tablaRecursos2.setItems(dataRecurso);
        //Locacio******************************************************************
        nombreLocacion = new TableColumn<>("Locacion");
        this.dataLocacion=FXCollections.observableArrayList();
        
        nombreLocacion.setCellValueFactory(new PropertyValueFactory<Locacion,String>("Nombre"));
        tablaLocacion.getColumns().addAll(
            nombreLocacion
        );
        tablaLocacion.setItems(dataLocacion);
        tablaLocacion2.getColumns().addAll(
            nombreLocacion
        );
        tablaLocacion2.setItems(dataLocacion);
        //Escena******************************************************************
        nombreEscena = new TableColumn<>("Escena");
        paginas = new TableColumn<>("Paginas");
        this.dataEscena=FXCollections.observableArrayList();
        
        nombreEscena.setCellValueFactory(new PropertyValueFactory<Escena,String>("Nombre"));
        paginas.setCellValueFactory(new PropertyValueFactory<Escena,String>("Paginas"));
        tablaEscena.getColumns().addAll(
            nombreEscena,
            paginas
        );
        tablaEscena.setItems(dataEscena);
        tablaEscena2.getColumns().addAll(
            nombreEscena
        );
        tablaEscena2.setItems(dataEscena);
         tablaEscena3.getColumns().addAll(
            nombreEscena
        );
        tablaEscena3.setItems(dataEscena);
        
        ///codigo de seleccion
        tablaEscena2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                Escena escena = this.tablaEscena2.getSelectionModel().getSelectedItem();
                this.escenaSelected=escena;
                escenalabel.setText("Escena: "+escena.getNombre());
                if(escena.getLocacion()!=null)
                    locacionlabel.setText("Locacion: "+escena.getLocacion().getNombre());
                else{
                    locacionlabel.setText("Locacion Sin asignar");
                }
                
            }
        });
            
        tablaRecursos2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
              
                this.recursoSelected =this.tablaRecursos2.getSelectionModel().getSelectedItem();
                  
            }
        });
          tablaEscena3.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                
                Escena escena = this.tablaEscena3.getSelectionModel().getSelectedItem();
                this.escena2Selected=escena;
                escena2label.setText("Escena: "+escena.getNombre());
                recursosDeEscena(escena);
           
     
            }
        });
         tablaLocacion2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.locacionSelected =this.tablaLocacion2.getSelectionModel().getSelectedItem();
            }
        });
         add();
        
    }    
    @FXML
    private void agregarRecurso(ActionEvent event){
        this.dataRecurso.add(new Recurso(nomRecursoTF.getText(), costoTF.getText()));
        nomRecursoTF.setText("");
        costoTF.setText("");
    }
    
    @FXML
    private void agregarLocacion(ActionEvent event){
        this.dataLocacion.add(new Locacion(nomLocacionTF.getText()));
        nomLocacionTF.setText("");
    }
    
    @FXML
    private void agregarEscena(ActionEvent event){

        this.dataEscena.add(new Escena(nomEscenaTF.getText(), numPaginasTF.getText(),this.tiempoEscena));
        nomEscenaTF.setText("");
        numPaginasTF.setText("");
    }
    
    @FXML
    private void eliminarRecurso(ActionEvent event){
        Recurso recurso = this.tablaRecursos.getSelectionModel().getSelectedItem();
        this.dataRecurso.remove(recurso);
    }
    
    @FXML
    private void eliminarLocacion(ActionEvent event){
        Locacion locacion = this.tablaLocacion.getSelectionModel().getSelectedItem();
        this.dataLocacion.remove(locacion);
    }
    
    @FXML
    private void eliminarEscena(ActionEvent event){
        Escena escena = this.tablaEscena.getSelectionModel().getSelectedItem();
        this.dataEscena.remove(escena);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
     @FXML
    private void asignarLocacion(ActionEvent event){
        if(this.escenaSelected!=null && locacionSelected!=null){
             this.escenaSelected.setLocacion(locacionSelected);
            escenalabel.setText("Escena: "+escenaSelected.getNombre());
                if(escenaSelected.getLocacion()!=null)
                    locacionlabel.setText("Locacion: "+escenaSelected.getLocacion().getNombre());
                else{
                    locacionlabel.setText("Sin asignar");
                }
           
        }
    }
    
    public void recursosDeEscena(Escena e)
    {
        String rs="Recursos: ";
        if(e.getRecurso().size()>0){
            for(Recurso r: e.getRecurso()){
                rs+=r.getNombre()+" ,";
            }
            this.recursoslabel.setText(rs);
        }else{
            this.recursoslabel.setText("Sin recursos asignados");
        }
        
    }
    
     @FXML
    private void asignarRecurso(ActionEvent event){
       
        if(this.escena2Selected!=null && recursoSelected!=null){
        
            escena2Selected.addRecurso(recursoSelected);
            recursosDeEscena(escena2Selected);
            
        }
    }
    public void add(){
          this.dataRecurso.add(new Recurso("R1", "100"));
          this.dataRecurso.add(new Recurso("R2", "500"));
          this.dataRecurso.add(new Recurso("R3", "100"));
          
          this.dataLocacion.add(new Locacion("L1"));
         this.dataRecurso.add(new Recurso("R4", "200"));
          this.dataRecurso.add(new Recurso("R5", "350"));
          this.dataRecurso.add(new Recurso("R6", "300"));
          this.dataRecurso.add(new Recurso("R7", "100"));
          this.dataRecurso.add(new Recurso("R8", "150"));
          this.dataRecurso.add(new Recurso("R9", "150"));
         

          this.dataEscena.add(new Escena("E1", "2",1));
          this.dataEscena.add(new Escena("E2", "5",0));
          this.dataEscena.add(new Escena("E3", "3",1));
         this.dataEscena.add(new Escena("E4", "2",1));
          this.dataEscena.add(new Escena("E5", "5",0));
          this.dataEscena.add(new Escena("E6", "7",1));
          this.dataEscena.add(new Escena("E7", "2",1));
          this.dataEscena.add(new Escena("E8", "4",0));
          this.dataEscena.add(new Escena("E9", "3",1));
    
          for(Escena e:dataEscena)
              e.setLocacion(dataLocacion.get(0));
          
    }
    
    ArrayList<int[]> iteraciones;
    ArrayList<int[]> iteraciones2;
    ArrayList<Mejores> mejores1,mejores2;
      @FXML
    private void run(ActionEvent event){
       mejores1 = new ArrayList<Mejores>();
       mejores2 = new ArrayList<Mejores>(); 
       ArrayList<Cromosoma> poblacion;
        
        int pj = Integer.valueOf(this.pJornada.getText());
        
        
        for(int i=0; i< 100; i++){
            Procesar p = new Procesar(this.dataRecurso , this.dataLocacion, this.dataEscena,pj);
            poblacion = p.generarPoblacionInicial();
            
          
            p.setPoblacion(poblacion);
            p.run(1);
            this.recursoDia.setText(p.printCaledario());
            this.calendario.setText(p.printJornadas());
            int[] e = new int[2];
            mejores1.add(p.getMejores());
            e[0]= p.getIteraciones();
            e[1]= p.getMejorF();
            p.printHistorial();
            iteraciones.add(e);
            
            
            Procesar p2 = new Procesar(this.dataRecurso , this.dataLocacion, this.dataEscena,pj);
            p2.setPoblacion(poblacion);
            p2.run(2);
            this.recursoDia.setText(p2.printCaledario());
            this.calendario.setText(p2.printJornadas());
            mejores2.add(p2.getMejores());
            int[] e2 = new int[2];
            e2[0]= p2.getIteraciones();
            e2[1]= p2.getMejorF();
            p2.printHistorial();
            iteraciones2.add(e2);
            
            
            
        }
        monteCarlo(mejores1);
        monteCarlo(mejores2);
        
    }
    
    public void monteCarlo(ArrayList<Mejores> mejores){
        System.out.println("Monte carlo/**********************/");
        int max=0;
        int actual=0;
        double iteraciones=0;
        System.out.println("tabla");
       for(Mejores m:mejores){
           for(int i=0; i< m.getIteracion();i++){
               System.out.print(m.getFitness(i)+",");
           }
           System.out.println("");
       }
        
        
        for(Mejores m:mejores){
           iteraciones+=m.getIteracion();
            actual = m.getIteracion();
            if(actual>max){
                max = actual;
            }
        }
        iteraciones/=mejores.size();
        System.out.println("El promedio de iteraciones es "+ iteraciones);
        int cont=0;
        double montecarlo[] = new double[max];
        for(int i=0; i< max;i++){
            cont = 0;
            for(Mejores m:mejores){
               if(i < m.getIteracion()){
                  cont++;
                  montecarlo[i]+=m.getFitness(i);
               }
            }
            montecarlo[i]/=cont;
        }
        for(int i=0; i< max;i++){
            System.out.println(montecarlo[i]);
        }
        
    
        System.out.println("/************************************/");
    }
    
     @FXML
    private void run2(ActionEvent event){
        mejores2 = new ArrayList<Mejores>();
        int pj = Integer.valueOf(this.pJornada.getText());
          for(int i=0; i< 100; i++){
            Procesar p = new Procesar(this.dataRecurso , this.dataLocacion, this.dataEscena,pj);
            p.setPoblacion(p.generarPoblacionInicial());
            p.run(2);
            this.recursoDia.setText(p.printCaledario());
            this.calendario.setText(p.printJornadas());
            mejores2.add(p.getMejores());
            int[] e = new int[2];
            e[0]= p.getIteraciones();
            e[1]= p.getMejorF();
            p.printHistorial();
            iteraciones2.add(e);
          }
          monteCarlo(mejores2);
    }
     @FXML
    private void estadisticas(ActionEvent event){
        printIteraciones();
    }
    
    
    public void printIteraciones(){
        System.out.println("Primero");
        double p1=0,p2=0;
        double pf1=0,pf2=0;
        double t1=0, t2=0;
        t1 = iteraciones.size();
        t2 = iteraciones2.size();
        
        for(int[]e:iteraciones){
            p1+=e[0];
            pf1+=e[1];
        }
       
        for(int[]e:iteraciones2){
           p2+=e[0];
           pf2+=e[1];
        }
        p1/=t1;
        p2/=t2;
        pf1/=t1;
        pf2/=t2;
        System.out.println("T "+t1);
        System.out.println("Promedio Iteraciones 1 "+p1);
        System.out.println("Promedio Fitnes 1 "+pf1);
        System.out.println("");
        System.out.println("T "+t2);
        System.out.println("Promedio Iteraciones 2 "+p2);
        System.out.println("Promedio Fitnes 2 "+pf2);
        iteraciones = new ArrayList<int[]>();
        iteraciones2 = new ArrayList<int[]>();
    
    
    }
    
}
