/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * InlineCommandEntry.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

import lexa.core.controller.command.Command;
import java.util.Arrays;
import java.util.List;

/**
 * Command handler for an in-line call
 * @author william
 * @since 2017-06
 */
public class InlineCommandEntry
        extends CommandHandler {

    private final Arguments arguments;

    public InlineCommandEntry(String[] arguments)
    {
        this(Arrays.asList(arguments));
    }
    public InlineCommandEntry(List<String> arguments)
    {
        this.arguments=new Arguments(arguments);
    }
    @Override
    public void execute()
    {
        Command.getCommand(this.environment, this.arguments).execute();
    }

}
