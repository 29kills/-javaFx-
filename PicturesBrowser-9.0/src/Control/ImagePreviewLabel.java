package Control;

import Control.PreviewInfoPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//图片预览窗口的的节点
public class ImagePreviewLabel {
    private final Label imageLabeled = new Label();

    ImagePreviewLabel(String imagePath,int width,int height){
        PreviewInfoPane.imagePane.getChildren().clear();
        imageLabeled.setPrefSize(width,height);
        imageLabeled.setWrapText(true);
        imageLabeled.setStyle("-fx-border-color: white");
        imageLabeled.setAlignment(Pos.BASELINE_CENTER);
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        imageView.setPreserveRatio(true);//保持缩放比例

        imageLabeled.setGraphic(imageView);
    }


    public Label getImageLabeled() {
        return imageLabeled;
    }

    public void addLabelOnPane(Label label){
        PreviewInfoPane.imagePane.getChildren().add(label);
    }
}
