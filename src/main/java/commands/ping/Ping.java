package commands.ping;

import commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;


public class Ping extends Command {


    public Ping(){

    }
    @Override
    public void start(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription("Pong: `"+ (System.currentTimeMillis() - time)+ "`");
        eb.setColor(Color.blue);
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
