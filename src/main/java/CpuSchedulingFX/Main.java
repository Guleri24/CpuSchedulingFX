package CpuSchedulingFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setTitle("CPU scheduler");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("input_response.css")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
