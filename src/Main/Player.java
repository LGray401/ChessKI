package Main;

import Figures.Figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {

    private boolean isBlack;

    private int winPossibility;
    private List<Figure> figureList;
    private boolean playerInCheck;
    private Figure nextFigureMove;
    private final long MAX_DURATION = 1000; // maximum duration
    private static final int MAX_DEPTH = 3;


    private ArrayList<String> allMovesInFenNotation;
    private int examinedPositions = 0; // number of examined positions
    public int getExaminedPositions() {
        return examinedPositions;
    }

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

    public int getWinPossibility() {
        return winPossibility;
    }

    public void setWinPossibility(int winPossibility) {
        this.winPossibility = winPossibility;
    }

    

    private void generateAllMoves(Board board){

        ArrayList<String> list = new ArrayList<>();

        for (Figure figure: this.getFigureList()) {
            figure.calculatePossibleMoves(board);
            figure.removeIllegalMoves(board);
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

        long startTime = System.currentTimeMillis();

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
        } while (f.getPossibleMoveList().size() == 0 && !isExceededMaxDuration(startTime));

        return f;
    }

    private boolean isExceededMaxDuration(long startTime) {
        return (System.currentTimeMillis() - startTime) > MAX_DURATION;
    }

    int evaluate(boolean isBlack, Board board) {

        int eval = 0;

        for(int i = 0; i < board.getBoard().length - 1; i++) {
            if(isBlack == isBlack) {
                int ownValue = board.getBoard()[i].getValue();
                eval += ownValue;
            } else {
                int enemyValue = board.getBoard()[i].getValue();
                eval -= enemyValue;
            }
        }

        this.setWinPossibility(eval);
        return eval;
    }

    public int minimax(Board board, int depth, boolean isMaximizingPlayer) {
        examinedPositions++;
        if (depth == 0 || board.isGameOver(this.isBlack()).isGameFinished()) {
            EndOfGame endOfGame = board.isGameOver(this.isBlack());
            if (endOfGame.isGameFinished()) {
                return endOfGame.getValue();
            }else {
                return evaluate(this.isBlack, board);
            }
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for(Board child : board.getChildren(this.isBlack())) {
                int eval = minimax(child, depth - 1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            for(Board child : board.getChildren(!this.isBlack)) {
                int eval = minimax(child, depth - 1, true);
                minEval = Math.min(minEval, eval);
            }

            return minEval;
        }
    }

    public Figure findBestMove(Board board) {
        int maxEval = Integer.MIN_VALUE;
        Figure bestMove = null;
        List<Figure> legalMoves = board.getValidMoves(this.isBlack());
        
        for (Figure figureMove: legalMoves) {
            for(int move: figureMove.getPossibleMoveList()) {
                examinedPositions++;
                Board newBoard = new Board(board);
                newBoard.simulateMove(figureMove.copy(), move);
                int eval = minimax(newBoard, MAX_DEPTH, false);

                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = figureMove;
                    bestMove.setPosition(move);
                }
            }
        }

            if (bestMove == null) {
                if (board.isPlayerInCheck(this.isBlack())) {
                    board.playerWon(!this.isBlack());
                } else {
                    board.itsADraw("Stalemate");
                }
            }


        return bestMove;
    }
}
