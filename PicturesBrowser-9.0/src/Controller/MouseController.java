package Controller;



import Control.ImageBoxButton;
import Control.PicturePane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
//�����ק������
public class MouseController {
    private double width,height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private double setX,setY;
    public static final Rectangle rectangle = new Rectangle();//��ק�γ�һ������
    
    public static Pane pane = new Pane();
    private boolean isDragged;//�ж��Ƿ���ק
    public boolean getDragged()
    {
    	return isDragged;
    }
    public MouseController(){
    	
        rectangle.setArcHeight(6);
        rectangle.setArcWidth(10);
        rectangle.setFill(Color.rgb(200,200,200,0.3));
        pane.getChildren().add(rectangle);
        MouseDraggedOnShow();
    }
    public void MouseDraggedOnShow(){
        pane.setOnMousePressed(e->{
        	this.MousePressed(e);
        });
        pane.setOnMouseDragged(e->{
        	this.MouseDragged(e);
        });
        pane.setOnMouseReleased(e->{
        	this.MouseReleased(e);
        });
    }
    //��갴�»������������ø�����
    private void MousePressed(MouseEvent mouseEvent) {
    	isDragged=false;
        setX = mouseEvent.getX() ;
        setY = mouseEvent.getY() ;
        rectangle.setX(setX);
        rectangle.setY(setY);

    }
    //��קʱ,���ݳ�ʼ��������γ��Ϳ���ʾ����
    private void MouseDragged(MouseEvent mouseEvent) {
    	isDragged=true;
    	//ѡ����ΰ�ѹ����������קʱ���������Сֵ��Ϊ���ε��µ����꣬ʹ������������Ҳ���γɾ���
        rectangle.setX(Math.min(setX, mouseEvent.getX()));
        rectangle.setY(Math.min(setY, mouseEvent.getY()));
        //���ó���(ȡ����ֵ)
        rectangle.setWidth(Math.abs(mouseEvent.getX()-setX));
        rectangle.setHeight(Math.abs(mouseEvent.getY()-setY));
        //������קʱ
        if(this.isDragged)
        {
        	ImageBoxButton.clearSelected();//���֮ǰ��ͼƬѡ��״̬
        	for(Node node: PicturePane.flowPane.getChildren())
        	{
        		//���ͼƬ����ͼƬ�ڵ�
        		if(node instanceof ImageBoxButton)
        		{
        			//�жϾ��������Ƿ���ͼƬ�ڵ����غ�,������ͼƬ�ڵ���Ϊѡ��״̬
        			if(RectOnButton((ImageBoxButton)node))
        			{
                        ((ImageBoxButton)node).setSelected(true);
                    }
        		}
        		
        	}
        }
    }
    
    //�ɿ��󣬾��������볤������Ϊ0,����ʾ����
	private void MouseReleased(MouseEvent mouseEvent) {
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setHeight(0);
        rectangle.setWidth(0);

    }
	//�жϾ��������Ƿ���ͼƬ�ڵ����غ�
	private boolean RectOnButton(ImageBoxButton node) {
    	double imageNodeCenterPointX =node.getLayoutX() + node.getWidth()/2.0;
		double imageNodeCenterPointY = node.getLayoutY() + node.getHeight()/2.0;
		double selectRectangleCenterPointX = rectangle.getX() + rectangle.getWidth()/2.0;
		double selectRectangleCenterPointY = rectangle.getY() + rectangle.getHeight()/2.0;
		return Math.abs(imageNodeCenterPointX - selectRectangleCenterPointX) <= (node.getWidth()/2.0 + rectangle.getWidth()/2.0) &&
				Math.abs(imageNodeCenterPointY - selectRectangleCenterPointY) <= (node.getHeight()/2.0 + rectangle.getHeight()/2.0);
		
	}

}