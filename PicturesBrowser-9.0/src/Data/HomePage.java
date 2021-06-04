package Data;

import java.awt.*;

public class HomePage {
    private static double width;
    private static double height;

    public HomePage(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth()*0.7;
        height = gd.getDisplayMode().getHeight()*0.7;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}