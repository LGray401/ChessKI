package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Figure {

    public King(boolean isblack, int position) {
        this.setValue(900);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, -1, 8, -8)));
    }
}
