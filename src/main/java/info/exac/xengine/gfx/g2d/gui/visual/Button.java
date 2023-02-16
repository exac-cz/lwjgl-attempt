package info.exac.xengine.gfx.g2d.gui.visual;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;
import info.exac.xengine.gfx.g2d.elements.ElementFactory;
import info.exac.xengine.gfx.g2d.elements.RasterText;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 06/02/2018 04:09
 */
public abstract class Button extends VisualComponent {

    public static final int STATE_UP   = 1;
    public static final int STATE_DOWN = 2;

    private int buttonState = STATE_UP;

    private Rgba color = Rgba.SILVER;

    private double borderWidth = 2.0;

    private String text;

    private RgbaTexture font;

    private Rgba textColor;



    public Button(String id) {
        super(id);
        initComponent();
    }



    public Button(String id, String text, double left, double top, double width, double height) {
        super(id, left, top, width, height);
        this.text = text;
        initComponent();
    }


    public Button(String id, double left, double top, double width, double height) {
        super(id, left, top, width, height);
        initComponent();
    }


    private void initComponent() {

    }



    public abstract void onClick();



    @Override
    public boolean isDoubleClickSupported() {
        return false;
    }



    @Override
    public void onMouseDown() {
        super.onMouseDown();
        buttonState = STATE_DOWN;
    }



    @Override
    public void onMouseUp() {
        super.onMouseUp();
        buttonState = STATE_UP;
    }



    @Override
    public void onMouseLeave() {
        super.onMouseLeave();
        buttonState = STATE_UP;
    }




    private final List<Abstract2DElement> buttonUpDrawList = new ArrayList<>();
    private final List<Abstract2DElement> buttonDownDrawList = new ArrayList<>();





    @Override
    public void redraw() {
        buttonUpDrawList.clear();
        buttonDownDrawList.clear();

        buttonUpDrawList.addAll(ElementFactory.shadowedFilledRectangle(left, top, width, height, color, Rgba.WHITE, Rgba.GRAY, borderWidth));
        buttonDownDrawList.addAll(ElementFactory.shadowedFilledRectangle(left, top, width, height, color, Rgba.GRAY, Rgba.WHITE, borderWidth));


        if (text != null && !text.isEmpty()) {
            int glyphWidht = 16;
            int glyphHeight = 24;

            double textLeft = left + ((width / 2) - (text.length() * glyphWidht / 2));
            double textTop = top + ((height / 2) - (glyphHeight / 2));

            RasterText upText = new RasterText(text, textLeft, textTop, textColor, font);
            upText.setGlyphHeight(glyphHeight);
            upText.setGlyphWidth(glyphWidht);
            buttonUpDrawList.add(upText);

            RasterText downText = new RasterText(text, textLeft + 1, textTop + 1, textColor, font);
            downText.setGlyphHeight(glyphHeight);
            downText.setGlyphWidth(glyphWidht);
            buttonDownDrawList.add(downText);
        }
    }



    @Override
    public List<Abstract2DElement> draw() {
        if (STATE_DOWN == buttonState) {
            return buttonDownDrawList;
        } else {
            return buttonUpDrawList;
        }

    }



    public Rgba getColor() {
        return color;
    }



    public void setColor(Rgba color) {
        requestRedraw();
        this.color = color;
    }



    public String getText() {
        return text;
    }



    public void setText(String text) {
        requestRedraw();
        this.text = text;
    }



    public void setFont(RgbaTexture font) {
        requestRedraw();
        this.font = font;
    }



    public void setTextColor(Rgba textColor) {
        requestRedraw();
        this.textColor = textColor;
    }



    public Rgba getTextColor() {
        return textColor;
    }
}
