package info.exac.xengine.gfx.g2d.gui.system;

/**
 * @author exac
 * @date 10/02/2018 01:07
 */
public class CollisionRectangle extends CollisionShape {

    private double left;

    private double top;

    private double width;

    private double height;


    public CollisionRectangle(double left, double top, double width, double height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }



    @Override
    public boolean isCollidingWith(CollisionShape collisionShape) {
        if (collisionShape instanceof CollisionRectangle) {
            return isCollidingWithRectangle((CollisionRectangle) collisionShape);
        }

        return false;
    }



    private boolean isCollidingWithRectangle(CollisionRectangle rectangle) {
        return rectangle.hasInside(left, top)
                || rectangle.hasInside(left + width, top)
                || rectangle.hasInside(left + width, top + height)
                || rectangle.hasInside(left, top + height)
                || this.hasInside(rectangle.getLeft(), rectangle.getTop())
                || this.hasInside(rectangle.getLeft() + rectangle.getWidth(), rectangle.getTop())
                || this.hasInside(rectangle.getLeft() + rectangle.getWidth(), rectangle.getTop() + rectangle.getHeight())
                || this.hasInside(rectangle.getLeft(), rectangle.getTop() + rectangle.getHeight());
    }



    public boolean hasInside(double x, double y) {
        return ( (left <= x) && (x <= (left + width) ) && (top <= y) && (y <= (top + height) ) );
    }



    public double getLeft() {
        return left;
    }



    public void setLeft(double left) {
        this.left = left;
    }



    public double getTop() {
        return top;
    }



    public void setTop(double top) {
        this.top = top;
    }



    public double getWidth() {
        return width;
    }



    public void setWidth(double width) {
        this.width = width;
    }



    public double getHeight() {
        return height;
    }



    public void setHeight(double height) {
        this.height = height;
    }
}
