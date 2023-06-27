package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Figure {



    public Rook (boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(50);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, -1, -2, -3, -4, -5, -6, -7, -8, -16, -24, -32, -40, -48, -56, 8, 16, 24, 32, 40, 48, 57)));
        if(isblack ? (position == 56 || position == 63) : (position == 0 || position == 7)) {
            this.setHasMoved(false);
        } else {
            this.setHasMoved(true);
        }
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(this.movingNorth(board));
        list.addAll(this.movingSouth(board));
        list.addAll(this.movingEAST(board));
        list.addAll(this.movingWEST(board));

        this.setPossibleMoveList(list);


    }

    @Override
    public int getTypeAsInt() {
        return 1;
    }




}
