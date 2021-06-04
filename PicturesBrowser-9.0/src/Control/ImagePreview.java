package Control;



import Control.PreviewInfoPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.io.File;
import java.text.DecimalFormat;

//��ʾԤ�����ݣ�����ͼƬ�Լ������ơ�λ�á���С
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
        imageInformationLabel.setText("ͼƬ����    "+imageName+"\n"+
                                      "λ��:     "+(imagePath.substring(5,imagePath.length()-imageName.length()))+"\n"+
        		                      "�ļ���С: "+ImageSize+"KB");
    }
    public void addInformationOfImageOnPane(Label label){
        PreviewInfoPane.imagePane.getChildren().add(label);
    }
}