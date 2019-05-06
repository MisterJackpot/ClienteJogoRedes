package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Character implements Initializable {

    @FXML
    Button btnEntrar;

    @FXML
    ColorPicker selectColor;

    @FXML
    TextField nick;

    Connection connection;

    static Color c;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Controller.connection;

    }

    @FXML
    private void entrar(ActionEvent event) {
        try {
            connection.os = new ObjectOutputStream(connection.socket.getOutputStream());
            connection.os.flush();
            connection.os.writeObject(nick.getText());

            c = selectColor.getValue();

            connection.is = new ObjectInputStream(connection.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Stage stage = (Stage) btnEntrar.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
