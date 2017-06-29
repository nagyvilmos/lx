/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Host.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller.command;

import lexa.core.controller.Environment;
import java.io.File;
import lexa.core.controller.Arguments;

/**
 * Manage the hosts
 * @author william
 * @since 2017-06
 */
class Host
        extends Command{

    private final static String ARG_ADD = "-a";
    private final static String ARG_REMOVE = "-r";
    private final static String ARG_LIST = "-l";
    private String fileName;
    private String option;
    private String selected;

    public Host(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    public void submit()
    {
        switch (this.option)
        {
            case ARG_LIST: break; //just list them
            case ARG_ADD :
            {
                this.executeAdd();
                return;
            }
            case ARG_REMOVE :
            {
                this.executeRemove();
                return;
            }
            default :
            {
                if (this.selected.isEmpty())
                {
                    break;
                }
                this.environment.setCurrentHost(this.arguments.get(0));
            }
        }
        this.executeList();
    }

    private void executeAdd()
    {
        this.environment.setHost(this.selected, this.fileName);
        this.environment.setCurrentHost(this.selected);
    }

    private void executeList()
    {
        String host = this.environment.getCurrentHost();
        if (!this.selected.isEmpty())
        {
            String current = (host.equals(this.selected)) ?
                    "  * current active host" :
                    "";
            System.out.println("Host  : " +
                    this.selected + current);
            System.out.println("File  : " +
                    this.environment.getHostFile(this.selected));
            System.out.println("Status: " +
                    this.environment.getHostStatus(this.selected));
            System.out.println("Update: " +
                    this.environment.getHostUpdateDate(this.selected));
        }
        else
        {
            for (String hostName : this.environment.getHostNames())
            {
                String prefix = (host.equals(hostName)) ?
                    "* " : "  ";
                String suffix = (hostName.length() < 11) ?
                    "           ".substring(0,(11-hostName.length())) : "  ";
                System.out.println(prefix + hostName + suffix +
                        this.environment.getHostStatus(hostName) + "  " +
                        this.environment.getHostFile(hostName));
            }
        }
        System.out.println("");
    }

    private void executeRemove()
    {
        throw new UnsupportedOperationException("Host.executeRemove not supported yet.");
    }

    private boolean validateHostName(boolean exists)
    {
        if (this.environment.isHost(this.selected))
        {
            return exists; // already exists
        }
        if (exists)
        {
            return false;
        }

        return (this.selected.matches("^[\\w]+$"));
    }

    private boolean validateHostFile()
    {
        if (!this.validateHostName(false) ||
                !new File(this.fileName).exists() ||
                this.arguments.size() != 3)
        {
            return false; // invalid name or missing file
        }

        // check if the host file is set to a different host
        for (String host : this.environment.getHostNames())
        {
            if (this.environment.getHostFile(host).equals(this.fileName))
            {
                return host.equals(this.selected);
            }
        }
        return true; // no one has the file
    }

    @Override
    public Command validate()
    {
        if (this.arguments.isEmpty())
        {
            this.option = ARG_LIST;
            this.selected = "";
        }
        else if (this.arguments.get(0).charAt(0) == '-')
        {
            this.option = this.arguments.get(0);
            this.selected = this.arguments.get(1);
            this.fileName = this.arguments.get(2);
        }
        else
        {
            this.option = "";
            this.selected = this.arguments.get(0);
        }

        switch (this.option)
        {
            case ARG_ADD :
            {
                if (this.arguments.size() == 3 &&
                        this.validateHostFile())
                {
                    break;
                }
                return InvalidCommand.command(environment,
                        "Invalid name or path for host '" +
                        new Arguments(this.arguments).toString() + "'"
                );
            }
            case ARG_LIST :
            {
                if (this.arguments.size() < 3 &&
                        (this.selected.isEmpty() ||
                        this.validateHostName(true)))
                {
                    break;
                }
                return InvalidCommand.command(environment,
                        "Invalid name or path for host '" +
                        new Arguments(this.arguments).toString() + "'"
                );
            }
            case ARG_REMOVE :
            {
                if (this.arguments.size() == 2 &&
                        this.validateHostName(true))
                {
                    break;
                }
                return InvalidCommand.command(environment,
                        "Invalid host '" +
                        new Arguments(this.arguments).toString() + "'"
                );
            }
            default :
            {
                if (this.validateHostName(true) &&
                        this.arguments.size() == 1)
                {
                    break;
                }
                return InvalidCommand.command(environment,
                        "Invalid host name '" +
                        this.arguments.toString() + "'"
                );
            }
        }
        return super.validate();
    }

}
