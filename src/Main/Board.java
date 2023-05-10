package Main;

import Figures.Figure;
import Figures.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private Figure[] board = new Figure[64];

    private List<String> previousBoardStates = new ArrayList<>();
    private int halfMoveClock = 0;

    public Board() {
    }

    public Board(Board originalBoard) {
        this.board = new Figure[64];
        for (int i = 0; i < 64; i++) {
            this.board[i] = originalBoard.board[i].copy(); // Assuming there's a copy() method in the Figure class
        }
    }

    public Figure[] getBoard() {
        return board;
    }

    public void setBoard(Figure[] board) {
        this.board = board;
    }

    public Figure[] initialize() {

        Figure[] board = new Figure[64];

        // add white pawns
        for (int i = 8; i <= 15; i++) {
            board[i] = new Pawn(false, i);
        }

        // add black pawns
        for (int i = 48; i <= 55; i++) {
            board[i] = new Pawn(true, i);
        }

        // add second row for white

        board[0] = new Rook(false, 0);
        board[1] = new Knight(false, 1);
        board[2] = new Bishop(false, 2);
        board[3] = new Queen(false, 3);
        board[4] = new King(false, 4);
        board[5] = new Bishop(false, 5);
        board[6] = new Knight(false, 6);
        board[7] = new Rook(false, 7);

        // add second row for black

        board[56] = new Rook(true, 56);
        board[57] = new Knight(true, 57);
        board[58] = new Bishop(true, 58);
        board[59] = new Queen(true, 59);
        board[60] = new King(true, 60);
        board[61] = new Bishop(true, 61);
        board[62] = new Knight(true, 62);
        board[63] = new Rook(true, 63);

        // add empty fields

        for (int i = 16; i <= 47; i++) {
            board[i] = new EmptyField(i);
        }

        return board;
    }


    public Board copy() {
        return new Board(this);
    }

    private void promotePawn(Figure figure, int move) {

            board[figure.getNextPosition()] = new Queen(figure.isBlack(), figure.getNextPosition());

    }


    public Figure findKing(boolean isBlack) {
        for (Figure figure : board) {
            if (figure instanceof King && figure.isBlack() == isBlack) {
                return figure;
            }
        }
        return null; // This should never happen in a valid game state
    }

    public List<Figure> getOpponentFigures(boolean isBlack) {
        List<Figure> opponentFigures = new ArrayList<>();
        for (Figure figure : board) {
            if (!figure.isEmptyField() && figure.isBlack() != isBlack) {
                opponentFigures.add(figure);
            }
        }
        return opponentFigures;
    }

    public void simulateMove(Figure figure, int move) {
        figure.setNextPosition(move);
        changeBoard(figure);

    }

    public void makeMove(Figure figure) {
        if (figure instanceof Pawn || !(board[figure.getNextPosition()] instanceof EmptyField)) {
            resetHalfMoveClock();
        } else {
            halfMoveClock++;
        }
        changeBoard(figure);

        String currentFEN = this.createFENFromBoard(this.getBoard());
        previousBoardStates.add(currentFEN);
    }

    void changeBoard(Figure figure) {

        if(board[figure.getNextPosition()] instanceof King) {
            System.out.println("King is moving from ");
        }

        board[figure.getPosition()] = new EmptyField(figure.getPosition());
        if (figure instanceof Pawn && (figure.getNextPosition() < 8 || figure.getNextPosition() > 55)) {
            promotePawn(figure, figure.getNextPosition());
        } else {
            board[figure.getNextPosition()] = figure;
            figure.setPosition(figure.getNextPosition());
        }
    }


    public boolean isPlayerInCheck(boolean isBlack) {
        Figure king = findKing(isBlack);
        if(king == null) {
            return true;
        }
        for (Figure opponentFigure : getOpponentFigures(isBlack)) {
            opponentFigure.calculatePossibleMoves(this);
            if (opponentFigure.canAttack(king)) {
                return true;
            }
        }
        return false;
    }

    public void setBoardFromFEN(String fen){

        //TODO:
        // Board board1 = new Board();
        // board1.setBoardFromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
        // -> init board


        this.setBoard(createBoardFromFEN(fen).getBoard());
    }

    Board createBoardFromFEN(String fen) {

        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        // white uppercase black lower case

        Figure[] brd = new Figure[64];

        String[] strings = fen.split("/");

        String myString = strings[7] + strings[6] + strings[5] + strings[4] + strings[3] + strings[2] + strings[1] + strings[0];

        //String newFEN = new StringBuilder(fen).reverse().toString();

        char[] helper = myString.toCharArray(); // equals {"r","n","b","q"...}
        int position = 56; // starting position in an array
        int counter = 0;

        for (char c : helper) {
            switch (c) {
                case 'r':
                    brd[counter] = new Rook(true, counter);
                    counter++;
                    break;
                case 'n':
                    brd[counter] = new Knight(true, counter);
                    counter++;
                    break;
                case 'b':
                    brd[counter] = new Bishop(true, counter);
                    counter++;
                    break;
                case 'q':
                    brd[counter] = new Queen(true, counter);
                    counter++;
                    break;
                case 'k':
                    brd[counter] = new King(true, counter);
                    counter++;
                    break;
                case 'p':
                    brd[counter] = new Pawn(true, counter);
                    counter++;
                    break;
                case 'R':
                    brd[counter] = new Rook(false, counter);
                    counter++;
                    break;
                case 'N':
                    brd[counter] = new Knight(false, counter);
                    counter++;
                    break;
                case 'B':
                    brd[counter] = new Bishop(false, counter);
                    counter++;
                    break;
                case 'Q':
                    brd[counter] = new Queen(false, counter);
                    counter++;
                    break;
                case 'K':
                    brd[counter] = new King(false, counter);
                    counter++;
                    break;
                case 'P':
                    brd[counter] = new Pawn(false, counter);
                    counter++;
                    break;
                case '/':
                    break; // skip
                default:
                    if (Character.isDigit(c)) {
                        int numIterations = Character.getNumericValue(c);
                        for (int i = 0; i < numIterations; i++) {
                            brd[counter] = new EmptyField(position);
                            counter++;
                            position = setPosition(position);
                        }
                    } else {
                        // handle invalid input
                    }
                    break;
            }
            position = setPosition(position); // increase position correctly according to positions in array in setPosition()
        }

        this.setBoard(brd);

        return this;
    }

    static int setPosition(int previousPosition) {
        int newPosition;

        if(previousPosition == 63){
            newPosition = 48;
        }
        else if(previousPosition == 55){
            newPosition = 40;
        }
        else if(previousPosition == 47){
            newPosition = 32;
        }
        else if(previousPosition == 39){
            newPosition = 24;
        }
        else if(previousPosition == 31){
            newPosition = 16;
        }
        else if(previousPosition == 23){
            newPosition = 8;
        }
        else if(previousPosition == 15){
            newPosition = 0;
        }
        else {
            newPosition = previousPosition++;} // To Do: case 7?

        return newPosition;
    }




    public boolean threefoldRepetition() {
        String currentFEN = createFENFromBoard(board);
        int occurrences = 0;

        for (String previousFEN : previousBoardStates) {
            if (currentFEN.equals(previousFEN)) {
                occurrences++;
                if (occurrences >= 2) {
                    return true;
                }
            }
        }

        return false;
    }

    public void resetHalfMoveClock() {
        halfMoveClock = 0;
    }
    public boolean fiftyMoveRule() {
        return halfMoveClock >= 100;
    }


    public boolean isGameOver(boolean isBlack) {
        if(isKingOfTheHill(isBlack)) return true;
        if(fiftyMoveRule() || threefoldRepetition()){
            this.itsADraw(fiftyMoveRule() ? "fifty move rule" : "threefold repetition");
            return true;
        };
        return false;
    }

    public void itsADraw(String reason) {
        exitGame(reason + " - it's a draw!");
    }

    //method to print out game is over and terminate program
    public void playerWon (boolean isBlack) {
        String msg = "Player " + (isBlack ? "Black" : "White") + " wins!";
        exitGame(msg);
    }

    public boolean isKingOfTheHill(boolean isBlack) {

        ArrayList<Integer> kingOfTheHill = new ArrayList<>(Arrays.asList(27, 28, 35, 36));
        Figure king = findKing(isBlack);

        if (kingOfTheHill.contains(king.getPosition())) {
            System.out.println("King of the Hill");
            this.playerWon(isBlack);
            return true;
        } else {
            return false;

        }
    }

    public void exitGame(String message) {
        System.out.println(message);
        System.exit(0);
    }




    public String createFENFromBoard(Figure[] board) {
        StringBuilder fen = new StringBuilder();
        int emptyCount = 0;

        for (int i = 0; i < 64; i++) {
            if (i > 0 && i % 8 == 0) {
                if (emptyCount > 0) {
                    fen.append(emptyCount);
                    emptyCount = 0;
                }
                fen.append("/");
            }

            Figure figure = board[i];
            if (figure instanceof EmptyField) {
                emptyCount++;
            } else {
                if (emptyCount > 0) {
                    fen.append(emptyCount);
                    emptyCount = 0;
                }

                char pieceChar;
                if (figure instanceof Pawn) {
                    pieceChar = 'p';
                } else if (figure instanceof Rook) {
                    pieceChar = 'r';
                } else if (figure instanceof Knight) {
                    pieceChar = 'n';
                } else if (figure instanceof Bishop) {
                    pieceChar = 'b';
                } else if (figure instanceof Queen) {
                    pieceChar = 'q';
                } else { // King
                    pieceChar = 'k';
                }

                if (!figure.isBlack()) {
                    pieceChar = Character.toUpperCase(pieceChar);
                }

                fen.append(pieceChar);
            }
        }

        if (emptyCount > 0) {
            fen.append(emptyCount);
        }

        // Add other FEN parts if necessary (active color, castling availability, en passant target square, halfmove clock, and fullmove number).

        return fen.toString();
    }


    public Figure[][] to2DArrayAndDisplay(Figure[] board) {

        Figure[][] board2D = new Figure[8][8];

        for (int i = 0; i < 64; i++) {
            int row = i / 8;
            int col = 7 - (i % 8);
            board2D[row][col] = board[63-i];
        }

        displayBoard(board2D);
        return board2D;
    }

    static void displayBoard(Figure[][] board) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = board[row][col];
                char pieceChar;
                if (figure instanceof EmptyField) {
                    pieceChar = '.';
                } else if (figure instanceof Pawn) {
                    pieceChar = 'P';
                } else if (figure instanceof Rook) {
                    pieceChar = 'R';
                } else if (figure instanceof Knight) {
                    pieceChar = 'N';
                } else if (figure instanceof Bishop) {
                    pieceChar = 'B';
                } else if (figure instanceof Queen) {
                    pieceChar = 'Q';
                } else { // King
                    pieceChar = 'K';
                }

                if (figure.isBlack()) {
                    pieceChar = Character.toLowerCase(pieceChar);
                }

                System.out.print(pieceChar + " ");
            }
            System.out.println();
        }
    }
    public static void simulateGame(Board board, Player player1, Player player2, int amountOfMoves) {

        for (int i = 0; i < amountOfMoves; i++) {
            board.to2DArrayAndDisplay(board.getBoard());
            player1.printAllMovesAndAmountOfMovesGivenBoard(board);
            board.isGameOver(player2.isBlack());
            Figure nextMove = player1.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player1 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player1 moved to: " + nextMove.getNextPosition());
            board.isGameOver(player1.isBlack());
            board.to2DArrayAndDisplay(board.getBoard());
            nextMove = player2.makeMove(board);
            nextMove.setNextPosition(nextMove.getPossibleMoveList().get((int) (Math.random() * nextMove.getPossibleMoveList().size())));
            System.out.println("Player2 moved " + nextMove.getClass().getSimpleName() + " from: " + nextMove.getPosition());
            board.changeBoard(nextMove);
            System.out.println("Player2 moved to: " + nextMove.getNextPosition());
            player2.printAllMovesAndAmountOfMovesGivenBoard(board);
            //System.out.println(board.createFENFromBoard(board.getBoard()));

        }
    }
}
