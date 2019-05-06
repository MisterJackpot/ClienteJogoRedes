package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import server.components.Field;
import server.components.FieldType;

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

    @FXML
    Label p1Nick;

    @FXML
    Label p2Nick;

    @FXML
    Label p1Field;

    @FXML
    Label p2Field;

    Random r;
    Connection connection;
    static boolean myTurn;

    ArrayList<FieldVisual> fields;
    static ArrayList<Player> players;
    ArrayList<Field> list;
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
            p1.setOffset(3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        players.add(p1);


        try {
            list = (ArrayList<Field>)connection.is.readObject();
            System.out.println(list);
            getBoard(list);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList> ps = null;
        try{
            ps = (ArrayList) connection.is.readObject();
            for (ArrayList<String> a: ps) {
                if(Integer.valueOf(a.get(0)) == p1.getId()){
                    p1.setName(a.get(1));
                }else{
                    Player p2 = new Player(Integer.valueOf(a.get(0)));
                    p2.setName(a.get(1));
                    p2.setColor(Color.BLUEVIOLET);
                    p2.setOffset(9);
                    p2.setCurrentField(fields.get(0));
                    players.add(p2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        p1.setCurrentField(fields.get(0));

        drawMap();

        p1Nick.setText(p1.getName());
        p2Nick.setText(players.get(1).getName());
        p1Field.setText("0");
        p2Field.setText("0");

        diceBtn.setDisable(true);

        Thread thread = new Thread(() -> game());

        thread.start();
    }

    private void game() {

        do {
            String turn = null;
            try {
                turn = (String) connection.is.readObject();
                if (turn.equals("Your Turn")) {
                    myTurn = true;
                    diceBtn.setDisable(false);
                }else if(turn.equals("Update Position")){
                    ArrayList<Integer> aux = (ArrayList<Integer>) connection.is.readObject();
                    for (int i = 0; i < 4; i+=2) {
                        if(aux.get(i) == players.get(0).getId()){
                            FieldVisual f = players.get(0).getCurrentField();
                            players.get(0).setCurrentField(fields.get(aux.get(i+1)));
                            redraw(players.get(0),f);
                        }else{
                            FieldVisual f = players.get(1).getCurrentField();
                            players.get(1).setCurrentField(fields.get(aux.get(i+1)));
                            redraw(players.get(1),f);
                        }
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            }


        } while (true) ;
    }

    public void redraw(Player p1, FieldVisual f){
        gc = board.getGraphicsContext2D();
        gc.setFill(f.getColor());
        gc.fillRect(f.getX()+5,f.getY()+p1.getOffset(),5,5);

        gc.setFill(p1.getColor());
        gc.fillRect(p1.getCurrentField().getX()+5,p1.getCurrentField().getY()+p1.getOffset(),5,5);
    }


    private void getBoard(ArrayList<Field> aux){
        FieldVisual field = new FieldVisual(aux.get(0).getType());
        field.setX(0);
        field.setY(0);
        fields.add(field);
        for (int i = 1; i < 9; i++) {
            field = new FieldVisual(aux.get(i).getType());
            field.setX(fields.get(i-1).getX() + 15);
            field.setY(0);
            fields.add(field);
        }
        field = new FieldVisual(aux.get(9).getType());
        field.setX(fields.get(8).getX());
        field.setY(15);
        fields.add(field);
        field = new FieldVisual(aux.get(10).getType());
        field.setX(fields.get(8).getX());
        field.setY(30);
        fields.add(field);
        for (int i = 11; i < 19; i++) {
            field = new FieldVisual(aux.get(i).getType());
            field.setX(fields.get(i-1).getX() - 15);
            field.setY(30);
            fields.add(field);
        }
        field = new FieldVisual(aux.get(19).getType());
        field.setX(fields.get(fields.size()-1).getX());
        field.setY(45);
        fields.add(field);
        field = new FieldVisual(aux.get(20).getType());
        field.setX(fields.get(fields.size()-1).getX());
        field.setY(60);
        fields.add(field);
        for (int i = 21; i < 30; i++) {
            field = new FieldVisual(aux.get(i).getType());
            field.setX(fields.get(i-1).getX() + 15);
            field.setY(60);
            fields.add(field);
        }
        field = new FieldVisual(aux.get(31).getType());
        field.setX(fields.get(fields.size()-1).getX());
        field.setY(75);
        fields.add(field);
    }

    @FXML
    private void rollDice(ActionEvent event) {
        diceBtn.setDisable(true);
        int dice = r.nextInt(6)+1;

        System.out.println(dice);


        diceValue.setText(String.valueOf(dice));

        try {
            connection.os.writeObject(dice);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void drawMap(){
        gc = board.getGraphicsContext2D();
        r = new Random();
        player = players.get(0);
        double x = board.getWidth();
        double y = board.getHeight();
        gc.setLineWidth(5);
        for (FieldVisual f: fields) {
            gc.setFill(f.getColor());
            gc.fillRect(f.getX(),f.getY(),size,size);
        }

        gc.setFill(player.getColor());
        gc.fillRect(player.getCurrentField().getX()+5,player.getCurrentField().getY()+player.getOffset(),5,5);

        gc.setFill(players.get(1).getColor());
        gc.fillRect(players.get(1).getCurrentField().getX()+5,players.get(1).getCurrentField().getY()+players.get(1).getOffset(),5,5);
    }
}
