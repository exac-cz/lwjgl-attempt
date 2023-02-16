package info.exac.xengine.gfx.g2d.elements;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;



/**
 * @author exac
 * @date 06/02/2018 21:47
 */
public class RasterText extends Abstract2DElement {

    private final RgbaTexture rgbaTexture;

    private String value;

    private double x;

    private double y;

    private Rgba rgba;

    private double glyphWidth;

    private double glyphHeight;


    public RasterText(String value, double x, double y, Rgba rgba, RgbaTexture rgbaTexture) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.rgba = rgba;
        this.rgbaTexture = rgbaTexture;
    }



    public String getValue() {
        return value;
    }



    public double getX() {
        return x;
    }



    public double getY() {
        return y;
    }



    public Rgba getRgba() {
        return rgba;
    }



    public RgbaTexture getRgbaTexture() {
        return rgbaTexture;
    }



    public double getGlyphWidth() {
        return glyphWidth;
    }



    public void setGlyphWidth(double glyphWidth) {
        this.glyphWidth = glyphWidth;
    }



    public double getGlyphHeight() {
        return glyphHeight;
    }



    public void setGlyphHeight(double glyphHeight) {
        this.glyphHeight = glyphHeight;
    }



    public void setValue(String value) {
        this.value = value;
    }

}

