package info.exac.xengine.tools.texture_editor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;



/**
 * @author exac
 * @date 05/02/2018 11:59
 */
public class TextureEditor extends Application {


    public static void main(String[] args) throws IOException {
        TextureEditor.launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("XEngine Texture Editor");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }


}
