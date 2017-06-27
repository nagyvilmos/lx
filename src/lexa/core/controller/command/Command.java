/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Command.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * A command for managing the Lexa stack
 * @author william
 * @since 2017-06
 */
public abstract class Command
{
    protected final Environment environment;
    protected final Arguments arguments;
    protected Command(Environment environment, Arguments arguments)
    {
        this.environment = environment;
        this.arguments = arguments;
    }

    protected void handleException(String text, Exception ex)
    {
        System.out.println(text);
        ex.printStackTrace(System.out);
    }

    public Command validate()
    {
        return this;
    }

    public abstract void execute();

    public static Command getCommand(Environment environment, Arguments arguments)
    {
        switch (arguments.get(0))
        {
            case "help" :       return new Help     (environment, new Arguments(arguments));
            case "env" :        return new Env      (environment, new Arguments(arguments));
            case "host" :       return new Host     (environment, new Arguments(arguments));
            case "connect" :    return new Connect  (environment, new Arguments(arguments));
            case "start" :      return new Start    (environment, new Arguments(arguments));
            case "stop" :       return new Stop     (environment, new Arguments(arguments));
            case "restart" :    return new Restart  (environment, new Arguments(arguments));
            case "exit" :       return new Exit     (environment, new Arguments(arguments));
        }
        return new InvalidCommand(environment, arguments);
    }
}
