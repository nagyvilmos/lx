/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * InvalidCommand.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

/**
 * When a command is invalid it is sent here.
 * @author william
 * @since 2017-06
 */
class InvalidCommand extends Command {

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
    public void execute()
    {
        if (this.arguments.isEmpty())
        {
            return;
        }
        System.out.print("Invalid command:");

        System.out.println(this.arguments.toString());
    }
}
