package proiect;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller5 {

	private double valoareAbatereAlezaj = -1;
	
	private double xOffset;
	private double yOffset;
	
	private String tipAbatereAlezaj;
	
	
	private double campTolerantaAlezaj = -1;
	
	
	
	private int valoareAbatereArbore = -1;


	private String tipAbatereArbore;
	
	
	private double campTolerantaArbore = -1;

	
	private double[] showResults;
	
	static Stage stage = null;
	

	@FXML
	private VBox text;
	
	@FXML
	private ScrollPane scroll;
	
	@FXML
	private AnchorPane anchor;
	
    @FXML
    private TextField fiArbore;

    @FXML
    private ChoiceBox<String> tarbore;

    @FXML
    private ChoiceBox<String> itarbore;

    @FXML
    private TextField fialezaj;

    @FXML
    private ChoiceBox<String> talezaj;

    @FXML
    private ChoiceBox<String> italezaj;

    @FXML
    private Button calculate;
    
    @FXML
    private Button back;
    
	@FXML
    private VBox minimize;

    @FXML
    private VBox close;
    
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
    void initialize() throws URISyntaxException {
    	
    	
    	scroll.setFitToHeight(true);
    	//adaug date in scroll option
    	{
	    	String sqlAlezajClasa = "SELECT alezaje.clasa FROM alezaje GROUP BY alezaje.clasa ORDER BY alezaje.clasa";
	    	String sqlArboreClasa = "SELECT arbori.clasa FROM arbori GROUP BY arbori.clasa ORDER BY arbori.clasa";
	    	String sqlTolerFund = "SELECT tolerante_fundamentale.treapta  FROM tolerante_fundamentale GROUP BY treapta ORDER BY treapta";
	    	
	    	try(Statement s1 = dbConn("abateri.accdb")){
	    		
	    		s1.execute(sqlAlezajClasa);
	    		ResultSet alezajClasa = s1.getResultSet();
	    		
	    		s1.execute(sqlArboreClasa);
	    		ResultSet arboreClasa = s1.getResultSet();
	
	    		s1.execute(sqlTolerFund);
	    		ResultSet tolerFund = s1.getResultSet();
	    		
	    		while(alezajClasa != null && alezajClasa.next()) {
	    			talezaj.getItems().add(alezajClasa.getString(1));
	    		}
	    		
	    		while(arboreClasa != null && arboreClasa.next()) {
	    			tarbore.getItems().add(arboreClasa.getString(1));
	    		}
	    		
	    		while(tolerFund != null && tolerFund.next()) {
	    			italezaj.getItems().add(tolerFund.getString(1));
	    			itarbore.getItems().add(tolerFund.getString(1));
	    		}
	    		
	    	}catch(Exception e) {
	    			System.out.println(e.getCause() + e.getMessage());
	    	}
	    	
    	}

    }
    
    @FXML
    void onBack(ActionEvent event) throws IOException {
    	Parent newScene = null;
    	stage = (Stage) back.getScene().getWindow();
    	newScene = FXMLLoader.load(getClass().getResource("../View.fxml"));
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
    void onCalculate(ActionEvent event)  throws SQLException {
  
    	text.getChildren().clear();

    	System.out.println(talezaj.getValue());
    	
    if(fiArbore.getText().equals("")) {
      	  	
	    alerta("Trebuiesc introduse diametrele alezajului si arborelui");
	    	
	}else {
	   
	    try(Statement s = dbConn("abateri.accdb")){

	    	String sqlAlezaj = "SELECT alezaje.valoare,alezaje.tip_abatere,alezaje.treapta_toler,"
					+ "dimensiuni.peste,dimensiuni.pana_la_inclusiv FROM alezaje INNER JOIN dimensiuni ON "
					+ "alezaje.dimensiuni=dimensiuni.ID WHERE alezaje.clasa = \""+ talezaj.getValue()+"\" AND " 
					+ "dimensiuni.peste < " + fiArbore.getText() + " AND dimensiuni.pana_la_inclusiv >=" +
					fiArbore.getText();
			
			String sqlArbore = "SELECT arbori.valoare,arbori.tip_abatere,arbori.treapta_toler,"
					+ "dimensiuni.peste,dimensiuni.pana_la_inclusiv FROM arbori INNER JOIN dimensiuni ON "
					+ "arbori.dimensiuni=dimensiuni.ID WHERE arbori.clasa = \""+tarbore.getValue()+"\" AND "
					+ "dimensiuni.peste < " + fiArbore.getText() + " AND dimensiuni.pana_la_inclusiv >=" +
					fiArbore.getText();
			
			String sqlTreaptaAlezaj = "SELECT tolerante_fundamentale.valoare from tolerante_fundamentale "
					+ "INNER JOIN dimensiuni_tol_fund ON tolerante_fundamentale.dimensiuni = dimensiuni_tol_fund.ID"
					+ " WHERE tolerante_fundamentale.treapta = \""+italezaj.getValue()+"\" AND "
					+ "dimensiuni_tol_fund.peste < " + fiArbore.getText() + " AND dimensiuni_tol_fund.pana_la_inclusiv >=" +
					fiArbore.getText();
			
			String sqlTreaptaArbore = "SELECT tolerante_fundamentale.valoare from tolerante_fundamentale "
					+ "INNER JOIN dimensiuni_tol_fund ON tolerante_fundamentale.dimensiuni = dimensiuni_tol_fund.ID"
					+ " WHERE tolerante_fundamentale.treapta = \""+itarbore.getValue()+"\" AND "
					+ "dimensiuni_tol_fund.peste < " + fiArbore.getText() + " AND dimensiuni_tol_fund.pana_la_inclusiv >=" +
					fiArbore.getText();						

			String sqlAlezajSpecial = "SELECT abateriSpecificeAlezaje.valoare, abateriSpecificeAlezaje.delta\r\n" + 
					"FROM ((abateriSpecificeAlezaje INNER JOIN alezaje ON abateriSpecificeAlezaje.clasa = alezaje.clasa )INNER JOIN"
					+ " dimensiuni ON abateriSpecificeAlezaje.dimensiuni = dimensiuni.ID)\r\n" + 
					"WHERE abateriSpecificeAlezaje.clasa= \"" + talezaj.getValue() +"\" AND abateriSpecificeAlezaje.treapta LIKE"+ "\"*,"+italezaj.getValue()+",*\"\r\n" + 
					"AND dimensiuni.peste <" + fiArbore.getText() + " AND dimensiuni.pana_la_inclusiv >= "+ fiArbore.getText() +" GROUP BY  "
							+ "abateriSpecificeAlezaje.delta, abateriSpecificeAlezaje.valoare ;\r\n" + 
					""; 
					
			String sqlArboreSpecial = "SELECT abateriSpecificeArbori.valoare\r\n" + 
					"FROM ((abateriSpecificeArbori INNER JOIN arbori ON abateriSpecificeArbori.clasa = arbori.clasa )INNER JOIN dimensiuni ON abateriSpecificeArbori.dimensiuni = dimensiuni.ID)\r\n" + 
					"WHERE abateriSpecificeArbori.clasa= \"" + tarbore.getValue() +"\" AND abateriSpecificeArbori.treapta LIKE"+ "\"*,"+itarbore.getValue()+",*\"\r\n" + 
					"AND dimensiuni.peste <" + fiArbore.getText() + " AND dimensiuni.pana_la_inclusiv >= "+ fiArbore.getText() +" GROUP BY  abateriSpecificeArbori.valoare ;\r\n" + 
					"";
			
			
			String sqlDelta = "SELECT delta.valoare FROM delta INNER JOIN dimensiuni ON dimensiuni.ID = delta.dimensiuni " + 
							"WHERE  delta.treapta = " + "\"" + italezaj.getValue() + "\""+ " AND dimensiuni.peste <" +
							fiArbore.getText() + " AND dimensiuni.pana_la_inclusiv >= " + fiArbore.getText() ;
			
			//fiArbore = fiAlezaj = dimensiunea nominala


					
			s.execute(sqlAlezaj);
			ResultSet rsAlezaj = s.getResultSet();
			
			s.execute(sqlArbore);
			ResultSet rsArbore = s.getResultSet();
			
			s.execute(sqlTreaptaAlezaj);
			ResultSet rsTreaptaAlezaj = s.getResultSet();
			
			s.execute(sqlTreaptaArbore);
			ResultSet rsTreaptaArbore = s.getResultSet();

			

			
			if(talezaj.getValue().equals("JS")) {
				valoareAbatereAlezaj = 0;
				tipAbatereAlezaj = "JS";	
			}else if(talezaj.getValue().equals("H")){
				valoareAbatereAlezaj = 0;
				tipAbatereAlezaj = "H";
			} else {			
				while(rsAlezaj!=null && rsAlezaj.next()) {
					
					if(rsAlezaj.getBoolean(3)) {
		
						valoareAbatereAlezaj = Integer.parseInt(rsAlezaj.getString(1));
						
					}else {
						s.execute(sqlAlezajSpecial);
						ResultSet rsAlezajSpecial = s.getResultSet();
						
						while(rsAlezajSpecial!=null && rsAlezajSpecial.next()) {
							valoareAbatereAlezaj = (double) Integer.parseInt(rsAlezajSpecial.getString(1));
							if(rsAlezajSpecial.getBoolean(2)) {
								s.execute(sqlDelta);
								ResultSet rsDelta = s.getResultSet();
								
								while(rsDelta!=null && rsDelta.next()) {
									valoareAbatereAlezaj += Double.parseDouble(rsDelta.getString(1));
								}
							}
						}
					}
					tipAbatereAlezaj = rsAlezaj.getString(2);
					
					if(rsAlezaj.getString(3)!="toate" && checkTolerante(rsAlezaj.getString(3),italezaj.getValue())) {
						alerta("Nu exista valori in standard pentru : IT" + italezaj.getValue());
					}		
	
				}
			}
			
			System.out.println("valoare abatere alezaj =  "+ valoareAbatereAlezaj);
			
			if(valoareAbatereAlezaj == -1) {
				alerta("nu exista dimensiuni pentru diametrul dat");
				return;
			}
			
			if(tarbore.getValue().equals("js")) {
				valoareAbatereArbore = 0;
				tipAbatereArbore = "js";
			}else
			if(tarbore.getValue().equals("h")){
				valoareAbatereArbore = 0;
				tipAbatereArbore = "h";
			}else{
				
				while(rsArbore!=null && rsArbore.next()) {

										
					if(rsArbore.getBoolean(3)) {
						valoareAbatereArbore = Integer.parseInt(rsArbore.getString(1));
					}else {
						s.execute(sqlArboreSpecial);
						ResultSet rsArboreSpecial = s.getResultSet();
						while(rsArboreSpecial!=null && rsArboreSpecial.next()) {
							valoareAbatereArbore = Integer.parseInt(rsArboreSpecial.getString(1));
						}
					}
					System.out.println("valoare abatere arbore :" + valoareAbatereArbore);
					tipAbatereArbore = rsArbore.getString(2);
					
	
					
				}
			}
			
			if(valoareAbatereArbore == -1) {
				alerta("nu exista dimensiuni pentru diametrul dat");
				return;
			}
				
				while(rsTreaptaAlezaj!=null && rsTreaptaAlezaj.next()) {
					
					if(rsTreaptaAlezaj.getString(1).equals("")) {
						alerta("Nu exista valori pentru :" + italezaj.getValue());
						return;
					}
					
					campTolerantaAlezaj = Double.parseDouble(rsTreaptaAlezaj.getString(1));
					System.out.println("campTolerantaAlezaj " + campTolerantaAlezaj);
					
				}
			
			if(campTolerantaAlezaj == -1) {
				alerta("nu exista dimensiuni pentru diametrul dat");
				return;
			}
				
				
				while(rsTreaptaArbore!=null && rsTreaptaArbore.next()) {
					

			
					campTolerantaArbore = Double.parseDouble(rsTreaptaArbore.getString(1));
					System.out.println("campTolerantaArbore " + campTolerantaArbore);

				}
				
			if(campTolerantaArbore == -1) {
				alerta("nu exista dimensiuni pentru diametrul dat");
				System.out.println("Adfadf");
				return;
				}

				Model5 model = new Model5();
				
				model.setText(text);
				model.setAnchor(anchor);
			
				showResults = model.calcAjustaj(Integer.parseInt(fiArbore.getText()),
						valoareAbatereArbore, valoareAbatereAlezaj, campTolerantaArbore, campTolerantaAlezaj, tipAbatereArbore,
						tipAbatereAlezaj);
				
				raspuns("Ajustajul este: " + model.tipAjustaj((int) showResults[showResults.length - 1]),model.ajustajMess(showResults));
				
				valoareAbatereAlezaj = -1;
		    	valoareAbatereArbore = -1;
		    	campTolerantaAlezaj = -1;
		    	tipAbatereArbore = null;
		    	campTolerantaArbore = -1;
		    	showResults = null;
		    	
	    	}
	    	catch(Exception e ) {
	    		System.out.println(e);
	    	}
		}
      }
    
    
    void alerta(String message) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setContentText(message);
    	alert.showAndWait();
    }
    
   void raspuns(String header, String message) {
	   Alert alert = new Alert(AlertType.INFORMATION);
	   alert.setTitle("Raspuns");
	   alert.setHeaderText(header);
	   alert.setContentText(message);
	   alert.show();
   }
   
   
   Statement dbConn (String dbName) throws URISyntaxException, SQLException {
	   
	   URI uri = new URI("jdbc:ucanaccess",Paths.get(dbName).toUri().getPath(),null);
	   System.out.println(uri.toString());
	   System.out.println(uri.toString().replaceAll("/","//"));
	   Connection conn = DriverManager.getConnection(uri.toString().replaceAll("/","//"), "", "");
	   Statement st = conn.createStatement();
	   
	   return st;
	
   }
   
   Boolean checkTolerante(String dbTolerante,String inputTolerante) {
	  String str[] = dbTolerante.split(",");
	  for(String elem : str) {
		  if(elem == inputTolerante)
			  return true;
	  }
	   return false;
   }
   
}

