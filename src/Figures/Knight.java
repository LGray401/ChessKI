package Figures;


import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Figure{

    public Knight(boolean isblack, int position) {
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(6, 10, 15, 17, 19, -6, -10, -15, -17 - 19)));
    }
}
