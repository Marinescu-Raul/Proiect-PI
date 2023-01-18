
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	    
	    private static Stage stg;
	    
	    public void start(Stage login) throws Exception{
	    	try {
	    	stg = login;
	    	login.setResizable(false); 
	    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    	login.setTitle("Proiect");
	   	login.setScene(new Scene(root, 600,400));
	   //  login.setMaximized(true);
	    	login.show();
	    }catch(Exception e) {
	        e.printStackTrace();
	  }
	}
	    
	    public void changeScene(String fxml) throws IOException{
	    	Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	    	stg.getScene().setRoot(pane);
	    }
	    
	    
	    public void dimensiuni(double a, double b) {
	    	stg.setHeight(b);
	    	stg.setWidth(a);
	    	
	    }
	    
	    

	
	public static void main(String[] args) {

		 launch(args);
	
		   
	}


}
