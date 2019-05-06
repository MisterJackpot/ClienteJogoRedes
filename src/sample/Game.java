package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

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
    Connection connection;
    boolean myTurn;

    ArrayList<Field> fields;
    ArrayList<Player> players;
    int size = 15;
    GraphicsContext gc;
    Player player;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = Controller.connection;
        fields = new ArrayList<>();
        players = new ArrayList<>();
        myTurn = false;

        Player p1 = null;
        try {
            p1 = new Player((Integer)connection.is.readObject());
            p1.setColor(Character.c);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        players.add(p1);




        diceBtn.setDisable(true);

    }

    private void getBoard(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Field field = new Field(FieldType.START);
                if(j%2 == 0) {
                    if (i % 2 == 0) {
                        field.setColor(Color.YELLOW);
                    } else {
                        field.setColor(Color.PURPLE);
                    }
                }else{
                    if(i%2 == 0){
                        field.setColor(Color.PURPLE);
                    }else{
                        field.setColor(Color.YELLOW);
                    }
                }
                field.setX(j*size);
                field.setY(i*size);
                fields.add(field);
                field.setValue(fields.indexOf(field));
            }
        }
    }

    @FXML
    private void rollDice(ActionEvent event) {
        diceBtn.setDisable(true);
        int dice = r.nextInt(6)+1;

        System.out.println(dice);

        diceValue.setText(String.valueOf(dice));

        Player p1;
        if(player.getId() == 1){
            p1 = player;
            player = players.get(1);
        }else{
            p1 = player;
            player = players.get(0);
        }

        gc.setFill(p1.getCurrentField().getColor());
        gc.fillRect(p1.getCurrentField().getX()+5,p1.getCurrentField().getY()+p1.getOffset(),5,5);



        if(p1.getCurrentField().getValue() + dice >= fields.size()){
            p1.setCurrentField(fields.get(fields.size()-1));
            System.out.println("WIN");
        }else {
            p1.setCurrentField(fields.get(p1.getCurrentField().getValue() + dice));
        }

        gc.setFill(p1.getColor());
        gc.fillRect(p1.getCurrentField().getX()+5,p1.getCurrentField().getY()+p1.getOffset(),5,5);

    }

    private void drawMap(){
        gc = board.getGraphicsContext2D();
        r = new Random();
        player = players.get(0);
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
