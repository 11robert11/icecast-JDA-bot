package org.icecast;

import org.icecast.bot.Bot;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyErrorHandler {
    private static final Logger logger = Logger.getLogger(Bot.class.getName());
    public static void dealWithStackTrace(Exception e)    {
        Boolean printStackTrace = false;
        if(printStackTrace) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "Hiding Stack Trace: " + e.getClass());
    }
}
