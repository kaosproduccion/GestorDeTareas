package es.dam.tareas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación JavaFX.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Ruta del FXML (empieza con / porque busca en el class-path)
        var url = getClass().getResource("/es/dam/tareas/view/TareasView.fxml");
        if (url == null) {
            throw new IllegalStateException("No se encuentra TareasView.fxml");
        }

        // Cargamos la escena
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setTitle("Gestor de tareas");
        stage.setScene(scene);
        // (opcional) Icono de la ventana si tuvieras uno en resources
        // stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);   // llama al método start()
    }
}
