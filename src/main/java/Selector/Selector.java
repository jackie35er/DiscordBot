package Selector;

import commands.GtfoTimer.GtfoTimer;
import commands.ping.Ping;
import commands.prefix.Prefix;
import commands.randomintGenerator.randomintGenerator;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
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
            case "ping":
                new Ping().start(event);
                break;
            case "prefix":
                new Prefix().start(event,Arrays.copyOfRange(args,1,args.length));
                break;
            case "gtfo":
            case "gtfotimer":
                GtfoTimer.gtfotimer(event);
                break;
            case "random":
                randomintGenerator.RandomintGenerator(event);
                break;
            default:
        }
    }


}
