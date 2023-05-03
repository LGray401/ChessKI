package Figures;


import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Figure{

    private static final ArrayList<Integer> eastEastBarrier = new ArrayList<>(Arrays.asList(1, 9, 17, 25, 33, 41, 49, 57));
    private static final ArrayList<Integer> westWestBarrier = new ArrayList<>(Arrays.asList(6, 14, 22, 30, 38, 46, 54, 62));
    private static final ArrayList<Integer> northNorthBarrier = new ArrayList<>(Arrays.asList(48, 49, 50, 51, 52, 53, 54, 55));
    private static final ArrayList<Integer> southSouthBarrier = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15));
    private static final ArrayList<Integer> knightMovingAdd = new ArrayList<>(Arrays.asList(10, 6, 15, 17, -6, -15, -17, -10));

    public Knight(boolean isblack, int position) {

        this.setEmptyField(false);
        this.setValue(30);
        this.setBlack(isblack);
        this.setPosition(position);
        this.setMoveSummandList(new ArrayList<>(Arrays.asList(17, 10, -6, -15, -17, -10, 6, 15)));
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(moving1Uhr(board));
        list.addAll(moving2Uhr(board));
        list.addAll(moving4Uhr(board));
        list.addAll(moving5Uhr(board));
        list.addAll(moving7Uhr(board));
        list.addAll(moving8Uhr(board));
        list.addAll(moving10Uhr(board));
        list.addAll(moving11Uhr(board));
        //list.remove(this.getPosition());
        this.setPossibleMoveList(list);
    }

    public List<Integer> moving1Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving2Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving4Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving5Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving7Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving8Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving10Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }

    public List<Integer> moving11Uhr(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        return list;
    }
}
