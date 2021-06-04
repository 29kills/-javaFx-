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

//ͼƬ����������ͼ�ڵ�
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
    //�ж��Ƿ�ѡ�� 
     public BooleanProperty selected = new SimpleBooleanProperty();
     //�������飬���ڽ���ѡ�е�ͼƬ���и���ճ���������ȴ���
     public static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
 	public static ArrayList<ImageBoxButton> selectedPictures = new ArrayList<>();
 	public static ArrayList<ImageBoxButton> CopyPictures = new ArrayList<>();
    //�ýڵ���
	public ImageBoxButton(String imagePath, String imageName){
         this.imagePath = imagePath;
         this.imageName = imageName;
        imageFile=new File(imagePath);
         image = new Image(imagePath);
         imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);//�������ű���
        imageLabel.setGraphic(imageView);
        Label ImageName = new Label();
        ImageName.setText(imageName);
        imageLabel.setAlignment(Pos.BASELINE_CENTER);
        imageLabel.setPrefSize(100,100);
        vBox.getChildren().addAll(imageLabel, ImageName);
        //button����С��ͼƬ��ͼƬ�������
        button.setGraphic(vBox);
        button.setStyle("-fx-background-color:transparent");

       //���ͼƬ�¼�
       setButtonEvents();
    //�һ�ͼƬ�¼�
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
    
    //ѡ��״̬������
    public void setSelected(boolean value)
    {
    	boolean istrue = selected.get();
		selected.set(value);
		if (selected.get() && !istrue)
			selectedPictures.add(this);
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		Main.myTextPane.secondtext.setText("��ѡ��"+selectedPictures.size()+" ��ͼƬ");
    }
    public static void clearSelected() {
		for (ImageBoxButton imageboxlabel : selectedPictures) {
			imageboxlabel.selected.set(false);
		}
		selectedPictures.removeAll(selectedPictures);
		System.out.println("clearselected");
		Main.myTextPane.secondtext.setText("��ѡ��"+selectedPictures.size()+" ��ͼƬ");
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
    	//���ݸýڵ�ѡ��״̬�ı���ɫ
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
    	
    	//������ýڵ�
    	button.setOnMouseEntered(mouseEvent->{
    		if(!selected.get())
    		{
    			System.out.println("blue");
    			button.setStyle("-fx-background-color: #06efe8");

    		}
    	});
    	//����˳��ýڵ�
    	button.setOnMouseExited(mouseEvent->{
    		if(!selected.get())
    		{
    			System.out.println("tuichu");
    			button.setStyle("-fx-background-color:transparent ");
    		}
    	});
        button.setAccessibleHelp(imageName);
        //���õ���¼�
    	button.setOnMouseClicked(mouseEvent -> {
            
    		PicturePane.imagemenuitem.maincontextMenu.hide();
    		//û�а�control��ʱ
            if(mouseEvent.isControlDown()==false)
            {
            	if(mouseEvent.getButton()!=MouseButton.SECONDARY || !(this.selected.getValue())) {
            		ImageBoxButton.clearSelected();
                }
				this.setSelected(true);
				
            //˫���������һ����̨
            if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
            {
            	if(this.selected.getValue())
            	{ System.out.println(mouseEvent.getClickCount());
                new ImageBrowsePane(imagePath,imageName);
                this.setSelected(true);

            	}
            }
            //������ʾ����ͼ
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