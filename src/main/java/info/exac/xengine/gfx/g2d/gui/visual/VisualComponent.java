package info.exac.xengine.gfx.g2d.gui.visual;

import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.gui.Component;
import info.exac.xengine.gfx.g2d.gui.common.IDrawable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;



/**
 * @author exac
 * @date 06/02/2018 04:09
 */
public abstract class VisualComponent extends Component implements IDrawable {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualComponent.class);


    protected boolean visible = true;


    protected double top;

    protected double left;

    protected double width;

    protected double height;

    protected boolean mouseEntered = false;

    protected boolean redrawRequested = true;



    public VisualComponent(String id) {
        super(id);
    }



    public VisualComponent(String id, double left, double top, double width, double height) {
        super(id);
        this.top = top;
        this.left = left;
        this.width = width;
        this.height = height;
    }



    @Override
    public abstract List<Abstract2DElement> draw();


    public abstract void redraw();


    public void requestRedraw() {
        this.redrawRequested = true;
    }



    public boolean isInside(double x, double y) {
        return (left <= x && x <= getRight()) && (top <= y && y <= getBottom());
    }



    public double getBottom() {
        return top + height;
    }

    public double getRight() {
        return left + width;
    }


    public boolean isDoubleClickSupported() {
        return false;
    }



    public void onMouseEnter() {
        LOGGER.debug("Event onMouseEnter");
    }



    public void onMouseLeave() {
        LOGGER.debug("Event onMouseLeave");
    }



    public void onMouseMove() {
        LOGGER.trace("Event onMouseMove");
    }



    public void onMouseDown() {
        LOGGER.debug("Event onMouseDown");
    }



    public void onMouseUp() {
        LOGGER.debug("Event onMouseUp");
    }



    public void onDoubleClick() {
        LOGGER.debug("Event onDoubleClick");
    }



    public void onClick() {
        LOGGER.debug("Event onClick");
    }



    //--- Getters / Setters -----



    public double getTop() {
        return top;
    }



    public void setTop(double top) {
        this.top = top;
    }



    public double getLeft() {
        return left;
    }



    public void setLeft(double left) {
        this.left = left;
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



    public boolean isMouseEntered() {
        return mouseEntered;
    }


    public void setMouseEntered(boolean mouseEntered) {
        this.mouseEntered = mouseEntered;
    }



    public boolean isVisible() {
        return visible;
    }



    public void setVisible(boolean visible) {
        this.visible = visible;
    }



    public boolean isRedrawRequested() {
        return redrawRequested;
    }



    public void setRedrawRequested(boolean redrawRequested) {
        this.redrawRequested = redrawRequested;
    }
}
