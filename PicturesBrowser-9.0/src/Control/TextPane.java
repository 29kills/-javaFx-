package Control;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TextPane extends HBox{
	public final Label maintext=new Label(); 
	public  final Label secondtext=new Label();
    public TextPane() {
		// TODO Auto-generated constructor stub
    	
    	this.getChildren().addAll(maintext,secondtext);
    	this.setSpacing(20);
    	this.setAlignment(Pos.CENTER_LEFT);
	}
}
