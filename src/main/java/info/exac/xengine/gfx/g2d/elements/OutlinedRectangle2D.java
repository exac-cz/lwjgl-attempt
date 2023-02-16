package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 20:55
 */
public class OutlinedRectangle2D extends Abstract2DElement {


    public List<Vertex2D> verteces = new ArrayList<>();

    public float size;



    public OutlinedRectangle2D(double ax, double ay, double bx, double by, Rgba color, double size) {

        this.size = (float) size;

        verteces.add(new Vertex2D(ax, ay, color));
        verteces.add(new Vertex2D(ax, by, color));
        verteces.add(new Vertex2D(bx, by, color));
        verteces.add(new Vertex2D(bx, ay, color));
    }

}
