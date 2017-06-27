/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * ConsoleCommandEntry.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller;

import lexa.core.controller.command.Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Command handler for the console
 * @author william
 * @since 2017-06
 */
public class ConsoleCommandEntry
        extends CommandHandler
{

    private BufferedReader input;

    @Override
    public void execute()
    {
        this.openConsole();
        int badCommandCount = 0;
        while (environment.isRunning() && badCommandCount < 3)
        {
            try
            {
                this.executeCommamd();
                badCommandCount = 0;
            }
            catch (Exception ex)
            {
                // really shouldn't hit here!
                ex.printStackTrace(System.out);
                badCommandCount++;
            }
        }
        this.closeConsole();
    }

    private void closeConsole()
    {
        try
        {
            this.input.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace(System.out);
        }
        this.input = null;
        System.out.println("lx closed");
    }

    private void executeCommamd()
    {
        System.out.print(this.environment.getPrompt());
        String commandText;
        try
        {
            commandText  = this.input.readLine().trim(); // ignore white spaces
        }
        catch (IOException ex)
        {
            ex.printStackTrace(System.out);
            this.environment.close();
            return;
        }
        Command.getCommand(this.environment, new Arguments(commandText))
                .validate()
                .execute();
    }

    private boolean openConsole()
    {
        if (System.in == null)
        {
            System.out.println("no input stream available");
            return false;
        }
        this.input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("lx open");
        this.environment.getSettings().printFormatted(System.out);
        return true;
    }


}
