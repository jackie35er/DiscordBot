package commands.games.ticTacToe;


import commands.games.AI;
import commands.games.GameSeasion;
import commands.games.Human;
import interfaces.Player;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.utils.tuple.ImmutablePair;
import net.dv8tion.jda.internal.utils.tuple.Pair;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicTacToeController {
    private static final Map<Pair<Player<Field>, Player<Field>>, TicTacToeSeasion> seasions = new HashMap<>();

    public static TicTacToeSeasion newGame(MessageChannel channel, String... players) throws IOException {
        Player<Field> p1;
        Player<Field> p2;

        if (!(players.length == 1 || players.length == 2)) {
            throw new IllegalArgumentException();
        } else if (players.length == 1) {
            p2 = new AI();
        } else {
            p2 = new Human(players[1]);
        }
        p1 = new Human(players[0]);

        return seasions.put(new ImmutablePair<>(p1, p2), TicTacToeSeasion.of(p1, p2));
    }

    public static Map<Pair<Player<Field>, Player<Field>>, TicTacToeSeasion> getSeasions() {
        return new HashMap<>(seasions);
    }

    public static boolean inGame(String playerId) {
        return seasions.keySet()
                        .stream()
                        .anyMatch(players -> players.getLeft().getPlayerId().equals(playerId)
                                || players.getRight().getPlayerId().equals(playerId));
    }

    public void sendMessage(MessageChannel channel, MessageAction msg, GameSeasion<Field> seasion) {


    }

    public static Optional<GameSeasion<Field>> getSeasion(Player<Field> p1, Player<Field> p2) {
        return Optional.ofNullable(seasions.get(new ImmutablePair<>(p1, p2)));
    }

    public static void main(String[] args) {
        System.out.println(
                getSeasion(new Human("asdf"), new AI())
        );

        seasions.put(
                new ImmutablePair<>(new Human("asdf"), new AI()),
                TicTacToeSeasion.of(new Human("asdf"), new AI())
        );
        System.out.println(seasions.get(new ImmutablePair<>(new Human("asdf"), new AI())));

        System.out.println(getSeasion(new Human("asdf"), new AI()));

        Player<Field> h1 = new Human("asdf");
        Player<Field> h2 = new Human("asdf");
        AI ai1 = new AI();
        AI ai2 = new AI();
        Pair<Player<Field>, Player<Field>> p1 = new ImmutablePair<>(new Human("asdf"), new AI());
        Pair<Player<Field>, Player<Field>> p2 = new ImmutablePair<>(new Human("asdf"), new AI());

        System.out.println(h1.equals(h2));
        System.out.println(ai1.equals(ai2));
        System.out.println(p1.equals(p2));
    }
}
