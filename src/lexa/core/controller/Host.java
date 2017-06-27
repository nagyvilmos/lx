/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Host.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

import java.io.File;

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

    public Host(Environment environment, Arguments arguments)
    {
        super(environment, arguments);
    }

    @Override
    void execute()
    {
        switch (this.arguments.get(0))
        {
            case "" :
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
                this.environment.setCurrentHost(this.arguments.get(0));
            }
        }
        this.executeList();
    }

    private void executeAdd()
    {
        this.environment.setHost(this.arguments.get(1), this.arguments.get(2));
        this.environment.setCurrentHost(this.arguments.get(1));
        this.executeList();
    }

    private void executeList()
    {
        if (!this.arguments.get(1).isEmpty())
        {
            String host = this.arguments.get(1);
            System.out.println("Host  : " + host);
            System.out.println("File  : " + this.environment.getHostFile(host));
            System.out.println("Status: " + this.environment.getHostStatus(host));
            System.out.println("Update: " + this.environment.getHostUpdateDate(host));
        }
        else
        {
            String host = this.environment.getCurrentHost();
            for (String hostName : this.environment.getHostNames())
            {
                String prefix = (host.equals(hostName)) ?
                    "* " : "  ";
                String suffix = (hostName.length() < 11) ?
                    "          ".substring(0,(11-hostName.length())) : " ";
                System.out.println(prefix + hostName + suffix +
                        this.environment.getHostStatus(host) + " " +
                        this.environment.getHostFile(hostName));
            }
        }
        System.out.println("");
    }

    private void executeRemove()
    {
        throw new UnsupportedOperationException("Host.executeRemove not supported yet.");
    }

    private boolean validateHostName(String hostName, boolean exists)
    {
        if (this.environment.isHost(hostName))
        {
            return true; // already exists
        }
        if (exists)
        {
            return false;
        }

        return (hostName.matches("^[\\w]+$"));
    }
    private boolean validateHostFile(String hostName, String hostFile)
    {
        if (!this.validateHostName(hostName, false) ||
                !new File(hostFile).exists())
        {
            return false; // invalid name or missing file
        }
        // check if the host file is set to a different host
        for (String host : this.environment.getHostNames())
        {
            if (this.environment.getHostFile(host).equals(hostFile))
            {
                return host.equals(hostName);
            }
        }
        return true; // no one has the file
    }

    @Override
    Command validate()
    {
        switch (this.arguments.get(0))
        {
            case "" : break; // simplest case, same as host -l
            case ARG_ADD :
            {
                if (this.validateHostFile(
                        this.arguments.get(1),
                        this.arguments.get(2)))
                {
                    break;
                }
                return new InvalidCommand(environment,
                        "Invalid name or path for host '" +
                        new Arguments(this.arguments).toString() + "'"
                );
            }
            case ARG_LIST :
            {
                if (this.arguments.get(1).isEmpty())
                {
                    break;
                }
                // carry on as both -l and -r now need a valid
            }
            case ARG_REMOVE :
            {
                if (this.arguments.size() == 2 &&
                        this.validateHostName(this.arguments.get(1), true))
                {
                    break;
                }
                return new InvalidCommand(environment,
                        "Invalid host '" +
                        new Arguments(this.arguments).toString() + "'"
                );
            }
            default :
            {
                if (this.validateHostName(this.arguments.get(0), true))
                {
                    break;
                }
                return new InvalidCommand(environment,
                        "Invalid host name '" +
                        this.arguments.toString() + "'"
                );
            }
        }
        return super.validate();
    }

}
