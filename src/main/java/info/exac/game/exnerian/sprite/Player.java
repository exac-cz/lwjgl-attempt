package info.exac.game.exnerian.sprite;

import info.exac.game.GameLoop;
import info.exac.xengine.gfx.g2d.gui.system.CollisionRectangle;
import info.exac.xengine.gfx.g2d.gui.system.CollisionShape;
import info.exac.xengine.gfx.g2d.gui.system.ICollidable;
import info.exac.xengine.gfx.g2d.gui.system.ImageSprite;



/**
 * @author Miroslav Exner
 */
public class Player extends ImageSprite implements ICollidable {


    public Player() {
        width = 64;
        height = 64;
    }



    @Override
    public void update(long delta) {
        x = GameLoop.get().getInputManager().getMouseX() - (width / 2);
        y = GameLoop.get().getInputManager().getMouseY() - (height / 2);
    }



    @Override
    public void onCollison(ICollidable colidedSprite) {

    }



    @Override
    public CollisionShape getCollisionShape() {
        return new CollisionRectangle(x, y, width, height);
    }

}
