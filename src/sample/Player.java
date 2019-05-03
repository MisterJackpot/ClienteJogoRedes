package sample;

import javafx.scene.paint.Color;

public class Player {
    private Field currentField;
    private int id;
    private Color color;

    public Player(int id) {
        this.id = id;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
