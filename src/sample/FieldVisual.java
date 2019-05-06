package sample;


import javafx.scene.paint.Color;
import server.components.FieldType;

import java.util.ArrayList;

public class FieldVisual {
    private Color color;
    private FieldType type;
    private ArrayList<Integer> playersInField;

    private double x;
    private double y;
    private int value;

    public FieldVisual(FieldType type) {
        this.type = type;
        switch (type){
            case START:
                this.color = Color.BLACK;
                break;
            case FINISH:
                this.color = Color.BLACK;
                break;
            case BACKWARD:
                this.color = Color.RED;
                break;
            case FOWARD:
                this.color = Color.AQUA;
                break;
            case JOKE:
                this.color = Color.LIGHTPINK;
                break;
            case AGAIN:
                this.color = Color.YELLOWGREEN;
                break;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}


