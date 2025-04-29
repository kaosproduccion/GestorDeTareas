package es.dam.tareas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        var fxml = getClass().getResource("/es/dam/tareas/view/TareasView.fxml");
        Scene scene = new Scene(FXMLLoader.load(fxml));
        stage.setTitle("Gestor de tareas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);   // Maven ejecutará esta clase
    }
}
