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
//中间的图片界面，存放图片缩略节点
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
        //加入用于显示图片的flowPane与用于鼠标拖拽的界面
        this.getChildren().addAll(flowPane, MouseController.pane);
        setEvents();
       
    }
    //三个面板都设置事件
    private void setEvents() {
		// TODO Auto-generated method stub
    	 //两者设置点击事件,分别放入该图片界面的上方
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
        //图片面板设置事件
       this.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->
        {
        	Node clickedPane = e.getPickResult().getIntersectedNode();
        	// 鼠标点击非图片节点,即图片面板的空白区域
        	if (clickedPane instanceof Pane && !(clickedPane instanceof ImageBoxButton) && !(clickedPane instanceof Text))
        	{
        		//按两下左键取消选中
                if(e.getButton() == MouseButton.PRIMARY&&e.getClickCount()>1)
                {
                	ImageBoxButton.clearSelected();// 取消被选择图片的选择状态
                }
                // 鼠标右键，弹出菜单栏
				if (e.getButton() == MouseButton.SECONDARY&&e.getClickCount()==1)
				{
					ImageBoxButton.clearSelected();// 取消被选择图片的选择状态
					Clipboard clipboard = Clipboard.getSystemClipboard();
					List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
					if (files.size() <= 0) 
					{
						imagemenuitem.getPaste().setDisable(true);//系统剪切版为空时，显示不可点击状态
					} 
					else {
						imagemenuitem.getPaste().setDisable(false);//显示可点击状态
					}
					imagemenuitem.maincontextMenu.show(this, e.getScreenX(), e.getScreenY());//实现在鼠标坐标位置
				} 
				//菜单栏隐藏
				else
				{
					//当再次点击空白区域且菜单栏已显示时，取消显示
					if (imagemenuitem.maincontextMenu.isShowing())
					{
						imagemenuitem.maincontextMenu.hide();
					}
				}
			} 
        	//点击图片的任意操作都会将菜单隐藏
        	else if(clickedPane instanceof ImageBoxButton )
			{
				if (imagemenuitem.maincontextMenu.isShowing()) {
					
					imagemenuitem.maincontextMenu.hide();
				}
			}
        });
           
	}
	public static void getPicture(TreeItem<File> file){
    	//清空和重新设置成员变量
		PitureFile.clear();
    	flowPane.getChildren().clear();
        setFile(file);
        setFilePath(file);
        
        //判断是否文件是否是图片格式
        File[] fileList = file.getValue().listFiles();
        if(fileList.length>0)
        {
            for (File value : fileList) {
                if (!value.isDirectory()) {
                    String fileName = value.getName();
                    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    //支持图片的格式
                    if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("gif")
                    		||suffix.equals("bmp")||suffix.equals("jpeg")) {
                        setPictureOnShow("File:"+value.getAbsolutePath(),fileName);//加入到图片界面
                        PitureFile.add(value);
                        
                    }
                }
            }
        }
        //下方显示该目录的图片项目数
        Main.myTextPane.maintext.setText(PitureFile.size()+"个项目");
    }

    //根据路径(File:)显示图片到界面中
    public static void setPictureOnShow(String picturePath, String fileName){

            //图片缩略图加载进去
    	    ImageBoxButton imageBoxLabel = new ImageBoxButton(picturePath,fileName);
            flowPane.getChildren().add(imageBoxLabel.getImageLabel());

    }
    



}

