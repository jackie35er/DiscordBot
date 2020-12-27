package commands.games;

import commands.games.ticTacToe.Field;
import interfaces.Player;

import java.util.List;
import java.util.Optional;

public class Human implements Player<Field> {
    @Override
    public Optional<Field> makeMove(List<Field> fields, boolean side) {
        return Optional.empty();
    }
}
