package commands.games;

import interfaces.Player;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.List;

public abstract class GameSeassion<F> {
    private Player<F> player1;
    private Player<F> player2;

    private boolean player1Side;
    private boolean player2Side;

    private List<F> board;

    private String messageId = "0";

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public abstract void startGame();

    public Player<F> getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player<F> player1) {
        this.player1 = player1;
    }

    public Player<F> getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player<F> player2) {
        this.player2 = player2;
    }

    public boolean isPlayer1Side() {
        return player1Side;
    }

    public void setPlayer1Side(boolean player1Side) {
        this.player1Side = player1Side;
    }

    public boolean isPlayer2Side() {
        return player2Side;
    }

    public void setPlayer2Side(boolean player2Side) {
        this.player2Side = player2Side;
    }

    public List<F> getBoard() {
        return board;
    }

    public void setBoard(List<F> board) {
        this.board = board;
    }

    public abstract void makeMove();
}
