package helpers;

import Figures.Figure;

import java.util.ArrayList;

public class MovesList extends ArrayList<Figure> {

    public MovesList() {
        super();
    }


    public boolean addMove(Figure figure) {

        if(figure.nextMove >= 0 && figure.nextMove < 64) {
            this.add(figure);
            return true;
        }
        return false;

    }

}
