package Control;



import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class InformationMenuBar extends MenuBar {

    public InformationMenuBar(){
        super();
        Label b=new Label("�ͷ�",new ImageView("/Pictures/�ͷ�.png"));
        Label c=new Label("�û���֪",new ImageView("/Pictures/��Ϣ.png"));
        b.setStyle("-fx-text-fill: #ffffff");
        Menu menuHelp = new Menu(null,b);
        Menu menuImfo = new Menu(null,c);
        getMenus().addAll(menuHelp);
        getMenus().addAll(menuImfo);
        setStyle("-fx-background-color:#4a5459");
        MenuItem d=new MenuItem("�ӳ���������\nWechat:cynm-2233");
        menuHelp.getItems().add(d);
        MenuItem e=new MenuItem("��Ա�����Z��\nWechat:Daaasein");
        menuHelp.getItems().add(e);
        MenuItem f=new MenuItem("��Ա����ΰǿ\nWechat:wxid_6hbm7ifacn8e22");
        menuHelp.getItems().add(f);
        MenuItem g=new MenuItem("��Ա���򽣷�\nWechat:FengGeGe30");
        menuHelp.getItems().add(g);
        MenuItem h=new MenuItem("δ����������ת��");
        menuImfo.getItems().add(h);
        b.setStyle("-fx-text-fill: #ffffff");
        c.setStyle("-fx-text-fill: #ffffff");
    }
}