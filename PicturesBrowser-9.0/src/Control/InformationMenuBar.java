package Control;



import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class InformationMenuBar extends MenuBar {

    public InformationMenuBar(){
        super();
        Label b=new Label("客服",new ImageView("/Pictures/客服.png"));
        Label c=new Label("用户须知",new ImageView("/Pictures/信息.png"));
        b.setStyle("-fx-text-fill: #ffffff");
        Menu menuHelp = new Menu(null,b);
        Menu menuImfo = new Menu(null,c);
        getMenus().addAll(menuHelp);
        getMenus().addAll(menuImfo);
        setStyle("-fx-background-color:#4a5459");
        MenuItem d=new MenuItem("队长：刘宝宁\nWechat:cynm-2233");
        menuHelp.getItems().add(d);
        MenuItem e=new MenuItem("队员：刘璟明\nWechat:Daaasein");
        menuHelp.getItems().add(e);
        MenuItem f=new MenuItem("队员：冯伟强\nWechat:wxid_6hbm7ifacn8e22");
        menuHelp.getItems().add(f);
        MenuItem g=new MenuItem("队员：万剑锋\nWechat:FengGeGe30");
        menuHelp.getItems().add(g);
        MenuItem h=new MenuItem("未经允许，请勿转载");
        menuImfo.getItems().add(h);
        b.setStyle("-fx-text-fill: #ffffff");
        c.setStyle("-fx-text-fill: #ffffff");
    }
}