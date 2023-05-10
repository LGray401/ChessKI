package Main;

import Figures.Figure;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        int amountOfMoves = 1;
        String fenString = "8/8/4k3/3pP3/3K4/8/8/8";

        Board.simulateGame(fenString, player1, player2, amountOfMoves);

    }
}