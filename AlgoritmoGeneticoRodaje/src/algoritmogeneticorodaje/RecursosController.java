/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje;

import algoritmogeneticorodaje.Recursos.Escena;
import algoritmogeneticorodaje.Recursos.Locacion;
import algoritmogeneticorodaje.Recursos.Procesar;
import algoritmogeneticorodaje.Recursos.Recurso;
import java.net.URL;
import java.sql.SQLException;
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
          this.dataRecurso.add(new Recurso("R2", "100"));
          this.dataRecurso.add(new Recurso("R3", "100"));
          this.dataLocacion.add(new Locacion("L1"));
          this.dataLocacion.add(new Locacion("L2"));
          this.dataLocacion.add(new Locacion("L3"));
          this.dataEscena.add(new Escena("E1", "2",1));
          this.dataEscena.add(new Escena("E2", "2",0));
          this.dataEscena.add(new Escena("E3", "2",1));
          this.dataEscena.add(new Escena("E4", "2",1));
          this.dataEscena.add(new Escena("E5", "2",0));
          this.dataEscena.add(new Escena("E6", "2",1));
    
    }
      @FXML
    private void run(ActionEvent event){
        int pj = Integer.valueOf(this.pJornada.getText());
        Procesar p = new Procesar(this.dataRecurso , this.dataLocacion, this.dataEscena,pj);
        
        this.recursoDia.setText(p.printCaledario());
        this.calendario.setText(p.printJornadas());
    }
     @FXML
    private void run2(ActionEvent event){
        int pj = Integer.valueOf(this.pJornada.getText());
        Procesar p = new Procesar(this.dataRecurso , this.dataLocacion, this.dataEscena,pj);
    }
    
}
