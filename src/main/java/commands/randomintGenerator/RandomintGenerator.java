package commands.randomintGenerator;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class RandomintGenerator {

    private static Random random = new Random();

    public static void randomIntGenerator(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        try {
            String[] snippets = msg.getContentRaw().substring(1).split(" ");

            if (snippets[0].equalsIgnoreCase("random")) {
                int min = Integer.parseInt(snippets[1]);
                int max = Integer.parseInt(snippets[2]);
                if(min > max) {
                    throw new IllegalArgumentException();
                }
                int randomNum = random.nextInt(max - min) + min;

                StringBuilder output = new StringBuilder("Your random Number is: ");
                output.append(randomNum);
                channel.sendMessage(output).queue();
            }
        } catch (ArrayIndexOutOfBoundsException use) {
            channel.sendMessage("Wrong syntax: random min max").queue();
        } catch (NumberFormatException e) {
            channel.sendMessage("min and max, need to be numbers!").queue();
        } catch (IllegalArgumentException e) {
            channel.sendMessage("max must be greater than min").queue();
        }
    }
}
