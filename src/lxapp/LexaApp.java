/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lxapp;

import lexa.core.controller.InlineCommandEntry;
import lexa.core.controller.CommandHandler;
import lexa.core.controller.ConsoleCommandEntry;

/**
 *
 * @author william
 */
public class LexaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // read the arguments
        CommandHandler handler;
        if (args == null || args.length == 0)
        {
            handler = new ConsoleCommandEntry();
        }
        else
        {
            handler = new InlineCommandEntry(args);
        }
        // expect lx <command> <hostname> [<arg> ...]
		handler.execute();
		//... and so on ...
        // load the command handler

        // execute

    }
}
