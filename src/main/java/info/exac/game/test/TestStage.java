package info.exac.game.test;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.TileMap;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.*;



/**
 * @author exac
 * @date 05/02/2018 15:19
 */
public class TestStage extends Stage2D {



    public TestStage(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);
    }



    @Override
    public void initComponents() {
        for (int i = 0; i < 800; i += 20) {
            this.add(new Line2D(
                    new Vertex2D(i, 0, Rgba.LIGHT_GRAY),
                    new Vertex2D(i, 600, Rgba.LIGHT_GRAY),
                    1.0
            ));
        }
        for (int i = 0; i < 600; i += 20) {
            this.add(new Line2D(
                    new Vertex2D(0, i, Rgba.LIGHT_GRAY),
                    new Vertex2D(800, i, Rgba.LIGHT_GRAY),
                    1.0
            ));
        }


        this.add(new OutlinedRectangle2D(0, 0, 800, 600, Rgba.WHITE, 1.0));


        this.add(new Point2D(new Vertex2D(0.0, 0.0, Rgba.WHITE), 2));
        this.add(new Point2D(new Vertex2D(150.0, 150.0, Rgba.WHITE), 10));

        this.add(new Line2D(
                new Vertex2D(400, 100, Rgba.BLUE),
                new Vertex2D(600, 300, Rgba.RED),
                3.0
        ));

        this.add(new Quad2D(
                new Vertex2D(10, 10, Rgba.RED),
                new Vertex2D(10, 110, Rgba.GREEN),
                new Vertex2D(110, 110, Rgba.BLUE),
                new Vertex2D(110, 10, Rgba.WHITE)
        ));


        this.add(new Triangle2D(
                new Vertex2D(200, 200, Rgba.CYAN),
                new Vertex2D(300, 200, Rgba.MAGENTA),
                new Vertex2D(300, 300, Rgba.YELLOW)
        ));

        this.add(new Line2D(
                new Vertex2D(200, 200, Rgba.BLACK),
                new Vertex2D(300, 200, Rgba.BLACK),
                1.0
        ));
        this.add(new Line2D(
                new Vertex2D(300, 200, Rgba.BLACK),
                new Vertex2D(300, 300, Rgba.BLACK),
                1.0
        ));
        this.add(new Line2D(
                new Vertex2D(300, 300, Rgba.BLACK),
                new Vertex2D(200, 200, Rgba.BLACK),
                1.0
        ));

        this.add(new Triangle2D(
                new Vertex2D(450, 450, Rgba.BLUE),
                new Vertex2D(550, 450, Rgba.BLUE),
                new Vertex2D(550, 550, Rgba.BLUE)
        ));

        Rgba red50 = new Rgba(1, 0, 0, 0.5);
        this.add(new Triangle2D(
                new Vertex2D(450, 550, red50),
                new Vertex2D(550, 550, red50),
                new Vertex2D(550, 450, red50)
        ));

        this.add(new Circle2D(640, 480, 100));


        RgbaTexture smileTexture = getTextureManager().loadRgbaTexture("./gfx/smile.properties");
        this.add(new TexturedRectangle2D(
//                new Vertex2D(300, 400, Rgba.WHITE),
//                new Vertex2D(300, 464, Rgba.RED),
//                new Vertex2D(364, 464, Rgba.RED),
//                new Vertex2D(364, 400, Rgba.WHITE),
                new Vertex2D(300, 400, Rgba.WHITE),
                new Vertex2D(300, 464, Rgba.WHITE),
                new Vertex2D(364, 464, Rgba.WHITE),
                new Vertex2D(364, 400, Rgba.WHITE),
                smileTexture
        ));


        RgbaTexture redBlueTexture = getTextureManager().loadRgbaTexture("./gfx/redblue.properties");
        this.add(new TexturedRectangle2D(
                new Vertex2D(400, 400, Rgba.DARK_GRAY),
                new Vertex2D(400, 464, Rgba.DARK_GRAY),
                new Vertex2D(464, 464, Rgba.DARK_GRAY),
                new Vertex2D(464, 400, Rgba.DARK_GRAY),
                redBlueTexture
        ));


        RgbaTexture animTexture = getTextureManager().loadRgbaTexture("./gfx/tiles4x32x32-animation.properties");
        this.add(new AnimatedRectagle2D(
                new Vertex2D(600, 300, Rgba.WHITE),
                new Vertex2D(632, 300, Rgba.WHITE),
                new Vertex2D(632, 332, Rgba.WHITE),
                new Vertex2D(600, 332, Rgba.WHITE),
                animTexture
        ));

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("00222");
        stringBuffer.append("00200");
        stringBuffer.append("33133");
        stringBuffer.append("00200");
        stringBuffer.append("22200");
        String map = stringBuffer.toString();

        int[][] values = new int[5][5];

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                values[x][y] = Integer.valueOf(map.substring(x + y * 5, x + y * 5 + 1));
            }
        }

        RgbaTexture tilesTexture = getTextureManager().loadRgbaTexture("./gfx/tiles4x32x32-tiles.properties");
        this.add(new TileMap(32, 32, 5, 5, 100.0, 300.0, tilesTexture, values));



//        this.add(new TexturedRectangle2D(
//                new Vertex2D(500, 400, Rgba.WHITE),
//                new Vertex2D(500, 464, Rgba.RED),
//                new Vertex2D(564, 464, Rgba.RED),
//                new Vertex2D(564, 400, Rgba.WHITE),
//                smileTexture
//        ));
    }


}
