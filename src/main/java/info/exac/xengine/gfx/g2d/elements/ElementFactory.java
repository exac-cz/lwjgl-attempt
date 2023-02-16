package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.Vertex2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 14:01
 */
public class ElementFactory {


    public static List<Abstract2DElement> filledRectangle(double left, double top, double width, double height, Rgba fillColor) {
        List<Abstract2DElement> result = new ArrayList<>();

        result.add(new FilledRectangle2D(left, top, left + width, top + height, fillColor));

        return result;
    }



    public static List<Abstract2DElement> outlinedRectangle(double left, double top, double width, double height, Rgba lineColor, double lineWidth) {
        return outlinedRectangle(left, top, width, height, lineColor, lineColor, lineColor, lineColor, lineWidth);
    }


    public static List<Abstract2DElement> outlinedRectangle(double left, double top, double width, double height,
                                                            Rgba topLineColor, Rgba rightLineColor, Rgba bottomLineColor, Rgba leftLineColor,
                                                            double lineWidth)
    {
        List<Abstract2DElement> list = new ArrayList<>();

        double right = left + width;
        double bottom = top + height;

        list.add(new Line2D(
                new Vertex2D(left, top, topLineColor),
                new Vertex2D(right, top, topLineColor),
                lineWidth
        ));

        list.add(new Line2D(
                new Vertex2D(right, top, rightLineColor),
                new Vertex2D(right, bottom, rightLineColor),
                lineWidth
        ));

        list.add(new Line2D(
                new Vertex2D(right, bottom, bottomLineColor),
                new Vertex2D(left, bottom, bottomLineColor),
                lineWidth
        ));

        list.add(new Line2D(
                new Vertex2D(left , bottom, leftLineColor),
                new Vertex2D(left, top, leftLineColor),
                lineWidth
        ));

        return list;
    }



    public static List<Abstract2DElement> shadowedFilledRectangle(double left, double top, double width, double height, Rgba fillColor, Rgba topLeftColor, Rgba bottomRightColor, double lineWidth) {
        List<Abstract2DElement> list = new ArrayList<>();

        list.addAll(filledRectangle(left, top, width, height, fillColor));
        list.addAll(outlinedRectangle(left, top, width, height, topLeftColor, bottomRightColor, bottomRightColor, topLeftColor, lineWidth));

        return list;
    }



}
