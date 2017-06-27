/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * CommandHandler.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller;

/**
 * A handler for Lexa commands
 * @author william
 * @since 2017-06
 */
public abstract class CommandHandler
{
    protected final Environment environment = new Environment();


    public abstract void execute();
}
