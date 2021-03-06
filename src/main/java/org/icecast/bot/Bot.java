package org.icecast.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.icecast.Main;
import org.icecast.MyErrorHandler;
import org.icecast.command.commandRunner;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends ListenerAdapter implements EventListener {
    private static final Logger logger = Logger.getLogger(Bot.class.getName());
    private static JDA jda;
    private static Yaml yaml;

    public Bot()    {
        logger.log(Level.INFO, Token());
        login(Token());
    }
    private static String Token()   {
        return Main.args[0];
    }
    private void yamlHandle()    {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        yaml = new Yaml();
        //Customer customer = yaml.load(inputStream);
    }
    /*
    public static void main(String[] args) throws LoginException {
        Yaml yaml = new Yaml();
        token = args[0];
        login(token);
    }
    */

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

    public void login(String token) {
        try {
            jda = JDABuilder.createDefault(token).build();
            jda.addEventListener();
            jda.getPresence().setActivity(Activity.of(Activity.ActivityType.DEFAULT, "https://github.com/11robert11/icecast-JDA-bot"));
        } catch (Exception e) {
            if(e.getClass() == ErrorResponseException.class)
            {
                logger.log(Level.SEVERE, "Couldn't Resolve api endpoint domain.");
            }
            if(e.getClass() == LoginException.class)    {
                logger.log(Level.SEVERE, "Couldn't login, possibly bad/invalid login token");
            }
            MyErrorHandler.dealWithStackTrace(e);
        }

    }


    public static JDA getAppJDA()  {
        return jda;
    }

}