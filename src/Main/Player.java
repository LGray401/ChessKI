package Main;

import Figures.Figure;

import java.util.ArrayList;
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


    public ArrayList<String> getAllMovesInFenNotation() {
        return allMovesInFenNotation;
    }


    public int getExaminedPositions() {
        return examinedPositions;
    }


    public void setAllMovesInFenNotation(ArrayList<String> allMovesInFenNotation) {
        this.allMovesInFenNotation = allMovesInFenNotation;
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
  
    public Player(boolean isblack) {
        isBlack = isblack;

    }

    public int getWinPossibility() {
        return winPossibility;
    }

    public void setWinPossibility(int winPossibility) {
        this.winPossibility = winPossibility;
    }



    int evaluate(boolean isBlack, Board board) {

        int eval = 0;

        for (Figure figure: this.getFigureList()) {
            eval += figure.mobilitaet();
            eval += figure.getValue();
            eval += figure.pawnStructureEvaluation(board);
            eval += figure.pieceSquareTable();
        }
        for (Figure figure: board.getOpponentFigures(!isBlack)){
            eval -= figure.mobilitaet();
            eval -= figure.getValue();
            eval -= figure.pawnStructureEvaluation(board);
            eval -= figure.pieceSquareTable();
        }
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

      private int alphaBeta(Board board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        
        examinedPositions++;
        if (depth == 0 || board.isGameOver(this.isBlack()).isGameFinished()) {
            EndOfGame endOfGame = board.isGameOver(this.isBlack());
            if (endOfGame.isGameFinished()) {
                return endOfGame.getValue();
            }else {
                return evaluate(this.isBlack, board);
            }
        }
          
          if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Board child : board.getChildren(this.isBlack)) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;

                }
              
             }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
              for (Board child : board.getChildren(!this.isBlack)) {
                int eval = alphaBeta(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
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


    public Figure makeAlphaBeta(Board board) {
        examinedPositions = 0;
        int maxDepth = 3;
        Figure bestMove = null;
        int bestScore = Integer.MIN_VALUE;
        final long startTime = System.currentTimeMillis();
        final long maxTime = 100000;
      //  while (maxTime - (System.currentTimeMillis() - startTime) > (maxDepth*maxDepth*maxDepth*maxDepth) *1000) {
            for (Figure figure : board.getValidMoves(this.isBlack())) {
                for (int nextMove : figure.getPossibleMoveList()) {
                    examinedPositions++;

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
      //      maxDepth++;
       // }

        return bestMove;
    }
}
