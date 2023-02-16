package info.exac.xengine.input;

import info.exac.xengine.gfx.g2d.gui.common.IUpdatable;
import info.exac.xengine.input.event.CharacterEvent;
import info.exac.xengine.input.event.KeyEvent;
import info.exac.xengine.input.event.MouseButtonEvent;
import info.exac.xengine.input.event.MousePositionEvent;
import info.exac.xengine.input.listener.KeyEventListener;
import info.exac.xengine.input.listener.MouseButtonListener;
import info.exac.xengine.input.listener.MousePositionListener;
import org.lwjgl.glfw.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



/**
 * @author exac
 * @date 06/02/2018 05:50
 */
@Component
public class InputManager implements IUpdatable {

    private static final Logger logger = LoggerFactory.getLogger(InputManager.class);



    private GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            mouseX = xpos;
            mouseY = ypos;

            MousePositionEvent event = new MousePositionEvent(xpos, ypos);
            mousePositionListeners.forEach(l -> l.onMousePositionEvent(event));
        }
    };



    private GLFWMouseButtonCallback mouseButtonCallBack = new GLFWMouseButtonCallback() {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            if (action == GLFW.GLFW_PRESS) {
                mouseButtonPressed[button] = true;
            }

            if (action == GLFW.GLFW_RELEASE) {
                mouseButtonPressed[button] = false;
            }

            MouseButtonEvent event = new MouseButtonEvent(button, action, mods, mouseX, mouseY);
            logger.debug("{}", event);
            mouseButtonListeners.forEach(l -> l.onMouseButtonEvent(event));
        }
    };



    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (action == GLFW.GLFW_PRESS) {
                keyPressed[key] = true;
            }

            if (action == GLFW.GLFW_RELEASE) {
                keyPressed[key] = false;
            }

            String keyName = GLFW.glfwGetKeyName(key, scancode);

            KeyEvent keyEvent = new KeyEvent(key, scancode, action, mods, keyName);
            logger.debug("{}", keyEvent);
            keyEventListeners.forEach(l -> l.onKeyEvent(keyEvent));
        }
    };



    private GLFWCharCallback charCallback = new GLFWCharCallback() {
        @Override
        public void invoke(long window, int codepoint) {
            CharacterEvent charEvent = new CharacterEvent(codepoint);

            logger.debug("{}", charEvent);
        }
    };



    private GLFWCharModsCallback charModsCallback = new GLFWCharModsCallback() {
        @Override
        public void invoke(long window, int codepoint, int mods) {
            logger.debug("Char mods event: {}, {}", codepoint, mods);
        }
    };


    private boolean keyPressed[] = new boolean[GLFW.GLFW_KEY_LAST + 1];

    private boolean mouseButtonPressed[] = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST + 1];

    private List<MousePositionListener> mousePositionListeners = new ArrayList<>();

    private List<MouseButtonListener> mouseButtonListeners = new ArrayList<>();

    private List<KeyEventListener> keyEventListeners = new ArrayList<>();


    private double mouseX;

    private double mouseY;



    private InputManager() {

    }



    @Override
    public void update(long delta) {
        // TODO receive time for click, double click and mouse still for 1s for example
    }



    public boolean isKeyPressed(int key) {
        return keyPressed[key];
    }


    public boolean isMouseButtonPressed(int button) {
        return mouseButtonPressed[button];
    }



    public void addMousePosiitonListener(MousePositionListener listner) {
        mousePositionListeners.add(listner);
    }

    public void removeMousePositionListener(MousePositionListener listener) {
        mousePositionListeners.remove(listener);
    }

    public void addMouseButtonListener(MouseButtonListener listener) {
        mouseButtonListeners.add(listener);
    }

    public void removeMouseButtonListener(MouseButtonListener listener) {
        mouseButtonListeners.remove(listener);
    }

    public void addKeyListener(KeyEventListener listener) {
        keyEventListeners.add(listener);
    }

    public void removeKeyListener(KeyEventListener listener) {
        keyEventListeners.remove(listener);
    }



    public GLFWCursorPosCallback getCursorPosCallback() {
        return cursorPosCallback;
    }



    public GLFWMouseButtonCallback getMouseButtonCallBack() {
        return mouseButtonCallBack;
    }



    public GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }



    public GLFWCharCallback getCharCallback() {
        return charCallback;
    }



    public GLFWCharModsCallback getCharModsCallback() {
        return charModsCallback;
    }



    public double getMouseX() {
        return mouseX;
    }



    public double getMouseY() {
        return mouseY;
    }
}
