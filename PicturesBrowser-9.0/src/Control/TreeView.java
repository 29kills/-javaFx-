package Control;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

//Ŀ¼��
public class TreeView extends javafx.scene.control.TreeView<File> {
    public StackPane rootPane = new StackPane();

    public TreeView() throws IOException {
        setPrefSize(311, 718);
        setTreeView();
    }

    public void setTreeView() throws IOException {


        ImageView folderIcon = new ImageView();
        folderIcon.setOpacity(0);
        folderIcon.setPreserveRatio(true);
        folderIcon.setFitWidth(16);
        folderIcon.setFitHeight(16);
        //����ļ�ϵͳ�ĸ�Ŀ¼
        File[] items = File.listRoots();
        TreeItem<File> mainTreeItem = new TreeItem<>();

        //������Ŀ�����Ŀ¼�µ�Ӳ��
        for (File item : items) {
            if(item.isDirectory()) {
                TreeItem<File> treeItem = new TreeItem<>(item);
                mainTreeItem.getChildren().add(treeItem);//��Ŀ¼��Ӳ�̼�����Ŀ¼�µ��ļ�
                addItems(treeItem, 0);
            }
        }
        //������Ŀ����treeview����Ϊ���ڵ��Ҳ��ɼ�
        javafx.scene.control.TreeView<File> treeView = new javafx.scene.control.TreeView<>(mainTreeItem);
        rootPane.getChildren().add(treeView);
        treeView.setRoot(mainTreeItem);
        treeView.setShowRoot(false);

       //��ѡ�е�����Ŀ��Ӽ������
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String path = newValue.getValue().getAbsolutePath();
           //�ſ�ѡ���ļ�Ŀ¼�µ��ļ�

            try {
            	//����������ļ�Ҳ������ļ�
                addItems(newValue, 0);
                PicturePane.getPicture(newValue);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //��״��ͼ������ʾ
        treeView.setCellFactory(new Callback<javafx.scene.control.TreeView<File>,TreeCell<File>>() {
            @Override
            public TreeCell<File> call(javafx.scene.control.TreeView<File> param) {
                return new TreeCell<File>() {
                    @Override
                    protected void updateItem(File item, boolean empty) {

                        if (!empty) {
                            super.updateItem(item, false);
                            Label label = new Label(isListRoots(item));
                            this.setGraphic(label);
                        } else {
                            this.setGraphic(null);
                        }
                    }
                };
            }
        });
        
        



    }

    
    //����ļ���Ŀ������һ�������,ÿ���������Σ������ļ�������ļ�
    public void addItems(TreeItem<File> root, int number) throws IOException {
        File[] fileList = root.getValue().listFiles();
        if (fileList != null) {
            if (number == 0) {
                
                root.getChildren().remove(0, root.getChildren().size());//�����ظ����
            }

            if (fileList.length > 0) {
                for (File file : fileList) {
                    if (file.isDirectory() & !file.isHidden()) {
                        TreeItem<File> newItem = new TreeItem<>(file);
                        if (number < 1&&(root.getValue().listRoots()!=File.listRoots())) 
                        {
                            //�ݹ���ö�һ�������Ŀ
                            addItems(newItem, number + 1);
                        }
                        root.getChildren().add(newItem);
                    }
                }
            }





        }
    }
    //�ж��Ƿ�Ϊ��Ŀ¼�µ��ļ�������ǣ������toString(�磺C:\,D:\,E:\)
       //�������,������ļ�����
    public String isListRoots(File item) {
        File[] rootlist = File.listRoots();
        for (File isListRoots : rootlist) {
            if (item.toString().equals(isListRoots.toString())) {
                return item.toString();
            }
        }
        return item.getName();
    }




}