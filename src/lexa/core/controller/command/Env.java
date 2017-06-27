/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Env.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import lexa.core.controller.Arguments;

/**
 * Manage the environment
 * @since 2017-06
 */
public class Env
        extends Command
{

    private String setting;
    private Object value;

    public Env(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    public void execute()
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
            case "set" :
            {
                this.executeSet();
                break;
            }
        }
        this.environment.getSettings().printFormatted(System.out);
    }

    @Override
    public Command validate()
    {
        String message = "format - env [load|save|set <setting> <value>]";
        if (this.arguments.size() < 2)
        {
            if (!this.arguments.get(0).isEmpty() &&
                    !this.arguments.get(0).equals("load") &&
                    !this.arguments.get(0).equals("save"))
            {
                return new InvalidCommand(environment, message);
            }
        }
        else if (this.arguments.size() != 3 ||
                !this.arguments.get(0).equals("set") ||
                !this.validateSet())
        {
            return new InvalidCommand(environment, message);
        }
        return super.validate();
    }

    private void executeSet()
    {
       this.environment.getSettings().put(this.setting, this.value);
    }

    private boolean validateSet()
    {
        this.setting = this.arguments.get(1);
        switch (this.setting)
        {
            case "autoLoad" :
            case "keepHost" :
            case "runInternal" :
            {
                return this.validateSetBoolean();
            }
        }
        return false;
    }

    private boolean validateSetBoolean()
    {
        String valStr = this.arguments.get(2);
        try
        {
            this.value = Boolean.parseBoolean(valStr);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

}
