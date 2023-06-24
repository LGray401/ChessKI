package Figures;

import Main.Board;
import Main.Player;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Figure {

    private boolean isEmptyField;
    private int value;
    private boolean isBlack;
    private int position;
    private int nextPosition;
    boolean hasMoved = false;
    private ArrayList<Integer> illegalMovesList;
    private ArrayList<Integer> moveSummandList;
    private ArrayList<Integer> possibleMoveList;
    private ArrayList<String> allMovesInFenNotation;
    private static final ArrayList<Integer> eastBarrier = new ArrayList<>(Arrays.asList(7, 15, 23, 31, 39, 47, 55, 63));
    private static final ArrayList<Integer> westBarrier = new ArrayList<>(Arrays.asList(0, 8, 16, 24, 32, 40, 48, 56));
    private static final ArrayList<Integer> northBarrier = new ArrayList<>(Arrays.asList(56, 57, 58, 59, 60, 61, 62, 63));
    private static final ArrayList<Integer> southBarrier = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15));
    private static final ArrayList<Integer> westWestBarrier = new ArrayList<>(Arrays.asList(1, 9, 17, 25, 33, 41, 49, 57));
    private static final ArrayList<Integer> eastEastBarrier = new ArrayList<>(Arrays.asList(6, 14, 22, 30, 38, 46, 54, 62));
    private static final ArrayList<Integer> northNorthBarrier = new ArrayList<>(Arrays.asList(48, 49, 50, 51, 52, 53, 54, 55));
    private static final ArrayList<Integer> southSouthBarrier = new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15));

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    public ArrayList<String> getAllMovesInFenNotation() {
        return allMovesInFenNotation;
    }

    public void setAllMovesInFenNotation(ArrayList<String> allMovesInFenNotation) {
        this.allMovesInFenNotation = allMovesInFenNotation;
    }

    public ArrayList<Integer> getIllegalMovesList() {
        return illegalMovesList;
    }

    public void setIllegalMovesList(ArrayList<Integer> illegalMovesList) {
        this.illegalMovesList = illegalMovesList;
    }

    public static ArrayList<Integer> getEastBarrier() {
        return eastBarrier;
    }

    public static ArrayList<Integer> getWestBarrier() {
        return westBarrier;
    }

    public static ArrayList<Integer> getNorthBarrier(){
        return northBarrier;
    }

    public static ArrayList<Integer> getSouthBarrier(){
        return southBarrier;
    }

    public static ArrayList<Integer> getEastEastBarrierBarrier() {
        return eastEastBarrier;
    }

    public static ArrayList<Integer> getWestWestBarrier(){
        return westWestBarrier;
    }

    public static ArrayList<Integer> getNorthNorthBarrier(){
        return northNorthBarrier;
    }

    public static ArrayList<Integer> getSouthSouthBarrier(){
        return southSouthBarrier;
    }

    public ArrayList<Integer> getPossibleMoveList() {
        return possibleMoveList;
    }

    public void setPossibleMoveList(ArrayList<Integer> possibleMoveList) {
        this.possibleMoveList = possibleMoveList;
    }

    public boolean isEmptyField() {
        return isEmptyField;
    }

    public void setEmptyField(boolean emptyField) {
        isEmptyField = emptyField;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(int nextPosition) {
        this.nextPosition = nextPosition;
    }

    public ArrayList<Integer> getMoveSummandList() {
        return moveSummandList;
    }

    public void setMoveSummandList(ArrayList<Integer> moveSummandList) {
        this.moveSummandList = moveSummandList;
    }

    public Figure() {
        this.setPossibleMoveList(new ArrayList<>());
        this.setAllMovesInFenNotation(new ArrayList<>());
    }

    public void concatenatePossibleMoveList(ArrayList<Integer> possibleMoveList){
        getPossibleMoveList().addAll(possibleMoveList);
    }



    public void calculatePossibleMoves(Board board) {

    }

    public abstract int getTypeAsInt();

    public boolean withInPossibleRange(int i){
        if (i >= 0 && i <= 63) return true;
        return false;
    }

    public ArrayList<Integer> movingEAST(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            if (getEastBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() + i;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos] instanceof EmptyField) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getEastBarrier().contains(newPos)) {
                    list.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;

                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingWEST(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            if (getWestBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() - i;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos].isEmptyField()) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getWestBarrier().contains(newPos)) {
                    list.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingNorth(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            //if (getNorthBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() + i*8;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos].isEmptyField()) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public List<Integer> movingSouth(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            //if (getSouthBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() - i*8;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos].isEmptyField()) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingNE(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < multiplier; i ++){

            if (getEastBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() + i*9;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos] instanceof EmptyField) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getEastBarrier().contains(newPos)) {
                    list.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingNW(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            if (getWestBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() + i*7;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos] instanceof EmptyField) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getWestBarrier().contains(newPos)) {
                    list.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> movingSE(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            if (getEastBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() - i*7;

            if (withInPossibleRange(newPos)){
                if (!(board.getBoard()[newPos] instanceof EmptyField) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getEastBarrier().contains(newPos)) {
                    list.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    list.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    list.add(newPos);
                    break;
                }
            }
        }
        return list;
    }

    public List<Integer> movingSW(Board board) {

        int multiplier = (this instanceof King) ? 2 : 8;

        ArrayList<Integer> southWestMoveList = new ArrayList<>();

        for (int i = 1; i < multiplier; i ++){

            if (getWestBarrier().contains(this.getPosition())) break;

            int newPos = this.getPosition() - i*9;

            if (withInPossibleRange(newPos)){

                if (!(board.getBoard()[newPos] instanceof EmptyField) && board.getBoard()[newPos].isBlack() == this.isBlack()) break;

                if (getWestBarrier().contains(newPos)) {
                    southWestMoveList.add(newPos);
                    break;
                }

                if (board.getBoard()[newPos].isEmptyField()) {
                    southWestMoveList.add(newPos);

                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    southWestMoveList.add(newPos);
                    break;
                }
            }
        }
        return southWestMoveList;
    }

    private String convertFigureToFEN(Figure myFigure){
        String result1 = "";
        switch (myFigure.getClass().getSimpleName()){
            case("King"): result1 += "k"; break;
            case("Queen"): result1 += "q"; break;
            case("Bishop"): result1 += "b"; break;
            case("Knight"): result1 += "n"; break;
            case("Pawn"): result1 += "p"; break;
            case("Rook"): result1 += "r"; break;
        }
        String result2 = result1.toUpperCase();
        if (myFigure.isBlack()){
            return result1;
        } else return result2;
    }

    private String convertPosToRow(int newPos){
        String result = "";

        if ((newPos % 8 - 0) == 0|| newPos == 0) result += "a";
        if ((newPos % 8 - 1) == 0|| newPos == 1) result += "b";
        if ((newPos % 8 - 2) == 0|| newPos == 2) result += "c";
        if ((newPos % 8 - 3) == 0|| newPos == 3) result += "d";
        if ((newPos % 8 - 4) == 0|| newPos == 4) result += "e";
        if ((newPos % 8 - 5) == 0|| newPos == 5) result += "f";
        if ((newPos % 8 - 6) == 0|| newPos == 6) result += "g";
        if ((newPos % 8 - 7) == 0|| newPos == 7) result += "h";


        return result;
    }

    private String convertPosToLine(int newPos){
        String result = "";

        if (newPos >= 56) {
            result += "8";
        } else if (newPos >= 48) {
            result += "7";
        } else if (newPos >= 40) {
            result += "6";
        } else if (newPos >= 32) {
            result += "5";
        } else if (newPos >= 24) {
            result += "4";
        } else if (newPos >= 16) {
            result += "3";
        } else if (newPos >= 8) {
            result += "2";
        } else if (newPos >= 0) {
            result += "1";
        }


        return result;
    }

    public String convertMoveToFEN(Figure myFigure, int newPos){

        String result = "";
        result += this.convertFigureToFEN(myFigure);
        result += this.convertPosToRow(newPos);
        result += this.convertPosToLine(newPos);

        return result;
    }

    public void convertAllMovesInFENNotation(){

        ArrayList<String> list = new ArrayList<>();

        for (Integer newPos: this.getPossibleMoveList()) {
            list.add(convertMoveToFEN(this, newPos));
        }

        this.setAllMovesInFenNotation(list);
    }

    public Figure copy() {
        // Create a new instance of the same class as the current instance
        Figure copiedFigure = null;
        if(this instanceof EmptyField) {
            try {
                Constructor<? extends Figure> constructor = this.getClass().getConstructor(int.class);
                copiedFigure = constructor.newInstance(this.getPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Constructor<? extends Figure> constructor = this.getClass().getConstructor(boolean.class, int.class);
                copiedFigure = constructor.newInstance(this.isBlack(), this.getPosition());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Assuming all properties have proper getter and setter methods
            copiedFigure.setValue(this.getValue());
            copiedFigure.setNextPosition(this.getNextPosition());
            copiedFigure.setHasMoved(this.hasMoved());
            copiedFigure.setMoveSummandList(new ArrayList<>(this.getMoveSummandList()));
            copiedFigure.setPossibleMoveList(new ArrayList<>(this.getPossibleMoveList()));
            copiedFigure.setAllMovesInFenNotation(new ArrayList<>(this.getAllMovesInFenNotation()));
        }

        return copiedFigure;
    }

    public boolean canAttack(Figure target) {
        return this.getPossibleMoveList().contains(target.getPosition());
    }

    public void removeIllegalMoves(Board board) {

        ArrayList<Integer> illegalMoves = new ArrayList<>();
        for (Integer move : this.getPossibleMoveList()) {
            Board tempBoard = board.copy();

            tempBoard = tempBoard.simulateMove(this.copy(), move);

            if (tempBoard.isPlayerInCheck(this.isBlack())) {
                illegalMoves.add(move);
            }
        }

        this.setIllegalMovesList(illegalMoves);

        ArrayList<Integer> legalMoves = this.getPossibleMoveList();

        legalMoves.removeAll(this.getIllegalMovesList());

        this.setPossibleMoveList(legalMoves);
    }

    public Map<Integer, Integer> sortMoves(List<Integer> myMoves, Player player, Board board){

        Map<Integer, Integer> moveEvalPair = new HashMap<>();
        for (Integer move: myMoves) {
            Board newBoard = new Board(board);
            int oldPosition = this.getPosition();
            this.setNextPosition(move);
            int eval1 = player.evaluate(player.isBlack(), newBoard.simulateMove(this.copy(), this.getNextPosition()));

            System.out.println("In SortMoves Evaluation ist: " + eval1);
            //System.out.println(newBoard.to2DArrayAndDisplay(newBoard.getBoard()));
            //System.out.println("Figure: " + this + ", Position: " + oldPosition + ", Next Move: " + this.getNextPosition() + ", Evaluation: " + eval1);

            moveEvalPair.put(move, eval1);
        }

        return moveEvalPair;
    }
}

