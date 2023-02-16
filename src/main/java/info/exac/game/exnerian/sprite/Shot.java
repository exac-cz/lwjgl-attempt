package info.exac.game.exnerian.sprite;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.Point2D;
import info.exac.xengine.gfx.g2d.gui.system.CollisionRectangle;
import info.exac.xengine.gfx.g2d.gui.system.CollisionShape;
import info.exac.xengine.gfx.g2d.gui.system.ICollidable;
import info.exac.xengine.gfx.g2d.gui.system.Sprite;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 23:58
 */
public class Shot extends Sprite implements ICollidable {

    private double x;

    private double y;

    private double speed;



    public Shot(double x, double y) {
        super();
        this.x = x;
        this.y = y;
        speed = 1000;
    }



    @Override
    public void update(long delta) {
        y -= (speed / 1000) * delta;
        if (y < -10) {
            destroy();
        }
    }



    @Override
    public List<Abstract2DElement> draw() {
        List<Abstract2DElement> list = new ArrayList<>();
        list.add(new Point2D(new Vertex2D(x, y, Rgba.RED), 5.0));
        return list;
    }



    @Override
    public void onCollison(ICollidable colidedSprite) {
        if (colidedSprite instanceof Enemy) {
            ((Enemy) colidedSprite).resetPosition();
            this.destroy();
        }
    }



    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionRectangle(x - 1, y -1, 3, 3);
    }
}
