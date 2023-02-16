package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 02/02/2018 19:23
 */
public class Circle2D extends Abstract2DElement {

    public static double ONE_DEGREE_TO_RADIAN = Math.PI / 180.0;



    public List<Vertex2D> verteces = new ArrayList<>();



    public Circle2D(double x, double y, double radius) {

        verteces.add(new Vertex2D(x,y, Rgba.random()));

        int step = (int) Math.round(radius / 2);

        for (int i = 0; i <= step; i++) {
            double angleInRad = i * ( (double) 360 / (double) step) * ONE_DEGREE_TO_RADIAN;

            double vx = (Math.cos(angleInRad) * radius) + x;
            double vy = (Math.sin(angleInRad) * radius) + y;

            verteces.add(new Vertex2D(vx, vy, new Rgba(1,1,1, 0.5)));
        }

    }
}
