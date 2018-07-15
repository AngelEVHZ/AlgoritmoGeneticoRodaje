/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticorodaje;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Valenzuela
 */
public class FXMLDocumentController implements Initializable {
    Stage stage;
    @FXML
    private Label label;
    
    @FXML
    private void abrirVentana(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Recursos.fxml"));
        Parent root = (Parent)loader.load();
        RecursosController controller = (RecursosController)loader.getController();
        controller.setStage(this.stage);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stage = new Stage();
    }    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
}
