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
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(10, 6, 15, 17, -6, -15, -17, -10)));
    }
}
