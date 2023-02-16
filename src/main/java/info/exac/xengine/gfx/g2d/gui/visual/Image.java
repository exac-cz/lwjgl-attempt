package info.exac.xengine.gfx.g2d.gui.visual;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.TexturedRectangle2D;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 18:59
 */
public class Image extends VisualComponent {

    private Rgba color;

    private RgbaTexture texture;

    public Image(String id, double left, double top, double width, double height, RgbaTexture texture, Rgba color) {
        super(id, left, top, width, height);
        this.texture = texture;
        this.color = color;
    }


    private final List<Abstract2DElement> drawList = new ArrayList<>();

    @Override
    public void redraw() {
        drawList.clear();
        drawList.add(new TexturedRectangle2D(
                new Vertex2D(left, top, color),
                new Vertex2D(left + width, top, color),
                new Vertex2D(left + width, top + height, color),
                new Vertex2D(left, top + height, color),
                texture
        ));
    }



    @Override
    public List<Abstract2DElement> draw() {
        return drawList;
    }


}
