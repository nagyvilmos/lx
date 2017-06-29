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
import lexa.core.data.exception.DataException;

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
    public void submit() {
        try
        {
            JavaProcessLauncher.launch("lxhost.HostApp", false,
                    this.getHostFile().getAbsolutePath());
            this.environment.connect(this.getHostName());
            this.environment.setCurrentHost(this.getHostName());
        }
        catch (IOException | InterruptedException | DataException ex)
        {
             this.handleException("Unable to start host", ex);
        }
    }
}
