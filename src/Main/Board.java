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

    void changeBoard(Figure figure) {

        board[figure.getPosition()] = new EmptyField(figure.getPosition());
        int helper = figure.getNextPosition();
        board[helper] = figure;
        figure.setPosition(helper);
    }
}