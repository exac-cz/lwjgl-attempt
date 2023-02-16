package info.exac.game.test;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.TileMap;
import info.exac.xengine.gfx.g2d.gui.common.HorizontalAlignment;
import info.exac.xengine.gfx.g2d.gui.common.VerticalAlignment;
import info.exac.xengine.gfx.g2d.gui.visual.Button;
import info.exac.xengine.gfx.g2d.gui.visual.Image;
import info.exac.xengine.gfx.g2d.gui.visual.Label;
import info.exac.xengine.gfx.g2d.gui.visual.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author exac
 * @date 05/02/2018 21:27
 */
public class MapStage extends Stage2D {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapStage.class);

    private Label label;



    public MapStage(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);
    }



    @Override
    public void initComponents() {

        RgbaTexture wallpaperTexture = getTextureManager().loadRgbaTexture("./gfx/wallpaper01.properties");
        add(new Image("image", 0,0, 800, 600, wallpaperTexture, Rgba.GRAY));

        RgbaTexture tilesTexture = getTextureManager().loadRgbaTexture("./gfx/grass-tiles-32x32-192x64.properties");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("00222");
        stringBuffer.append("04740");
        stringBuffer.append("06160");
        stringBuffer.append("04744");
        stringBuffer.append("56789");
        String map = stringBuffer.toString();

        int[][] values = new int[5][5];

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                values[x][y] = Integer.valueOf(map.substring(x + y * 5, x + y * 5 + 1));
            }
        }

        this.add(new TileMap(32, 32, 5, 5, 32.0, 32.0, tilesTexture, values));



        RgbaTexture asciiFont = getTextureManager().loadRgbaTexture("./gfx/font-16x16.properties");


        Button button = null;
        this.add(button = new Button("button", "Stisk", 600, 50, 100, 50) {
            @Override
            public void onClick() {
                this.setColor(Rgba.random());
                this.setTextColor(Rgba.random());
                label.setText("xxx");
                LOGGER.debug("Button clicked");
            }
        });
        button.setFont(asciiFont);
        button.setTextColor(Rgba.RED);

        add(new Button("exitButton", 100, 450, 100, 50) {
            @Override
            public void onClick() {
//                getStageManager().setActiveStage(new TestStage());
                getStageManager().setActiveStage(new TextStage(getScreenWidth(), getScreenHeight()));
            }
        });

        this.add(new TextField("TextField", 600, 550, 100, 50));

        label = new Label("label", 200, 200, 400, 300) {
            @Override
            public void onClick() {
                super.onClick();
                setText("aaa");
            }
        };
        label.setText("Pokus");
        label.setTextColor(Rgba.GREEN);
        label.setFont(asciiFont);
        label.setBackgroundColor(Rgba.DIM_GRAY);

        label.setVerticalAlign(VerticalAlignment.BOTTOM);
        label.setHorizontalAlign(HorizontalAlignment.RIGHT);

        add(label);

    }



}
