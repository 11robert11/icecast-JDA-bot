package org.example;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.internal.handle.GuildCreateHandler;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class commandRunner {
    private static final Logger logger = Logger.getLogger(commandRunner.class.getName());
    public static void doThis(MessageReceivedEvent event) {
        CommandHandler commandhandle = new CommandHandler();
        commandhandle.handle(event);

        System.out.println(commandhandle.getEvent().getMessage().toString());


        switch (commandhandle.getCommand()) {
            case "join":
                join(commandhandle);
                break;
            case "help":
                help(commandhandle);
                break;
            case "leave":
                leave(commandhandle);
                break;
            case "connection":
                connection(commandhandle);


        }
    }
    private static void connection(CommandHandler commandhandle)    {
        commandhandle.getEvent().getChannel().sendMessage(Arrays.toString(commandhandle.getCommandArgs())).queue();
    }
    private static void leave(CommandHandler commandhandle) {
        logger.log(Level.INFO, "Leaving Voice Channel");
        Bot.getAppJDA().getDirectAudioController().disconnect(commandhandle.getEvent().getGuild());
    }
    private static void join(CommandHandler commandhandle) {
        logger.log(Level.INFO, "Trying to join voice channel");
        VoiceChannel connectedChannel = commandhandle.getEvent().getMember().getVoiceState().getChannel();
        if(connectedChannel == null)    {
            commandhandle.getEvent().getChannel().sendMessage("It Seems You're Not in a Voice Channel").queue();
            logger.log(Level.INFO, "User Not in a Voice Channel");
            return;
        }
        AudioManager audioManager = commandhandle.getEvent().getGuild().getAudioManager();
        audioManager.openAudioConnection(connectedChannel);
        commandhandle.getEvent().getChannel().sendMessage("Connected to " + connectedChannel.getName() + " with " + connectedChannel.getMembers()).queue();
    }
    public static void help(CommandHandler commandHandler)  {
        commandHandler.getEvent().getChannel().sendMessage("help stuff").queue();
        System.out.println("help stuff sent");
    }
}