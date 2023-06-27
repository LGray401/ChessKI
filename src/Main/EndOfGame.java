package Main;

public class EndOfGame {

    private boolean gameFinished;
    private int value;
    private String reason;
    private String winner;

    public String getWinner() {
        return winner;
    }

    public void setWinner(boolean isBlack) {
        this.winner = isBlack ? "Black" : "White";
    }


    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameOver) {
        this.gameFinished = gameOver;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public EndOfGame(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public EndOfGame(boolean gameFinished, int value, String reason, boolean isBlack) {
        this.gameFinished = gameFinished;
        this.value = value;
        this.reason = reason;
        this.winner = isBlack ? "Black" : "White";
    }

}
