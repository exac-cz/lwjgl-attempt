package info.exac.xengine.gfx.g2d.gui.system;



import info.exac.xengine.gfx.g2d.gui.Component;
import info.exac.xengine.gfx.g2d.gui.common.IUpdatable;



/**
 * @author exac
 * @date 07/02/2018 15:26
 */
public abstract class Timer extends Component implements IUpdatable {

    private long sinceLastUpdate = 0;

    private long interval;


    public Timer(String id) {
        super(id);
    }



    public Timer(String id, long interval) {
        super(id);
        this.interval = interval;
    }



    @Override
    public void update(long delta) {
        if ( (sinceLastUpdate + delta) > interval) {
            tick();
            sinceLastUpdate = 0;
        } else {
            sinceLastUpdate += delta;
        }
    }


    public abstract void tick();


    public long getInterval() {
        return interval;
    }



    public void setInterval(long interval) {
        this.interval = interval;
    }

}
