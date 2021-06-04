package Control;



import Action.PPT;
import Data.PictureSize;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageBrowsePane {
	//gai
    private WritableImage wImage;   // ����ֻ�ܴ�JavaFX��Image�ж�ȡ���أ�������д�룬����Ҫ��WritableImage���в�����
	private BorderPane borderPane1 =new BorderPane();
    private BorderPane borderPane = new BorderPane();

    private ScrollPane scrollPane = new ScrollPane();
    private String imagePath;
    private String imageName;
    private Label imageLabel = new Label();
    private HBox hBox = new HBox();
    private HBox vBox = new HBox();
    private FileChooser fileChooser;
    private PictureSize picturesize = new PictureSize();
    private double times =1;  //ͼƬ�Ŵ����С�ı���
    private Image image ;
    ImageView imageView;


    ImageBrowsePane(String imagePath, String imageName){
        this.imagePath = imagePath;
        this.imageName = imageName;
        System.out.println(imagePath);
        Stage imageStage = new Stage();

        imageStage.setTitle("��JAVA����ͼƬ�༭���� ");
        //picturesize picturesize = new picturesize();
        borderPane.setStyle("-fx-background-color:linear-gradient( from 0.0% 0.0% to 100.0% 0.0%, rgb(4,23,68) 0.0, rgb(4,23,68) 100.0)");
        initImage(imagePath);
        addMenuOnPane();
        imageStage.setScene(new Scene(borderPane,picturesize.getWidth()*0.7,picturesize.getHeight()*0.7));
        imageStage.show();
    }


    private void initImage(String imagePath){
        image = new Image(imagePath);  
        imageView = new ImageView(image);   //��ͼƬ�ŵ�ImageView�д���
        imageView.setFitWidth(picturesize.getWidth()-100); 
        imageView.setFitHeight(picturesize.getHeight()-150);
        System.out.println("ss"+picturesize.getWidth()+"ss"+picturesize.getHeight());
        imageView.setPreserveRatio(true);//�������ű���
        borderPane.setCenter(scrollPane);
        addImageOnPane(imageView);
    }



    public void addImageOnPane(ImageView imageView){
        imageLabel.setStyle("-fx-border-opacity:0");   //�������ߵı߿�Ϊ��ɫ
        imageLabel.setPrefSize(picturesize.getWidth(),picturesize.getHeight());
        imageLabel.setAlignment(Pos.BASELINE_CENTER);
        imageLabel.setGraphic(imageView);
        scrollPane.setContent(borderPane1);
        scrollPane.setStyle("-fx-background-color:#041744");
        borderPane1.setCenter(imageLabel);
        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);

    }

    public void addMenuOnPane(){
        Stage imageStage = new Stage();
        imageStage.setTitle(imageName);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ͼƬ�ļ�", "*.png","*.jpg", "*.bmp", "*.gif"));

        hBox.setPrefSize(picturesize.getWidth()-100,100);
        hBox.setSpacing(10);
        vBox.setSpacing(2);
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().clear();
        vBox.getChildren().clear();
        Button exportButton = new Button("",new ImageView("/Pictures/����3.png"));
        Button recoverButton = new Button("��ԭ");
        Button PPTButton = new Button("",new ImageView("/Pictures/PPT3.png"));
        Button grayButton = new Button("�Ҷȴ���");
        Button darkButton = new Button("�䰵");
        Button brightButton = new Button("����");
        Button saturate  = new Button("���ӱ��Ͷ�");
        Button desaturate = new Button("���ٱ��Ͷ�");
        Button invertButton = new Button("��ɫ��ת");


        Button enlargeButton = new Button("",new ImageView("/Pictures/big.png"));
        Button narrowButton = new Button("",new ImageView("/Pictures/small.png"));
        Button moveLeftButton = new Button("",new ImageView("/Pictures/left.png"));
        Button moveRightButton = new Button("",new ImageView("/Pictures/right.png"));
        Button rotateButton = new Button("",new ImageView("/Pictures/rotate.png"));
        enlargeButton.setStyle("-fx-background-color:#041744");
        narrowButton.setStyle("-fx-background-color:#041744");
        moveLeftButton.setStyle("-fx-background-color:#041744");
        moveRightButton.setStyle("-fx-background-color:#041744");
        rotateButton.setStyle("-fx-background-color:#041744");

        PPTButton.setPrefSize(100, 50);
        grayButton.setPrefSize(100, 50);
        darkButton.setPrefSize(100,50);
        brightButton.setPrefSize(100,50);
        exportButton.setPrefSize(100,50);
        recoverButton.setPrefSize(100,50);
        saturate.setPrefSize(100,50);
        desaturate.setPrefSize(100,50);
        invertButton.setPrefSize(100,50);

        enlargeButton.setPrefSize(100,100);
        narrowButton.setPrefSize(100,100);
        moveLeftButton.setPrefSize(100,100);
        moveRightButton.setPrefSize(100,100);
        rotateButton.setPrefSize(100,100);

        hBox.getChildren().addAll(moveLeftButton,enlargeButton,rotateButton,narrowButton,moveRightButton,saturate);
        /*22222*/
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        VBox mVBox = new VBox(10);
        HBox mButtonsBox = new HBox(10);

        mButtonsBox.setAlignment(Pos.CENTER);
        mVBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(PPTButton,grayButton,darkButton,brightButton,saturate,desaturate,invertButton,recoverButton,exportButton);
/*3333*/
        recoverButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageView.setImage(image);
            }
        });

        invertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("invert");
            }
        });


        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showSaveDialog(imageStage.getOwner());
                if (file != null) {
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(wImage, null), "png", file);
                        System.out.print("YES");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        PPTButton.setOnAction(e->{
            new PPT(this.imagePath);
        });

        saturate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("saturate");//���ε������ӱ��Ͷȵĺ���
            }
        });
        desaturate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("desaturate");//���ε��ý��ͱ��Ͷȵĺ���
            }
        });
        brightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("brighter");//���ε��ñ����ĺ���
            }
        });
        darkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("darker");//���ε��ñ䰵�ĺ���
            }
        });
        grayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pixImage("gray");//���ε��ûҶȵĺ���
            }
        });


        PPTButton.setOnAction(e->{
        	new PPT(this.imagePath);
        });


        rotateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				rotateImage();      //���ε�����ת�ĺ���
			}

			private void rotateImage() {
				// TODO Auto-generated method stub
				imageView.setRotate((imageView.getRotate() + 90) % 360); //����ǰ��imageView����90��
			}
		});

        enlargeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enlargeImage();
            }
        });

        narrowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                narrowImage();
            }
        });

        moveLeftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                moveLeft();
            }
        });

        moveRightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                moveRight();
            }
        });

        borderPane.setBottom(hBox);
        borderPane.setTop(vBox);
    }


	private void enlargeImage(){
            times += 0.15;
        setChangedSize();
    }



    private void pixImage(String type){
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        // Create WritableImage
        wImage = new WritableImage(       //ֻ�ܴ�JavaFX��Image�ж�ȡ���أ�������д�룬Ҫ��WritableImage������
                (int)image.getWidth(),
                (int)image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();    //  PixelReader��PixelWriter���������Ƿֱ�������ض�ȡ������д����ࡣ
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                Color color = pixelReader.getColor(x, y);
                switch (type) {
                    case "brighter":
                        color = color.brighter();  //ʹ��java�Դ��ı�������
                        break;
                    case "darker":
                        color = color.darker();  //ʹ��java�Դ��ı䰵����
                        break;
                    case "gray":
                        color = color.grayscale();  //ʹ��java�Դ��ĻҶȴ�����
                        break;
                    case "invert":
                        color = color.invert(); //ʹ��javafx����ɫ��ת����
                        break;
                    case "saturate":
                        color = color.saturate();//���ӱ��Ͷ�
                        break;
                    case "desaturate":
                        color = color.desaturate();//���ͱ��Ͷ�
                        break;
                    default:
                        break;
                }
                pixelWriter.setColor(x, y, color); //�ڶ�Ӧ�����ص�д�϶�Ӧ���޸ĺ������
            }
        }
        imageView.setImage(wImage);   //���޸ĺ��ͼ�����imageview��
    }


    private void narrowImage(){
        times -= 0.15;
        if(times<=0){      //ÿ�μ��ٳ����0.15��
            times +=0.15;   //���������������Ϊ��
        }
        setChangedSize();
    }

    private void setChangedSize() {
        imageView.setFitWidth((picturesize.getWidth()-100)*times);
        imageView.setFitHeight((picturesize.getHeight()-150)*times);
        //
        borderPane1.getChildren().clear();
        borderPane1.setCenter(imageLabel);
        //
        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);

    }

    private void moveLeft(){
        File file = new File(imagePath.substring(5));
        File fileParent = new File(file.getParent());
        File[] fileList = fileParent.listFiles();
        int last = 0;
        for(int i =0;i<fileList.length;i++){
            if (!fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //֧��ͼƬ�ĸ�ʽ
                if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")||suffix.equals("gif")
                        ||suffix.equals("bmp")||suffix.equals("jpeg")) {
                        last = i;
                }
            }
        }
        System.out.println("last="+last);
        int t1=-1;
        int t2=-1;
        for(int i =0;i<fileList.length;i++){
            if (!fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //֧��ͼƬ�ĸ�ʽ
                if (suffix.equals("jpg")||suffix.equals("JPG")||suffix.equals("png")) {
                    t1=t2;
                    t2=i;
                    if(fileList[i].getAbsolutePath().equals(imagePath.substring(5))){
                            if(t1==-1){
                                t1 = last;
                            }
                        System.out.println("t1="+t1);
                            break;
                    }
                }
            }
        }

        imageView = new ImageView(new Image("File:"+fileList[t1].getAbsolutePath()));

        imageView.setFitWidth(picturesize.getWidth()-100);
        imageView.setFitHeight(picturesize.getHeight()-150);
        imageView.setPreserveRatio(true);//�������ű���

        imagePath = "File:"+fileList[t1].getAbsolutePath();
        //System.out.println(imagePath);
        addImageOnPane(imageView);
        //System.out.println("t2:"+t2);
        times =1;
        initImage(imagePath);
    }

    private void moveRight() {
        File file = new File(imagePath.substring(5));
        File fileParent = new File(file.getParent());
        File[] fileList = fileParent.listFiles();

        int t = 0;
        int t1 = 0;
        int t2 = 0;

        label:
        for (int i = 0; i < fileList.length; i++) {
            if (!fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                //֧��ͼƬ�ĸ�ʽ
                if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("png")||suffix.equals("gif")
                        ||suffix.equals("bmp")||suffix.equals("jpeg")) {
                    if (t1 == 0) {
                        t2 = i;
                        t1++;
                    }
                    if (fileList[i].getAbsolutePath().equals(imagePath.substring(5))) {

                        for (int j = i + 1; j < fileList.length; j++) {
                            if (!fileList[j].isDirectory()) {
                                fileName = fileList[j].getName();
                                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                                //֧��ͼƬ�ĸ�ʽ
                                if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("png")||suffix.equals("gif")
                                        ||suffix.equals("bmp")||suffix.equals("jpeg")) {
                                    t = j;
                                    System.out.println(t);
                                    break label;
                                }
                            }
                        }
                        t = t2;
                        break;
                    }
                }
            }
        }

        imageView = new ImageView(new Image("File:" + fileList[t].getAbsolutePath()));

        imageView.setFitWidth(picturesize.getWidth() - 100);
        imageView.setFitHeight(picturesize.getHeight() - 150);
        imageView.setPreserveRatio(true);//�������ű���

        imagePath = "File:" + fileList[t].getAbsolutePath();
        //System.out.println(imagePath);
        addImageOnPane(imageView);
        //System.out.println("t2:"+t2);
        times = 1;
        initImage(imagePath);  //
    }
    }