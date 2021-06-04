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
        primaryStage.setMaximized(true);//�ܹ����
        primaryStage.setResizable(true);//�ܹ��ı䴰�ڴ�С
        InitPanes();//��ʼ������
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @SuppressWarnings("unused")
	private void InitPanes() throws IOException {
		// TODO Auto-generated method stub
    	//���Ⲽ�ְ�
    	borderPane = new BorderPane();
    	//����Ϊ�����С
        homePage01 = new HomePage();
        scene = new Scene(borderPane,homePage01.getWidth(), homePage01.getHeight());
        

        //�����˵���
        InformationMenuBar myMenuBar = new InformationMenuBar();
        borderPane.setTop(myMenuBar);
        myMenuBar.setId("menubar");

        //����������Ϣ��
        PreviewInfoPane PreviewInfoPane = new PreviewInfoPane();
        borderPane.setRight(PreviewInfoPane);
        PreviewInfoPane.setId("PreviewInfoPane");
        
        //����Ŀ¼��
         treeView = new TreeView();
        treeView.rootPane.setId("treeview");
        borderPane.setLeft(treeView.rootPane);

        //����ͼƬ���Ž���
        PicturePane = new PicturePane();
        borderPane.setCenter(PicturePane);
        PicturePane.setId("flowpane");
        
        //�����·���Ϣ��
         myTextPane=new TextPane();
        borderPane.setBottom(myTextPane);
        
        //�����϶��ؼ�
        MouseController mouseController = new MouseController();
        
        //����رպ����ճ��������
        primarystage.setOnCloseRequest(event->{
          	 Clipboard.getSystemClipboard().clear();
          });
	}


	public static void main(String[] args) {
        launch(args);
    }
}