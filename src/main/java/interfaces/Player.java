package interfaces;

import commands.games.ticTacToe.Field;

import java.util.List;
import java.util.Optional;

public interface Player<F> {

    Optional<Field> makeMove(List<F> fields, boolean side);

    String getPlayerId();

    boolean isHuman();

    boolean equals(Object obj);

    int hashCode();
}
