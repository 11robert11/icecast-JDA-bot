package org.example;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.internal.handle.GuildCreateHandler;

import java.util.Arrays;

public class commandRunner {
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

        }
    }

    private static void join(CommandHandler commandhandle) {
        System.out.println("joining achelln");
        VoiceChannel connectedChannel = commandhandle.getEvent().getMember().getVoiceState().getChannel();
        if(connectedChannel == null)    {
            commandhandle.getEvent().getChannel().sendMessage("It seems you're note in a channel").queue();
            return;
        }
        AudioManager audioManager = commandhandle.getEvent().getGuild().getAudioManager();
        audioManager.openAudioConnection(connectedChannel);
        commandhandle.getEvent().getChannel().sendMessage("Connected to " + connectedChannel.getName() + " with " + connectedChannel.getMembers()).queue();
        audioManager.getReceivingHandler().handleUserAudio();
    }
    public static void help(CommandHandler commandHandler)  {
        commandHandler.getEvent().getChannel().sendMessage("help stuff").queue();
        System.out.println("help stuff sent");
    }
}