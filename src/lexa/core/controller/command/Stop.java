/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Stop.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * Stop a host
 * @author william
 * @since 2017-06
 */
public class Stop
        extends HostCommand {

    Stop(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    public void submit()
    {
        throw new UnsupportedOperationException("Stop.submit not supported yet.");
    }

}
