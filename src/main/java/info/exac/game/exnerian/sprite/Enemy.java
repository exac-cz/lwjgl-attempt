package info.exac.game.exnerian.sprite;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.gui.system.*;



/**
 * @author exac
 * @date 10/02/2018 00:49
 */
public class Enemy extends ImageSprite implements ICollidable {

    private double speed;


    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        this.color = Rgba.RED;
        this.speed = Math.random() * 100 + 1;
    }



    @Override
    public void update(long delta) {
        y += speed / 1000.0 * delta;
        if (y > 600) {
            resetPosition();
        }
    }



    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionRectangle(x, y, 64, 64);
    }


    @Override
    public void onCollison(ICollidable collidedSprite) {
         if (collidedSprite instanceof Player) {
            this.resetPosition();
        }
    }



    public void resetPosition() {
        y = -100;
        x = Math.random() * 800;
    }


}
