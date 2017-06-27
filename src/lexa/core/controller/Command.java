/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Command.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller;

/**
 * A command for managing the Lexa stack
 * @author william
 * @since 2017-06
 */
abstract class Command
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

    Command validate()
    {
        return this;
    }

    abstract void execute();

    static Command getCommand(Environment environment, Arguments arguments)
    {
        switch (arguments.get(0))
        {
            case "connect" :    return new Connect(environment, new Arguments(arguments));
            case "env" :        return new Env(environment, new Arguments(arguments));
            case "exit" :       return new Exit(environment, new Arguments(arguments));
            case "help" :       return new Help(environment, new Arguments(arguments));
            case "host" :       return new Host(environment, new Arguments(arguments));
            case "start" :      return new Start(environment, new Arguments(arguments));
            case "stop" :       return new Stop(environment, new Arguments(arguments));
        }
        return new InvalidCommand(environment, arguments);
    }
}
