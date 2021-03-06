/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Connect.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import java.io.IOException;
import lexa.core.controller.Arguments;
import lexa.core.data.exception.DataException;

/**
 * Connect to a host
 * @author william
 * @since 2017-06
 */
class Connect extends Command {

    public Connect(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
   public Command validate()
    {
        switch (this.arguments.get(0))
        {
            case "" :
            {
                if (this.environment.getCurrentHost().isEmpty())
                {
                    return InvalidCommand.command(environment, "Unknown host");
                }
                break;

            }
            default :
            {
                if (this.arguments.size() == 1 &&
                        this.environment.isHost(this.arguments.get(0)))
                {
                    break;
                }
                return InvalidCommand.command(environment, "Unknown host " +
                        this.arguments.toString());
            }
        }
        return super.validate(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void submit()
    {
        String host = this.arguments.get(0).isEmpty() ?
                    this.environment.getCurrentHost() :
                    this.arguments.get(0);
        try
        {
            this.environment.connect(host);
        }
        catch (IOException | DataException ex)
        {
            InvalidCommand.command(this.environment,
                    "Unable to connect to host server '" + host +
                    "'\nException:\n" + ex.getLocalizedMessage()
            ).submit();
        }
        // now call host
        new Host(environment,
                new Arguments(host)).execute();
    }

}
