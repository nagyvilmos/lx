/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Start.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import java.io.IOException;
import lexa.core.controller.Arguments;
import lexa.core.controller.launcher.JavaProcessLauncher;

/**
 * Start a host
 * @author william
 * @since 2017-06
 */
public class Start
    extends HostCommand {
    Start(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }


    @Override
    public void execute() {
        try
        {
            JavaProcessLauncher.launch("lxhost.HostApp", false, this.getHostFile());
        }
        catch (IOException | InterruptedException ex)
        {
             this.handleException("Unable to launch host", ex);
        }
    }
}
