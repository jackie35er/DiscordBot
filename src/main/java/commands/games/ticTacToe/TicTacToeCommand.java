package commands.games.ticTacToe;

import commands.Command;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


import java.awt.*;
import java.io.IOException;

public class TicTacToeCommand extends Command {

    public void start(MessageReceivedEvent event, String ... args) {
        if(args.length == 0){
            if(TicTacToeController.inGame(event.getAuthor().getId())){
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.red);
                eb.setTitle("You are already in a game");
                eb.setDescription("Please finsish your other game first");
                event.getChannel().sendMessage(eb.build()).queue();
            }
            else{
                try {
                    TicTacToeController.newGame(event.getChannel(),event.getAuthor().getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
