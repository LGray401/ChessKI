package Figures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import Main.Board;

public abstract class Figure {

    private boolean isEmptyField;
    private int value;
    private boolean isBlack;
    private int position;
    private int nextPosition;
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

    public ArrayList<String> getAllMovesInFenNotation() {
        return allMovesInFenNotation;
    }

    public void setAllMovesInFenNotation(ArrayList<String> allMovesInFenNotation) {
        this.allMovesInFenNotation = allMovesInFenNotation;
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

    public void concatenatePossibleMoveList(ArrayList<Integer> possibleMoveList){
        getPossibleMoveList().addAll(possibleMoveList);
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

    public void calculatePossibleMoves(Board board) {

    }

    public boolean withInPossibleRange(int i){
        if (i >= 0 && i <= 63) return true;
        return false;
    }

    public ArrayList<Integer> movingEAST(Board board) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() + i;
            if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            int newPos = this.getPosition() - i;
            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public ArrayList<Integer> movingSN(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() + i*8;
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public List<Integer> movingNS(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() - i*8;
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public ArrayList<Integer> movingNE(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() + i*9;
            if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public ArrayList<Integer> movingNW(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() - i*7;
            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public ArrayList<Integer> movingSE(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() - i*7;
            if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                list.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
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

    public List<Integer> movingSW(Board board) {

        int multiplier = (this instanceof King) ? 1 : 7;

        ArrayList<Integer> southWestMoveList = new ArrayList<>();

        for (int i = 0; i < multiplier; i ++){
            int newPos = this.getPosition() - i*9;
            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                southWestMoveList.add(newPos);
                break;
            }
            if (newPos == this.getPosition()) continue;
            if (withInPossibleRange(newPos)){
                if (board.getBoard()[newPos].isEmptyField()) {
                    southWestMoveList.add(newPos);
                } else if (board.getBoard()[newPos].isBlack() != this.isBlack()){
                    southWestMoveList.add(newPos);
                    break;
                } else if (board.getBoard()[newPos].isBlack() == this.isBlack()) {
                    break;
                }
            }
        }
        return southWestMoveList;
    }

    public String convertFigureToFEN(Figure myFigure){
        String result = "";
        switch (myFigure.getClass().getSimpleName()){
            case("King"): result += "k"; break;
            case("Queen"): result += "q"; break;
            case("Bishop"): result += "b"; break;
            case("Knight"): result += "n"; break;
            case("Pawn"): result += "p"; break;
            case("Rook"): result += "r"; break;
        }
        if (myFigure.isBlack()){
            result = result.toUpperCase(Locale.ROOT);
        }
        return result;
    }

    public String convertPosToRow(int newPos){
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

    public String convertPosToLine(int newPos){
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
}
