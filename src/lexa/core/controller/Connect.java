/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * Connect.java
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

import java.io.IOException;
import lexa.core.data.exception.DataException;

/**
 *
 * @author william
 */
class Connect extends Command {

    public Connect(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    Command validate()
    {
        switch (this.arguments.get(0))
        {
            case "" :
            {
                if (this.environment.getCurrentHost().isEmpty())
                {
                    return new InvalidCommand(environment, "Unknown host");
                }
                break;

            }
            default :
            {
                if (this.arguments.size() == 1 &&
                        this.environment.isHost(this.arguments.get(0)))
                {
                    break;
                }
                return new InvalidCommand(environment, "Unknown host " +
                        this.arguments.toString());
            }
        }
        return super.validate(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    void execute()
    {
        String host = this.arguments.get(0).isEmpty() ?
                    this.environment.getCurrentHost() :
                    this.arguments.get(0);
        try
        {
            this.environment.connect(host);
        }
        catch (IOException | DataException ex)
        {
            new InvalidCommand(this.environment,
                    "Unable to connect to host server " + host +
                    "\nException:\n" + ex.getLocalizedMessage()
            ).execute();
        }
        // now call host
        new Host(environment, new Arguments()).execute();
    }

}
