package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 15:08
 */
public class Line2D extends Abstract2DElement {

    public Vertex2D a;

    public Vertex2D b;

    public float size;

    public List<Vertex2D> verteces = new ArrayList<>();

    public Line2D(Vertex2D a, Vertex2D b, double size) {
        this.a = a;
        this.b = b;

        this.size = (float) size;

        verteces.add(a);
        verteces.add(b);
    }

}
