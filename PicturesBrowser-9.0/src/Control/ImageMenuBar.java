package Control;





import Action.Copy;
import Action.Delete;
import Action.Paste;
import Action.Rename;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;


public class ImageMenuBar {
    private final   ContextMenu contextMenu = new ContextMenu();//ÿ��ͼƬ�Ĳ˵�����
    public final  ContextMenu maincontextMenu =new ContextMenu();//PicturePane�Ĳ˵���
    MenuItem selectedAll=new MenuItem("ȫѡ");
    MenuItem paste=new MenuItem("ճ��");
    MenuItem delete = new MenuItem("ɾ��");
    MenuItem copy = new MenuItem("����");
    MenuItem reName = new MenuItem("������");
     
    
    
    ImageMenuBar(){
        delete.setStyle("-fx-text-fill:RED");
        //����
        copy.setOnAction(e->{
        	new Copy();
        });
        //ճ��
        paste.setOnAction(e->{
        	new Paste();
        });
        //ȫѡ
        selectedAll.setOnAction(e->{
        	for (Node node :  PicturePane.flowPane.getChildren())    			
    				((ImageBoxButton)node).setSelected(true);	
        });
        //������
        reName.setOnAction(e->{
        	//��û�б�ѡ���ͼƬ������������������
        	if(ImageBoxButton.getSelectedPictures().size()==0)
        	{
        		 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.titleProperty().set("����");
                 alert.headerTextProperty().set("����ѡ��һ��ͼƬ");
                 alert.showAndWait();
        	}
        	else 
        	{
        		
        	    new Rename();
        	}
        });
        //ɾ��
        delete.setOnAction(e->{
        	new Delete();
        });
        
        maincontextMenu.getItems().addAll(selectedAll,paste);
        contextMenu.getItems().addAll(delete,copy,reName);
        contextMenu.setStyle("-fx-background-color:rgb(255, 255, 255, .85)");    //͸������


    }
    
    public ContextMenu getContextMenu(){
        return contextMenu;
    }

    public MenuItem getPaste()
    {
    	return paste;
    }

    // ��ȡ������չ�����ļ���
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}