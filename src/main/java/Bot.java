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


public class Bot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        JDABuilder.createLight(new SecretGetter(Secrets.TOKEN).getSecret(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Selector())
                .setActivity(Activity.playing("Type (ping"))
                .build();

    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("(ping")) {
            channel.sendMessage("your Mom is a hoe").queue();/* => RestAction<Message> */
        }

    }
}
