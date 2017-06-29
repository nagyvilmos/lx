/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Restart.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * Restart a host
 * @author william
 * @since 2017-06
 */
public class Restart extends HostCommand {

    Restart(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }
    @Override
    public void submit() {
//        HostCommand stop = new Stop(this.environment, this.arguments);
//        stop.initialise(this.hostConfig, this.command);
//        stop.execute();
//
//        HostCommand start = new Start(this.environment, this.arguments);
//        start.initialise(this.hostConfig, this.command);
//        start.execute();
    }

}
