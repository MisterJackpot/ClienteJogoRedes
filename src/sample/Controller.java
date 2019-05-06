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

    public static Connection connection;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println(portNumber.getText());

        connection = new Connection(Integer.valueOf(portNumber.getText()),"192.168.0.195");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("character.fxml"));
            Stage stage = (Stage) enterBtn.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
