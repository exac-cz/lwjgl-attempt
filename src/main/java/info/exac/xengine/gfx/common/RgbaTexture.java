package info.exac.xengine.gfx.common;

import java.nio.ByteBuffer;



/**
 * @author exac
 * @date 05/02/2018 12:24
 */
public class RgbaTexture {

    private int border;

    private String code;

    private int width;

    private int height;

    private ByteBuffer rgbaData;

    private int textureId;



    private int frameCount;
    private int frameCountX;
    private int frameCountY;
    private int frameWidth;
    private int frameHeight;

    private int tileCount;
    private int tileCountX;
    private int tileCountY;
    private int tileWidth;
    private int tileHeight;


    public RgbaTexture(String code, int width, int height, ByteBuffer textureBuffer) {
        this.code = code;
        this.width = width;
        this.height = height;
        this.rgbaData = textureBuffer;
    }



    public RgbaTexture(String code, int width, int height, ByteBuffer textureBuffer,
                       int frameCount, int frameCountX, int frameCountY, int frameWidth, int frameHeight,
                       int tilesCount, int tilesCountX, int tilesCountY, int tileWidth, int tileHeight, int border) {
        this.code = code;
        this.width = width;
        this.height = height;
        this.rgbaData = textureBuffer;

        this.frameCount = frameCount;
        this.frameCountX = frameCountX;
        this.frameCountY = frameCountY;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        this.tileCount = tilesCount;
        this.tileCountX = tilesCountX;
        this.tileCountY = tilesCountY;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.border = border;
    }



    public String getCode() {
        return code;
    }


    public int getWidth() {
        return width;
    }



    public int getHeight() {
        return height;
    }



    public ByteBuffer getRgbaData() {
        return rgbaData;
    }



    public void setTextureId(int textureId) {
        this.textureId = textureId;
    }



    public int getTextureId() {
        return textureId;
    }



    public int getFrameCount() {
        return frameCount;
    }



    public int getFrameCountX() {
        return frameCountX;
    }



    public int getFrameCountY() {
        return frameCountY;
    }



    public int getFrameWidth() {
        return frameWidth;
    }



    public int getFrameHeight() {
        return frameHeight;
    }



    public int getTileCount() {
        return tileCount;
    }



    public int getTileCountX() {
        return tileCountX;
    }



    public int getTileCountY() {
        return tileCountY;
    }



    public int getTileWidth() {
        return tileWidth;
    }



    public int getTileHeight() {
        return tileHeight;
    }



    public int getBorder() {
        return border;
    }
}
