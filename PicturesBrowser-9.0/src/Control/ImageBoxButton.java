package Control;

import java.io.File;
import java.util.ArrayList;

import application.Main;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

//图片界面中缩略图节点
public class ImageBoxButton extends Button {
    private final String imageName;
    private final String imagePath;
    private final Labeled imageLabel = new Label();
    private final Button button=this;
    private final VBox vBox = new VBox();
    private final ImageView imageView;
    private final Image image;
    private final File imageFile;
    private final ImageMenuBar item = new ImageMenuBar();
    //判断是否被选中 
     public BooleanProperty selected = new SimpleBooleanProperty();
     //三个数组，用于将被选中的图片进行复制粘贴重命名等处理
     public static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
 	public static ArrayList<ImageBoxButton> selectedPictures = new ArrayList<>();
 	public static ArrayList<ImageBoxButton> CopyPictures = new ArrayList<>();
    //该节点由
	public ImageBoxButton(String imagePath, String imageName){
         this.imagePath = imagePath;
         this.imageName = imageName;
        imageFile=new File(imagePath);
         image = new Image(imagePath);
         imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);//保持缩放比例
        imageLabel.setGraphic(imageView);
        Label ImageName = new Label();
        ImageName.setText(imageName);
        imageLabel.setAlignment(Pos.BASELINE_CENTER);
        imageLabel.setPrefSize(100,100);
        vBox.getChildren().addAll(imageLabel, ImageName);
        //button由缩小的图片与图片名字组成
        button.setGraphic(vBox);
        button.setStyle("-fx-background-color:transparent");

       //点击图片事件
       setButtonEvents();
    //右击图片事件
    imageLabel.setContextMenu(item.getContextMenu());


    }
    //
	public String getImagePath()
	{
		return this.imagePath;
	}
    public File getImageFile()
    {
    	return this.imageFile;
    }
    public Image getImage()
    {
    	return this.image;
    }
    public ImageView getImageView()
    {
    	return this.imageView;
    }
    public String getName()
    {
    	return imageName;
    }
    
    //选择状态的设置
    public void setSelected(boolean value)
    {
    	boolean istrue = selected.get();
		selected.set(value);
		if (selected.get() && !istrue)
			selectedPictures.add(this);
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		Main.myTextPane.secondtext.setText("已选中"+selectedPictures.size()+" 张图片");
    }
    public static void clearSelected() {
		for (ImageBoxButton imageboxlabel : selectedPictures) {
			imageboxlabel.selected.set(false);
		}
		selectedPictures.removeAll(selectedPictures);
		System.out.println("clearselected");
		Main.myTextPane.secondtext.setText("已选中"+selectedPictures.size()+" 张图片");
	}
    //
    public static ArrayList<File> getSelectedPictureFiles() {
		return selectedPictureFiles;
	}
	public static ArrayList<ImageBoxButton> getSelectedPictures() {
		return selectedPictures;
	}
	public static void setCopyPictures(ArrayList<ImageBoxButton> cutedPictures) {
		ImageBoxButton.CopyPictures = cutedPictures;
	}
	public static void addCopyPictures(ImageBoxButton pNode){
		ImageBoxButton.CopyPictures.add(pNode);
	}
	public static ArrayList<ImageBoxButton> getCopyPictures() {
		return CopyPictures;
	}
	public static void clearCutedPictures() {
		CopyPictures.clear();
	}
	
    //
    public void setButtonEvents()
    {
    	//根据该节点选中状态改变颜色
    	selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()) {
					button.setStyle("-fx-background-color:#03bfb9;");
                    
					
				}else {
					button.setStyle("-fx-background-color:transparent;");
				}
			}
		});
    	
    	//鼠标进入该节点
    	button.setOnMouseEntered(mouseEvent->{
    		if(!selected.get())
    		{
    			System.out.println("blue");
    			button.setStyle("-fx-background-color: #06efe8");

    		}
    	});
    	//鼠标退出该节点
    	button.setOnMouseExited(mouseEvent->{
    		if(!selected.get())
    		{
    			System.out.println("tuichu");
    			button.setStyle("-fx-background-color:transparent ");
    		}
    	});
        button.setAccessibleHelp(imageName);
        //设置点击事件
    	button.setOnMouseClicked(mouseEvent -> {
            
    		PicturePane.imagemenuitem.maincontextMenu.hide();
    		//没有按control键时
            if(mouseEvent.isControlDown()==false)
            {
            	if(mouseEvent.getButton()!=MouseButton.SECONDARY || !(this.selected.getValue())) {
            		ImageBoxButton.clearSelected();
                }
				this.setSelected(true);
				
            //双击左键创建一个舞台
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
            {
            	if(this.selected.getValue())
            	{ System.out.println(mouseEvent.getClickCount());
                new ImageBrowsePane(imagePath,imageName);
                this.setSelected(true);

            	}
            }
            //单机显示缩略图
            else if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 1){
            	
            	ImageBoxButton.clearSelected();
            	this.setSelected(true);
                ImagePreviewLabel imagePreviewLabel = new ImagePreviewLabel(imagePath,300,300);
                imagePreviewLabel.addLabelOnPane(imagePreviewLabel.getImageLabeled());
                ImagePreview imagePreviewInformation = new ImagePreview(imagePath,imageName);
                imagePreviewInformation.addInformationOfImageOnPane(imagePreviewInformation.getImageInformationLabel());
            }
            
            }

        });

    }

    public Button getImageLabel() {
        return button;
    }
}