package info.exac.xengine.gfx.g2d.gui.system;

/**
 * @author exac
 * @date 10/02/2018 01:07
 */
public class CollisionCircle extends CollisionShape {


    private double centerX;

    private double centerY;

    private double radius;



    public CollisionCircle(double centerX, double centerY, double radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }



    @Override
    public boolean isCollidingWith(CollisionShape collisionShape) {
        return false;
    }






    public double getCenterX() {
        return centerX;
    }



    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }



    public double getCenterY() {
        return centerY;
    }



    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }



    public double getRadius() {
        return radius;
    }



    public void setRadius(double radius) {
        this.radius = radius;
    }
}
