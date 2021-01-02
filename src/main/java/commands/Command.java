package commands;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public abstract class Command {

    public void start(GenericEvent event){}
    public void start(GenericEvent event,String ... args){}

    public void start(GenericMessageEvent event){}
    public void start(GenericMessageEvent event,String ... args){}

    public void start(MessageReceivedEvent event){}
    public void start(MessageReceivedEvent event,String ... args) {}
}
