package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(0);
		gridPane.setVgap(10); 
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		
		Text text0 = new Text(10, 50, "Podaj klucz : "); 
		TextArea textArea0 = new TextArea ();
		textArea0.setPrefSize(120, 10);
		
		Text text1 = new Text(10, 50, "Wiadomoœæ do zakodowania / odkodowania : ");
        TextArea textArea1 = new TextArea ();
        textArea1.setPrefSize(120, 100);
		
        Text text2 = new Text(10, 50, "Wiadomoœæ po odkodowaniu / zakodowaniu : ");
        TextArea textArea2 = new TextArea ();
        textArea2.setEditable(false);
        textArea2.setPrefSize(120, 100);
        
		Button button1 = new Button("Zakoduj / Odkoduj");       
		button1.setOnAction(new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		        String text = textArea1.getText();
		        String code = textArea0.getText();
		        text = encrypt(text,code);
		        textArea2.setText(text); 
		    }
		});
		
		gridPane.add(text0,0,0,1,1);
		gridPane.add(textArea0,0,1,2,1);
        gridPane.add(text1,0,2,1,1);
		gridPane.add(textArea1,0,3,2,1);
		gridPane.add(button1,0,4,1,1);
		gridPane.add(text2,0,5,1,1);
		gridPane.add(textArea2,0,6,2,1);
		
		Scene scene = new Scene(gridPane,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	String encrypt(String text,String code){
		char[] encryptTable = code.toCharArray();
		char firstLetter;
		char secondLetter;
		char[] myText = text.toCharArray();
		for(int i=0;i<code.length();i=i+2){
			firstLetter = encryptTable[i];
			secondLetter = encryptTable[i+1];
			for(int j=0;j<text.length();j++){
				if(myText[j] == firstLetter){
					myText[j] = secondLetter;
				}
				else{
					if(myText[j] == secondLetter){
						myText[j] = firstLetter;
					}	
				}
			}
		}
		text = new String(myText);
		return text;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
