package Control;



import Control.PreviewInfoPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.File;
import java.text.DecimalFormat;

//显示预览内容，包括图片以及其名称、位置、大小
public class ImagePreview {
    private final String imagePath;
    private final String imageName;
    private final String ImageSize;
    private final Label imageInformationLabel = new Label();
    public String getImagePath() {
        return imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public Label getImageInformationLabel() {
        return imageInformationLabel;
    }

    public String getSizeOfImage() {
        return ImageSize;
    }


    public ImagePreview(String imagePath, String imageName){


        this.imagePath = imagePath;
        Image image = new Image(imagePath);
        this.imageName = imageName;
        
        File file = new File(imagePath.substring(5));
        System.out.println(file.length());
        DecimalFormat df=new DecimalFormat("0.00");
        
        this.ImageSize = df.format((float)file.length()/1024);

        setInformationOfImageOnLabel();

    }

    private void setInformationOfImageOnLabel(){
        imageInformationLabel.setText("图片名：    "+imageName+"\n"+
                                      "位置:     "+(imagePath.substring(5,imagePath.length()-imageName.length()))+"\n"+
        		                      "文件大小: "+ImageSize+"KB");
    }
    public void addInformationOfImageOnPane(Label label){
        PreviewInfoPane.imagePane.getChildren().add(label);
    }
}