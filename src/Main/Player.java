package Main;

import Figures.Figure;
import Figures.Rook;
import Figures.King;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private boolean isBlack;
    private List<Figure> figureList;
    private boolean playerInCheck;
    private Figure nextFigureMove;

    public boolean isLongRochadepossible() {
        return longRochadepossible;
    }

    public void setLongRochadepossible(boolean longRochadepossible) {
        this.longRochadepossible = longRochadepossible;
    }

    public boolean isShortRochadepossible() {
        return shortRochadepossible;
    }

    public void setShortRochadepossible(boolean shortRochadepossible) {
        this.shortRochadepossible = shortRochadepossible;
    }

    private  boolean longRochadepossible;
    private  boolean shortRochadepossible;

    private ArrayList<String> allMovesInFenNotation;

    public ArrayList<String> getAllMovesInFenNotation() {
        return allMovesInFenNotation;
    }

    public void setAllMovesInFenNotation(ArrayList<String> allMovesInFenNotation) {
        this.allMovesInFenNotation = allMovesInFenNotation;
    }

    public Player(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public List<Figure> getFigureList() {
        return figureList;
    }

    public void setFigureList(List<Figure> figureList) {
        this.figureList = figureList;
    }

    public boolean isPlayerInCheck() {
        return playerInCheck;
    }

    public void setPlayerInCheck(boolean playerInCheck) {
        this.playerInCheck = playerInCheck;
    }

    public Figure getNextFigureMove() {
        return nextFigureMove;
    }

    public void setNextFigureMove(Figure nextFigureMove) {
        this.nextFigureMove = nextFigureMove;
    }

    private void generateAllMoves(Board board){

        ArrayList<String> list = new ArrayList<>();

        for (Figure figure: this.getFigureList()) {
            figure.calculatePossibleMoves(board);
            figure.convertAllMovesInFENNotation();
            list.addAll(figure.getAllMovesInFenNotation());
            //System.out.println(figure.getAllMovesInFenNotation());
        }
        this.setAllMovesInFenNotation(list);
    }

    private void createFigureListForPlayer(Board board) {

        List<Figure> figureList = new ArrayList<>();

        for (Figure figure : board.getBoard()) {
            if (figure.isEmptyField()) continue;
            if (this.isBlack() == figure.isBlack()) {
                figureList.add(figure);
            }
            this.setFigureList(figureList);
        }
    }

    public ArrayList<Integer> getAllPossibleMovesPlayer(Board board) {

            //this.setFigureAndMovesListForPlayerGivenBoard(board); this breaks the code because the all possible moves are generated again after illegal moves are removed

            ArrayList<Integer> list = new ArrayList<>();

            for (Figure figure: this.getFigureList()) {
                list.addAll(figure.getPossibleMoveList());
            }
            return list;
    }

    public void setFigureAndMovesListForPlayerGivenBoard(Board board){

        this.createFigureListForPlayer(board);
        this.generateAllMoves(board);
    }

    public Integer amountOfLegalMovesGivenBoard(Board board){

        this.createFigureListForPlayer(board);
        this.generateAllMoves(board);
        return this.getAllPossibleMovesPlayer(board).size();

    }

    public ArrayList<String> getAllMovesInFENNotationGivenBoard(Board board){

        this.createFigureListForPlayer(board);
        this.generateAllMoves(board);
        return this.getAllMovesInFenNotation();

    }

    public void printAllMovesAndAmountOfMovesGivenBoard(Board board){
        String color = (this.isBlack()) ? "Black" : "White";

        System.out.println(color + " has these moves: " + this.getAllMovesInFENNotationGivenBoard(board));
        System.out.println(color + " has " + this.amountOfLegalMovesGivenBoard(board) + " legal moves.");
    }

    public Figure makeMove(Board board) {

        this.createFigureListForPlayer(board);
        this.generateAllMoves(board);
        for (Figure figure: this.getFigureList()) {
            figure.removeIllegalMoves(board);
        }
        Figure f;
        do {
             f = this.getFigureList().get((int) (Math.random() * this.getFigureList().size()));
             if(this.getAllPossibleMovesPlayer(board).size() == 0) {
                 if (board.isPlayerInCheck(this.isBlack())) {
                     board.playerWon(!isBlack());
                 }
                 else {
                     board.itsADraw("Stalemate");
                 }

             }
        } while (f.getPossibleMoveList().size() == 0);

        if (f instanceof King){
            ((King) f).setAlreadyMoved(true);
        } else if (f instanceof Rook){
            ((King) f).setAlreadyMoved(true);
        }
        return f;
    }

}
