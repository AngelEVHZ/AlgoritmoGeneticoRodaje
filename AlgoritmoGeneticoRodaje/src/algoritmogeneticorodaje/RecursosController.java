/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje;

import algoritmogeneticorodaje.Recursos.Escena;
import algoritmogeneticorodaje.Recursos.Locacion;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML 
    private TableView<Recurso> tablaRecursos;
    private TableColumn<Recurso,String> nombreRecurso;
    private TableColumn<Recurso,String> costo;
    ObservableList<Recurso> dataRecurso;
    @FXML 
    private TableView<Locacion> tablaLocacion;
    private TableColumn<Locacion,String> nombreLocacion;
    ObservableList<Locacion> dataLocacion;
    @FXML 
    private TableView<Escena> tablaEscena;
    private TableColumn<Escena,String> nombreEscena;
    private TableColumn<Escena,String> paginas;
    ObservableList<Escena> dataEscena;
    @FXML
    public TextField  nomRecursoTF, costoTF, nomLocacionTF, nomEscenaTF, numPaginasTF;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ;
        //Recursos*********************************************************************
        nombreRecurso = new TableColumn<>("Nombre");
        costo = new TableColumn<>("Costo");
        this.dataRecurso=FXCollections.observableArrayList();
        
        nombreRecurso.setCellValueFactory(new PropertyValueFactory<Recurso,String>("Nombre"));
        costo.setCellValueFactory(new PropertyValueFactory<Recurso,String>("Costo"));
        tablaRecursos.getColumns().addAll(
            nombreRecurso,
            costo
        );
        tablaRecursos.setItems(dataRecurso);
        //Locacio******************************************************************
        nombreLocacion = new TableColumn<>("Nombre");
        this.dataLocacion=FXCollections.observableArrayList();
        
        nombreLocacion.setCellValueFactory(new PropertyValueFactory<Locacion,String>("Nombre"));
        tablaLocacion.getColumns().addAll(
            nombreLocacion
        );
        tablaLocacion.setItems(dataLocacion);
        //Escena******************************************************************
        nombreEscena = new TableColumn<>("Nombre");
        paginas = new TableColumn<>("Paginas");
        this.dataEscena=FXCollections.observableArrayList();
        
        nombreEscena.setCellValueFactory(new PropertyValueFactory<Escena,String>("Nombre"));
        paginas.setCellValueFactory(new PropertyValueFactory<Escena,String>("Paginas"));
        tablaEscena.getColumns().addAll(
            nombreEscena,
            paginas
        );
        tablaEscena.setItems(dataEscena);
        /*try {
            agregarRecurso(;)
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(Eliminar1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
        this.dataEscena.add(new Escena(nomEscenaTF.getText(), numPaginasTF.getText()));
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
}
