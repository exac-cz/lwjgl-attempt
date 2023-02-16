package info.exac.xengine.gfx.g2d.gui.system;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.TexturedRectangle2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 23:40
 */
public abstract class ImageSprite extends Sprite {


    private RgbaTexture texture;


    protected double x;

    protected double y;

    protected double width = 64;

    protected double height = 64;

    protected Rgba color = Rgba.WHITE;


    @Override
    public List<Abstract2DElement> draw() {
        List<Abstract2DElement> list = new ArrayList<>();
        list.add(new TexturedRectangle2D(
                new Vertex2D(x, y, color),
                new Vertex2D(x + width, y, color),
                new Vertex2D(x + width, y + height, color),
                new Vertex2D(x , y + height, color),
                texture));
        return list;
    }



    // --- Getters / Setters -------------------------------------------------------------------------------------------



    public double getX() {
        return x;
    }



    public void setX(double x) {
        this.x = x;
    }



    public double getY() {
        return y;
    }



    public void setY(double y) {
        this.y = y;
    }



    public RgbaTexture getTexture() {
        return texture;
    }



    public void setTexture(RgbaTexture texture) {
        this.texture = texture;
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



    public Rgba getColor() {
        return color;
    }



    public void setColor(Rgba color) {
        this.color = color;
    }
}
