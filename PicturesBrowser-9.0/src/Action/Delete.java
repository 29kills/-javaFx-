package Action;
import Control.PicturePane;
import java.io.File;

import Control.ImageBoxButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
public class Delete {
	 
     public Delete()
     {
    	 if(ImageBoxButton.selectedPictures.size()==0)
    	 {
    		 Alert alert = new Alert(AlertType.INFORMATION);
             alert.titleProperty().set("����");
             alert.headerTextProperty().set("����ѡ��һ��ͼƬ");
             alert.showAndWait();
    	 }
    	 else 
    	 {
    		 //�����Ƿ�ɾ������
    		 Alert alert = new Alert(AlertType.CONFIRMATION);
    		 Button ok=(Button)alert.getDialogPane().lookupButton(ButtonType.OK);
    		 Button cancel=(Button)alert.getDialogPane().lookupButton(ButtonType.CANCEL);
    		 
    		 ok.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//ͼƬ�ļ���ϵͳ��ɾ��
					for(ImageBoxButton Label:ImageBoxButton.getSelectedPictures())
					{
						new File(Label.getImagePath().substring(5)).delete();
					}
					//ͬ����ͼƬ�����ɾ����������ʾ
					ImageBoxButton.getSelectedPictures().clear();
					PicturePane.getPicture(PicturePane.file);
				}
			});
    		 
    		 cancel.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					alert.close();
				}
			});
             alert.titleProperty().set("ɾ��");
             alert.headerTextProperty().set("ȷ��ɾ����ͼƬ��");
             alert.showAndWait();
             
    	 }
     }
}
