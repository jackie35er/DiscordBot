package Selector;

import commands.ping.Ping;
import commands.prefix.Prefix;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Selector extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if(!msg.getContentRaw().startsWith(Prefix.getPrefix())){
            return;
        }
        String[] args = msg.getContentRaw().substring(Prefix.getPrefix().length()).split("\\s+");

        switch(args[0]){
            case "ping" -> new Ping().start(event);
            case "prefix" -> new Prefix().start(event,Arrays.copyOfRange(args,1,args.length));
            default -> System.out.println("gay");
        }
    }
}
