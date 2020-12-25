import Selector.Selector;
import net.dv8tion.jda.api.EmbedBuilder;
import enmus.Secrets;
import filehandler.SecretGetter;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ThreadLocalRandom;

import javax.security.auth.login.LoginException;


public class Bot extends ListenerAdapter
{



    public static void main(String[] args) throws LoginException
    {

        JDABuilder.createLight(new SecretGetter(Secrets.TOKEN).getSecret(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Selector())
                .setActivity(Activity.playing("Type (ping"))
                .build();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("(ping"))
        {
            channel.sendMessage("your Mom is a hoe").queue();/* => RestAction<Message> */
        }

        gtfotimer(event);
        randomintGenerator(event);

    }

    public void randomintGenerator(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        try {
            String[] snippets = msg.getContentRaw().split(" ");

            if (snippets[0].equalsIgnoreCase("random")) {
                int randomNum = ThreadLocalRandom.current().nextInt(Integer.parseInt(snippets[1]), Integer.parseInt(snippets[2]));

                StringBuilder output = new StringBuilder("Your random Number is: ");
                output.append(randomNum);
                channel.sendMessage(output).queue();
            }
        }
        catch(ArrayIndexOutOfBoundsException use)
        {
            channel.sendMessage("Wrong syntax: random min max").queue();
            //use.printStackTrace();
        }

    }

    public void gtfotimer(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();

        try {
            String[] snippets = msg.getContentRaw().split(" ");

            if (snippets[0].equalsIgnoreCase("gtfotimer")) {

                GtfoTimer.setMassageChannel(channel);

                try {
                    if (snippets.length == 2) {
                        StringBuilder input = new StringBuilder(snippets[1]);
                        input.append(":00");

                        GtfoTimer.startnewtimer(input);

                    } else if (snippets.length == 3) {
                        StringBuilder input = new StringBuilder(snippets[1]);
                        input.append(":00");

                        GtfoTimer.startnewtimer(input);

                        GtfoTimer.runddownName = snippets[2];
                    } else {
                        channel.sendMessage("Wrong syntax: gtfotimer hour:minute").queue();
                    }
                }
                catch(DateTimeParseException use)
                {
                    if(snippets[1].equalsIgnoreCase("cancel"))
                    {
                        GtfoTimer.deletetimer();

                    }
                    else
                    {
                        GtfoTimer.runddownName = snippets[1];
                    }

                }

                msg.delete().queue();

            }
            else if(snippets[0].equalsIgnoreCase("gtfo"))
            {
                if(snippets[1].equalsIgnoreCase("ac"))
                {
                    if(GtfoTimer.started)
                    {
                        GtfoTimer.addmitspieler(msg);
                    }

                }
                msg.delete().queue();
            }
        }
        catch(ArrayIndexOutOfBoundsException use)
        {
            channel.sendMessage("Wrong syntax: gtfotimer hour:minute").queue();
            //use.printStackTrace();
        }



    }



}
