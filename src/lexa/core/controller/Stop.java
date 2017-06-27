/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexa.core.controller;

/**
 *
 * @author william
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
