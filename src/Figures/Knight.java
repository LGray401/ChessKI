package Figures;


import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Knight extends Figure{

    private static final ArrayList<Integer> knightMovingAdd = new ArrayList<>(Arrays.asList(10, 6, 15, 17, -6, -15, -17, -10));

    public Knight(boolean isblack, int position) {

        this.setEmptyField(false);
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(17, 10, -6, -15, -17, -10, 6, 15)));
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>((Arrays.asList(this.moving1Uhr(board), this.moving2Uhr(board), this.moving4Uhr(board), this.moving5Uhr(board), this.moving7Uhr(board), this.moving8Uhr(board), this.moving10Uhr(board), this.moving11Uhr(board))));
        list.removeIf(Objects::isNull);
        this.setPossibleMoveList(list);
    }

    @Override
    public int pieceSquareTable() {
        int result = 0;
        if ((this.getPosition() == 0) ||this.getPosition() == 7 ||this.getPosition() == 56 ||this.getPosition() == 63){
            result = -5;
        } else if (this.getPosition() == 1){
            result = -4;
        } else if (this.getPosition() == 2) {
            result = -3;
        } else if (this.getPosition() == 3) {
            result = -3;
        }
        return result;
    }

    public Integer moving1Uhr(Board board) {

        int newPos = this.getPosition() + 17;

        if (getEastBarrier().contains(this.getPosition()) || getNorthBarrier().contains(this.getPosition()) || getNorthNorthBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;

    }

    public Integer moving2Uhr(Board board) {

        int newPos = this.getPosition() + 10;

        if (getEastBarrier().contains(this.getPosition()) || getNorthBarrier().contains(this.getPosition()) || getEastEastBarrierBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving4Uhr(Board board) {

        int newPos = this.getPosition() - 6;

        if (getEastBarrier().contains(this.getPosition()) || getSouthBarrier().contains(this.getPosition()) || getEastEastBarrierBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving5Uhr(Board board) {

        int newPos = this.getPosition() - 15;

        if (getEastBarrier().contains(this.getPosition()) || getSouthBarrier().contains(this.getPosition()) || getSouthSouthBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving7Uhr(Board board) {

        int newPos = this.getPosition() - 17;

        if (getWestBarrier().contains(this.getPosition()) || getSouthBarrier().contains(this.getPosition()) || getSouthSouthBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving8Uhr(Board board) {

        int newPos = this.getPosition() - 10;

        if (getWestBarrier().contains(this.getPosition()) || getSouthBarrier().contains(this.getPosition()) || getWestWestBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving10Uhr(Board board) {

        int newPos = this.getPosition() + 6;

        if (getWestBarrier().contains(this.getPosition()) || getNorthBarrier().contains(this.getPosition()) || getWestWestBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }

    public Integer moving11Uhr(Board board) {

        int newPos = this.getPosition() + 15;

        if (getWestBarrier().contains(this.getPosition()) || getNorthBarrier().contains(this.getPosition()) || getNorthNorthBarrier().contains(this.getPosition()) || newPos == this.getPosition() || !withInPossibleRange(newPos)) {
            return null;
        }

        if (board.getBoard()[newPos].isEmptyField()) {
            return newPos;
        } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
            return newPos;
        }
        /*
        else if (board.getBoard()[newPos].isBlack() == this.isBlack()) { return null; }
         */
        return null;
    }
}
