package proiect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller1 {
    
	private  double xOffset;
	private  double yOffset;

	@FXML
    private VBox minimize;

    @FXML
    private VBox close;
	
	@FXML
	private Button ajustaje;
	
    @FXML
    private Button button1;
    
    @FXML
    private Pane mypane;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    public static Stage stage = null;
    
 
    @FXML
    void onClick(ActionEvent event) throws IOException {
    	Parent newScene =null;
    	stage = (Stage) button1.getScene().getWindow();
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
    void onAjustaje(ActionEvent event) throws IOException {
    	Parent newScene =null;
    	stage = (Stage) ajustaje.getScene().getWindow();
    	newScene = FXMLLoader.load(getClass().getResource("../View5.fxml"));
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
    void onClose(MouseEvent event) {
    	
    	Platform.exit();
    	
    }

    @FXML
    void onMinimize(MouseEvent event) {
    	Stage stage = (Stage) minimize.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    @FXML
    void initialize() {

    }
}
