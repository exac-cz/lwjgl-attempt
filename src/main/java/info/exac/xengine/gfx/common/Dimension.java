package info.exac.xengine.gfx.common;

/**
 * @author exac
 * @date 09/02/2018 15:11
 */
public class Dimension {

    public static Dimension dimension(double width, double height) {
        return new Dimension(width, height);
    }

    private double width;

    private double height;


    public Dimension(double width, double height) {
        this.width = width;
        this.height = height;
    }


    public double getWidth() {
        return width;
    }



    public double getHeight() {
        return height;
    }

}
