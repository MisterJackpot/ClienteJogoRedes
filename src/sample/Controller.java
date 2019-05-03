package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    Button enterBtn;

    @FXML
    TextField portNumber;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Stage stage = (Stage) enterBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}