package info.exac.xengine.gfx.g2d.gui.system;

import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.gui.Component;
import info.exac.xengine.gfx.g2d.gui.common.IDrawable;
import info.exac.xengine.gfx.g2d.gui.common.IUpdatable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 22:20
 */
public class SpriteEngine extends Component implements IUpdatable, IDrawable {


    private final List<Sprite> sprites = new ArrayList<>();

    private List<Sprite> spritesToAdd = new ArrayList<>();

    private List<Sprite> spritesToRemove = new ArrayList<>();

    private int spriteCount;

    private boolean collisionDetection = true;

    private List<ICollidable> collisioningSprite = new ArrayList<>();



    public SpriteEngine(String id) {
        super(id);
    }



    @Override
    public synchronized void update(long delta) {
        synchronized (sprites) {
            // Adding sprites waiting in the queue
            sprites.addAll(spritesToAdd);
            for (Sprite sprite : spritesToAdd) {
                if (sprite instanceof ICollidable) {
                    collisioningSprite.add((ICollidable) sprite);
                }
            }
            spritesToAdd.clear();

            // Updating sprites
            Iterator<Sprite> iterator = sprites.iterator();
            while (iterator.hasNext()) {
                Sprite sprite = iterator.next();
                sprite.update(delta);
            }

            // Collision detection
            if (collisionDetection) {
                Iterator<ICollidable> iterator1 = collisioningSprite.iterator();
                while (iterator1.hasNext()) {
                    ICollidable testingSprite = iterator1.next();
                    Iterator<ICollidable> iterator2 = collisioningSprite.iterator();
                    while (iterator2.hasNext()) {
                        ICollidable collidingSprite = iterator2.next();
                        if (testingSprite != collidingSprite) {
                            CollisionShape collisionShape = testingSprite.getCollisionShape();
                            if (collisionShape != null && collisionShape.isCollidingWith(collidingSprite.getCollisionShape())) {
                                testingSprite.onCollison(collidingSprite);
                            }
                        }
                    }

                }
            }


            // Remove sprites marked to destroy
            sprites.removeAll(spritesToRemove);
            collisioningSprite.removeAll(spritesToRemove);

            // Update sprite count
            spriteCount = sprites.size();
        }
    }



    @Override
    public List<Abstract2DElement> draw() {
        List<Abstract2DElement> list = new ArrayList<>();

        synchronized (sprites) {
            Iterator<Sprite> iterator = sprites.iterator();
            while (iterator.hasNext()) {
                Sprite sprite = iterator.next();
                list.addAll(sprite.draw());
            }
        }
        return list;
    }



    public void add(Sprite sprite) {
        sprite.setSpriteEngine(this);
        spritesToAdd.add(sprite);
    }



    public void remove(Sprite sprite) {
        sprite.setSpriteEngine(null);
        spritesToRemove.add(sprite);
    }



    public int getSpriteCount() {
        return spriteCount;
    }

}
