package commands.games.ticTacToe;


import commands.games.AI;
import commands.games.GameSeassion;
import commands.games.Human;
import interfaces.Player;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.utils.tuple.ImmutablePair;
import net.dv8tion.jda.internal.utils.tuple.Pair;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class TicTacToeController {
    private static final Map<Pair<Player<Field>, Player<Field>>, TicTacToeSeassion> seassions = new HashMap<>();

    public static TicTacToeSeassion newGame(MessageChannel channel, String... players) throws IOException {
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
        TicTacToeSeassion temp = TicTacToeSeassion.of(p1, p2);
        seassions.put(new ImmutablePair<>(p1, p2), temp);
        return temp;
    }

    public static Map<Pair<Player<Field>, Player<Field>>, TicTacToeSeassion> getSeassions() {
        return new HashMap<>(seassions);
    }

    public static boolean inGame(String playerId) {
        return seassions.keySet()
                        .stream()
                        .anyMatch(players -> players.getLeft().getPlayerId().equals(playerId)
                                ||  players.getRight().getPlayerId().equals(playerId));

    }

    public static String sendMessage( MessageAction msg,TicTacToeSeassion seassion) {
        AtomicReference<String> newId = new AtomicReference<>();
        Message m = msg.getChannel().getHistory().getMessageById(seassion.getMessageId());
        if(m != null){
            m.delete().queue();
        }
        msg.queue(message ->{
            seassion.setMessageId(newId.updateAndGet(a -> a = message.getId()));
        });
        return newId.get();
    }

    public static Optional<GameSeassion<Field>> getSeassion(Player<Field> p1, Player<Field> p2) {
        return Optional.ofNullable(seassions.get(new ImmutablePair<>(p1, p2)));
    }

}
