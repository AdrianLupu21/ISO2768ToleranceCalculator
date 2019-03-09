package proiect;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private double xOffset;
	private double yOffset;

	public static void main(String[] args) {
		Application.launch();
	}
	public static Stage Stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage = primaryStage;
		Pane myPane = (Pane) FXMLLoader.load(getClass().getResource("../View.fxml"));
		Stage.setScene(new Scene(myPane));
		Stage.setTitle("proiectTCD");
		Stage.setMinHeight(700);
		Stage.setMinWidth(1000);
    	Stage.initStyle(StageStyle.UNDECORATED);
    	
    	  myPane.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  xOffset = event.getSceneX();
                  yOffset = event.getSceneY();
              }
          });
    	  
    	  myPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                  Stage.setX(event.getScreenX() - xOffset);
                  Stage.setY(event.getScreenY() - yOffset);
              }
          });
    	
		Stage.show();
		Main.Stage.setResizable(true);
	}
}
