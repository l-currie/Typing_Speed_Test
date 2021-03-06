import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/mainWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1100, 400));
        primaryStage.show();
        String css = this.getClass().getResource("UI/mainWindow.css").toExternalForm();
        root.getStylesheets().add(css);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
