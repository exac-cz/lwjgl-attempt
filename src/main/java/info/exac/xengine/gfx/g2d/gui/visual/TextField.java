package info.exac.xengine.gfx.g2d.gui.visual;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.ElementFactory;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 06/02/2018 04:58
 */
public class TextField extends VisualComponent {


    public TextField(String id) {
        super(id);
        initComponent();
    }



    public TextField(String id, double left, double top, double width, double height) {
        super(id, left, top, width, height);
        initComponent();
    }



    private void initComponent() {

    }



    private final List<Abstract2DElement> drawList = new ArrayList<>();

    @Override
    public void redraw() {
        drawList.clear();
        drawList.addAll(ElementFactory.shadowedFilledRectangle(top, left, width, height, Rgba.WHITE, Rgba.GRAY, Rgba.SILVER, 2.0));
    }



    @Override
    public List<Abstract2DElement> draw() {
        return drawList;
    }


}
