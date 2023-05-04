package Figures;

import Main.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Figure {

    boolean hasMoved = false;
    boolean firstmove = true;

    public Pawn(boolean isBlack, int position) {
        this.setEmptyField(false);
        this.setValue(10);
        this.setBlack(isBlack);
        this.setPosition(position);
        if (isBlack) {
            this.setMoveSummandList(new ArrayList<>(Arrays.asList(-7, -8, -9)));
        } else {
            this.setMoveSummandList(new ArrayList<>(Arrays.asList(7, 8, 9)));
        }
    }

    public void calculatePossibleMoves(Board board) {

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(this.movingNorth(board));
        list.addAll(this.movingSouth(board));
        list.addAll(this.movingSE(board));
        list.addAll(this.movingSW(board));
        list.addAll(this.movingNE(board));
        list.addAll(this.movingNW(board));
        this.setPossibleMoveList(list);
    }


    public ArrayList<Integer> movingSouth(Board board) //black: wenn auf den Feldern 8,9,10,11,12,13,14,15, dann + 16, sonst 6
    // wenn auf sW und SE ein weisser bauer ist schlagen, sonst nach vorn oder blockiert
    {   // weiss, gl``
        if (!this.isBlack()) {
            return null;
        }
        int multiplier = 1;
        if (firstmove == true) {
            firstmove = false;// am anfang angeben ob man 2 züge machen will momentan macht man immer
            multiplier = 2;
        } else {
            multiplier = 1;
        }

        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < multiplier; i++) {
                int newPos = this.getPosition() + i * 8;
                if (newPos == this.getPosition()) continue;
                if (withInPossibleRange(newPos)) {
                    if (board.getBoard()[newPos].isEmptyField() && newPos > 55) {
                        char n = 'B';

                        switch (n) {
                            case 'Q':
                                System.out.println("Verwandlung in Queen");
                                //  board.[56] = new Queen(true, newPos);
                                break;
                            case 'R':
                                System.out.println("Verwandlung in Rook");
                                break;
                            case 'B':
                                System.out.println("Verwandlung in Bishop");
                                break;
                            case 'K':
                                System.out.println("Verwandlung in Knight");
                                break;


                        }
                    } else if (board.getBoard()[newPos].isEmptyField()) {
                        list.add(newPos);
                    }
                }

            }
            return list;
        }

    }

        public ArrayList<Integer> movingNorth (Board board) {
            if (this.isBlack()) {
                return null;
            }
            int multiplier = 1;
            if (firstmove == true) {
                firstmove = false;// am anfang angeben ob man 2 züge machen will momentan macht man immer
                multiplier = 2;
            } else {
                multiplier = 1;
            }

            {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < multiplier; i++) {
                    int newPos = this.getPosition() - i * 8;
                    if (newPos == this.getPosition()) continue;
                    if (withInPossibleRange(newPos)) {
                        if (board.getBoard()[newPos].isEmptyField() && newPos < 8) {
                            char n = 'B';

                            switch (n) {
                                case 'Q':
                                    System.out.println("Verwandlung in Queen");
                                    //  board.[56] = new Queen(true, newPos);
                                    break;
                                case 'R':
                                    System.out.println("Verwandlung in Rook");
                                    break;
                                case 'B':
                                    System.out.println("Verwandlung in Bishop");
                                    break;
                                case 'K':
                                    System.out.println("Verwandlung in Knight");
                                    break;


                            }
                        } else if (board.getBoard()[newPos].isEmptyField()) {
                            list.add(newPos);
                        }

                    }


                }
                return list;
            }
        }



        public ArrayList<Integer> movingNE (Board board){
        if (this.isBlack()) {
            return null;}
            int multiplier = 1;

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < multiplier; i++) {
                int newPos = this.getPosition() + i * 9;
                if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                    list.add(newPos);
                    break;
                }
                if (newPos == this.getPosition()) continue;
                if (withInPossibleRange(newPos)) {
                    if (board.getBoard()[newPos].isBlack()&& newPos < 8) {
                        char n = 'B';

                        switch (n) {
                            case 'Q':
                                System.out.println("Verwandlung in Queen");
                                //  board.[56] = new Queen(true, newPos);
                                break;
                            case 'R':
                                System.out.println("Verwandlung in Rook");
                                break;
                            case 'B':
                                System.out.println("Verwandlung in Bishop");
                                break;
                            case 'K':
                                System.out.println("Verwandlung in Knight");
                                break;
                        }
                    }
                                    else if(board.getBoard()[newPos].isBlack())
                                { list.add(newPos);
                            }

                    }else if (board.getBoard()[newPos].isBlack() != this.isBlack()) {

                        break;
                    } else if (board.getBoard()[newPos].isEmptyField()) {
                        break;
                    }
                }
            return list;
            }



        public ArrayList<Integer> movingNW (Board board){
            if (this.isBlack()) {
                return null;}
                int multiplier = 1;

                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < multiplier; i++) {
                    int newPos = this.getPosition() - i * 7;
                    if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                        list.add(newPos);
                        break;
                    }
                    if (newPos == this.getPosition()) continue;
                    if (withInPossibleRange(newPos)) {
                        if (board.getBoard()[newPos].isBlack()&& newPos < 8) {
                            char n = 'B';

                            switch (n) {
                                case 'Q':
                                    System.out.println("Verwandlung in Queen");
                                    //  board.[56] = new Queen(true, newPos);
                                    break;
                                case 'R':
                                    System.out.println("Verwandlung in Rook");
                                    break;
                                case 'B':
                                    System.out.println("Verwandlung in Bishop");
                                    break;
                                case 'K':
                                    System.out.println("Verwandlung in Knight");
                                    break;
                            }
                        }
                        else if(board.getBoard()[newPos].isBlack())
                        { list.add(newPos);
                        }

                    }else if (board.getBoard()[newPos].isBlack() != this.isBlack()) {

                        break;
                    } else if (board.getBoard()[newPos].isEmptyField()) {
                        break;
                    }
                }
            return list;
        }
            public ArrayList<Integer> movingSE (Board board) {
                if (!this.isBlack()) {
                    return null;}
                    int multiplier = 1;

                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = 0; i < multiplier; i++) {
                        int newPos = this.getPosition() - i * 7;
                        if (getEastBarrier().contains(newPos) && newPos != this.getPosition()) {
                            list.add(newPos);
                            break;
                        }
                        if (newPos == this.getPosition()) continue;
                        if (withInPossibleRange(newPos)) {
                            if (!board.getBoard()[newPos].isBlack()&& newPos >55 ) {
                                char n = 'B';

                                switch (n) {
                                    case 'Q':
                                        System.out.println("Verwandlung in Queen");
                                        //  board.[56] = new Queen(true, newPos);
                                        break;
                                    case 'R':
                                        System.out.println("Verwandlung in Rook");
                                        break;
                                    case 'B':
                                        System.out.println("Verwandlung in Bishop");
                                        break;
                                    case 'K':
                                        System.out.println("Verwandlung in Knight");
                                        break;
                                }
                            }
                            else if(board.getBoard()[newPos].isBlack())
                            { list.add(newPos);
                            }

                        }else if (board.getBoard()[newPos].isBlack() != this.isBlack()) {

                            break;
                        } else if (board.getBoard()[newPos].isEmptyField()) {
                            break;
                        }
                    }
                return list;
            }


                public List<Integer> movingSW (Board board){
                    if (!this.isBlack()) {
                        return null;}
                        int multiplier = 1;


                        ArrayList<Integer> list = new ArrayList<>();
                        for (int i = 0; i < multiplier; i++) {
                            int newPos = this.getPosition() - i * 9;
                            if (getWestBarrier().contains(newPos) && newPos != this.getPosition()) {
                                list.add(newPos);
                                break;
                            }
                            if (newPos == this.getPosition()) continue;
                            if (withInPossibleRange(newPos)) {
                                if (!board.getBoard()[newPos].isBlack()&& newPos >55) {
                                    char n = 'B';

                                    switch (n) {
                                        case 'Q':
                                            System.out.println("Verwandlung in Queen");
                                            //  board.[56] = new Queen(true, newPos);
                                            break;
                                        case 'R':
                                            System.out.println("Verwandlung in Rook");
                                            break;
                                        case 'B':
                                            System.out.println("Verwandlung in Bishop");
                                            break;
                                        case 'K':
                                            System.out.println("Verwandlung in Knight");
                                            break;
                                    }
                                }
                                else if(board.getBoard()[newPos].isBlack())
                                { list.add(newPos);
                                }

                            }else if (board.getBoard()[newPos].isBlack() != this.isBlack()) {

                                break;
                            } else if (board.getBoard()[newPos].isEmptyField()) {
                                break;
                            }
                        }
                    return list;
                }
                }




