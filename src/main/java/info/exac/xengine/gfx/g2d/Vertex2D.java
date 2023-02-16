package info.exac.xengine.gfx.g2d;

import info.exac.xengine.gfx.common.Rgba;



/**
 * @author exac
 * @date 02/02/2018 15:00
 */
public class Vertex2D {

    public double x;

    public double y;

    public Rgba rgba;

    public Vertex2D(double x, double y, Rgba rgba) {
        this.x = x;
        this.y = y;
        this.rgba = rgba;
    }

}
