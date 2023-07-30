package Main;

import Figures.Figure;
import Helpers.LimitedSizeMap;
import Helpers.Stopwatch;
import Helpers.TranspositionTableEntry;
import Helpers.ZobristHashCreator;

import java.util.ArrayList;
import java.util.List;
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

    private LimitedSizeMap<Long, TranspositionTableEntry> transpositionTable = new LimitedSizeMap<>();

    private final long MAX_DURATION = 1000; // maximum duration

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
    Stopwatch stopwatchCutOff = new Stopwatch("CutOff");
    Stopwatch stopwatchAlphaBeta = new Stopwatch("AlphaBeta erste Hälfte");
    private int alphaBeta(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        stopwatchAlphaBeta.start();
        long zobristHash = ZobristHashCreator.calculateZobristHash(board.getBoard(), maximizingPlayer);

        TranspositionTableEntry entry = transpositionTable.get(zobristHash);
        if (entry != null && entry.getDepth() >= depth) {
            return entry.getScore();
        }
        stopwatchGameOver.start();
        if (depth == 0 || board.isGameOver(this.isBlack()).isGameFinished()) {
            EndOfGame endOfGame = board.isGameOver(this.isBlack());
            stopwatchAlphaBeta.stop();
            if (endOfGame.isGameFinished()) {

                return endOfGame.getValue();
            }else {
                return evaluate(this.isBlack(), board);
            }

        }
        stopwatchGameOver.stop();

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board child : board.getChildren(this.isBlack, stopwatchGetChildren)) {
                stopwatchAlphaBeta.stop();
                int eval = alphaBeta(child, depth - 1, alpha, beta, false);
                //stopwatchAlphaBeta.start();
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }


            }
            transpositionTable.put(zobristHash, new TranspositionTableEntry(maxEval, depth));
            //stopwatchAlphaBeta.stop();
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Board child : board.getChildren(!this.isBlack, stopwatchGetChildren)) {
                stopwatchAlphaBeta.stop();
                int eval = alphaBeta(child, depth - 1, alpha, beta, true);
                //stopwatchAlphaBeta.start();
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }

            }
            transpositionTable.put(zobristHash, new TranspositionTableEntry(minEval, depth));
            //stopwatchAlphaBeta.stop();
            return minEval;
        }
    }
    Stopwatch stopwatchmakeAlphaBeta = new Stopwatch("makeAlphaBeta");
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
                    stopwatchmakeAlphaBeta.stop();
                    int score = alphaBeta(newBoard, maxDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                    stopwatchmakeAlphaBeta.start();
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
        stopwatchmakeAlphaBeta.cumulativeElapsedTime();
        stopwatchAlphaBeta.cumulativeElapsedTime();
        stopwatchGameOver.cumulativeElapsedTime();


        return bestMove;
    }
}
