package Figures;
// import Figures.Figure;

import helpers.MovesList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen  extends Figure{

    public Queen(boolean isblack, int position, int nextmove) {
        this.value = 90;
        this.isBlack = isblack;
        this.position = position;
        this.nextMove = nextmove;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,-1,-2,-3,-4,-5,-6,-7,-8,-16,-24,-32,-40,-48,-56, 8, 16, 24, 32, 40, 48, 56, // up, down, left, right
                9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, - 54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49)); // diagonal
    }



    @Override
    public List<Figure> calculatePossibleMoves() {
        MovesList nextPossibleMoves = new MovesList();

        for(int move: possibleMoves){
            nextPossibleMoves.addMove(new Queen(isBlack, position, position + move));
        }

        return nextPossibleMoves;
    }
}
