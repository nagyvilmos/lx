/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * ConnectedCommand.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Arguments;
import lexa.core.controller.Environment;
import lexa.core.controller.command.HostCommand;

/**
 * A command that requires a connected session
 * @author william
 * @since 2017-06
 */
public abstract class ConnectedCommand
        extends HostCommand {

//    protected DataSet reply;
//
    ConnectedCommand(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }
//    @Override
//    public void execute() {
//        try {
//            this.open();
//        } catch (IOException ex) {
//             unable to connect
//            System.err.println("The host is already running.");
//            return;
//        }
//
//        this.sendCommand();
//        this.waitReply();
//        this.handleReply();
//        this.close();
//    }
//
//    @Override
//    public void message(Session session, DataSet message) {
//        this.reply = message;
//        this.callNotify();
//    }
//
//    public abstract void sendCommand();
//    public abstract void handleReply();
//    private synchronized void waitReply() {
//        try {
//            this.wait();
//        } catch (InterruptedException ex) {
//            System.err.println(ex);
//        }
//    }
//
//    private synchronized void callNotify() {
//        this.notifyAll();
//    }


}
