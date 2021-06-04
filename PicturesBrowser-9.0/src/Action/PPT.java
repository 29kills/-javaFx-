package Action;





import java.io.File;

import Data.PictureSize;
import javafx.animation.FadeTransition;

import javafx.animation.KeyFrame;

import javafx.animation.Timeline;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

import javafx.util.Duration;
//幻灯片播放
public class PPT
{
	PictureSize pictureSize=new PictureSize();
	File file=null;
	File fileParent=null;
	File[] fileList=null;
	Image[]img =new Image[50];
	String[] imagePath2=new String[50];
	String imagePath;
	int index=0;
	int imgIndex;
	BorderPane pane=new BorderPane();
	BorderPane pane1=new BorderPane();
	
	Stage stage=new Stage();
	Button b1=new Button("",new ImageView("/Pictures/start.png"));
	Button b2=new Button("",new ImageView("/Pictures/stop.png"));
	HBox hbox=new HBox();
    
	
	public PPT(String imagePath)
	{
		this.imagePath=imagePath;
		findFile();
		b1.setPrefSize(100, 80);
		b2.setPrefSize(100, 80);
		hbox.setPrefSize(1800, 100);
		hbox.getChildren().addAll(b1,b2);
		hbox.setSpacing(1600/3);
		hbox.setAlignment(Pos.CENTER);
		pane.setBottom(hbox);
		pane.setCenter(pane1);
		pane.setStyle("-fx-background-color:linear-gradient( from 0.0% 0.0% to 100.0% 0.0%, rgb(255,255,255) 0.0, rgb(0,0,0) 100.0)");
		Scene scene = new Scene(pane, pictureSize.getWidth()*0.83,pictureSize.getHeight()*0.8);
		stage.setScene(scene);
		stage.setTitle("Slide Show");
		stage.show();
	}

    //找到该目录的图片文件，并且找到显示图片的位置
	private void findFile()
	{
		// TODO Auto-generated method stub
		file = new File(this.imagePath.substring(5));
		 fileParent = new File(file.getParent());
		 fileList = fileParent.listFiles();
		 for(int i =0;i<fileList.length;i++)
		 {
		     if (!fileList[i].isDirectory())
		     {
		         String fileName = fileList[i].getName();
		         String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		         //支持图片的格式
		                         if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("png")||suffix.equals(".gif")
		                         		||suffix.equals(".bmp")||suffix.equals(".jpeg"))
		                         {
		                         	imagePath2[index]="File:"+fileList[i].getAbsolutePath();
		                         	if(imagePath.equals(imagePath2[index]))
		                         	{
		                         		imgIndex=index;
		                         	}
		                             System.out.println(index+" "+ imagePath2[index]);
		                             index++;
		                         }
		               
		     }
		 }
		 //
		 final int index1=index;
		 ImageView[] ii=new ImageView[100];
		 for(int i=0;i<index1;i++)
		 {
			 Image fakeimage=new Image(imagePath2[i]);
			  if(fakeimage.getHeight()>900)
			 {Image image=new Image(imagePath2[i],0,900,true,true);
			 
			 
				 
				 ii[i]=new ImageView(image);
			 }
			 else 
			 {
               Image image=new Image(imagePath2[i]);
			 
			 
				 
				 ii[i]=new ImageView(image);
			 }
				 	
			 
			 
		 	
		 	
		 	
		 }
         //开始显示被选择的图片
		 pane1.setCenter(ii[imgIndex]);
         //事件处理
		 EventHandler<ActionEvent> eventHandler = e -> {
	     //图片切换事件
		 if (imgIndex < index1-1) {
		 pane1.getChildren().removeAll(ii[imgIndex]);
		 imgIndex++;
		 pane1.setCenter(ii[imgIndex]);
		 ii[imgIndex].setOpacity(0);
		 //每次切换图片增加一个透明度渐变的动画
		 FadeTransition ft = new FadeTransition(Duration.seconds(1), ii[imgIndex]);
		 ft.setFromValue(0);
		 ft.setToValue(1);
		 ft.play();
		 }
		 else if (imgIndex == index1-1) {
		 imgIndex = 0;
		 pane1.getChildren().remove(ii[index1-1]);
		 pane1.setCenter(ii[imgIndex]);
		 ii[imgIndex].setOpacity(0);
		 FadeTransition ft = new FadeTransition(Duration.seconds(1), ii[imgIndex]);
		 ft.setFromValue(0);
		 ft.setToValue(1);
		 ft.play();
		 }
		 };
         //总时间线动画
		 Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1.5), eventHandler));
		 animation.setCycleCount(index);
         //播放
		 b1.setOnMouseClicked(e -> {
		 	animation.play();
		 });
		 //停止
		 b2.setOnMouseClicked(e -> {
		 	animation.pause();
		 });
	}
}