package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.g2d.Vertex2D;



/**
 * @author exac
 * @date 02/02/2018 15:06
 */
public class Point2D extends Abstract2DElement {

    public Vertex2D vertex;

    public float size;


    public Point2D(Vertex2D vertex, double size) {
        this(vertex, (float) size);
    }

    public Point2D(Vertex2D vertex, float size) {
        this.vertex = vertex;
        this.size = size;
    }

}
