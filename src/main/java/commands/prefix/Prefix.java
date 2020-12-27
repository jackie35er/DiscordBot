package commands.prefix;

import commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.prefs.Preferences;


public class Prefix extends Command {
    private static String prefix;


    static {
        Preferences prefs = Preferences.userNodeForPackage(Prefix.class);
        if(prefs.get("prefix",null) == null){
            prefs.put("prefix","-");
        }
        prefix = prefs.get("prefix","-");
    }


    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Preferences prefs = Preferences.userNodeForPackage(Prefix.class);
        prefs.put("prefix",prefix);
        Prefix.prefix = prefix;
    }

    @Override

    public void start(@NotNull MessageReceivedEvent event, String [] args){

        if(args.length == 0){
            printPrefix(event);
            return;
        }
        if ("set".equals(args[0])) {
            setPrefixFromUser(event, args[1]);
        } else {
            printPrefix(event);
        }


    }
    private void printPrefix(MessageReceivedEvent event){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription("Current Prefix: `" +prefix+"`");
        eb.setColor(Color.blue);
        event.getChannel().sendMessage(eb.build()).queue();
    }
    @SuppressWarnings("ConstantConditions")
    private void setPrefixFromUser(MessageReceivedEvent event,String prefix){
        if(event.getAuthor().isBot()
                || !event.getMember().hasPermission(Permission.MANAGE_SERVER)
                && !event.getMember().getUser().getAsTag().equals("jackie35er#0130")){

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("PERMISSION ERROR");
            eb.setColor(Color.RED);
            eb.setDescription("You need `"+ Permission.MANAGE_SERVER +"` permision to do that");
            event.getChannel().sendMessage(eb.build()).queue();
            return;
        }

        setPrefix(prefix);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Prefix updated");
        eb.setColor(Color.BLUE);
        eb.setDescription("New Prefix: `"+ prefix +"`");
        event.getChannel().sendMessage(eb.build()).queue();
    }

}
