package Figures;
// import Figures.Figure;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen  {

    int value;
    boolean isBlack;
    int position;

    public ArrayList<Integer> possibleMoves;

    public Queen(boolean isblack, int position) {
        this.value = 90;
        this.isBlack = isblack;
        this.position = position;
        this.possibleMoves = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,-1,-2,-3,-4,-5,-6,-7,-8,-16,-24,-32,-40,-48,-56, 8, 16, 24, 32, 40, 48, 56, // up, down, left, right
                9, 18, 27, 36, 45, 54, 63, -9, -18, -27, -36, -45, - 54, -63, 7, 14, 21, 28, 35, 42, 49, -7, -14, -21, -28, -35, -42, -49)); // diagonal
    }

    static int[] helperNS = {-8,8};
    static int[] helperNwSw = {-9,7};
    static int[] helperNoSo = {-7,9};
    static int[] leftBorder = {0,8,16,24,32,40,48,56};
    static int[] rightBorder = {7,15,23,31,39,47,55,63};


    public ArrayList<Integer> listMoves(){

        int pos = 0;

        // moves N, S
        for(int i = 1; i <= 7; i++) {
            for(int j = 0; j < helperNS.length; j++) {
                pos = this.position + i * helperNS[j];

                if(pos >= 0 && pos <= 63) { // check if pos is within valid range
                    this.possibleMoves.add(pos);
                }
            }
        }

        // add moves to NO, SO

        for(int j = 0; j < helperNoSo.length; j++) {
            for(int i = 1; i <= 7; i++) {
                pos = this.position + i * helperNoSo[j];
                if(pos >= 0 && pos <= 63) {
                    this.possibleMoves.add(pos);
                }

                boolean found = false;

                for(int k = 0; k < rightBorder.length; k++) {
                    if(pos == rightBorder[k]) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        // add moves to NW, SW

        for(int j = 0; j < helperNwSw.length; j++) {
            for(int i = 1; i <= 7; i++) {
                pos = this.position + i * helperNwSw[j];
                if(pos >= 0 && pos <= 63) {
                    this.possibleMoves.add(pos);
                }

                boolean found = false;

                for(int k = 0; k < leftBorder.length; k++) {
                    if(pos == leftBorder[k]) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        // add moves to the west
        for(int i = -7; i <= -1; i++) {

            pos = this.position + i;

            if(pos >= 0 && pos <= 63) {
                this.possibleMoves.add(pos);
            }

            boolean found = false;
            for(int j = 0; j < leftBorder.length; j++) {
                if(pos == leftBorder[j]) {
                    found = true;
                    break;
                }
            }

            if(found) {
                break;
            }

        }

        // add moves to the east
        for(int i = 1; i <= 7; i++) {

            pos = position + i;

            if(pos >= 0 && pos <= 63) {
                this.possibleMoves.add(pos);
            }

            boolean found = false;
            for(int j = 0; j < rightBorder.length; j++) {
                if(pos == rightBorder[j]) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        return this.possibleMoves;
    }
}
