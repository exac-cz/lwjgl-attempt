package info.exac.game;

import info.exac.game.test.MapStage;
import info.exac.xengine.gfx.common.RgbaGfxManager;
import info.exac.xengine.gfx.g2d.StageManager;
import info.exac.xengine.gfx.opengl_renderer.OpenGlRenderer;
import info.exac.xengine.input.InputManager;
import info.exac.xengine.input.event.ICustomEvent;
import info.exac.xengine.input.event.KeyEvent;
import info.exac.xengine.input.event.MousePositionEvent;
import info.exac.xengine.input.event.QuitEngineEvent;
import info.exac.xengine.input.listener.CustomEventListener;
import info.exac.xengine.input.listener.KeyEventListener;
import info.exac.xengine.input.listener.MousePositionListener;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;


@Component
public class GameLoop implements MousePositionListener, KeyEventListener, ApplicationContextAware, CustomEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameLoop.class);

    // FIXME
    private static GameLoop instance = null;


    public static final int SCREEN_WIDTH = 800;

    public static final int SCREEN_HEIGHT = 600;


    @Autowired
    private InputManager inputManager;

    @Autowired
    private RgbaGfxManager textureManager;

    @Autowired
    private ApplicationContext applicationContext;


    // The window handle
    private long window;


    private double mouseX;

    private double mouseY;

    private boolean countFps = true;


    private int framePerSecond = 0;



    public void run() throws IOException {
        LOGGER.info("Hello LWJGL " + Version.getVersion() + "!");
        instance = this;

        try {
            init();
            loop();

//            InputManager.getInstance().releaseCallbacks();

        } finally {
            textureManager.releaseTextures();

            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);

            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }



    private void init() {

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
//        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // the window will not be resizable
        glfwWindowHint(GLFW_DECORATED, GL_FALSE);

        GLFWVidMode modes = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Create the window
//        window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "XEngine", glfwGetPrimaryMonitor(), NULL);
        window = glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "XEngine", NULL, NULL);


        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup alpha key callback. It will be called every time alpha key is pressed, repeated or released.
        glfwSetKeyCallback(window, inputManager.getKeyCallback());
        glfwSetCharCallback(window, inputManager.getCharCallback());
        glfwSetCharModsCallback(window, inputManager.getCharModsCallback());

        glfwSetCursorPosCallback(window, inputManager.getCursorPosCallback());

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
//        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetMouseButtonCallback(window, inputManager.getMouseButtonCallBack());

        inputManager.addMousePosiitonListener(this);
        inputManager.addKeyListener(this);


        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }



    private void loop() throws IOException {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the ContextCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities(); // valid for latest build
//        GLContext.createFromCurrent(); // use this line instead with the 3.0.0a build


        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        glViewport(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        GL11.glShadeModel(GL11.GL_SMOOTH);

        glEnable(GL_LINE_SMOOTH);
        glEnable(GL_POLYGON_SMOOTH);
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        textureManager.loadRgbaTexture("./gfx/font-16x16.properties");
        textureManager.loadRgbaTexture("./gfx/deep_space-wallpaper-800x600.properties");

        textureManager.loadRgbaTexture("./gfx/smile.png");
//        textureManager.loadRgbaTexture("./gfx/smile.properties");



        OpenGlRenderer renderer = new OpenGlRenderer();

        StageManager stageManager = new StageManager(textureManager);
        inputManager.addKeyListener(stageManager);
        inputManager.addMouseButtonListener(stageManager);
        inputManager.addMousePosiitonListener(stageManager);

//        stageManager.pushStage(new ExnerianMenu(SCREEN_WIDTH, SCREEN_HEIGHT));
//        stageManager.pushStage(new TestStage(SCREEN_WIDTH, SCREEN_HEIGHT));
        stageManager.pushStage(new MapStage(SCREEN_WIDTH, SCREEN_HEIGHT));




        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        long startTime = System.currentTimeMillis();
        int frame = 0;
        long lastTimestamp = System.currentTimeMillis();

        while (!glfwWindowShouldClose(window)) {
            long nowTimestamp = System.currentTimeMillis();
            long delta = nowTimestamp - lastTimestamp;

            glClear(GL_COLOR_BUFFER_BIT /*| GL_DEPTH_BUFFER_BIT*/); // clear the framebuffer

            stageManager.update(delta);
            renderer.renderStage(stageManager.getActiveStage());

            glLineWidth(1.0f);
            glEnable(GL_LINE_WIDTH);


            glBegin(GL_LINES);
            glColor3d(1.0, 0.0, 0.0);
            glVertex2d(mouseX, mouseY - 10);
            glVertex2d(mouseX, mouseY + 10);
            glVertex2d(mouseX - 10, mouseY);
            glVertex2d(mouseX + 10, mouseY);
            glEnd();


            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            inputManager.update(delta);

            // FPS count;
            if (countFps) {
                frame++;
                if ((nowTimestamp - startTime) > 1000) {
                    framePerSecond = frame;
                    frame = 0;
                    startTime = nowTimestamp;
                }
            }

            lastTimestamp = nowTimestamp;
        }
    }



    @Override
    public void onMousePositionEvent(MousePositionEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
    }



    @Override
    public void onKeyEvent(KeyEvent event) {

        if (event.isKeyReleased(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
        }

    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



    public int getFramePerSecond() {
        return framePerSecond;
    }


    public static GameLoop get() {
        return instance;
    }



    @Override
    public void onCustomEvent(ICustomEvent customEvent) {
        if (customEvent instanceof QuitEngineEvent) {
            glfwSetWindowShouldClose(window, true);
        }
    }



    public InputManager getInputManager() {
        return inputManager;
    }
}
