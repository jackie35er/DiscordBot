package commands.ping;

import commands.Command;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class Ping extends Command {


    public Ping(){

    }
    @Override
    public void start(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        long time = System.currentTimeMillis();
        channel.sendMessage("Pong!") /* => RestAction<Message> */
                .queue(response /* => Message */ -> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });
    }
}
