package info.exac.xengine.gfx.g2d.gui.system;

/**
 * Created by exac on 10/02/2018.
 */
public interface ICollidable {

    void onCollison(ICollidable colidedSprite);

    CollisionShape getCollisionShape();

}
