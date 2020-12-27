package commands.games;

import commands.games.ticTacToe.Field;
import commands.games.ticTacToe.TicTacToeAI;
import interfaces.Player;

import java.util.List;
import java.util.Optional;

public class AI implements Player<Field> {
    @Override
    public Optional<Field> makeMove(List<Field> fields, boolean side) {
        return getBoardDiffrence(fields, new TicTacToeAI().nextMove(fields, -1, side));//-1 depth calculates all moves
    }

    private static Optional<Field> getBoardDiffrence(List<Field> f1, List<Field> f2) {
        for (int i = 0; i < f1.size(); i++) {
            if (f1.get(i) != f2.get(i)) {
                return Optional.of(f1.get(i));
            }
        }
        return Optional.empty();
    }
}
