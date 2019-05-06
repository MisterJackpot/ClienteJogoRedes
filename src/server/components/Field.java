package server.components;


import java.io.Serializable;

public class Field implements Serializable {

    private static final long serialVersionUID = 7905074695125152681L;

    private FieldType type;
    private int value;

    public Field(FieldType type) {
        this.type = type;
    }

    public FieldType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int numberOfFields) {
        this.value = numberOfFields;
    }

}