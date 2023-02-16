package info.exac.xengine.gfx.g2d.gui.system;



import org.junit.Assert;
import org.junit.Test;



/**
 * Created by exac on 10/02/2018.
 */
public class CollisionRectangleTest {


    @Test
    public void isCollidingWith() throws Exception {
        CollisionRectangle rectangle = new CollisionRectangle(100, 100, 100, 100);

        Assert.assertTrue(rectangle.isCollidingWith(new CollisionRectangle(50, 50, 100, 100)));
        Assert.assertTrue(rectangle.isCollidingWith(new CollisionRectangle(120, 50, 50, 100)));
        Assert.assertTrue(rectangle.isCollidingWith(new CollisionRectangle(150, 50, 100, 100)));

        Assert.assertTrue(rectangle.isCollidingWith(new CollisionRectangle(120, 120, 20, 20)));

        Assert.assertFalse(rectangle.isCollidingWith(new CollisionRectangle(50, 50, 20, 20)));
        Assert.assertFalse(rectangle.isCollidingWith(new CollisionRectangle(150, 50, 20, 20)));
    }



    @Test
    public void hasInside() {
        CollisionRectangle rectangle = new CollisionRectangle(100, 100, 100, 100);

        Assert.assertTrue(rectangle.hasInside(100, 100));
        Assert.assertTrue(rectangle.hasInside(200, 100));
        Assert.assertTrue(rectangle.hasInside(200, 200));
        Assert.assertTrue(rectangle.hasInside(100, 200));
        Assert.assertTrue(rectangle.hasInside(150, 150));

        Assert.assertFalse(rectangle.hasInside(50, 50));
        Assert.assertFalse(rectangle.hasInside(150, 50));
        Assert.assertFalse(rectangle.hasInside(250, 50));

        Assert.assertFalse(rectangle.hasInside(50, 150));
        Assert.assertFalse(rectangle.hasInside(250, 150));

        Assert.assertFalse(rectangle.hasInside(50, 250));
        Assert.assertFalse(rectangle.hasInside(150, 250));
        Assert.assertFalse(rectangle.hasInside(250, 250));

    }





}