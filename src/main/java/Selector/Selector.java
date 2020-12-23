package Selector;

import commands.ping.Ping;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Selector extends ListenerAdapter {

    private final String prefix = "-";

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message msg = event.getMessage();

        if(!msg.getContentRaw().substring(0,prefix.length()).equals(prefix)){
            return;
        }
        String[] args = msg.getContentRaw().split("\\s+");

        switch(args[0].substring(prefix.length())){
            case "ping":
                Ping.ping(event);
        }
    }


}
