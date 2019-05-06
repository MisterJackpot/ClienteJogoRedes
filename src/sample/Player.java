package sample;

import javafx.scene.paint.Color;

public class Player {
    private FieldVisual currentField;
    private int id;
    private Color color;
    private int offset;
    private String name;

    public Player(int id) {
        this.id = id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldVisual getCurrentField() {
        return currentField;
    }

    public void setCurrentField(FieldVisual currentField) {
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
