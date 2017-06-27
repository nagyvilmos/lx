/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * InlineCommandEntry.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: Month YEAR
 *------------------------------------------------------------------------------
 * Change Log
 * Date:        By: Description:
 * ----------   --- ------------------------------------------------------------
 * -            -   -
 *==============================================================================
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lexa.core.controller;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author william
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
