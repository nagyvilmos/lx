/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Help.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * Get help on a subject
 * @author william
 * @since 2017-06
 */
class Help
        extends Command
{

    private static final String HELP_LIST = "-l";

    Help(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }


    @Override
    public void execute()
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

        if (HELP_LIST.equals(this.arguments.get(0)))
        {
            return this.environment.getHelpSummary(new Arguments(arguments));
        }
        return "No help available for subject " +
                (this.arguments.isEmpty() ?
                    "help" :
                    this.arguments.toString());
    }

}
