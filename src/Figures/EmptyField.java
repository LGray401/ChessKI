package Figures;

public class EmptyField extends Figure{

    public static EmptyField getEmptyField() {
        return emptyField;
    }

    public static void setEmptyField(EmptyField emptyField) {
        EmptyField.emptyField = emptyField;
    }

    static EmptyField emptyField;

    public EmptyField() {
        this.value = 0;
    }
}
