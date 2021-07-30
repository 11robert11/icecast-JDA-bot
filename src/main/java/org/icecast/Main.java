package org.icecast;

import org.icecast.bot.Bot;

public class Main {
    public static String[] args;
    public static void main(String[] args)   {
        Main.args = args;
        new Bot();
    }
}
