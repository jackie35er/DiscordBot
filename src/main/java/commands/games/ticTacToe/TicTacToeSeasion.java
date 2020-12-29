package commands.games.ticTacToe;

import commands.games.GameSeasion;
import interfaces.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TicTacToeSeasion extends GameSeasion<Field> {
    private final List<Field> fields = new ArrayList<>();
    private BoardImageGenerator image;

    private TicTacToeSeasion(Player<Field> p1, Player<Field> p2) {
        try {
            image = new BoardImageGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.setPlayer1(p1);
        super.setPlayer2(p2);
        startGame();
    }

    @Override
    public void startGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields.add(new Field(i, j, 0));
            }
        }
    }

    @Override
    public void makeMove() {

    }

    public static TicTacToeSeasion of(Player<Field> p1, Player<Field> p2) {
        return new TicTacToeSeasion(p1, p2);
    }

    public File getBoardStateImage() throws IOException {
        return image.safeToTemp(super.getPlayer1().toString());
    }
}
