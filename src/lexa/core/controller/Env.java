/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * Env.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: Month YEAR
 *------------------------------------------------------------------------------
 * Change Log
 * Date:        By: Description:
 * ----------   --- ------------------------------------------------------------
 * -            -   -
 *==============================================================================
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lexa.core.controller;

/**
 *
 * @author william
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
