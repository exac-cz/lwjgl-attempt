package info.exac.xengine.tools.png_to_rgba_converter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;



/**
 * @author exac
 * @date 05/02/2018 15:59
 */
public class PngToRgbaConverter {

    public static final int BYTES_PER_PIXEL = 4;


    Logger logger = Logger.getLogger("logger");



    public void convert(String fileNameFrom, String fileNameTo) throws IOException {

        logger.info("Converting from " + fileNameFrom + " to " + fileNameTo);
        logger.info("Loading from " + fileNameFrom);

        BufferedImage image = ImageIO.read(new File(fileNameFrom));

        logger.info("Loaded image " + image.getWidth() + "px x " + image.getHeight() + "px");

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());


        FileOutputStream os = new FileOutputStream(fileNameTo);

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                byte[] rgba = new byte[4];

                rgba[0] = ((byte) ((pixel >> 16) & 0xFF));     // Red component
                rgba[1] = ((byte) ((pixel >> 8) & 0xFF));      // Green component
                rgba[2] = ((byte) (pixel & 0xFF));               // Blue component
                rgba[3] = ((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA

                os.write(rgba);
            }
        }

        os.flush();
        os.close();


    }



    public static void main(String[] argv) throws IOException {
        PngToRgbaConverter converter = new PngToRgbaConverter();
//        converter.convert("./gfx/smile.png", "./gfx/smile.rgba");
//        converter.convert("./gfx/redblue.png", "./gfx/redblue.rgba");
//        converter.convert("./gfx/tiles4x32x32.png", "./gfx/tiles4x32x32.rgba");
//        converter.convert("./gfx/grass-tiles-32x32-192x64.png", "./gfx/grass-tiles-32x32-192x64.rgba");
//        converter.convert("./gfx/ascii-16x16.png", "./gfx/ascii-16x16.rgba");
//        converter.convert("./gfx/ascii-9x16.png", "./gfx/ascii-9x16.rgba");
//        converter.convert("./gfx/font-16x16.png", "./gfx/font-16x16.rgba");
//        converter.convert("./gfx/wallpaper01.jpg", "./gfx/wallpaper01.rgba");
        converter.convert("./gfx/deep_space-wallpaper-800x600.jpg", "./gfx/deep_space-wallpaper-800x600.rgba");


    }



}
