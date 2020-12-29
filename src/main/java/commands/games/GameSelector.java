package commands.games;

import commands.Command;
import commands.games.ticTacToe.TicTacToeCommand;
import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class GameSelector extends Command {
    @Override
    public void start(MessageReceivedEvent event, String ... args) {
        if (args.length == 0) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Games:");
            eb.setDescription("Currently available Games: None");
            eb.setColor(Color.CYAN);
            event.getChannel().sendMessage(eb.build()).queue();
        }
        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "tictactoe":
                new TicTacToeCommand().start(event, Arrays.copyOfRange(args, 1, args.length));

        }
    }

}
