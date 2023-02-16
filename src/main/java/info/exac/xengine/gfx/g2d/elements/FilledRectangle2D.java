package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 06/02/2018 04:27
 */
public class FilledRectangle2D extends Abstract2DElement {

    private double ax;

    private double ay;

    private double bx;

    private double by;

    private double rgba;


    public List<Vertex2D> verteces = new ArrayList<>();


    public FilledRectangle2D(double ax, double ay, double bx, double by, Rgba rgba) {
        super();
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;

        verteces.add(new Vertex2D(ax , ay, rgba));
        verteces.add(new Vertex2D(ax, by, rgba));
        verteces.add(new Vertex2D(bx, by, rgba));
        verteces.add(new Vertex2D(bx, ay, rgba));
    }



    public double getAx() {
        return ax;
    }



    public double getAy() {
        return ay;
    }



    public double getBx() {
        return bx;
    }



    public double getBy() {
        return by;
    }



    public double getRgba() {
        return rgba;
    }



    public List<Vertex2D> getVerteces() {
        return verteces;
    }
}
