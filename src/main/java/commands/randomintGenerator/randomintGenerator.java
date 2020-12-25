package commands.randomintGenerator;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.ThreadLocalRandom;

public class randomintGenerator {

    public static void RandomintGenerator(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        try {
            String[] snippets = msg.getContentRaw().substring(1).split(" ");

            if (snippets[0].equalsIgnoreCase("random")) {
                int randomNum = ThreadLocalRandom.current().nextInt(Integer.parseInt(snippets[1]), Integer.parseInt(snippets[2]));

                StringBuilder output = new StringBuilder("Your random Number is: ");
                output.append(randomNum);
                channel.sendMessage(output).queue();
            }
        } catch (ArrayIndexOutOfBoundsException use) {
            channel.sendMessage("Wrong syntax: random min max").queue();
            //use.printStackTrace();
        }

    }

}
