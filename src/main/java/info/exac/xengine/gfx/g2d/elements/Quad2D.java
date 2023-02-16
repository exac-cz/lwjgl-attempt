package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 15:35
 */
public class Quad2D extends Abstract2DElement {

    public Vertex2D a;

    public Vertex2D b;

    public Vertex2D c;

    public Vertex2D d;

    public final List<Vertex2D> verteces = new ArrayList<>();


    public Quad2D(Vertex2D a, Vertex2D b, Vertex2D c, Vertex2D d) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        this.verteces.add(a);
        this.verteces.add(b);
        this.verteces.add(c);
        this.verteces.add(d);
    }

}
