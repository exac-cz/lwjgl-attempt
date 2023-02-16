package info.exac.xengine.gfx.common;

/**
 * @author exac
 * @date 02/02/2018 15:01
 */
public class Rgba {

    private static final double DEFAULT_ALPHA = 1.0;

    public static final Rgba BLACK      = new Rgba(0.0, 0.0, 0.0, DEFAULT_ALPHA);
    public static final Rgba LIGHT_GRAY = Rgba.fromRGB(211, 211, 211);
    public static final Rgba SILVER     = Rgba.fromRGB(192, 192, 192);
    public static final Rgba DARK_GRAY  = Rgba.fromRGB(169, 169, 169);
    public static final Rgba GRAY       = Rgba.fromRGB(128, 128, 128);
    public static final Rgba DIM_GRAY   = Rgba.fromRGB(105, 105, 105);
    public static final Rgba WHITE      = new Rgba(1.0, 1.0, 1.0, DEFAULT_ALPHA);

    public static final Rgba RED        = new Rgba(1.0, 0.0, 0.0, DEFAULT_ALPHA);
    public static final Rgba GREEN      = new Rgba(0.0, 1.0, 0.0, DEFAULT_ALPHA);
    public static final Rgba BLUE       = new Rgba(0.0, 0.0, 1.0, DEFAULT_ALPHA);

    public static final Rgba CYAN       = new Rgba(0.0, 1.0, 1.0, DEFAULT_ALPHA);
    public static final Rgba MAGENTA    = new Rgba(1.0, 0.0, 1.0, DEFAULT_ALPHA);
    public static final Rgba YELLOW     = new Rgba(1.0, 1.0, 0.0, DEFAULT_ALPHA);


    public static final Rgba _SHADOW    = Rgba.fromHtml("#322125", 0.6);


    private static double scaleColorToDouble(int colorPart) {
        return (double) colorPart / (double) 255;
    }



    public static Rgba fromRGB(int red, int green, int blue) {
        return fromRGBA(red, green, blue, DEFAULT_ALPHA);
    }



    public static Rgba fromRGBA(int red, int green, int blue, double alpha) {
        return new Rgba(
                scaleColorToDouble(red),
                scaleColorToDouble(green),
                scaleColorToDouble(blue),
                alpha);
    }



    public static Rgba grayScale(int brightness) {
        return new Rgba(
                scaleColorToDouble(brightness),
                scaleColorToDouble(brightness),
                scaleColorToDouble(brightness),
                DEFAULT_ALPHA);
    }



    public static Rgba grayScale(double brightness) {
        return new Rgba(brightness, brightness, brightness, DEFAULT_ALPHA);
    }



    public static Rgba fromHtml(String htmlColorFormat, double alpha) {
        return fromRGBA(
                Integer.valueOf( htmlColorFormat.substring( 1, 3 ), 16 ),
                Integer.valueOf( htmlColorFormat.substring( 3, 5 ), 16 ),
                Integer.valueOf( htmlColorFormat.substring( 5, 7 ), 16 ),
                alpha);
    }



    public static Rgba random() {
        return new Rgba(Math.random(), Math.random(), Math.random(), DEFAULT_ALPHA);
    }



    public double red;

    public double green;

    public double blue;

    public double alpha;



    public Rgba(double red, double green, double blue) {
        this(red, green, blue, DEFAULT_ALPHA);
    }



    public Rgba(double red, double green, double blue, double alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }



}
