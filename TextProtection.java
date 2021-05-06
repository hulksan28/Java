import javafx.application.Application; 
import static javafx.application.Application.launch; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType; 
import javafx.scene.control.PasswordField; 
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.text.*;
import javafx.scene.Group; 
import javafx.scene.control.Label;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField; 
import javafx.stage.Stage;  
import java.io.*;
import javafx.stage.FileChooser; 
import java.util.Scanner;
public class TextProtection extends Application
{
	public static void main(String [] args)
	{
		launch(args);
	}
	public static String encryptDecrypt(String inputString) 
    { 
        char xorKey = 'P'; 
        String outputString = ""; 
        int len = inputString.length();
        for (int i = 0; i < len; i++)  
        { 
            outputString = outputString +  
            Character.toString((char) (inputString.charAt(i) ^ xorKey)); 
        } 
        System.out.println(outputString); 
        return outputString; 
    } 
	public void start(Stage stage)
	{
		Text name=new Text("Enter your Name : ");
		
		TextField Name=new TextField();
		
		Dialog<String> dialog = new Dialog<String>();
		
		dialog.setTitle("Dialog");
		ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
		
		FileChooser file = new FileChooser();
		
		Label label =new Label("No Files Selected");
		
		Button b3=new Button("Select");
		
		Button b1=new Button("Lock");
		
		Button b2=new Button("Unlock");
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() 
		{ 
  
            public void handle(ActionEvent e) 
            { 
			
                File file1 = file.showOpenDialog(stage); 
  
                if (file1!= null) 
				{ 
                      
                    label.setText(file1.getAbsolutePath() + "selected"); 
					
                } 
            
			} 
        
		}; 
		
		b3.setOnAction(event);
		
		Button b4=new Button("Show selected File");
		
		EventHandler<ActionEvent> event1 =  new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
  
                File file1 = file.showSaveDialog(stage); 
  
                if (file1!= null) 
				{ 
                    label.setText(file1.getAbsolutePath()+ "  selected"); 
                } 
            } 
        }; 
  
        b4.setOnAction(event1); 
		
		EventHandler<ActionEvent> event2 =  new EventHandler<ActionEvent>() { 
  
            public void handle(ActionEvent e) 
            { 
  
				try
				{
					File file1 = file.showSaveDialog(stage); 
				
					String path = file1.getAbsolutePath();
  
					Lock(path);
					
					label.setText("Welcome! "+Name.getText()+", the selected File is locked :)");
				}
				catch(Exception ex)
				{}
				
            } 
        }; 
		
		b1.setOnAction(event2);
		
		EventHandler<ActionEvent> event3 =  new EventHandler<ActionEvent>() { 
  
        public void handle(ActionEvent e) 
        { 
  
			try
			{
				File file1 = file.showSaveDialog(stage); 
				
				String path = file1.getAbsolutePath();
  
				Lock(path);
					
				label.setText("The selected File is unlocked :)");
			}
			catch(Exception ex)
			{}
				
		} 
        }; 
		
		b2.setOnAction(event3);
		
		GridPane pane=new GridPane();
		
		pane.setMinSize(200,200);
		
		pane.setPadding(new Insets(10,10,10,10));
		
		pane.setVgap(5);
		
		pane.setHgap(5);
		
		pane.setAlignment(Pos.CENTER);
		
		pane.add(name,0,0);
		pane.add(Name,1,0);
		pane.add(b1,0,4);
		pane.add(b2,1,4);
		//pane.add(b3,0,2);
		//pane.add(b4,1,2);
		pane.add(label,0,3);
		
		b1.setStyle("-fx-background-color: black; -fx-text-fill: lightgreen;");
		b2.setStyle("-fx-background-color: black; -fx-text-fill: lightgreen;");
		//b3.setStyle("-fx-background-color: black; -fx-text-fill: lightgreen;");
		//b4.setStyle("-fx-background-color: black; -fx-text-fill: lightgreen;");
		
		Name.setStyle("-fx-font: normal bold italic 20px 'TimesRoman' ");
		pane.setStyle("-fx-background-color: linear-gradient(#61a2b1, #2A5058)");
		
		Scene Entry=new Scene(pane);
		
		stage.setTitle("PDF Entry Form");
		
		stage.setScene(Entry);
		
		stage.show();
	}
	public static void Lock(String path) throws Exception
	{
				
		Scanner sc = new Scanner(new File(path));
				
		StringBuffer buffer = new StringBuffer();
				
		while (sc.hasNextLine()) 
		{
			buffer.append(sc.nextLine()+System.lineSeparator());
		}
						
		String fileContents = buffer.toString();
		sc.close();
		String newLine =encryptDecrypt(fileContents);
		
		fileContents=fileContents.substring(0,fileContents.length()-2);
				
		fileContents = fileContents.replace(fileContents, newLine);
			
		FileWriter writer = new FileWriter(path);
		
		writer.append(fileContents);
		writer.flush();
	}
}