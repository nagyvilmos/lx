/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Env.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller;

/**
 * Manage the environment
 * @since 2017-06
 */
public class Env
        extends Command
{

    public Env(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    void execute()
    {
        switch (this.arguments.get(0))
        {
            case "load" :
            {
                this.environment.load();
                break;
            }
            case "save" :
            {
                this.environment.save();
                break;
            }
        }
        this.environment.getSettings().printFormatted(System.out);
    }

    @Override
    Command validate()
    {
        if (this.arguments.size() > 1 || (
                !this.arguments.get(0).isEmpty() &&
                !this.arguments.get(0).equals("load") &&
                !this.arguments.get(0).equals("save")))
        {
            return new InvalidCommand(environment, "format - env ['load'|'save']");
        }
        return super.validate(); //To change body of generated methods, choose Tools | Templates.
    }

}
