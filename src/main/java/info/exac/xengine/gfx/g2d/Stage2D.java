package info.exac.xengine.gfx.g2d;

import info.exac.game.GameLoop;
import info.exac.xengine.gfx.common.RgbaGfxManager;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.gui.Component;
import info.exac.xengine.gfx.g2d.gui.common.IDrawable;
import info.exac.xengine.gfx.g2d.gui.common.IUpdatable;
import info.exac.xengine.gfx.g2d.gui.visual.VisualComponent;
import info.exac.xengine.input.event.ICustomEvent;
import info.exac.xengine.input.event.KeyEvent;
import info.exac.xengine.input.event.MouseButtonEvent;
import info.exac.xengine.input.event.MousePositionEvent;
import info.exac.xengine.input.listener.KeyEventListener;
import info.exac.xengine.input.listener.MouseButtonListener;
import info.exac.xengine.input.listener.MousePositionListener;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 15:10
 */
public abstract class Stage2D implements KeyEventListener, MouseButtonListener, MousePositionListener {

    public static final long CLICK_INTERVAL        = 500;
    public static final long DOUBLE_CLICK_INTERVAL = 500;

    private double screenWidth;

    private double screenHeight;

    private boolean isInitialized;

    private RgbaGfxManager textureManager;

    private StageManager stageManager;

    private List<Abstract2DElement> elements;

    private List<MouseButtonEvent> mouseEvents = new ArrayList<>(4);

    private List<Component> allComponents;

    private List<IDrawable> drawableComponents;

    private List<IUpdatable> updatableComponents;


    public Stage2D(double screenWidth, double screenHeight) {
        this.elements = new ArrayList<>();
        this.allComponents = new ArrayList<>();
        this.drawableComponents = new ArrayList<>();
        this.updatableComponents = new ArrayList<>();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }



    public abstract void initComponents();



    void initilizeInternal() {
        initComponents();
        isInitialized = true;
    }



    public void add(Abstract2DElement element) {
        this.elements.add(element);
    }



    public void add(Component component) {
        Assert.notNull(component, "Component cannot be null!");

        this.allComponents.add(component);

        if (component instanceof IUpdatable) {
            updatableComponents.add((IUpdatable) component);
        }

        if (component instanceof IDrawable) {
            drawableComponents.add((IDrawable) component);
        }
    }



    @Override
    public void onMouseButtonEvent(MouseButtonEvent mouseButtonEvent) {
        mouseEvents.add(0, mouseButtonEvent);
        if (mouseEvents.size() > 4) {
            mouseEvents.remove(4);
        }

        boolean doubleClickDetected = detectDoubleClick(mouseEvents);
        boolean clickDetected = detectClick(mouseEvents);

        allComponents.stream()
                .filter(component -> component instanceof VisualComponent)
                .filter(Component::isEnabled)
                .filter(component -> ((VisualComponent) component).isVisible() )
                .forEach(c ->
                {
                    VisualComponent component = (VisualComponent) c;
                    if (component.isInside(mouseButtonEvent.getMouseX(), mouseButtonEvent.getMouseY())) {
                        if (mouseButtonEvent.isPrimaryDown()) {
                            component.onMouseDown();
                        }

                        if (mouseButtonEvent.isPrimaryUp()) {
                            component.onMouseUp();

                            // FIXME double click fires a click before :(
                            if (component.isDoubleClickSupported() && doubleClickDetected) {
                                component.onDoubleClick();
                            }
                            else if (clickDetected) {
                                component.onClick();
                            }
                        }
                    }
                });

    }



    private boolean detectClick(List<MouseButtonEvent> mouseEvents) {
        if (mouseEvents.size() < 2)
            return false;

        if ((mouseEvents.get(0).getTimeStamp() - mouseEvents.get(1).getTimeStamp()) <= CLICK_INTERVAL) {
            return true;
        }

        return false;
    }



    private boolean detectDoubleClick(List<MouseButtonEvent> mouseEvents) {
        if (mouseEvents.size() < 4)
            return false;

        if ( (mouseEvents.get(0).getTimeStamp() - mouseEvents.get(1).getTimeStamp()) <= CLICK_INTERVAL
                && (mouseEvents.get(2).getTimeStamp() - mouseEvents.get(3).getTimeStamp()) <= CLICK_INTERVAL
                && (mouseEvents.get(0).getTimeStamp() - mouseEvents.get(3).getTimeStamp() <= DOUBLE_CLICK_INTERVAL)) {
            return true;
        }

        return false;
    }



    @Override
    public void onMousePositionEvent(MousePositionEvent event) {
        allComponents.stream()
                .filter(component -> component instanceof VisualComponent)
                .forEach(c -> {
                    VisualComponent component = (VisualComponent) c;
                    if (component.isInside(event.getX(), event.getY())) {
                        // Mouse is inside
                        if (!component.isMouseEntered()) {
                            component.setMouseEntered(true);
                            component.onMouseEnter();
                        }
                        component.onMouseMove();
                    } else {
                        // Mouse is outside
                        if (component.isMouseEntered()) {
                            component.setMouseEntered(false);
                            component.onMouseLeave();
                        }
                    }
                });

    }



    @Override
    public void onKeyEvent(KeyEvent event) {
        // TODO propagate key event

//        System.out.println("STAGE - " + event);
    }



    public void update(long delta) {
        Iterator<IUpdatable> iterator = updatableComponents.iterator();
        while (iterator.hasNext()) {
            IUpdatable updatable = iterator.next();
            updatable.update(delta);
        }
    }



    public void fireCustomEvent(ICustomEvent customEvent) {
        GameLoop.get().onCustomEvent(customEvent);
    }



    // --- Getters / Setters -------------------------------------------------------------------------------------------



    public List<Abstract2DElement> getElements() {
        return elements;
    }

    public List<Component> getAllComponents() {
        return allComponents;
    }

    public List<IDrawable> getDrawableComponents() {
        return drawableComponents;
    }


    public RgbaGfxManager getTextureManager() {
        return textureManager;
    }


    public void setTextureManager(RgbaGfxManager textureManager) {
        this.textureManager = textureManager;
    }


    public StageManager getStageManager() {
        return stageManager;
    }



    void setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
    }



    boolean isInitialized() {
        return isInitialized;
    }



    public double getScreenWidth() {
        return screenWidth;
    }



    public double getScreenHeight() {
        return screenHeight;
    }
}
