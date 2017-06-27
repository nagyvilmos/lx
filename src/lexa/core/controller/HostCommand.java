/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * HostCommand.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

/**
 * A command to deal with a host
 * @author william
 * @since 2017-06
 */
abstract class HostCommand
        extends Command {

    HostCommand(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    Command validate()
    {
        if (this.environment.getCurrentHost().isEmpty() && this.arguments.get(0).isEmpty())
        {
            return new InvalidCommand(environment, "Missing host name");
        }
        else if (this.arguments.size() > 1)
        {
            return new InvalidCommand(environment, "Only one host can be provided");
        }
        return super.validate();
    }

    protected String getHostFile()
    {
        return this.environment.getHostFile(this.getHostName());
    }
    protected String getHostName()
    {
        return this.arguments.get(0).isEmpty() ?
                this.environment.getCurrentHost() :
                this.arguments.get(0);
    }

}
