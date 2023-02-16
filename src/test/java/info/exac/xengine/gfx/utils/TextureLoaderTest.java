package info.exac.xengine.gfx.utils;


import info.exac.xengine.gfx.common.RgbaTexture;
import org.junit.Test;

import java.io.IOException;



/**
 * Created by exac on 05/02/2018.
 */
class TextureLoaderTest {


    @Test
    public void loadFromFile() throws Exception {
        RgbaTexture texture = TextureLoader.loadFromFile("./gfx/smile.png");
        System.out.println(texture.getWidth());

    }



    @Test
    void loadFromPropertyFile() throws IOException {

        RgbaTexture texture = TextureLoader.loadFromPropertyFile("./gfx/smile.properties");

        System.out.println(texture.getWidth());

    }

}