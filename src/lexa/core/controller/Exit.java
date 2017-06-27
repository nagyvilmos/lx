/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * Exit.java
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
class Exit extends Command {

    public Exit(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    Command validate()
    {
        if (this.arguments.size() > 1)
        {
            return new InvalidCommand(this.environment, "invalid command arguments");
        }
        return super.validate();
    }


    @Override
    void execute()
    {
        this.environment.close();
    }

}
