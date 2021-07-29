package org.example;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

public class commands {
    public static void whatDoIDo(MessageReceivedEvent event) {
        String[] commandFull = event.getMessage().toString().split(" "); //Splits message into parts regexing at spaces
        switch (commandFull[0]) {
            case "join":
                join(commandFull, event);
                break;

        }
    }

    private static void join(String[] commandAdj, MessageReceivedEvent event) {
        System.out.println(Arrays.toString(commandAdj));
        System.out.println("asdfasdfasdf");
    }
}