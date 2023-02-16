package info.exac.xengine.gfx.utils;

import info.exac.xengine.gfx.common.RgbaTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;



/**
 * @author exac
 * @date 05/02/2018 12:26
 */
public class TextureLoader {

    public static final int BYTES_PER_PIXEL = 4;

    public static final String PROPERTY_CODE   = "code";
    public static final String PROPERTY_WIDTH  = "width";
    public static final String PROPERTY_HEIGHT = "height";
    public static final String PROPERTY_FILE   = "file";

    public static final String PROPERTY_TILES_COUNT_X   = "tilesCountX";
    public static final String PROPERTY_TILES_COUNT_Y   = "tilesCountY";
    public static final String PROPERTY_TILES_COUNT     = "tilesCount";
    public static final String PROPERTY_TILE_WIDTH      = "tileWidth";
    public static final String PROPERTY_TILE_HEIGHT     = "tileHeight";

    public static final String PROPERTY_FRAME_COUNT     = "frameCount";
    public static final String PROPERTY_FRAME_COUNT_X   = "frameCountX";
    public static final String PROPERTY_FRAME_COUNT_Y   = "frameCountY";
    public static final String PROPERTY_FRAME_WIDTH     = "frameWidth";
    public static final String PROPERTY_FRAME_HEIGHT    = "frameHeight";

    public static final String PROPERTY_BORDER          = "border";

    private TextureLoader() { }




    public static RgbaTexture loadFromPropertyFile(String fileName) throws IOException {

        Path pathToProperties = Paths.get(fileName);
        Properties textureProperties = new Properties();
        textureProperties.load(Files.newInputStream(pathToProperties));

        String code = textureProperties.getProperty(PROPERTY_CODE);
        int width = Integer.valueOf(textureProperties.getProperty(PROPERTY_WIDTH));
        int height = Integer.valueOf(textureProperties.getProperty(PROPERTY_HEIGHT));

        String textureFileName = textureProperties.getProperty(PROPERTY_FILE);

        Path pathToRgba = Paths.get(pathToProperties.getParent() + File.separator + textureFileName);
        byte[] rgbaData = Files.readAllBytes(pathToRgba);

        int rgbaDataLength = width * height * BYTES_PER_PIXEL;
        ByteBuffer textureBuffer = BufferUtils.createByteBuffer(rgbaDataLength);
        for (int i = 0; i < rgbaDataLength; i++) {
            textureBuffer.put(rgbaData[i]);
        }
        textureBuffer.flip();


        int frameCount = getIntProperty(PROPERTY_FRAME_COUNT, textureProperties);
        int frameCountX = getIntProperty(PROPERTY_FRAME_COUNT_X, textureProperties);
        int frameCountY = getIntProperty(PROPERTY_FRAME_COUNT_Y, textureProperties);
        int frameWidth = getIntProperty(PROPERTY_FRAME_WIDTH, textureProperties);
        int frameHeight = getIntProperty(PROPERTY_FRAME_HEIGHT, textureProperties);

        int tilesCount = getIntProperty(PROPERTY_TILES_COUNT, textureProperties);
        int tilesCountX = getIntProperty(PROPERTY_TILES_COUNT_X, textureProperties);
        int tilesCountY = getIntProperty(PROPERTY_TILES_COUNT_Y, textureProperties);
        int tileWidth = getIntProperty(PROPERTY_TILE_WIDTH, textureProperties);
        int tileHeight = getIntProperty(PROPERTY_TILE_HEIGHT, textureProperties);

        int border = getIntProperty(PROPERTY_BORDER, textureProperties, 0);

        return new RgbaTexture(code, width, height, textureBuffer,
                frameCount, frameCountX, frameCountY, frameWidth, frameHeight,
                tilesCount, tilesCountX, tilesCountY, tileWidth, tileHeight, border);
    }



    public static int getIntProperty(String propertyName, Properties properties) {
        return getIntProperty(propertyName, properties, -1);
    }



    public static int getIntProperty(String propertyName, Properties properties, int nullValue) {
        String propertyValue = properties.getProperty(propertyName);
        try {
            return Integer.valueOf(propertyValue);
        } catch (NumberFormatException e) {
            // DO NOTHING
        }

        return nullValue;
    }



    public static RgbaTexture loadFromFile(String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            stbi_set_flip_vertically_on_load(false);
            image = stbi_load(path, w, h, comp, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!" + System.lineSeparator() + stbi_failure_reason());
            }

            /* Get width and height of image */
            width = w.get();
            height = h.get();
        }

        return new RgbaTexture(path, width, height, image);
    }

}
