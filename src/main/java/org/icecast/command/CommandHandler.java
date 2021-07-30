package org.icecast.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Locale;

public class CommandHandler {
    private static String command;
    private static String[] commandArgs;
    private static String[] rawCommand;
    private static MessageReceivedEvent messageReceivedEvent;
    public void handle(MessageReceivedEvent event)    {
        messageReceivedEvent = event;
        rawCommand = sanitize(event);
        command = rawCommand[1]; //Index 0 is self, in most cases its gona be the mention
        commandArgs = ArrayUtils.remove(rawCommand, 0);
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
    public String[] getCommandArgs() {
        return commandArgs;
    }

}
