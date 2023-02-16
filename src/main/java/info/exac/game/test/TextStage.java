package info.exac.game.test;

import info.exac.game.GameLoop;
import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.gui.system.Timer;
import info.exac.xengine.gfx.g2d.Vertex2D;
import info.exac.xengine.gfx.g2d.elements.AnimatedRectagle2D;
import info.exac.xengine.gfx.g2d.elements.RasterText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author exac
 * @date 06/02/2018 13:51
 */
public class TextStage extends Stage2D {

    public static final Logger LOGGER = LoggerFactory.getLogger(TextStage.class);


    private RasterText fpsText;



    public TextStage(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);
    }



    @Override
    public void initComponents() {

        RgbaTexture asciiFont = getTextureManager().loadRgbaTexture("./gfx/font-16x16.properties");
        this.add(new AnimatedRectagle2D(
                new Vertex2D(500, 500, Rgba.WHITE),
                new Vertex2D(532, 500, Rgba.WHITE),
                new Vertex2D(532, 532, Rgba.WHITE),
                new Vertex2D(500, 532, Rgba.WHITE),
                asciiFont
        ));

        this.add(new RasterText("Pokus", 10.0, 50.0, Rgba.WHITE, asciiFont));

        RasterText rasterText = new RasterText("Miluju te, lasko\nFakt moc", 10.0, 100.0, Rgba.WHITE, asciiFont);
        rasterText.setGlyphWidth(24);
        rasterText.setGlyphHeight(32);
        this.add(rasterText);



        fpsText = new RasterText("FPS: 0", 0.0, 0.0, Rgba.WHITE, asciiFont);
        fpsText.setGlyphWidth(24);
        fpsText.setGlyphHeight(32);
        add(fpsText);

        Timer timer = new Timer("timer", 1000) {
            @Override
            public void tick() {
                fpsText.setValue("FPS: " + Integer.toString(GameLoop.get().getFramePerSecond()));
            }
        };
        add(timer);

    }


}
