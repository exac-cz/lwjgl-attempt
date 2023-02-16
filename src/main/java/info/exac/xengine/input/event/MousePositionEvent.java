package info.exac.xengine.input.event;

/**
 * @author exac
 * @date 06/02/2018 05:57
 */
public class MousePositionEvent extends Event {

    private double x;

    private double y;

    private long timeStamap;



    public MousePositionEvent(double x, double y) {
        this.x = x;
        this.y = y;
        this.timeStamap = System.currentTimeMillis();
    }



    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Mouse position event: at (");
        buffer.append(x).append(", ").append(y);
        buffer.append(") on ").append(timeStamap);

        return buffer.toString();
    }



    public double getX() {
        return x;
    }



    public double getY() {
        return y;
    }



    public long getTimeStamap() {
        return timeStamap;
    }

}
