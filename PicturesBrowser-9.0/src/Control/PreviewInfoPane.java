package Control;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;



public class PreviewInfoPane extends Pane {
    public static FlowPane imagePane = new FlowPane();
   
    public PreviewInfoPane(){
        super();
        setPrefSize(350,718);
        setStyle("-fx-background-color:#6f8487");  //”“≤‡‘§¿¿Õº∆¨±≥æ∞…´
        imagePane.setPadding(new Insets(12,13,14,15));
        imagePane.setOrientation(Orientation.VERTICAL);
        imagePane.setHgap(8);
        imagePane.setVgap(5);

        imagePane.setPrefSize(350,718);
        getChildren().add(imagePane);
    }



}