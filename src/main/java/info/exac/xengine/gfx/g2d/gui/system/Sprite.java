package info.exac.xengine.gfx.g2d.gui.system;

import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.gui.common.IDrawable;
import info.exac.xengine.gfx.g2d.gui.common.IUpdatable;

import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 22:20
 */
public abstract class Sprite implements IUpdatable, IDrawable {

    private SpriteEngine spriteEngine;

    private boolean markedToDestroy;

    @Override
    public abstract void update(long delta);


    @Override
    public abstract List<Abstract2DElement> draw();


    public void destroy() {
        if (!markedToDestroy) {
            markedToDestroy = true;
            spriteEngine.remove(this);
        }
    }



    // --- Getter / Setter --------------------------------------------------------------





    public SpriteEngine getSpriteEngine() {
        return spriteEngine;
    }



    public void setSpriteEngine(SpriteEngine spriteEngine) {
        this.spriteEngine = spriteEngine;
    }
}
