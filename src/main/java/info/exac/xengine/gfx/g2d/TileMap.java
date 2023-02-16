package info.exac.xengine.gfx.g2d;

import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.elements.Abstract2DElement;



/**
 * @author exac
 * @date 04/02/2018 20:06
 */
public class TileMap extends Abstract2DElement {

    private int tileWidth;

    private int tileHeight;

    private int tileCountX;

    private int tileCountY;

    private double mapPositionX;

    private double mapPositionY;

    private int[][] values;

    private RgbaTexture rgbaTexture;

    public TileMap(int tileWidth, int tileHeight, int tileCountX, int tileCountY, double mapPositionX, double mapPositionY, RgbaTexture rgbaTexture, int[][] values) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.tileCountX = tileCountX;
        this.tileCountY = tileCountY;
        this.mapPositionX = mapPositionX;
        this.mapPositionY = mapPositionY;

        this.rgbaTexture = rgbaTexture;
        this.values = values;

//        this.values  = new int[tileCountX][tileCountY];
//        for (int y = 0; y < tileCountY; y++) {
//            for (int x = 0; x < tileCountX; x++) {
//                values[x][y] = (int) Math.round(Math.random() * 4);
//            }
//        }
    }



    public int getTileWidth() {
        return tileWidth;
    }



    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }



    public int getTileHeight() {
        return tileHeight;
    }



    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }



    public int getTileCountX() {
        return tileCountX;
    }



    public void setTileCountX(int tileCountX) {
        this.tileCountX = tileCountX;
    }



    public int getTileCountY() {
        return tileCountY;
    }



    public void setTileCountY(int tileCountY) {
        this.tileCountY = tileCountY;
    }



    public double getMapPositionX() {
        return mapPositionX;
    }



    public void setMapPositionX(double mapPositionX) {
        this.mapPositionX = mapPositionX;
    }



    public double getMapPositionY() {
        return mapPositionY;
    }



    public void setMapPositionY(double mapPositionY) {
        this.mapPositionY = mapPositionY;
    }



    public int[][] getValues() {
        return values;
    }



    public RgbaTexture getRgbaTexture() {
        return rgbaTexture;
    }
}
