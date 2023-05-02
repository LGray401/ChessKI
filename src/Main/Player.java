package Main;

import Figures.EmptyField;
import Figures.Figure;

import java.util.ArrayList;
import java.util.List;

public class Player {

    boolean isBlack;
    List<Figure> legalMoves; //nicht eher figureList?
    boolean playerInCheck;

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    public List<Figure> getLegalMoves() {
        return legalMoves;
    }

    public void setLegalMoves(List<Figure> legalMoves) {
        this.legalMoves = legalMoves;
    }

    public boolean isPlayerInCheck() {
        return playerInCheck;
    }

    public void setPlayerInCheck(boolean playerInCheck) {
        this.playerInCheck = playerInCheck;
    }

    Player(boolean isblack) {
        isBlack = isblack;
    }

    Figure calculateMove(Board board) {

        List<Figure> possibleMoves = new ArrayList<>();

        for (Figure figure : board.board)
            if (figure.isBlack == this.isBlack) {
                possibleMoves.addAll(figure.calculatePossibleMoves(board));
            }
        legalMoves = removeIllegalMoves(board, possibleMoves);

        return legalMoves.get(0);
    }




    List<Figure> removeIllegalMoves(List<Figure> moves) {

        // return all possible moves of the figure

        return null;
    }

    List<Figure> removeIllegalMoves(Board board, List<Figure> moves) {


        // prüft, ob auf dem Weg zum Zielfeld alle Felder frei sind (außer beim Pferd) -> Linh
        // prüft, ob Zielfeld leer ist oder gegnerischer Spieler drauf ist -> Ely
        // prüft, ob man im Schach steht -> Rudi
        // prüft, ob man nach dem Move im Schach steht -> Rudi
        // prüft, ob man nach dem Move immer noch im Schach steht -> Rudi
        // König schlägt König filtern -> Rudi


        testEly(board, moves); // last method to call

        return moves;
    }

    List<Figure> testEly(Board board, List<Figure> moves) {

        List<Figure> newList = new ArrayList<>();

        for (Figure figure: moves) {
            for(int i = 0; i < figure.getPossibleMoves().size(); i++) {
                int newPosition = figure.getPosition() + i;
                Figure player1 = board.board[figure.getPosition()];
                Figure player2 = board.board[newPosition];

                // checks if target field is empty or occupied by enemy
                if(player2 == EmptyField.getEmptyField()|| player1.isBlack != player2.isBlack) {
                    newList.add(figure);
                }
            }
        }
        return newList;
        //statt return newList, einfach instanzvariable legalMoves bzw. figureList mit setter Methode verändern? Oder einfach immer das Board returnen?
    }

    Board filterKingBeatsKing(Board board, Player otherPlayer){
        Figure kingOfOpponent = (Figure) otherPlayer.legalMoves.stream().filter(Figures.King.class::isInstance);
        Figure kingOfNotOpponent = (Figure) this.legalMoves.stream().filter(Figures.King.class::isInstance);

        if (kingOfNotOpponent.calculatePossibleMoves().contains(kingOfOpponent.getPosition())){
            board.board[kingOfNotOpponent.getPosition()].calculatePossibleMoves().remove(kingOfOpponent.getPosition());
        }
        return board;
    }

    boolean isCheckTrue(Board board, Player otherPlayer){

        Figure kingOfPlayer = (Figure) this.legalMoves.stream().filter(Figures.King.class::isInstance);

        for (Figure figure: otherPlayer.legalMoves) {
            for (Figure possibleMove : figure.calculatePossibleMoves()) {
                if ((possibleMove.nextPosition == kingOfPlayer.getPosition())){
                    this.setPlayerInCheck(true);
                    return true;
                }
            }
        }
        return false;
    }

    Board afterMoveStillCheck(Board board){
        //TODO:


        return board;
    }

}
