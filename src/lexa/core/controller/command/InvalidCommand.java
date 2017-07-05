/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * InvalidCommand.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * When a command is invalid it is sent here.
 * @author william
 * @since 2017-06
 */
class InvalidCommand extends Command {

    private String message;

    public InvalidCommand(Environment environment, String arguments)
    {
        this(environment, new Arguments(arguments));
    }
    public InvalidCommand(Environment environment, Arguments arguments)
    {
        // add a dummy arg in front as it is lost further down the stack;
        super(environment, arguments ); //Arguments.insert(arguments, 0, "invalid command"));
    }

    @Override
    public Command validate()
    {
        if (this.arguments.isEmpty())
        {
            this.message = "";
        }
        else
        {
            this.message = this.arguments.toString();
        }

        return super.validate();
    }

    @Override
    public void submit()
    {
        if (this.message.isEmpty())
        {
            return;
        }
        System.out.print("Invalid command:");

        System.out.println(this.message);
    }


    public static Command command(Environment environment, String arguments)
    {
        return new InvalidCommand(environment, arguments).validate();
    }
    public static Command command(Environment environment, Arguments arguments)
    {
        return new InvalidCommand(environment, arguments).validate();
    }

}
