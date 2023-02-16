package info.exac.xengine.input.event;


import static org.lwjgl.glfw.GLFW.*;


/**
 * @author exac
 * @date 06/02/2018 05:13
 */
public class MouseButtonEvent extends Event {

    public static final int BUTTON_PRIMARY   = GLFW_MOUSE_BUTTON_1;
    public static final int BUTTON_SECONDARY = GLFW_MOUSE_BUTTON_2;
    public static final int BUTTON_MIDDLE    = GLFW_MOUSE_BUTTON_3;

    public static final int ACTION_UP   = GLFW_RELEASE;
    public static final int ACTION_DOWN = GLFW_PRESS;



    private int button;

    private int action;

    private int mods;

    private double mouseX;

    private double mouseY;

    private long timeStamp;


    public MouseButtonEvent(int button, int action, int mods, double mouseX, double mouseY) {
        this.button = button;
        this.action = action;
        this.mods = mods;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.timeStamp = System.currentTimeMillis();
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Mouse button event: ");

        if (isPrimary())
            buffer.append("PRIMARY ");
        if (isSecondary())
            buffer.append("SECONDARY ");
        if (isMiddle())
            buffer.append("MIDDLE ");
        if (isActionDown())
            buffer.append("button DOWN ");
        if (isActionUp())
            buffer.append("button UP ");

        if (mods > 0) {
            buffer.append("with ");

            if (isWithShift())
                buffer.append("SHIFT ");
            if (isWithControl())
                buffer.append("CONTROL ");
            if (isWithAlt())
                buffer.append("ALT ");
            if (isWithCommand())
                buffer.append("COMMAND ");
        }

        buffer.append("at (").append(mouseX).append(", ").append(mouseY);
        buffer.append(") on ").append(timeStamp);

        return buffer.toString();
    }


    public boolean isPrimary() {
        return button == BUTTON_PRIMARY;
    }


    public boolean isSecondary() {
        return button == BUTTON_SECONDARY;
    }

    public boolean isMiddle() {
        return button == BUTTON_MIDDLE;
    }

    public boolean isActionDown() {
        return action == ACTION_DOWN;
    }

    public boolean isActionUp() {
        return action == ACTION_UP;
    }


    public boolean isWithShift() {
        return MOD_SHIFT == (mods & MOD_SHIFT);
    }

    public boolean isWithCommand() {
        return MOD_COMMAND == (mods & MOD_COMMAND);
    }

    public boolean isWithControl() {
        return MOD_CONTROL == (mods & MOD_CONTROL);
    }

    public boolean isWithAlt() {
        return MOD_ALT == (mods & MOD_ALT);
    }


    public boolean isPrimaryUp() {
        return isPrimary() && isActionUp();
    }


    public boolean isPrimaryDown() {
        return isPrimary() && isActionDown();
    }


    //--- Getters / Setters -----------------



    public int getButton() {
        return button;
    }



    public int getAction() {
        return action;
    }



    public int getMods() {
        return mods;
    }



    public double getMouseX() {
        return mouseX;
    }



    public double getMouseY() {
        return mouseY;
    }


    public long getTimeStamp() {
        return timeStamp;
    }

}
