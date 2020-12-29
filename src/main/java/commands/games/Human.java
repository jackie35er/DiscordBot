package commands.games;

import commands.games.ticTacToe.Field;
import interfaces.Player;

import java.util.*;

public class Human implements Player<Field> {
    private String playerId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Human(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public Optional<Field> makeMove(List<Field> fields, boolean side) {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(playerId, human.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}


