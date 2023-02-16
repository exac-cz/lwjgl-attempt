package info.exac.xengine.gfx.common;

import info.exac.xengine.gfx.utils.TextureLoader;
import org.lwjgl.opengl.GL12;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;



/**
 * @author exac
 * @date 05/02/2018 13:00
 */
@Component
public class RgbaGfxManager {

    private RgbaGfxManager() {

    }

    private Map<String, RgbaTexture> textureMap = new HashMap<>();




    public RgbaTexture loadRgbaTexture(String propertyFileName) {
        try {
            RgbaTexture rgbaTexture = null;
            if (propertyFileName.contains(".properties")) {
                rgbaTexture = TextureLoader.loadFromPropertyFile(propertyFileName);
            } else if (propertyFileName.contains(".png")) {
                rgbaTexture = TextureLoader.loadFromFile(propertyFileName);
                rgbaTexture = new RgbaTexture("SMILE", rgbaTexture.getWidth(), rgbaTexture.getHeight(), rgbaTexture.getRgbaData());
            }
            textureMap.put(rgbaTexture.getCode(), rgbaTexture);

            int textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);

            //Setup wrap mode
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

            //Setup texture scaling filtering
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8,
                    rgbaTexture.getWidth(), rgbaTexture.getHeight(),
                    rgbaTexture.getBorder(),
                    GL_RGBA, GL_UNSIGNED_BYTE,
                    rgbaTexture.getRgbaData());

            // Unbinded
            glBindTexture(GL_TEXTURE_2D, 0);

            rgbaTexture.setTextureId(textureId);

            return rgbaTexture;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public RgbaTexture getRgbaTexture(String textureCode) {
        return textureMap.get(textureCode);
    }



    public void releaseTextures() {
        textureMap.values().forEach(i -> glDeleteTextures(i.getTextureId()));
        textureMap.clear();
    }


}
