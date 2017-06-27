/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * Help.java
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

/**
 *
 * @author william
 */
public class Help
        extends Command
{

    public Help(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }


    @Override
    void execute()
    {
        System.out.print(this.loadHelpText());
        System.out.print("\n\n");
    }
    private String loadHelpText()
    {
        String help = this.arguments.isEmpty() ?
                this.environment.getHelp(new Arguments("help")) :
                this.environment.getHelp(this.arguments);
        if (help != null)
        {
            return help;
        }

        if (this.arguments.get(0).equals("-c"))
        {
            return this.environment.getHelpSummary(new Arguments(arguments));
        }
        return "No help available for subject " +
                (this.arguments.isEmpty() ?
                    "help" :
                    this.arguments.toString());
    }

}
