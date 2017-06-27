/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Stop.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

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
    void execute()
    {
        throw new UnsupportedOperationException("Stop.execute not supported yet.");
    }

}
