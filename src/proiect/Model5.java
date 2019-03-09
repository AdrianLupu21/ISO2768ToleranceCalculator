package proiect;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Model5 {

	AnchorPane anchor;
	VBox text;
	
	
	public AnchorPane getAnchor() {
		return anchor;
	}

	public void setAnchor(AnchorPane anchor) {
		this.anchor = anchor;
	}

	public VBox getText() {
		return text;
	}

	public void setText(VBox text) {
		this.text = text;
	}

	
	public double[] calcAjustaj(int diametruArboreAlezaj, int valoareAbatereArbore, double valoareAbatereAlezaj, double campTolerantaArbore,
					  		double campTolerantaAlezaj, String tipAbatereArbore, String tipAbatereAlezaj) throws IOException {
		
		double[] arr;
		double diametruMinArbore = 0,diametruMaxArbore = 0;
		double diametruMinAlezaj = 0,diametruMaxAlezaj = 0;
		double valoareAbatereArboreSuperioara = 0, valoareAbatereArboreInferioara = 0;
		double valoareAbatereAlezajSuperioara = 0, valoareAbatereAlezajInferioara = 0;
		int tipAjustajNum = 0;
		
		switch(tipAbatereArbore) {
		case("inferioara"):
			valoareAbatereArboreSuperioara = campTolerantaArbore + valoareAbatereArbore;
			valoareAbatereArboreInferioara = valoareAbatereArbore;
			diametruMinArbore = diametruArboreAlezaj + valoareAbatereArboreInferioara * Math.pow(10, -3); 
			diametruMaxArbore = diametruArboreAlezaj + valoareAbatereArboreSuperioara * Math.pow(10, -3);
			break;
		
		case("superioara"):
			valoareAbatereArboreInferioara = valoareAbatereArbore - campTolerantaArbore;
			valoareAbatereArboreSuperioara = valoareAbatereArbore;
			diametruMinArbore = diametruArboreAlezaj + valoareAbatereArboreInferioara * Math.pow(10, -3); 
			diametruMaxArbore = diametruArboreAlezaj + valoareAbatereArboreSuperioara * Math.pow(10, -3);
			break;
		case("js"):
			valoareAbatereArboreSuperioara = (double) campTolerantaArbore / 2; 
			valoareAbatereArboreInferioara = - valoareAbatereArboreSuperioara; 
			diametruMinArbore = diametruArboreAlezaj + valoareAbatereArboreInferioara * Math.pow(10, -3); 
			diametruMaxArbore = diametruArboreAlezaj + valoareAbatereArboreSuperioara * Math.pow(10, -3);
			break;
		case("h"):
			valoareAbatereArboreInferioara = - campTolerantaArbore;
			valoareAbatereArboreSuperioara = 0;
			diametruMinArbore = diametruArboreAlezaj + valoareAbatereArboreInferioara * Math.pow(10, -3); 
			diametruMaxArbore = diametruArboreAlezaj + valoareAbatereArboreSuperioara * Math.pow(10, -3);
			break;
		default:
			valoareAbatereArboreSuperioara = (double) campTolerantaAlezaj /2;
			valoareAbatereArboreInferioara = -valoareAbatereArboreSuperioara;
				diametruMinArbore = diametruArboreAlezaj + valoareAbatereArboreInferioara * Math.pow(10, -3); 
			diametruMaxArbore = diametruArboreAlezaj + valoareAbatereArboreSuperioara * Math.pow(10, -3);
		}
		
		switch(tipAbatereAlezaj) {
		case("inferioara"):
			valoareAbatereAlezajSuperioara = campTolerantaAlezaj + valoareAbatereAlezaj;
			valoareAbatereAlezajInferioara =  valoareAbatereAlezaj;
			diametruMinAlezaj = diametruArboreAlezaj + valoareAbatereAlezajInferioara * Math.pow(10, -3); 
			diametruMaxAlezaj = diametruArboreAlezaj + valoareAbatereAlezajSuperioara * Math.pow(10, -3);
			break;
		
		case("superioara"):
			valoareAbatereAlezajInferioara = valoareAbatereAlezaj - campTolerantaAlezaj;
			System.out.println();
			valoareAbatereAlezajSuperioara = valoareAbatereAlezaj;
			diametruMinAlezaj = diametruArboreAlezaj + valoareAbatereAlezajInferioara * Math.pow(10, -3); 
			diametruMaxAlezaj = diametruArboreAlezaj + valoareAbatereAlezajSuperioara * Math.pow(10, -3);
			break;
		case("JS"):
			valoareAbatereAlezajInferioara =  - (double) campTolerantaAlezaj / 2; 
			valoareAbatereAlezajSuperioara = valoareAbatereAlezajInferioara; 
			diametruMinAlezaj = diametruArboreAlezaj + valoareAbatereAlezajInferioara * Math.pow(10, -3); 
			diametruMaxAlezaj = diametruArboreAlezaj + valoareAbatereAlezajSuperioara * Math.pow(10, -3);
			break;
		case("H"):
			valoareAbatereAlezajInferioara =  0;
			valoareAbatereAlezajSuperioara = campTolerantaAlezaj;
			diametruMinAlezaj = diametruArboreAlezaj + valoareAbatereAlezajInferioara * Math.pow(10, -3); 
			diametruMaxAlezaj = diametruArboreAlezaj + valoareAbatereAlezajSuperioara * Math.pow(10, -3);
			break;
		default: 
			valoareAbatereAlezajSuperioara = (double) campTolerantaAlezaj/2;
			valoareAbatereAlezajInferioara = -valoareAbatereAlezajSuperioara;
			diametruMinAlezaj = 0;
			diametruMaxAlezaj = 0;
		}
		
		
		fit(valoareAbatereAlezajSuperioara, valoareAbatereAlezajInferioara, valoareAbatereArboreSuperioara, valoareAbatereArboreInferioara);

		addText(diametruMaxAlezaj,diametruMinAlezaj,diametruMaxArbore,diametruMinArbore);
		
		
		if(diametruMaxAlezaj>diametruMaxArbore && diametruMinArbore<diametruMinAlezaj && diametruMaxAlezaj>diametruMinArbore && diametruMinAlezaj<diametruMaxArbore || diametruMaxAlezaj<diametruMaxArbore && diametruMinArbore>diametruMinAlezaj && diametruMaxAlezaj>diametruMinArbore && diametruMinAlezaj<diametruMaxArbore) {
				arr = new double[4];
			}else if(diametruMinArbore>diametruMaxAlezaj) {
				arr = new double[4];
				tipAjustajNum = 1;
			}else if(diametruMaxArbore<=diametruMinAlezaj) {
				arr = new double[4];
				tipAjustajNum = 2;
			}
			arr = joc(valoareAbatereAlezajSuperioara,valoareAbatereAlezajInferioara,valoareAbatereArboreSuperioara,
					valoareAbatereArboreInferioara, campTolerantaArbore, campTolerantaAlezaj,tipAjustajNum);
		
		return arr;
		}
		
	private	double [] joc (double valoareAbatereAlezajSuperioara, double valoareAbatereAlezajInferioara,
				double valoareAbatereArborejSuperioara, double valoareAbatereArboreInferioara,
				double campTolerantaArb, double campTolerantaAlez, int tipAjustajNum) {
			double[] arr;
			
			// 0 -> ajustaj intermediar
			// 1 -> ajustaj strangere
			// 2 -> ajustaj joc
			switch(tipAjustajNum) {
			case(2):{
				arr = new double[5];
				
				arr[0] = valoareAbatereAlezajSuperioara - valoareAbatereArboreInferioara;
				arr[1] = valoareAbatereAlezajInferioara - valoareAbatereArborejSuperioara;
				arr[2] = arr[0] - (double)(arr[0]-arr[1] - Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				arr[3] = arr[1] + (double)(arr[0]-arr[1] - Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				arr[4] = tipAjustajNum;
				break;
			}
			case(0):{
				
				arr = new double[5];
				
				
				arr[0] = valoareAbatereArborejSuperioara - valoareAbatereAlezajInferioara;
			    
				arr[1] = valoareAbatereAlezajSuperioara - valoareAbatereArboreInferioara;	
				
				arr[2] = valoareAbatereArborejSuperioara - valoareAbatereAlezajInferioara - (double) ((double)(campTolerantaAlez+campTolerantaArb)/2
						- Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				
				arr[3] = valoareAbatereAlezajSuperioara - valoareAbatereArboreInferioara - (double) ((double)(campTolerantaAlez+campTolerantaArb)/2
						- Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				
			
				
				arr[2] = tipAjustajNum;
				break;
			}
			case(1):{
				
				arr = new double[5];
				
				arr[0] = valoareAbatereArborejSuperioara - valoareAbatereAlezajInferioara;
				arr[1] = valoareAbatereArboreInferioara - valoareAbatereAlezajSuperioara;
				arr[2] = arr[0] - (double)(arr[0]-arr[1] - Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				arr[3] = arr[1] + (double)(arr[0]-arr[1] - Math.sqrt(Math.pow(campTolerantaAlez,2)+Math.pow(campTolerantaArb,2)))/2;
				arr[4] = tipAjustajNum;
				break;
			}
			default:{
				arr = null;
			}
			}
			
			
			return arr;
		}
	
	   String tipAjustaj(int number) {
		   switch(number) {
		   case 0 : return "intermediar";
		 
		   case 1 : return "strangere";
		   			
		   case 2 : return "joc";
		   			
		   default: return "error";
		   }
	   }
	   String ajustajMess(double[] arr) {
		  
		   switch((int) arr[4]) {
		   case(1): return "Smax: " + (int) arr[0] +", Smin: " + (int) arr[1] + ", Smax_probabil: " + (int) arr[2] + ", Smin_probabil: " + (int) arr[3];
		   case(2): return "Jmax: " + (int) arr[0] +", Jmin: " + (int) arr[1] + ", Jmax_probabil: " + (int) arr[2] + ", Jmin_probabil: " + (int) arr[3];
		   case(0): return("Smax: " + (int) arr[0] +", Jmax: " + (int) arr[1]);
		   default: return "error";
		   }
		   
	   }
	   void fit(double alezajSuper, double alezajInf, double arboreSuper, double arboreInf) {
			 
		   double yl1=123,yr2=10,yL3=122;
		   double yL1= 0; //modific partea de jos a gaurii in functie de partea de jos a arborelui
		   
		   if(Math.abs(arboreInf)+123 > 250 && arboreInf < 0) {
			   yL1 = Math.abs(arboreInf)+123+50;
		   }else {
			   yL1 = yl1- alezajSuper +250;
		   }
		   
		   
		   if(arboreSuper > 100 || alezajSuper > 100) { // pentru overflow
			   yl1+=500;
			   yL3+=500;
			   yL1+=500;
		   }
		   
		   Line l1 = new Line(158,yl1,650,yl1); //linia zero
		   Line l2 = new Line(169,10,169,yL1+70); // margine stanga alezaj
		   Line l3 = new Line(320,10,320,yL1+70); // margine dreapta alezaj
		   Line l4 = new Line(169,yL1,320,yL1);
		   Image lines = new Image("lines.png");
		   Image hash = new Image("hash.png");
		 
		   System.out.println("super " + arboreSuper);
		   System.out.println("inf "+ arboreInf);
		   Rectangle r1;
		   Rectangle r2 = new Rectangle(169,yr2,150,Math.abs(yl1-10-alezajSuper));
		   Rectangle r3 = new Rectangle(169,yL1,150,70);
		   if(alezajInf<=0) {
			    r1 = new Rectangle(170,yl1-alezajSuper,150,alezajSuper+Math.abs(alezajInf));
		   }else {
			    r1 = new Rectangle(170,yl1-alezajSuper,150,alezajSuper-alezajInf);
		   }
		   r1.setFill(new ImagePattern(lines));
		   r2.setFill(new ImagePattern(hash));
		   r3.setFill(new ImagePattern(hash));
		   anchor.getChildren().clear();
		   
		   //arbore
		   Line L1;

		   L1 = new Line(500,yl1-arboreSuper,500,yL1); // margine stanga arbore
		   Line L2 = new Line(500,yL1,650,yL1);
		   Line L3 = new Line(500,yL3-arboreSuper,650,yL3-arboreSuper);
		   Rectangle R1;
		  
		   if(arboreInf<=0 || arboreInf == arboreSuper) {
			    R1 = new Rectangle(500,yl1-arboreSuper,150,Math.abs(arboreSuper+Math.abs(arboreInf)));
		   }else {
			    R1 = new Rectangle(500,yl1-arboreSuper,150,arboreSuper-arboreInf);
		   }
		   
		   R1.setFill(new ImagePattern(lines));
		   
		   anchor.getChildren().addAll(l1,l2,l3,l4,r1,r2,r3,L1,L2,L3,R1);
		   
		 
	 }
		void addText(double diametruMaxAlezaj, double diametruMinAlezaj, double diametruMaxArbore, double diametruMinArbore) {
			Text rasp = new Text(10,50,"    Diametrul maxim al alezajului = " + diametruMaxAlezaj + " \n    Diamentrul minim al alezajului = " + diametruMinAlezaj + "\n    Diamentru maxim al arborelui = " + diametruMaxArbore + 
					"\n    Diamentrul minim al arborelui = " + diametruMinArbore);
			rasp.setFont(Font.font("Helvetica",20));
			this.text.getChildren().add(rasp);
		}
	
}
