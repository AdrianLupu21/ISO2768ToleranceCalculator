 package proiect;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller3 {
	
	private double xOffset;
	private double yOffset;
	
    @FXML
    private Button start;

    @FXML
    private Button back;
    
    @FXML
    private VBox minimize;
    
    @FXML
    private Stage stage;
    
    @FXML
    void onBack(ActionEvent event) throws IOException {
    	Parent newScene = null;
    	
    	stage = (Stage) back.getScene().getWindow();
    	
    	newScene = FXMLLoader.load(getClass().getResource("../View2.fxml"));
    	
    	Scene scene = new Scene(newScene);
    	stage.setScene(scene);
    	stage.show();
    	
    	  newScene.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  xOffset = event.getSceneX();
                  yOffset = event.getSceneY();
              }
          });
    	  
    	  newScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  stage.setX(event.getScreenX() - xOffset);
                  stage.setY(event.getScreenY() - yOffset);
              }
          });
    	
    }

    @FXML
    void onStart(ActionEvent event) throws IOException {
    	
    	Parent newScene = null;
    	
    	stage = (Stage) start.getScene().getWindow();
    	
    	newScene = FXMLLoader.load(getClass().getResource("../View4.fxml"));
    	
    	Scene scene = new Scene(newScene);
    	stage.setScene(scene);
    	stage.show();
    	
    	  newScene.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  xOffset = event.getSceneX();
                  yOffset = event.getSceneY();
              }
          });
    	  
    	  newScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  stage.setX(event.getScreenX() - xOffset);
                  stage.setY(event.getScreenY() - yOffset);
              }
          });
    }
    
    @FXML
    void onMinimize(MouseEvent event){
    	Stage stage = (Stage) minimize.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    @FXML
    void onClose(MouseEvent event){
    	Platform.exit();
    }

}
