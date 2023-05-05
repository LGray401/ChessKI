package Main;

import Figures.Figure;
import Figures.King;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private boolean isBlack;
    private List<Figure> figureList;
    private boolean playerInCheck;
    private Figure nextFigureMove;
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

    public void generateAllMoves(Board board){

        ArrayList<String> list = new ArrayList<>();

        for (Figure figure: this.getFigureList()) {
            figure.calculatePossibleMoves(board);
            figure.convertAllMovesInFENNotation();
            list.addAll(figure.getAllMovesInFenNotation());
            //System.out.println(figure.getAllMovesInFenNotation());
        }
        this.setAllMovesInFenNotation(list);
    }

    void playerGetFigureList(Board board) {

        List<Figure> figureList = new ArrayList<>();

        for (Figure figure : board.getBoard()) {
            if (figure.isEmptyField()) continue;
            if (this.isBlack() == figure.isBlack()) {
                figureList.add(figure);
            }
            this.setFigureList(figureList);
        }
    }

        public Figure makeMove(Board board) {

            this.playerGetFigureList(board);
            this.generateAllMoves(board);
            for (Figure figure: this.getFigureList()) {
                figure.removeIllegalMoves(board);
            }
            Figure f;
            do {
                 f = this.getFigureList().get((int) (Math.random() * this.getFigureList().size()));
            }
            while (f.getAllMovesInFenNotation().size() == 0);
            return f;
        }
}
