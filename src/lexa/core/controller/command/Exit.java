/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Exit.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * Exit the command line environment
 * @author william
 * @since 2017-06
 */
class Exit extends Command {

    public Exit(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    public Command validate()
    {
        if (this.arguments.size() > 1)
        {
            return new InvalidCommand(this.environment, "invalid command arguments");
        }
        return super.validate();
    }


    @Override
    public void execute()
    {
        this.environment.close();
    }

}
