package info.exac.game;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author exac
 * @date 05/02/2018 12:00
 */
public class XEngineMain {

    public static void main(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        GameLoop gameLoop = (GameLoop) context.getBean("xEngine");
        GameLoop gameLoop = context.getBean(GameLoop.class);
        gameLoop.run();

    }


}
