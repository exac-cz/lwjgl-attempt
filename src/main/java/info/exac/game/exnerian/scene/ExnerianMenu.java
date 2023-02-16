package info.exac.game.exnerian.scene;

import info.exac.xengine.gfx.common.Rgba;
import info.exac.xengine.gfx.common.RgbaTexture;
import info.exac.xengine.gfx.g2d.Stage2D;
import info.exac.xengine.gfx.g2d.gui.visual.Button;
import info.exac.xengine.gfx.g2d.gui.visual.Image;
import info.exac.xengine.input.event.QuitEngineEvent;



/**
 * @author exac
 * @date 09/02/2018 21:23
 */
public class ExnerianMenu extends Stage2D {


    public ExnerianMenu(double screenWidth, double screenHeight) {
        super(screenWidth, screenHeight);
    }


    @Override
    public void initComponents() {


        RgbaTexture asciiFont = getTextureManager().getRgbaTexture("FONT");
        RgbaTexture wallpaper = getTextureManager().getRgbaTexture("WALLPAPER");

        add(new Image("background",0, 0, getScreenWidth(), getScreenHeight(), wallpaper, Rgba.WHITE));

        Button newGameButton = new Button("newGameButton", getScreenWidth() / 2 - 150, 200, 300, 50) {
            @Override
            public void onClick() {
                getStageManager().pushStage(new ExnerianGame(getScreenWidth(), getScreenHeight()));
            }
        };
        newGameButton.setText("New game");
        newGameButton.setFont(asciiFont);
        newGameButton.setTextColor(Rgba.GREEN);
        add(newGameButton);


        Button exitGameButton = new Button("exitGameButton", getScreenWidth() / 2 - 150, 300, 300, 50) {
            @Override
            public void onClick() {
                fireCustomEvent(new QuitEngineEvent());
            }
        };
        exitGameButton.setText("Exit game");
        exitGameButton.setFont(asciiFont);
        exitGameButton.setTextColor(Rgba.RED);
        add(exitGameButton);


    }

}
