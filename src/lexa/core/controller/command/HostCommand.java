/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * HostCommand.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import java.io.File;
import lexa.core.controller.Arguments;
import lexa.core.controller.Environment;
import lexa.core.controller.command.InvalidCommand;
import lexa.core.controller.command.Command;

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
    public Command validate()
    {
        if (this.arguments.size() > 1)
        {
            return new InvalidCommand(environment, "Only one host can be provided");
        }
        String hostName = this.arguments.get(0);
        if (this.environment.getCurrentHost().isEmpty() && hostName.isEmpty())
        {
            return new InvalidCommand(environment, "Missing host name");
        }
        if (!hostName.isEmpty() &&  !this.environment.isHost(hostName))
        {
            return new InvalidCommand(environment, "Unknown host name");
        }
        return super.validate();
    }

    protected File getHostFile()
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
