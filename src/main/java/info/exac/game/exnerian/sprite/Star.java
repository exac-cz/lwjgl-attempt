package info.exac.game.exnerian.sprite;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.Point2D;
import info.exac.xengine.gfx.g2d.gui.system.Sprite;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 22:23
 */
public class Star extends Sprite {

    private double x;

    private double y;

    private double speed;

    private Rgba color;



    public Star(double x, double y, Rgba color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }



    @Override
    public void update(long delta) {
        y += speed / (double) delta;

        if (y > 600) {
            y = -10;
            x = Math.random() * 800;
        }
    }



    @Override
    public List<Abstract2DElement> draw() {
        List<Abstract2DElement> list = new ArrayList<>();
        list.add(new Point2D(new Vertex2D(x, y, color), 2.0));
        return list;
    }



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



    public double getSpeed() {
        return speed;
    }



    public void setSpeed(double speed) {
        this.speed = speed;
    }



    public Rgba getColor() {
        return color;
    }



    public void setColor(Rgba color) {
        this.color = color;
    }
}
