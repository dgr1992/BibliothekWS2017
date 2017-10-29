package at.fhv.team05;

import at.fhv.team05.presentation.mainView.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 */
public class App extends Application {
    public static void main(String[] args)

    {
        launch(args);
        System.out.println("Hello World!");
    }

    @Override
    public void start(Stage stage) throws Exception {

        MainView appView = new MainView();

        Scene scene = new Scene(appView.getView());
        stage.setTitle("Bibliothek");
        stage.setScene(scene);
        stage.show();
    }
}
