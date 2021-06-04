package Action;

import java.io.File;

import Control.ImageBoxButton;
import Control.PicturePane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class Copy {
	public static String dir;
	public Copy()
	{
		dir= PicturePane.filePath;//���Ҫ���������Ŀ¼
		ImageBoxButton.getSelectedPictureFiles().clear();//���ѡ��ͼƬ�ļ�����
		//û��ѡ��ͼƬ���и��Ʋ���ʱ��������
	if(ImageBoxButton.getSelectedPictures().size()<=0) 
	{
		 Alert alert = new Alert(AlertType.INFORMATION);
         alert.titleProperty().set("����");
         alert.headerTextProperty().set("����ѡ��һ��ͼƬ");
         alert.showAndWait();
	
	}
	if(ImageBoxButton.getSelectedPictures().size() > 0) {
		
		ImageBoxButton.getCopyPictures().clear();//����ϴθ���ͼƬ����
	}
	Clipboard clipboard = Clipboard.getSystemClipboard();//��ȡϵͳ������
	ClipboardContent clipboardContent = new ClipboardContent();
	clipboard.clear();
	
	//ѡ��ͼƬ�����ͼƬ���ν���Ӧ���ļ����뵽ѡ��ͼƬ�ļ�����
	for(ImageBoxButton label : ImageBoxButton.getSelectedPictures()) {
		ImageBoxButton.getSelectedPictureFiles().add(label.getImageFile());
	}
	//ѡ��ͼƬ�ļ����鵼�뵽���а�
	clipboardContent.putFiles(ImageBoxButton.getSelectedPictureFiles());
	clipboard.setContent(clipboardContent);
	clipboard = null;
   clipboardContent = null;
	
	
	}
}
