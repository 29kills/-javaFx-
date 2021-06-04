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
             alert.titleProperty().set("警告");
             alert.headerTextProperty().set("请先选择一张图片");
             alert.showAndWait();
    	 }
    	 else 
    	 {
    		 //弹出是否删除警告
    		 Alert alert = new Alert(AlertType.CONFIRMATION);
    		 Button ok=(Button)alert.getDialogPane().lookupButton(ButtonType.OK);
    		 Button cancel=(Button)alert.getDialogPane().lookupButton(ButtonType.CANCEL);
    		 
    		 ok.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//图片文件在系统的删除
					for(ImageBoxButton Label:ImageBoxButton.getSelectedPictures())
					{
						new File(Label.getImagePath().substring(5)).delete();
					}
					//同步到图片界面的删除并重新显示
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
             alert.titleProperty().set("删除");
             alert.headerTextProperty().set("确认删除该图片？");
             alert.showAndWait();
             
    	 }
     }
}
