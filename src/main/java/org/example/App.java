package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends ListenerAdapter implements EventListener {
    private static String token;
    private static final Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String args[]) throws LoginException {
        token = args[0];
        login(token);
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //logger.log(Level.INFO, "Mentioned: " + event.getMessage().isMentioned(event.getJDA().getSelfUser()));
        if(event.getMessage().getAuthor().isBot())  {
            logger.log(Level.INFO, "Message from bot.");
            return;
        }
        if(event.getMessage().isMentioned(event.getJDA().getSelfUser()));   {
            logger.log(Level.INFO, "Is not bot?" + String.valueOf(event.getAuthor().isBot() == false));
            logger.log(Level.INFO, "I have been mentioned!!");

            commandRunner.doThis(event);
        }
    }


    public static void login(String token) {
        try {
            JDABuilder.createDefault(token).build().addEventListener(new App());
        } catch (LoginException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Login Failed");
        }
    }
}