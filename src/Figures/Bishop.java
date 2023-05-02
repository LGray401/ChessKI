package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(boolean isblack, int position) {
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, -54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49)));
    }
}
