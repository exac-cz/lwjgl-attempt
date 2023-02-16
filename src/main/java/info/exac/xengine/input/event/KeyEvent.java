package info.exac.xengine.input.event;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_ALT;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_SUPER;



/**
 * @author exac
 * @date 06/02/2018 06:23
 */
public class KeyEvent extends Event {

    private final long key;

    private final int scancode;

    private final int action;

    private final int mods;

    private String keyName;

    private long timeStamp;



    public KeyEvent(int key, int scancode, int action, int mods, String keyName) {
        this.key = key;
        this.scancode = scancode;
        this.action = action;
        this.mods = mods;
        this.keyName = keyName;
        this.timeStamp = System.currentTimeMillis();
    }



    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Key event: ");

        buffer.append("key = ").append(key);
        if (!Character.isWhitespace((int) key)) {
            buffer.append(" '").append((char) key).append("'");
        }
        buffer.append(", ");

        buffer.append("scancode = ").append(scancode).append(", ");

        buffer.append("name = ").append(keyName).append(", ");

        if (isKeyPressed())
            buffer.append("action PRESS ");

        if (isKeyReleased())
            buffer.append("action RELEASE ");

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

        buffer.append("on ").append(timeStamp);

        return buffer.toString();
    }



    public boolean isKey(int key) {
        return this.key == key;
    }


    public boolean isKeyPressed() {
        return action == ACTION_PRESSED;
    }


    public boolean isKeyPressed(int key) {
        return isKey(key) && isKeyPressed();
    }


    public boolean isKeyReleased() {
        return action == ACTION_RELEASED;
    }


    public boolean isKeyReleased(int key) {
        return isKey(key) && isKeyReleased();
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


    public long getKey() {
        return key;
    }



    public int getScancode() {
        return scancode;
    }



    public int getAction() {
        return action;
    }



    public int getMods() {
        return mods;
    }



    public long getTimeStamp() {
        return timeStamp;
    }

}
