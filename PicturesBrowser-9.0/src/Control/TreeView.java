package Control;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;

//目录树
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
        //获得文件系统的根目录
        File[] items = File.listRoots();
        TreeItem<File> mainTreeItem = new TreeItem<>();

        //主树项目加入根目录下的硬盘
        for (File item : items) {
            if(item.isDirectory()) {
                TreeItem<File> treeItem = new TreeItem<>(item);
                mainTreeItem.getChildren().add(treeItem);//根目录的硬盘加入其目录下的文件
                addItems(treeItem, 0);
            }
        }
        //主树项目加入treeview并设为根节点且不可见
        javafx.scene.control.TreeView<File> treeView = new javafx.scene.control.TreeView<>(mainTreeItem);
        rootPane.getChildren().add(treeView);
        treeView.setRoot(mainTreeItem);
        treeView.setShowRoot(false);

       //被选中的树项目添加监听书简
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String path = newValue.getValue().getAbsolutePath();
           //张开选择文件目录下的文件

            try {
            	//点击后其子文件也添加子文件
                addItems(newValue, 0);
                PicturePane.getPicture(newValue);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //树状试图设置显示
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

    
    //添加文件项目，不能一次添加完,每次添加两层次，即子文件添加子文件
    public void addItems(TreeItem<File> root, int number) throws IOException {
        File[] fileList = root.getValue().listFiles();
        if (fileList != null) {
            if (number == 0) {
                
                root.getChildren().remove(0, root.getChildren().size());//避免重复添加
            }

            if (fileList.length > 0) {
                for (File file : fileList) {
                    if (file.isDirectory() & !file.isHidden()) {
                        TreeItem<File> newItem = new TreeItem<>(file);
                        if (number < 1&&(root.getValue().listRoots()!=File.listRoots())) 
                        {
                            //递归调用多一次添加项目
                            addItems(newItem, number + 1);
                        }
                        root.getChildren().add(newItem);
                    }
                }
            }





        }
    }
    //判断是否为根目录下的文件，如果是，则输出toString(如：C:\,D:\,E:\)
       //如果不是,则输出文件名字
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