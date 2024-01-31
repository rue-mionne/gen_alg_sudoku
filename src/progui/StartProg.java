package progui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartProg extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            stage.setTitle("Sudoku Solver");
            FXMLLoader fxmlLoader = new FXMLLoader(StartProg.class.getResource("SudokuUI.fxml"));
            Scene scena1 = new Scene(fxmlLoader.load());
            ControllerMain controllerMain = fxmlLoader.getController();
            stage.setScene(scena1);
            stage.show();
            controllerMain.populate();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }



    }



    public static void main(String[] args) {
        launch();
    }
}