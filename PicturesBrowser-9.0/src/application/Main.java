package application;

import java.io.IOException;

import Control.*;
import Controller.MouseController;
import Data.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primarystage;
    public static BorderPane borderPane;
    public static Scene scene;
    public static HomePage homePage01;
    public static InformationMenuBar myMenuBar;
    public static PreviewInfoPane PreviewInfoPane;
    public static TreeView treeView;
    public static PicturePane PicturePane;
    public static TextPane myTextPane;
    public static MouseController mouseController;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Main.primarystage=primaryStage;
        primaryStage.setTitle("Electronic Picture Management Program");
        primaryStage.setMaximized(true);//能够最大化
        primaryStage.setResizable(true);//能够改变窗口大小
        InitPanes();//初始化界面
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @SuppressWarnings("unused")
	private void InitPanes() throws IOException {
		// TODO Auto-generated method stub
    	//主题布局版
    	borderPane = new BorderPane();
    	//设置为桌面大小
        homePage01 = new HomePage();
        scene = new Scene(borderPane,homePage01.getWidth(), homePage01.getHeight());
        

        //建立菜单栏
        InformationMenuBar myMenuBar = new InformationMenuBar();
        borderPane.setTop(myMenuBar);
        myMenuBar.setId("menubar");

        //建立具体信息栏
        PreviewInfoPane PreviewInfoPane = new PreviewInfoPane();
        borderPane.setRight(PreviewInfoPane);
        PreviewInfoPane.setId("PreviewInfoPane");
        
        //建立目录树
         treeView = new TreeView();
        treeView.rootPane.setId("treeview");
        borderPane.setLeft(treeView.rootPane);

        //建立图片缩放界面
        PicturePane = new PicturePane();
        borderPane.setCenter(PicturePane);
        PicturePane.setId("flowpane");
        
        //建立下方信息栏
         myTextPane=new TextPane();
        borderPane.setBottom(myTextPane);
        
        //建立拖动控件
        MouseController mouseController = new MouseController();
        
        //程序关闭后清空粘贴版内容
        primarystage.setOnCloseRequest(event->{
          	 Clipboard.getSystemClipboard().clear();
          });
	}


	public static void main(String[] args) {
        launch(args);
    }
}