package Control;
import application.*;
import Controller.MouseController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//�м��ͼƬ���棬���ͼƬ���Խڵ�
public class PicturePane extends StackPane {
   public static final FlowPane flowPane = new FlowPane();
   public static TreeItem<File> file = new TreeItem<>();
   public static StackPane stackPane = new StackPane();
   public ScrollPane scrollPane = new ScrollPane();
   public static ToggleGroup group;
   public static ImageMenuBar imagemenuitem=new ImageMenuBar();
   public static String filePath;
   public static final ArrayList<File> PitureFile=new ArrayList<>();
    public static void setFile(TreeItem<File> file) {
        PicturePane.file = file;
    }
    public static void setFilePath(TreeItem<File> file)
    {
    	PicturePane.filePath=file.getValue().getAbsolutePath();
    }

    public PicturePane() throws IOException {
        super();
        flowPane.setPadding(new Insets(10,20,20,20));
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setHgap(20);
        flowPane.setVgap(25);
        flowPane.setStyle("-fx-background-color:#6f8487 ");
        flowPane.setPrefSize(579.8,600);
        //����������ʾͼƬ��flowPane�����������ק�Ľ���
        this.getChildren().addAll(flowPane, MouseController.pane);
        setEvents();
       
    }
    //������嶼�����¼�
    private void setEvents() {
		// TODO Auto-generated method stub
    	 //�������õ���¼�,�ֱ�����ͼƬ������Ϸ�
        MouseController.pane.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
                getChildren().add(flowPane);
            }
        });
        flowPane.setOnMouseDragged(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent mouseEvent) {
              
                getChildren().clear();
                getChildren().addAll(flowPane,MouseController.pane);
            }
        });
        //ͼƬ��������¼�
       this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->
        {
        	Node clickedPane = e.getPickResult().getIntersectedNode();
        	// �������ͼƬ�ڵ�,��ͼƬ���Ŀհ�����
        	if (clickedPane instanceof Pane && !(clickedPane instanceof ImageBoxButton) && !(clickedPane instanceof Text))
        	{
        		//���������ȡ��ѡ��
                if(e.getButton() == MouseButton.PRIMARY&&e.getClickCount()>1)
                {
                	ImageBoxButton.clearSelected();// ȡ����ѡ��ͼƬ��ѡ��״̬
                }
                // ����Ҽ��������˵���
				if (e.getButton() == MouseButton.SECONDARY&&e.getClickCount()==1)
				{
					ImageBoxButton.clearSelected();// ȡ����ѡ��ͼƬ��ѡ��״̬
					Clipboard clipboard = Clipboard.getSystemClipboard();
					List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
					if (files.size() <= 0) 
					{
						imagemenuitem.getPaste().setDisable(true);//ϵͳ���а�Ϊ��ʱ����ʾ���ɵ��״̬
					} 
					else {
						imagemenuitem.getPaste().setDisable(false);//��ʾ�ɵ��״̬
					}
					imagemenuitem.maincontextMenu.show(this, e.getScreenX(), e.getScreenY());//ʵ�����������λ��
				} 
				//�˵�������
				else
				{
					//���ٴε���հ������Ҳ˵�������ʾʱ��ȡ����ʾ
					if (imagemenuitem.maincontextMenu.isShowing())
					{
						imagemenuitem.maincontextMenu.hide();
					}
				}
			} 
        	//���ͼƬ������������Ὣ�˵�����
        	else if(clickedPane instanceof ImageBoxButton )
			{
				if (imagemenuitem.maincontextMenu.isShowing()) {
					
					imagemenuitem.maincontextMenu.hide();
				}
			}
        });
           
	}
	public static void getPicture(TreeItem<File> file){
    	//��պ��������ó�Ա����
		PitureFile.clear();
    	flowPane.getChildren().clear();
        setFile(file);
        setFilePath(file);
        
        //�ж��Ƿ��ļ��Ƿ���ͼƬ��ʽ
        File[] fileList = file.getValue().listFiles();
        if(fileList.length>0)
        {
            for (File value : fileList) {
                if (!value.isDirectory()) {
                    String fileName = value.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //֧��ͼƬ�ĸ�ʽ
                    if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("gif")
                    		||suffix.equals("bmp")||suffix.equals("jpeg")) {
                        setPictureOnShow("File:"+value.getAbsolutePath(),fileName);//���뵽ͼƬ����
                        PitureFile.add(value);
                        
                    }
                }
            }
        }
        //�·���ʾ��Ŀ¼��ͼƬ��Ŀ��
        Main.myTextPane.maintext.setText(PitureFile.size()+"����Ŀ");
    }

    //����·��(File:)��ʾͼƬ��������
    public static void setPictureOnShow(String picturePath, String fileName){

            //ͼƬ����ͼ���ؽ�ȥ
    	    ImageBoxButton imageBoxLabel = new ImageBoxButton(picturePath,fileName);
            flowPane.getChildren().add(imageBoxLabel.getImageLabel());

    }
    



}

