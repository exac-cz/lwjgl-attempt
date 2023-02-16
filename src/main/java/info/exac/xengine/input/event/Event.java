package info.exac.xengine.input.event;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_ALT;
import static org.lwjgl.glfw.GLFW.GLFW_MOD_SUPER;



/**
 * @author exac
 * @date 09/02/2018 16:42
 */
public abstract class Event {

    public static final int ACTION_RELEASED = GLFW_RELEASE;
    public static final int ACTION_PRESSED  = GLFW_PRESS;

    public static final int MOD_SHIFT   = GLFW_MOD_SHIFT;
    public static final int MOD_CONTROL = GLFW_MOD_CONTROL;
    public static final int MOD_ALT     = GLFW_MOD_ALT;
    public static final int MOD_COMMAND = GLFW_MOD_SUPER;


}
