/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * LexaApp.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lxapp;

import lexa.core.controller.InlineCommandEntry;
import lexa.core.controller.CommandHandler;
import lexa.core.controller.ConsoleCommandEntry;

/**
 * Launch the Lexa Command line application
 * @author william
 * @since 2017-06
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
