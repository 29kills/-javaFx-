package Action;


import java.io.File;

import Control.ImageBoxButton;
import Control.PicturePane;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Rename
{
	Stage stage=new Stage();
	TextField textfield =new TextField();
	TextField start=new TextField();
	TextField bit=new TextField();
	Text text=new Text();
	Tooltip tooltip=new Tooltip("�ļ������ܰ��������κ��ַ�: ? \\ * | \" < > : /");
	BorderPane borderPane=new BorderPane();
    Button button=new Button("ȷ��");
     public Rename()
     {

    	 
    	 
    	 //���������Ĵ��ڲ���
    	 if(ImageBoxButton.getSelectedPictures().size()==1)
    	 {
    	      VBox vbox=new VBox();
    	      vbox.getChildren().add(text);
    	      vbox.setAlignment(Pos.CENTER_LEFT);
    	       
    	       textfield.setTooltip(tooltip);
    	       textfield.setPromptText("����������");
    	       borderPane.setTop(textfield);
       	       borderPane.setCenter(vbox);
       	       borderPane.setBottom(button);
       	       Scene scene=new Scene(borderPane,300,100);
       	 
       	       
       	       stage.setResizable(false);
       	       stage.initOwner(Main.primarystage);
       	      stage.initModality(Modality.APPLICATION_MODAL);
       	      stage.setScene(scene);
       	       stage.show();
    	 }
    	 //�����������Ĵ��ڲ���
    	 else 
    	 {
    		 VBox vbox1=new VBox(); 
    		 VBox vbox2=new VBox(); 
    		 VBox vbox3=new VBox(); 
    		 textfield.setTooltip(tooltip);
    		 start.setPromptText("��ʼ����");
    		 bit.setPromptText("��׺λ��");
    		 vbox1.getChildren().addAll(new Label("����       "),textfield);
    		 vbox2.getChildren().addAll(new Label("��ʼ���"),start);
    		 vbox3.getChildren().addAll(new Label("��׺λ��"),bit);
    		 borderPane.setTop(vbox1);
    		 borderPane.setCenter(new VBox(vbox2, vbox3,text));
    		 borderPane.setBottom(button);
    		 Scene scene=new Scene(borderPane,500,200);
        	 
        	 
        	 stage.setResizable(false);
        	 stage.initOwner(Main.primarystage);
        	 stage.initModality(Modality.APPLICATION_MODAL);
        	 stage.setScene(scene);
        	 stage.show();
    		 
    	 }
    	 
    	 //�ļ��������ֵ����ƣ����ܳ��ַǷ��ַ�
    	 textfield.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				//�зǷ��ַ�
				if(!NameCheck(arg2))
				{
					tooltip.show(textfield,stage.getX(),stage.getY());
					textfield.setText(arg1);
					
				}
				//�޷Ƿ��ַ�
				if(NameCheck(arg2)&&NameCheck(arg1))
				{
					tooltip.hide();
					text.setText(null);
				}
				
			}
		});
    	 //ȷ����ť�Ķ���
    	 button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// ��ͼƬ������
				if(ImageBoxButton.getSelectedPictures().size()==1)
				{
				   if(CheckEmpty(textfield))
				   {
					//�ж��Ƿ�ɹ�
					if(OneRename())
					{
						stage.close();
					}
					else
					{
						text.setText("�ļ����ظ�");
					}
					
				   }
				   else
				
				   {
					text.setText("����δ����");
				   }
				}
				//����ͼƬ������
				else if(ImageBoxButton.getSelectedPictures().size()>1)
				{
					if (CheckEmpty(textfield)&&CheckEmpty(start)&&CheckEmpty(bit))
					{
						//�ж��Ƿ�ɹ�
						if(MutilRename())
						{
							
							stage.close();
						}
						else
						{
							
							text.setText("�����д���");
						}
						
					}
					else 
					{
						text.setText("��Ϣδ����");
					}
				}
			}
		});
     }
     //�ж�����������Ƿ�Ϊ��
    protected boolean CheckEmpty(TextField textfield)
    {
		return (textfield.getText()!=null&&!textfield.getText().isEmpty());
    	
    }
    //������������Ƿ���ַǷ��ַ�
	protected boolean NameCheck(String name) {
		// TODO Auto-generated method stub
		if(name.contains("?")||name.contains("/")||name.contains("\\")||name.contains("|")||name.contains("*")||name.contains("\"")||name.contains("<")||name.contains(">")||name.contains(":"))
		{
			return false;
		}
		return true;
	}
    //�жϵ������Ƿ�ɹ�
    protected boolean OneRename()
{
	//�������ļ�
    	ImageBoxButton Label=ImageBoxButton.getSelectedPictures().get(0);
	File file=new File(PicturePane.filePath+File.separator+Label.getName());
	String parent=file.getParent();
	String[] filename=file.getName().split("\\.");
	String suf =filename[filename.length-1];
	File tmp =new File(parent+"\\"+textfield.getText()+"."+suf);
    //�Ƚ����ļ��ڸ�Ŀ¼���Ƿ�������
	if(!file.renameTo(tmp))
	{
		return false;//ʧ��
	}
   
	else
	{
	//�ɹ���ͬ�����µ�ͼƬ�����ʾ
	PicturePane.flowPane.getChildren().clear();
	PicturePane.getPicture(PicturePane.file);
	}
	return true;
}

	//����������Ƿ�ɹ�
    protected boolean MutilRename()
    {
    	//�ж������������λ���Ƿ��ڷ�Χ��
    	int startid=Integer.valueOf(start.getText());
    	int bits=Integer.valueOf(bit.getText());
    	if(startid<0 ||(startid+ImageBoxButton.getSelectedPictures().size())>=(int)Math.pow(10, bits)) {
			return false;
		}
    	
    	
    	for(ImageBoxButton Label:ImageBoxButton.getSelectedPictures())
    	{
    		//�������ļ�
    		File file=new File(PicturePane.filePath+File.separator+Label.getName());
    		String parent=file.getParent();
    		String[] filename=file.getName().split("\\.");
    		String suf1 =filename[filename.length-1];
    		System.out.println("???????????");
    		String newName=createNewname(startid,bits);
    		File tmp =new File(parent+"\\"+newName+"."+suf1);
    		//�Ƚ����ļ��ڸ�Ŀ¼���Ƿ�������
    		if(!file.renameTo(tmp))
    		{
    			
    			return false;
    		}
    	    startid++;
    	  
    	 }
    	//�ɹ���ͬ�����µ�ͼƬ�����ʾ
        PicturePane.flowPane.getChildren().clear();
        PicturePane.getPicture(PicturePane.file);
    	
    	
    	return true;
    }
    //������ʼ�������׺λ������������
	private String createNewname(int id, int bit)
	{
    String newName = textfield.getText();
		
		int tt = id;
    	int cnt=0;
    	while(tt!=0) {
    		cnt++;
    		tt/=10;
    	}
    	if(id==0) {
			cnt++;
		}
		while(bit>cnt) {
			newName+=0;
			cnt++;
		}
    	newName += id;
		return newName;
	}
	
}
	

