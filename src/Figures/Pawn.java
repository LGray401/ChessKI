package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Figure {
    public Pawn(boolean isblack, int position) {
        this.setEmptyField(false);
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(7,8,9,16)));

    }

    @Override
    public void calculatePossibleMoves(Board board) {
        this.getPossibleMoveList().clear();
        int direction = this.isBlack() ? -1 : 1;
        int startPos = this.getPosition() + direction * 8;

        // Forward movement (without capturing)
        if (withInPossibleRange(startPos) && board.getBoard()[startPos].isEmptyField()) {
            this.getPossibleMoveList().add(startPos);
            this.checkAndPromote(startPos, board);

            // Double forward movement for pawns at starting positions
            int doubleMovePos = this.getPosition() + direction * 16;
            if ((!this.isBlack() && this.getPosition() >= 8 && this.getPosition() <= 15)
                    || (this.isBlack() && this.getPosition() >= 48 && this.getPosition() <= 55)) {
                if (withInPossibleRange(doubleMovePos) && board.getBoard()[doubleMovePos].isEmptyField()) {
                    this.getPossibleMoveList().add(doubleMovePos);
                }
            }
        }

        // Capturing movements
        int[] captureOffsets = {7, 9};
        for (int offset : captureOffsets) {
            int capturePos = this.getPosition() + direction * offset;
            if (withInPossibleRange(capturePos)) {
                Figure targetFigure = board.getBoard()[capturePos];
                if (!targetFigure.isEmptyField() && targetFigure.isBlack() != this.isBlack()) {
                    this.getPossibleMoveList().add(capturePos);
                    this.checkAndPromote(startPos, board);
                }
            }
        }


    }
    private void checkAndPromote(int targetPosition, Board board) {
        if (!this.isBlack() && targetPosition >= 56 && targetPosition <= 63
                || this.isBlack() && targetPosition >= 0 && targetPosition <= 7) {
            promote(board);
        }
    }

    private void promote(Board board) {
        Queen promotedQueen = new Queen(this.isBlack(), this.getPosition());
        board.getBoard()[this.getPosition()] =  promotedQueen;
    }
    // (withInPossibleRange method from Figure class)
}
