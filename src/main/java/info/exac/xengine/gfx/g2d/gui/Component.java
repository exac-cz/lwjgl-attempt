package info.exac.xengine.gfx.g2d.gui;

/**
 * @author exac
 * @date 07/02/2018 15:09
 */
public class Component {

    private String id;

    private boolean enabled = true;



    public Component(String id) {
        this.id = id;
    }



    //--- Getters / Setters


    public String getId() {
        return id;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
