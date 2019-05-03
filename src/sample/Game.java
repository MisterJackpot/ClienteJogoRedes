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
import java.util.ArrayList;
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

    ArrayList<Field> fields;
    ArrayList<Player> players;
    int size = 15;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fields = new ArrayList<>();
        players = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j+=2) {
                Field field = new Field();
                if(i%2 == 0){
                    field.setColor(Color.YELLOW);
                }else{
                    field.setColor(Color.PURPLE);
                }
                field.setX(j*size);
                field.setY(i*size);
                fields.add(field);
            }
            for (int j = 1; j < 10; j+=2) {
                Field field = new Field();
                if(i%2 == 0){
                    field.setColor(Color.PURPLE);
                }else{
                    field.setColor(Color.YELLOW);
                }
                field.setX(j*size);
                field.setY(i*size);
                fields.add(field);
            }
        }
        Player p1 = new Player(1);
        p1.setColor(Color.GREEN);
        p1.setCurrentField(fields.get(0));
        players.add(p1);


        Player p2 = new Player(2);
        p2.setColor(Color.RED);
        p2.setCurrentField(fields.get(0));
        players.add(p2);
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
        for (Field f: fields) {
            gc.setFill(f.getColor());
            gc.fillRect(f.getX(),f.getY(),size,size);
        }

        gc.setFill(players.get(0).getColor());
        gc.fillRect(players.get(0).getCurrentField().getX()+5,players.get(0).getCurrentField().getY()+1,5,5);
        gc.setFill(players.get(1).getColor());
        gc.fillRect(players.get(1).getCurrentField().getX()+5,players.get(1).getCurrentField().getY()+7,5,5);

    }
}
