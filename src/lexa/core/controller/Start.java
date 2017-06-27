/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexa.core.controller;

import java.io.IOException;
import lexa.core.controller.launcher.JavaProcessLauncher;

/**
 *
 * @author william
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
