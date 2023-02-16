package info.exac.game.exnerian.scene;

import info.exac.game.GameLoop;
import info.exac.game.exnerian.sprite.Enemy;
import info.exac.game.exnerian.sprite.Player;
import info.exac.game.exnerian.sprite.Shot;
import info.exac.game.exnerian.sprite.Star;
import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.gui.common.HorizontalAlignment;
import info.exac.xengine.gfx.g2d.gui.common.VerticalAlignment;
import info.exac.xengine.gfx.g2d.gui.system.SpriteEngine;
import info.exac.xengine.gfx.g2d.gui.system.Timer;
import info.exac.xengine.gfx.g2d.gui.visual.Button;
import info.exac.xengine.gfx.g2d.gui.visual.Image;
import info.exac.xengine.gfx.g2d.gui.visual.Label;
import info.exac.xengine.input.event.KeyEvent;
import info.exac.xengine.input.event.MouseButtonEvent;
import org.lwjgl.glfw.GLFW;



/**
 * @author exac
 * @date 09/02/2018 21:23
 */
public class ExnerianGame extends Stage2D {


    private Player pLayer;

    private SpriteEngine spriteEngine;



    public ExnerianGame(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);
    }


    @Override
    public void initComponents() {

        RgbaTexture asciiFont = getTextureManager().getRgbaTexture("FONT");
        RgbaTexture wallpaper = getTextureManager().getRgbaTexture("WALLPAPER");
        RgbaTexture smileTexture = getTextureManager().getRgbaTexture("SMILE");

        add(new Image("background",0, 0, getScreenWidth(), getScreenHeight(), wallpaper, Rgba.WHITE));

        spriteEngine = new SpriteEngine("spriteEngine");
        add(spriteEngine);


        Button menuButton = new Button("menuButton", getScreenWidth() - 160, getScreenHeight() - 30, 160, 30) {
            @Override
            public void onClick() {
                getStageManager().popStage();
            }
        };
        menuButton.setText("Menu (F10)");
        menuButton.setFont(asciiFont);
        menuButton.setTextColor(Rgba.CYAN);
        add(menuButton);


        Label fpsLabel = new Label("fpsLabel", 0,0, 100, 32);
        fpsLabel.setText("0");
        fpsLabel.setFont(asciiFont);
        fpsLabel.setTextColor(Rgba.WHITE);
        fpsLabel.setVerticalAlign(VerticalAlignment.TOP);
        fpsLabel.setHorizontalAlign(HorizontalAlignment.LEFT);
        add(fpsLabel);


        Label spriteCountLabel = new Label("spriteCountLabel", 0,32, 100, 32);
        spriteCountLabel.setText("0");
        spriteCountLabel.setFont(asciiFont);
        spriteCountLabel.setTextColor(Rgba.WHITE);
        spriteCountLabel.setVerticalAlign(VerticalAlignment.TOP);
        spriteCountLabel.setHorizontalAlign(HorizontalAlignment.LEFT);
        add(spriteCountLabel);

        add(new Timer("fpsTimer", 1000) {
            @Override
            public void tick() {
                fpsLabel.setText(String.valueOf(GameLoop.get().getFramePerSecond()));
                spriteCountLabel.setText(String.valueOf(spriteEngine.getSpriteCount()));
            }
        });


        // Sprites
        for (int i = 0; i < 400; i++) {
            Star star = new Star(Math.random() * 800, Math.random() * 600, Rgba.grayScale(Math.random()));
            star.setSpeed(Math.random() * 200 + 50);
            spriteEngine.add(star);
        }

        pLayer = new Player();
        pLayer.setTexture(smileTexture);
        spriteEngine.add(pLayer);

        for (int i = 0; i < 20; i++) {
            Enemy enemy = new Enemy(Math.random() * 800, Math.random() * 600 - 600);
            enemy.setTexture(smileTexture);
            spriteEngine.add(enemy);
        }
    }



    @Override
    public void onMouseButtonEvent(MouseButtonEvent mouseButtonEvent) {
        super.onMouseButtonEvent(mouseButtonEvent);

        spriteEngine.add(new Shot(pLayer.getX() + 32, pLayer.getY() + 32));
    }



    @Override
    public void onKeyEvent(KeyEvent event) {
        super.onKeyEvent(event);

        if (event.getKey() == GLFW.GLFW_KEY_F10) {
            getStageManager().popStage();
        }

    }

}
