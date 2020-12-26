package commands.GtfoTimer;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;


public class GtfoTimer {

    private static EmbedBuilder eb;
    private static MessageEmbed embed;
    private static String themessage;
    private static ArrayList<String> mittspieler = new ArrayList<>();
    private static MessageChannel channel;

    public static Boolean started = false;
    public static String runddownName;
    public static LocalTime gamestart;
    public static boolean deletemassage = false;

    public static void gtfotimer(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();

        try {

            String[] snippets = msg.getContentRaw().substring(1).split(" ");

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
                } catch (DateTimeParseException use) {
                    if (snippets[1].equalsIgnoreCase("cancel")) {
                        GtfoTimer.deletetimer();

                    } else {
                        GtfoTimer.runddownName = snippets[1];
                    }

                }

                msg.delete().queue();

            } else if (snippets[0].equalsIgnoreCase("gtfo")) {
                if (snippets[1].equalsIgnoreCase("ac")) {
                    if (GtfoTimer.started) {
                        GtfoTimer.addmitspieler(msg);
                    }

                }
                msg.delete().queue();
            }
        } catch (ArrayIndexOutOfBoundsException use) {
            channel.sendMessage("Wrong syntax: gtfotimer hour:minute").queue();
            //use.printStackTrace();
        }
    }

    public static void setMassageChannel(MessageChannel newchannel) {
        channel = newchannel;
    }

    private static String getallPlayers(String zeroplayermassage) {

        StringBuilder output = new StringBuilder();
        for (String l : GtfoTimer.mittspieler) {
            output.append(l);
            output.append(", ");
        }

        if (output.toString().length() == 0) {
            output.append(zeroplayermassage);
        } else {
            output.delete(output.toString().length() - 2, output.toString().length());
        }

        return output.toString();
    }

    private static float gethue() {
        return switch (mittspieler.size()) {
            case 1 -> 0.02F;
            case 2 -> 0.05F;
            case 3 -> 0.15F;
            case 4 -> 0.29F;
            default -> 0F;
        };
    }

    public static void addmitspieler(Message msg) {
        if (!mittspieler.contains(msg.getAuthor().getName()))
            mittspieler.add(msg.getAuthor().getName());
    }

    public static void deletetimer() {
        if (!LocalTime.now().isAfter(GtfoTimer.gamestart)) {
            GtfoTimer.deletemassage = true;
        }
    }

    public static void startnewtimer(StringBuilder newtime) {

        mittspieler = new ArrayList<>();

        GtfoTimer.eb = new EmbedBuilder();

        GtfoTimer.eb.setTitle("GTFO Timer:");
        GtfoTimer.eb.setDescription("Starting Timer...");
        GtfoTimer.eb.setColor(Color.RED);
        GtfoTimer.embed = eb.build();

        GtfoTimer.gamestart = LocalTime.parse(newtime);

        if (LocalTime.now().isAfter(GtfoTimer.gamestart)) {
            channel.sendMessage("This Time is over! @Rafael ;)").queue();
            return;
        }

        GtfoTimer.started = true;

        channel.sendMessage(embed).queue(message -> {
            themessage = message.getId();
            //System.out.println(themessage);
            while (true) {
                eb.setTitle("GTFO Timer:");
                if (GtfoTimer.runddownName == null) {
                    eb.setDescription("Next Rundown in " + timeuntilGamestart().getHour() + " h " + timeuntilGamestart().getMinute() + " min " + timeuntilGamestart().getSecond() + " sec\n"
                            + "Prisoners: " + getallPlayers("NONE :(") + "\n"
                            + "gtfotimer cancel to cancel timer\n"
                            + "gtfo ac to accept");
                } else {
                    eb.setDescription("Next Rundown(" + GtfoTimer.runddownName + ") in " + timeuntilGamestart().getHour() + " h " + timeuntilGamestart().getMinute() + " min " + timeuntilGamestart().getSecond() + " sec\n"
                            + "Prisoners: " + getallPlayers("NONE :(") + "\n"
                            + "gtfotimer cancel to cancel timer\n"
                            + "gtfo ac to accept");
                }

                eb.setColor(Color.getHSBColor(gethue(), 1F, 1F));
                channel.editMessageById(themessage, eb.build()).queue();

                if (LocalTime.now().isAfter(GtfoTimer.gamestart)) {
                    eb.setDescription("Starting Game with: " + getallPlayers("Nobody :("));
                    eb.setColor(Color.getHSBColor(0.70F, 1F, 1F));
                    channel.editMessageById(themessage, eb.build()).queue();
                    GtfoTimer.started = false;
                    break;
                }

                if (deletemassage) {
                    GtfoTimer.deletemassage = false;
                    GtfoTimer.started = false;
                    channel.deleteMessageById(themessage).queue();
                    break;
                }

                try {
                    Thread.sleep(5000);     //Halts Maul Intelij mir doch egal ob das busy waiting ist, ist eh async das ganze
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static LocalTime timeuntilGamestart() {

        LocalTime localTime = LocalTime.now();
        LocalTime diff;

        diff = LocalTime.of(gamestart.minusHours(localTime.getHour()).getHour(), gamestart.minusMinutes(localTime.getMinute()).getMinute(), gamestart.minusSeconds(localTime.getSecond()).getSecond());

        return diff.minusMinutes(1);
    }
}
