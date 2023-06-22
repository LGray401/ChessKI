package Main;

import Figures.Figure;
import Helpers.Stopwatch;
import Helpers.TranspositionTableEntry;
import Helpers.ZobristHashCreator;

import java.util.*;

public class Player {

    private boolean isBlack;

    private int winPossibility;
    private List<Figure> figureList;
    private boolean playerInCheck;
    private Figure nextFigureMove;
    private ArrayList<String> allMovesInFenNotation;
    public ArrayList<String> getAllMovesInFenNotation() {
        return allMovesInFenNotation;
    }
    private HashMap<Long, TranspositionTableEntry> transpositionTable = new HashMap<>();
    private final long MAX_DURATION = 1000; // maximum duration
    private List<Integer> sortedMovesListPerFigure;

    public List<Integer> getSortedMovesListPerFigure() {
        return sortedMovesListPerFigure;
    }

    public void setSortedMovesListPerFigure(List<Integer> sortedMovesListPerFigure) {
        this.sortedMovesListPerFigure = sortedMovesListPerFigure;
    }

    public void setAllMovesInFenNotation(ArrayList<String> allMovesInFenNotation) { this.allMovesInFenNotation = allMovesInFenNotation; }

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

    public int evaluate(boolean isBlack, Board board) {

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



    private void generateAllMovesFEN(Board board){

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
        this.generateAllMovesFEN(board);
    }

    public Integer amountOfLegalMovesGivenBoard(Board board){

        this.createFigureListForPlayer(board);
        this.generateAllMovesFEN(board);
        return this.getAllPossibleMovesPlayer(board).size();

    }

    public ArrayList<String> getAllMovesInFENNotationGivenBoard(Board board){

        this.createFigureListForPlayer(board);
        this.generateAllMovesFEN(board);
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
        this.generateAllMovesFEN(board);
        for (Figure figure: this.getFigureList()) {
            figure.removeIllegalMoves(board);
        }
        Figure f;
        do {
             f = this.getFigureList().get((int) (Math.random() * this.getFigureList().size()));
/*             if(this.getAllPossibleMovesPlayer(board).size() == 0) {
                 if (board.isPlayerInCheck(this.isBlack())) {
                     board.playerWon(!isBlack());
                 }
                 else {
                     board.itsADraw("Stalemate");
                 }

             }*/
        } while (f.getPossibleMoveList().size() == 0 && !isExceededMaxDuration(startTime));

        return f;
    }

    private boolean isExceededMaxDuration(long startTime) {
        return (System.currentTimeMillis() - startTime) > MAX_DURATION;
    }

    Stopwatch stopwatchGetChildren = new Stopwatch("getChildren");
    Stopwatch stopwatchGameOver = new Stopwatch("GameOver");
    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {

        long zobristHash = ZobristHashCreator.calculateZobristHash(board.getBoard(), maximizingPlayer);

        TranspositionTableEntry entry = transpositionTable.get(zobristHash);
        if (entry != null && entry.getDepth() >= depth) {
            return entry.getScore();
        }
        stopwatchGameOver.start();
        if (depth == 0 || board.isGameOver(this.isBlack()).isGameFinished()) {
            EndOfGame endOfGame = board.isGameOver(this.isBlack());
            if (endOfGame.isGameFinished()) {
                stopwatchGameOver.stop();
                stopwatchGameOver.cumulativeElapsedTime();
                return endOfGame.getValue();
            }else {
                stopwatchGameOver.stop();
                stopwatchGameOver.cumulativeElapsedTime();
                return evaluate(this.isBlack(), board);
            }

        }
        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board child : board.getChildren(this.isBlack, stopwatchGetChildren)) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }

            }
            transpositionTable.put(zobristHash, new TranspositionTableEntry(maxEval, depth));
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Board child : board.getChildren(!this.isBlack, stopwatchGetChildren)) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            transpositionTable.put(zobristHash, new TranspositionTableEntry(minEval, depth));
            return minEval;
        }
    }

    public Figure makeAlphaBeta(Board board) {


        int maxDepth = 1;
        Figure bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        final long startTime = System.currentTimeMillis();
        final long maxTime = 100000;
        while (maxTime - (System.currentTimeMillis() - startTime) > (maxDepth*maxDepth*maxDepth*maxDepth) *1000) {
            for (Figure figure : board.getValidMoves(this.isBlack())) {
                for (int nextMove : figure.getPossibleMoveList()) {

                    Board newBoard = new Board(board);
                    newBoard.simulateMove(figure.copy(), nextMove);
                    int score = alphaBeta(newBoard, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = figure;
                        bestMove.setNextPosition(nextMove);
                    }
                }

            }
            maxDepth++;
        }
        System.out.println("Depth: " + maxDepth);
        System.out.println("Size of Transposition Table: " + transpositionTable.size());
        return bestMove;
    }


}
