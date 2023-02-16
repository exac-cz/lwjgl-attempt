package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 15:03
 */
public class Triangle2D extends Abstract2DElement {

    public Vertex2D a;

    public Vertex2D b;

    public Vertex2D c;

    public List<Vertex2D> verteces = new ArrayList<>();

    public Triangle2D(Vertex2D a, Vertex2D b, Vertex2D c) {
        this.a = a;
        this.b = b;
        this.c = c;

        this.verteces.add(a);
        this.verteces.add(b);
        this.verteces.add(c);
    }

}
