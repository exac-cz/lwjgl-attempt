package info.exac.xengine.gfx.g2d;

import info.exac.xengine.gfx.common.RgbaGfxManager;
import info.exac.xengine.input.event.KeyEvent;
import info.exac.xengine.input.event.MouseButtonEvent;
import info.exac.xengine.input.event.MousePositionEvent;
import info.exac.xengine.input.listener.KeyEventListener;
import info.exac.xengine.input.listener.MouseButtonListener;
import info.exac.xengine.input.listener.MousePositionListener;

import java.util.Stack;



/**
 * @author exac
 * @date 06/02/2018 09:37
 */
public class StageManager implements KeyEventListener, MouseButtonListener, MousePositionListener {

    private RgbaGfxManager textureManager;


    private Stage2D activeStage;

    private Stack<Stage2D> stageStack = new Stack<>();


    public StageManager(RgbaGfxManager textureManager) {
        this.textureManager = textureManager;
    }



    public void pushStage(Stage2D stage) {
       stageStack.push(stage);

       if (!stage.isInitialized()) {
           initializeStage(stage);
       }
       activeStage = stage;
    }


    public void popStage() {
        stageStack.pop();
        activeStage = stageStack.peek();
    }


    public void setActiveStage(Stage2D stage) {
        stageStack.clear();
        if (!stage.isInitialized()) {
            initializeStage(stage);
        }
        pushStage(stage);
    }



    private void initializeStage(Stage2D stage) {
        stage.setTextureManager(textureManager);
        stage.setStageManager(this);
        stage.initilizeInternal();
    }



    public Stage2D getActiveStage() {
        return activeStage;
    }


    @Override
    public void onKeyEvent(KeyEvent event) {
        if (activeStage != null) {
            activeStage.onKeyEvent(event);
        }
    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent event) {
        if (activeStage != null) {
            activeStage.onMouseButtonEvent(event);
        }
    }


    @Override
    public void onMousePositionEvent(MousePositionEvent event) {
        if (activeStage != null) {
            activeStage.onMousePositionEvent(event);
        }
    }



    public void update(long delta) {
        if (activeStage != null) {
            activeStage.update(delta);
        }
    }

}
