package helpers;

import Figures.Figure;

import java.util.ArrayList;

public class MovesList extends ArrayList<Figure> {

    public MovesList() {
        super();
    }


    public boolean addMove(Figure figure) {

        if(figure.nextPosition >= 0 && figure.nextPosition < 64) {
            this.add(figure);
            return true;
        }
        return false;

    }

}
