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
        while (environment.isRunning())
        {
            this.execeteCommand();
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

    private void execeteCommand()
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
