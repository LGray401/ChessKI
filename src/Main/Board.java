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

    static Figure[] createBoardFromFEN(String fen) {

        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        // white uppercase black lower case

        Figure[] brd = new Figure[64];

        char[] helper = fen.toCharArray(); // equals {"r","n","b","q"...}
        int position = 56; // starting position in an array
        int counter = 0;

        for(char c: helper) {

            switch (c) {
                case 'r': brd[counter] = new Rook(false, position); break;
                case 'n': brd[counter] = new Knight(false, position); break;
                case 'b': brd[counter] = new Bishop(false, position); break;
                case 'q': brd[counter] = new Queen(false, position); break;
                case 'k': brd[counter] = new King(false, position); break;
                case 'p': brd[counter] = new Pawn(false,position); break;
                case 'R': brd[counter] = new Rook(true, position); break;
                case 'N': brd[counter] = new Knight(true, position); break;
                case 'B': brd[counter] = new Bishop(true, position); break;
                case 'Q': brd[counter] = new Queen(true, position); break;
                case 'K': brd[counter] = new King(true, position); break;
                case 'P': brd[counter] = new Pawn(true,position); break;
                case '/': break; // skip
                // for every number n create n EmptyFields()
                case 1: brd[counter] = new EmptyField(position);setPosition(position); break;
                case 2: for(int i = 0; i < 2; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 3: for(int i = 0; i < 3; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 4: for(int i = 0; i < 4; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 5: for(int i = 0; i < 5; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 6: for(int i = 0; i < 6; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 7: for(int i = 0; i < 7; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
                case 8: for(int i = 0; i < 8; i++) {
                    brd[counter] = new EmptyField(position);
                    counter++;setPosition(position); } break;
            }
            counter++; // increase array index
            position = setPosition(position); // increase position correctly according to positions in array in setPostion()
        }


// bugs: / creates null in array
        // To Do: 8 = create null 8times
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

    /*static Figure[] createBoardFromFEN(String fen) {

        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
        // white uppercase black lower case

        Figure[] brd = new Figure[64];

        char[] helper = fen.toCharArray(); // equals {"r","n","b","q"...}
        int position = 56; // starting position in an array

        for(char c: helper) {

            switch (c) {
                case 'r': brd[position] = new Rook(false, position); break;
                case 'n': brd[position] = new Knight(false, position); break;
                case 'b': brd[position] = new Bishop(false, position); break;
                case 'q': brd[position] = new Queen(false, position); break;
                case 'k': brd[position] = new King(false, position); break;
                case 'p': brd[position] = new Pawn(false,position); break;
                case 'R': brd[position] = new Rook(true, position); break;
                case 'N': brd[position] = new Knight(true, position); break;
                case 'B': brd[position] = new Bishop(true, position); break;
                case 'Q': brd[position] = new Queen(true, position); break;
                case 'K': brd[position] = new King(true, position); break;
                case 'P': brd[position] = new Pawn(true,position); break;
                case '/': break; // skip
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    brd[position]= new EmptyField(position);
                    break; // create for every number an emptyField
            }

            // increase position correctly according to positions in array
            if(position == 63){
                position = 48;
            }
            else if(position == 55){
                position = 40;
            }
            else if(position == 47){
                position = 32;
            }
            else if(position == 39){
                position = 24;
            }
            else if(position == 31){
                position = 16;
            }
            else if(position == 23){
                position = 8;
            }
            else if(position == 15){
                position = 0;
            }
            else {
            position++;} // To Do: case 7?
        }

        /*int index = 0;
        for (char c : parts[0].toCharArray()) {
            if (c == '/') {
                continue;
            } else if (Character.isDigit(c)) {
                index += Character.getNumericValue(c);
            } else {
                board[index] = c;
                index++;
            }}

        return brd;
    }*/
}
