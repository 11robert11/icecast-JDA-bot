package org.example;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Locale;

public class CommandHandler {
    private static String command;
    private static String[] commandAdj;
    private static String[] rawCommand;
    private static MessageReceivedEvent messageReceivedEvent;
    public void handle(MessageReceivedEvent event)    {
        messageReceivedEvent = event;
        rawCommand = sanitize(event);
        command = rawCommand[1]; //Index 0 is self, in most cases its gona be the mention
        System.out.println("line 18: " + command);
    }
    private String[] sanitize(MessageReceivedEvent event)   {
        String messageRaw = event.getMessage().toString();
        String message = (
                messageRaw
                        .split(":")[messageRaw.split(":").length-1])
                .replace("(" + event.getMessageId() + ")", "");
        message.toLowerCase(Locale.ROOT);
        return message.split(" ");
    }
    public MessageReceivedEvent getEvent()  {
        return messageReceivedEvent;
    }
    public String getCommand() {
        return command;
    }

}
