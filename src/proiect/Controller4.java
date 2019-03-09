package proiect;
import java.io.File;
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller4 {
	
	private double xOffset;
	private double yOffset;
	
	final File f = new File(Controller4.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	
	private int slide = 0;
	
	//linia lipeste plan
	private Line ln = null;
	
	//liniile de incadrare
	private Line ln1,ln2 = null;
	
	//tau
	private Line[] tau1; //tau A
	private Line[] tau2; //tau 1
	private Line[] tau3; //tau 2
	private Line[] tau21;//tau 1.1
	private Line[] tau31; //tau 2.1
	
	//Cota slide 1
	private Line ann,ann1,ann2,ann3,ann4;
	private Text an;
	
	//litera A(t), cifra 1(t2), cifra 2(t3), semn paralel(p)
	private Text t,t2,t11,t3,t31,p;
	
	//linii perpendiuclare
	private Line perp1,perp2;
	
	//test
	private Text myText1,myText2,myText3;
	
	@FXML
	private VBox minimize;
	
	@FXML
	private VBox vbox;
	
    @FXML
    private Text text;
	
	@FXML
	private AnchorPane anchor;
	
    @FXML
    private Button next;

    @FXML
    private Button back;

    @FXML
    private Button backToDrawings;
    
    @FXML
    private Stage stage;

    @FXML
    void onBack(ActionEvent event) throws IOException {
    	switch(slide) {
    		case(0):{
    			updateGui("View3.fxml");
    		}
    		break;
    		case(1):{
    			vbox.getChildren().removeAll(myText1);
				anchor.getChildren().removeAll(ln,tau1[0],tau1[1],tau1[2],t);
				slide--;
    		}
    		break;
    		case(2):{	
    			vbox.getChildren().removeAll(myText2,myText3);
    			anchor.getChildren().removeAll(tau2[0],tau2[1],tau2[2],tau21[0],tau21[1],tau21[2],
    											tau3[0],tau3[1],tau3[2],tau31[0],tau31[1],tau31[2],
    											t2,t3,t11,t31,perp1,perp2,p,ln1,ln2,ann,ann1,ann2,
    											ann3,ann4,an);
    			next.setStyle("visibility: visible;");
    			slide--;
    		}
    		break;
    		case(3):{
    			vbox.getChildren().add(next);
    		}
    		break;
    	}
    }

    @FXML
    void onBackToDrawings(ActionEvent event) throws IOException {
    	updateGui("View2.fxml");
    
    }
    
    //ca sa pot folosi metoda de mai multe ori 
    void updateGui(String fisier) throws IOException {
    	Parent newScene = null;
    	
    	stage = (Stage) backToDrawings.getScene().getWindow();
    	
    	newScene = FXMLLoader.load(getClass().getResource("../"+fisier));
    	
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
    void onMinimize(MouseEvent event) {
    	Stage stage = (Stage) minimize.getScene().getWindow();
    	stage.setIconified(true);
    }
    
    @FXML
    void onClose(MouseEvent event) {
    	Platform.exit();
    }
    @FXML
    void onNext(ActionEvent event) throws Exception {
    	switch(slide) {
    		case 0 :{
    			
    			myText1 = new Text(400,400,"1.Se duce un plan adiacent \n la suprafata A.");
    			myText1.setFont(Font.font("Helvetica", 20));
    			
    		    ln = new Line(10,300,300,300);
    			ln.setStroke(Color.RED);
    			Line pat = new Line(200,300,200,204);
    			PathTransition tr = new PathTransition();
    			FadeTransition ftrText = new FadeTransition();
    			FadeTransition ftrTau1 = new FadeTransition();
    			FadeTransition ftrTau2 = new FadeTransition();
    			FadeTransition ftrTau3 = new FadeTransition();
    			ftrText.setDuration(Duration.seconds(2));
    			ftrText.setFromValue(0.3);
    			ftrText.setToValue(1.0);
    			
    			ftrTau1.setDuration(Duration.seconds(2));
    			ftrTau1.setFromValue(0.3);
    			ftrTau1.setToValue(1.0);
    			
    			ftrTau2.setDuration(Duration.seconds(2));
    			ftrTau2.setFromValue(0.3);
    			ftrTau2.setToValue(1.0);
    			
    			ftrTau3.setDuration(Duration.seconds(2));
    			ftrTau3.setFromValue(0.3);
    			ftrTau3.setToValue(1.0);
    			
    			tr.setDuration(Duration.seconds(1));
    			tr.setNode(ln);
    			tr.setPath(pat);
    			tr.setCycleCount(1);
    			tr.play();
    			tau1 = tauBuilder(300,200);

    			t = new Text(306,200,"A");
    			
    			ftrText.setNode(t);
    			ftrTau1.setNode(tau1[0]);
    			ftrTau2.setNode(tau1[1]);
    			ftrTau3.setNode(tau1[2]);
    			
    			ftrText.play();
    			ftrTau1.play();
    			ftrTau2.play();
    			ftrTau3.play();
    			
    			t.setFont(new Font(10));
    			anchor.getChildren().add(ln);
    			anchor.getChildren().addAll(tau1[0],tau1[1],tau1[2],t);
    			vbox.getChildren().add(myText1);
    			
    			slide++;
    			break;
    		}
    		case 1 :{
    			
    			myText2 = new Text(400,400,"   2.Se duc doua plane \n   paralele la o distanta de \n   0.08mm "
    					+ "unul fata de celalalt \n   si  perpendiculare pe A."); 
    			myText2.setFont(Font.font("Helvetica", 20));
    			
    			tau2 = tauBuilder(280,15);
    			tau3 = tauBuilder(310,15);
    			tau21 = tauBuilder(235,15);
    			tau31 = tauBuilder(200,15);
    			
    			//instantare linie cota
    			ann = new Line(230,250,330,250);
    			
    			//instantare sageti
    			ann1 = new Line(240,240,250,250);
    			ann2 = new Line(240,260,250,250);
    			ann3 = new Line(274,250,284,260);
    			ann4 = new Line(274,250,284,240);
    					
    			//instantare valoare cota
    			an = new Text(300,248,"0.08");
    			
    			//instantare cifra 1
    			t2 = new Text(286,15,"1");
    			t2.setFont(new Font(10));
    			t11 = new Text(241,15,"A");
    			t11.setFont(new Font(10));
    			
    			//instantare cifra 2
    			t3 = new Text(316,15,"2");
    			t3.setFont(new Font(10));
    			t31 = new Text(206,15,"2");
    			t31.setFont(new Font(10));
    			
    			//instantare linii perpendiculare
    			perp1 = new Line(216,15,226,15);
    			perp2 = new Line(221,15,221,5);
    			
    			//instantare linii paralelism
    			p = new Text(295,15,"//");
    			
    			ln1 = new Line(50,10,50,300);
    			ln1.setStroke(Color.GREEN);
    			
    			ln2 = new Line(274,10,274,300);
    			ln2.setStroke(Color.GREEN);

    			Line pat1 = new Line(50,155,250,155);
    			PathTransition tr = new PathTransition();
    			
    			//instantare traseu path pentru sageata1sus
    			Line sagPathSus = new Line(200,245,245,245);
    			    
    			//instantare traseu path pentru sageata1jos
    			Line sagPathJos = new Line(200,255,245,255);
    			
    			//instantare traseu path pentru sageata2sus
    			Line sagPathSus2 = new Line(400,245,279,245);
    			    
    			//instantare traseu path pentru sageata2jos
    			Line sagPathJos2 = new Line(400,255,279,255);
    			
    			//instantare traseu path pentru linia de cota
    			Line patPath = new Line(280,400,280,250);
    			
    			//instantare path pentru sageata 1
    			PathTransition ann1Path = new PathTransition();
    			PathTransition ann2Path = new PathTransition();
    			
    			//instantare path pentru sageata 2
    			PathTransition ann3Path = new PathTransition();
    			PathTransition ann4Path = new PathTransition();

    			//instantare path pentru linia de cota
    			PathTransition annPath = new PathTransition();
   
    			//instantare animatie fade pentru valoarea cotei
    			FadeTransition anFade = new FadeTransition();
    			
    			//instantare animatie fade pentru linii perpendiculare
    			FadeTransition perpFade1 = new FadeTransition();
    			FadeTransition perpFade2 = new FadeTransition();
    			
    			//instantare animatie fade pentru tau2 -->
    			FadeTransition ftrTau1 = new FadeTransition();
    			FadeTransition ftrTau2 = new FadeTransition();
    			FadeTransition ftrTau3 = new FadeTransition();
    			//<--
    			
    			// instantare animatie fade pentru tau21 -->
    			FadeTransition ftrTau12 = new FadeTransition();
    			FadeTransition ftrTau22 = new FadeTransition();
    			FadeTransition ftrTau32 = new FadeTransition();
    			//<--
    			
    			// instantare animatie fade pentru tau3 -->
    			FadeTransition ftrTau11 = new FadeTransition();
    			FadeTransition ftrTau21 = new FadeTransition();
    			FadeTransition ftrTau31 = new FadeTransition();
    			//<--
    			
    			//instantare animatie fade pentru tau31 -->
    			FadeTransition ftrTau13 = new FadeTransition();
    			FadeTransition ftrTau23 = new FadeTransition();
    			FadeTransition ftrTau33 = new FadeTransition();
    			// <--
    			
    			//instantare animatie fade  pentru cifra1
    			FadeTransition ftrT = new FadeTransition();
    			
    			//instantare animatie fade  pentru litera A
    			FadeTransition ftrA = new FadeTransition();
    			
    			//instantare animatie fade  pentru cifra 2
    			FadeTransition ftr2 = new FadeTransition();
    			
    			//instantare animatie fade  pentru cifra 2.2
    			FadeTransition ftr22 = new FadeTransition();
    			
    			//instantare animatie fade pentru linii paralelism
    			FadeTransition ftrP = new FadeTransition();
    			
    			tr.setDuration(Duration.seconds(2));
    			tr.setNode(ln1);
    			tr.setPath(pat1);
    			tr.setCycleCount(1);
    			tr.play();
    			
    			//setari sageata cota transtion 1
    			ann1Path.setDuration(Duration.seconds(2));
    			ann1Path.setNode(ann1);
    			ann1Path.setPath(sagPathSus);
    			ann1Path.play();
    			
    			ann2Path.setDuration(Duration.seconds(2));
    			ann2Path.setNode(ann2);
    			ann2Path.setPath(sagPathJos);
    			ann2Path.play();
    			
    			//setari sageata cota transtion 2
    			ann3Path.setDuration(Duration.seconds(2));
    			ann3Path.setNode(ann4);
    			ann3Path.setPath(sagPathSus2);
    			ann3Path.play();
    			
    			ann4Path.setDuration(Duration.seconds(2));
    			ann4Path.setNode(ann3);
    			ann4Path.setPath(sagPathJos2);
    			ann4Path.play();
    			
    			//setari cota transition
    			annPath.setDuration(Duration.seconds(2));
    			annPath.setNode(ann);
    			annPath.setPath(patPath);
    			annPath.play();
    			
    			//setari fade valoare cota
    			
    			anFade.setNode(an);
    			anFade.setDuration(Duration.seconds(5));
    			anFade.setFromValue(0.3);
    			anFade.setToValue(1);
    			anFade.play();
    			
    			//setari fade perp1
    			perpFade1.setNode(perp1);
    			perpFade1.setDuration(Duration.seconds(2));
    			perpFade1.setFromValue(0.3);
    			perpFade1.setToValue(1);
    			perpFade1.play();
    			
    			//setari fade perp2
    			perpFade2.setNode(perp2);
    			perpFade2.setDuration(Duration.seconds(2));
    			perpFade2.setFromValue(0.3);
    			perpFade2.setToValue(1);
    			perpFade2.play();
    			
    			//setari fade pentru tau2 -->
    			ftrTau1.setDuration(Duration.seconds(2));
    			ftrTau1.setNode(tau2[0]);
    			ftrTau1.setFromValue(0.3);
    			ftrTau1.setToValue(1);
    			ftrTau1.play();
    			
    			ftrTau2.setDuration(Duration.seconds(2));
    			ftrTau2.setNode(tau2[1]);
    			ftrTau2.setFromValue(0.3);
    			ftrTau2.setToValue(1);
    			ftrTau2.play();
    			
    			ftrTau3.setDuration(Duration.seconds(2));
    			ftrTau3.setNode(tau2[2]);
    			ftrTau3.setFromValue(0.3);
    			ftrTau3.setToValue(1);
    			ftrTau3.play();
    			//<--
    			
    			//setari fade pentru tau21 -->
    			ftrTau12.setDuration(Duration.seconds(2));
    			ftrTau12.setNode(tau21[0]);
    			ftrTau12.setFromValue(0.3);
    			ftrTau12.setToValue(1);
    			ftrTau12.play();
    			
    			ftrTau22.setDuration(Duration.seconds(2));
    			ftrTau22.setNode(tau21[1]);
    			ftrTau22.setFromValue(0.3);
    			ftrTau22.setToValue(1);
    			ftrTau22.play();
    			
    			ftrTau32.setDuration(Duration.seconds(2));
    			ftrTau32.setNode(tau21[2]);
    			ftrTau32.setFromValue(0.3);
    			ftrTau32.setToValue(1);
    			ftrTau32.play();
    			//<--
    			
    			// setari fade pentru tau3 -->
    			ftrTau11.setDuration(Duration.seconds(2));
    			ftrTau11.setNode(tau3[0]);
    			ftrTau11.setFromValue(0.3);
    			ftrTau11.setToValue(1);
    			ftrTau11.play();
    			
    			ftrTau21.setDuration(Duration.seconds(2));
    			ftrTau21.setNode(tau3[1]);
    			ftrTau21.setFromValue(0.3);
    			ftrTau21.setToValue(1);
    			ftrTau21.play();
    			
    			ftrTau31.setDuration(Duration.seconds(2));
    			ftrTau31.setNode(tau3[2]);
    			ftrTau31.setFromValue(0.3);
    			ftrTau31.setToValue(1);
    			ftrTau31.play();
    			// <---
    			
    			//setari fade pentru tau2 -->
    			ftrTau13.setDuration(Duration.seconds(2));
    			ftrTau13.setNode(tau31[0]);
    			ftrTau13.setFromValue(0.3);
    			ftrTau13.setToValue(1);
    			ftrTau13.play();
    			
    			ftrTau23.setDuration(Duration.seconds(2));
    			ftrTau23.setNode(tau31[1]);
    			ftrTau23.setFromValue(0.3);
    			ftrTau23.setToValue(1);
    			ftrTau23.play();
    			
    			ftrTau33.setDuration(Duration.seconds(2));
    			ftrTau33.setNode(tau31[2]);
    			ftrTau33.setFromValue(0.3);
    			ftrTau33.setToValue(1);
    			ftrTau33.play();
    			//<--
    			
    			//pentru cifra 1
    			ftrT.setDuration(Duration.seconds(2));
    			ftrT.setNode(t2);
    			ftrT.setFromValue(0.3);
    			ftrT.setToValue(1);
    			ftrT.play();
    			
    			//pentru litera A
    			ftrA.setDuration(Duration.seconds(2));
    			ftrA.setNode(t11);
    			ftrA.setFromValue(0.3);
    			ftrA.setToValue(1);
    			ftrA.play();
    			
    			//pentru cifra 2
    			ftr2.setDuration(Duration.seconds(2));
    			ftr2.setNode(t3);
    			ftr2.setFromValue(0.3);
    			ftr2.setToValue(1);
    			ftr2.play();
    			
    			//pentru cifra 2.2
    			ftr22.setDuration(Duration.seconds(2));
    			ftr22.setNode(t31);
    			ftr22.setFromValue(0.3);
    			ftr22.setToValue(1);
    			ftr22.play();
    			
    			//pentru liniile de paralelism
    			ftrP.setDuration(Duration.seconds(2));
    			ftrP.setNode(p);
    			ftrP.setFromValue(0.3);
    			ftrP.setToValue(1);
    			ftrP.play();
    			
    			anchor.getChildren().addAll(ln1,ln2,tau2[0],tau2[1],tau2[2],t2,t11,p,tau3[0],
    										tau3[1],tau3[2],tau31[0],tau31[1],tau31[2],
    										tau21[0],tau21[1],tau21[2],t3,t31,perp1,perp2,
    										ann,ann1,ann2,ann3,ann4,an);
    			vbox.getChildren().add(myText2);
    			
    			slide++;
    			break;
    		}
    		case 2 :{
    			
    			myText3 = new Text(400,400,"       3.Se masoara piesa.Daca \n       oricare punct de pe suprafata \n" + 
    					"       plana tolerata se incadreaza \n       intre cele doua plane inseamna \r\n" + 
    					"       ca piesa este conforma."); 
    			myText3.setFont(Font.font("Helvetica", 20));
    			next.setStyle("visibility: hidden;");
    			vbox.getChildren().add(myText3);
    			break;
    		}
    	}
    }
    
    Line[] tauBuilder(int x, int y) {
    	
    	Line[] arr = new Line[3];
    	
    	arr[0] = new Line(x,y,x,y-10);
    	arr[1] = new Line(x,y-10,x+5,y-10);
    	arr[2] = new Line(x+5,y-10,x+5,y-6);
    	
    	return arr;
    }

}
