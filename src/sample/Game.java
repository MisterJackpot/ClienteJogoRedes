package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {
    @FXML
    Canvas board;

    @FXML
    Button diceBtn;

    @FXML
    Label diceValue;

    Random r;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawMap();
    }

    @FXML
    private void rollDice(ActionEvent event) {
        int dice = r.nextInt(6)+1;

        System.out.println(dice);

        diceValue.setText(String.valueOf(dice));
    }

    private void drawMap(){
        GraphicsContext gc = board.getGraphicsContext2D();
        r = new Random();
        double x = board.getWidth();
        double y = board.getHeight();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.fillRect(0,0,x,y);
        gc.strokeLine(0, 0, x, 0);
        gc.strokeLine(0, 0, 0, y);
        gc.strokeLine(x, y, 0, y);
        gc.strokeLine(x, y, x, 0);
    }
}
