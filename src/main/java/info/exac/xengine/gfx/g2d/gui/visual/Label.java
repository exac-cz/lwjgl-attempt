package info.exac.xengine.gfx.g2d.gui.visual;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.ElementFactory;
import info.exac.xengine.gfx.g2d.elements.RasterText;
import info.exac.xengine.gfx.g2d.gui.common.HorizontalAlignment;
import info.exac.xengine.gfx.g2d.gui.common.VerticalAlignment;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 09/02/2018 15:32
 */
public class Label extends VisualComponent {


    private String text;

    private RgbaTexture font;

    private Rgba textColor;

    private Rgba backgroundColor;

    private VerticalAlignment verticalAlign = VerticalAlignment.MIDDLE;

    private HorizontalAlignment horizontalAlign = HorizontalAlignment.CENTER;


    private final List<Abstract2DElement> drawList = new ArrayList<>();


    public Label(String id, double left, double top, double width, double height) {
        super(id, left, top, width, height);
    }



    @Override
    public void redraw() {
        drawList.clear();

        if (backgroundColor != null) {
            drawList.addAll(ElementFactory.filledRectangle(left, top, width, height, backgroundColor));
        }

        if (text != null && !text.isEmpty()) {
            double textLeft = 0;
            double textTop = 0;

            double textWidth = text.length() * font.getTileWidth();
            double textHeight = font.getTileHeight();

            switch (horizontalAlign) {
                case LEFT:
                    textLeft = left;
                    break;
                case CENTER:
                    textLeft = left + ((width / 2) - (textWidth / 2));
                    break;
                case RIGHT:
                    textLeft = (left + width) - textWidth;
                    break;
                default:
                    textLeft = left;
                    break;
            }

            switch (verticalAlign) {
                case TOP:
                    textTop = top;
                    break;
                case MIDDLE:
                    textTop = top + ((height / 2) - (textHeight / 2));
                    break;
                case BOTTOM:
                    textTop = (top + height) - textHeight;
                    break;
                default:
                    textTop = top;
                    break;
            }

            drawList.add(new RasterText(text, textLeft, textTop, textColor, font));
        }

    }


    @Override
    public List<Abstract2DElement> draw() {
        return drawList;
    }



    public String getText() {
        return text;
    }



    public void setText(String text) {
        requestRedraw();
        this.text = text;
    }



    public RgbaTexture getFont() {
        return font;
    }



    public void setFont(RgbaTexture font) {
        requestRedraw();
        this.font = font;
    }



    public Rgba getTextColor() {
        return textColor;
    }



    public void setTextColor(Rgba textColor) {
        requestRedraw();
        this.textColor = textColor;
    }



    public Rgba getBackgroundColor() {
        return backgroundColor;
    }



    public void setBackgroundColor(Rgba backgroundColor) {
        this.backgroundColor = backgroundColor;
    }



    public VerticalAlignment getVerticalAlign() {
        return verticalAlign;
    }



    public void setVerticalAlign(VerticalAlignment verticalAlign) {
        this.verticalAlign = verticalAlign;
    }



    public HorizontalAlignment getHorizontalAlign() {
        return horizontalAlign;
    }



    public void setHorizontalAlign(HorizontalAlignment horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }
}
