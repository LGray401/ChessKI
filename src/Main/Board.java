package Main;

import Figures.Figure;
import Figures.*;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Board {

    private Figure[] board = new Figure[64];

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

    static Figure[] intialize() {

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

    void changeBoard(Figure figure) {

        board[figure.getPosition()] = new EmptyField(figure.getPosition());
        //this.to2DArrayAndDisplay(this.getBoard());
        int helper = figure.getNextPosition();
        board[helper] = figure;
        figure.setPosition(helper);
    }


    public boolean isPlayerInCheck(boolean isBlack) {
        Figure king = findKing(isBlack);
        for (Figure opponentFigure : getOpponentFigures(isBlack)) {
            if (opponentFigure.canAttack(king)) {
                return true;
            }
        }
        return false;
    }

    static Figure[] createBoardFromFEN(String fen) {

        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        // white uppercase black lower case

        Figure[] brd = new Figure[64];

        char[] helper = fen.toCharArray(); // equals {"r","n","b","q"...}
        int position = 56; // starting position in an array
        int counter = 0;

        for (char c : helper) {
            switch (c) {
                case 'r':
                    brd[counter] = new Rook(false, position);
                    counter++;
                    break;
                case 'n':
                    brd[counter] = new Knight(false, position);
                    counter++;
                    break;
                case 'b':
                    brd[counter] = new Bishop(false, position);
                    counter++;
                    break;
                case 'q':
                    brd[counter] = new Queen(false, position);
                    counter++;
                    break;
                case 'k':
                    brd[counter] = new King(false, position);
                    counter++;
                    break;
                case 'p':
                    brd[counter] = new Pawn(false, position);
                    counter++;
                    break;
                case 'R':
                    brd[counter] = new Rook(true, position);
                    counter++;
                    break;
                case 'N':
                    brd[counter] = new Knight(true, position);
                    counter++;
                    break;
                case 'B':
                    brd[counter] = new Bishop(true, position);
                    counter++;
                    break;
                case 'Q':
                    brd[counter] = new Queen(true, position);
                    counter++;
                    break;
                case 'K':
                    brd[counter] = new King(true, position);
                    counter++;
                    break;
                case 'P':
                    brd[counter] = new Pawn(true, position);
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

        return brd;
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



    //method to print out game is over and terminate program
    public void gameOver(boolean isBlack) {
        String winner = isBlack ? "Black" : "White";
        System.out.println("Player " + winner + " wins!");
        System.exit(0);
    }

    public boolean isKingOfTheHill(boolean isBlack) {

        ArrayList<Integer> kingOfTheHill = new ArrayList<>(Arrays.asList(27, 28, 35, 36));
        Figure king = findKing(isBlack);

        if (kingOfTheHill.contains(king.getPosition())) {
            System.out.println("King of the Hill");
            this.gameOver(isBlack);
            return true;
        } else {
            return false;

        }
    }



    public static Figure[][] to2DArrayAndDisplay(Figure[] board) {
        Figure[][] board2D = new Figure[8][8];

        for (int i = 0; i < 64; i++) {
            int row = i / 8;
            int col = i % 8;
            board2D[row][col] = board[i];
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



}
