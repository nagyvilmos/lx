/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * Environment.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: February 2017
 *==============================================================================
 */
package lexa.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lexa.core.comms.Session;
import lexa.core.data.DataItem;
import lexa.core.data.DataSet;
import lexa.core.data.ArrayDataSet;
import lexa.core.data.DataType;
import lexa.core.data.config.ConfigDataSet;
import lexa.core.data.exception.DataException;
import lexa.core.data.io.DataReader;
import lexa.core.data.io.DataWriter;

/**
 * The runtime environment
 * @author william
 * @since 2017-06
 */
public final class Environment {

    private static final String PROMPT_SUFFIX = "> ";
    private static final String NO_HOST_PROMPT = "[no host]" + PROMPT_SUFFIX;
    private final File envFile;
    private final File helpFile;
    private final Map<String, Session> sessionList;


    private DataSet environment;
    private DataSet helpData;
    private DataSet hostConfig; // any loaded config files;
    private String prompt;
    private boolean runnng;

    public Environment()
    {
        this.envFile = new File("lx.config.lexa");
        this.helpFile = new File("lx.help.lexa");
        this.sessionList = new HashMap();
        this.load();
        this.runnng = true;
    }

    public void connect(String host) throws IOException, DataException
    {
        if (this.sessionList.containsKey(host))
        {
            return; //already have one
        }

        DataSet hostCfg = this.loadConfig("host", host, this.getHostFile(host));
        ConfigDataSet sessionCfg = new ConfigDataSet(
                new ArrayDataSet()
                .put("host","localhost")
                .put("port",hostCfg.item("server.port").getInteger())
        );
        Session session = new Session(sessionCfg);
    }

    public void setCurrentHost(String hostName)
    {
        this.environment.put("hostName",hostName);
        this.setPrompt(hostName);
    }

    public String getHelpSummary(Arguments arguments)
    {
        DataSet help = this.helpData;
        for (int a = 0; a < arguments.size(); a++)
        {
            if (!help.contains(arguments.get(a)) ||
                    !help.getType(arguments.get(a)).equals(DataType.DATA_SET))
            {
                break;
            }
            help=help.getDataSet(arguments.get(a));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Summary for ").append(
                arguments.isEmpty() ? "lx environment" :
                arguments.toString()
        ).append('\n');
        if (help.contains("summary"))
        {
            sb.append(help.getString("summary"));
        }
        for (DataItem item : help)
        {
            if (item.getKey().equals("summary") ||
                    (item.getKey().equals("help") && !arguments.isEmpty()))
            {
                continue;
            }
            sb.append('\n').append(item.getKey()).append('\t');
            String text = "no summary information available";
            switch (item.getType())
            {
                case STRING :
                {
                    text = item.getString();
                    break;
                }
                case DATA_SET :
                {
                    DataSet sub = item.getDataSet();
                    if (sub.contains("summary"))
                    {
                        text = sub.getString("summary");
                    }
                    else if (sub.contains("help"))
                    {
                        text = sub.getString("help");
                    }
                    break;
                }
            }
            int eol = text.indexOf('\n');
            if (eol > -1)
            {
                sb.append(text.substring(0, eol));
            }
            else
            {
                sb.append(text);
            }
        }
        return sb.toString();
    }

    public File getHostFile(String hostName)
    {
        return new File(this.environment.getDataSet("hostConfig")
                .getDataSet(hostName)
                .getString("hostFile"));
    }

    public String[] getHostNames()
    {
        return this.environment.getDataSet("hostConfig").keys();
    }

    public String getHostStatus(String hostName)
    {
        // do we have a connection?
        DataSet hostConfig = this.environment
                .getDataSet("hostConfig")
                .getDataSet(hostName);
        if (!this.sessionList.containsKey(hostName))
        {
            hostConfig.put("status","unknown");
        } else
        {
            throw new IllegalArgumentException("cannot check status");
            //hostConfig.put("status","offline");
        }
        return hostConfig.getString("status");
    }

    public Date getHostUpdateDate(String hostName)
    {
        return this.environment.getDataSet("hostConfig")
                .getDataSet(hostName)
                .getDate("updated");
    }

    public String getPrompt()
    {
        return this.prompt;
    }

    public DataSet getSettings()
    {
        return this.environment;
    }

    public String getCurrentHost()
    {
        return this.environment.getString("hostName");
    }

    public String getHelp(Arguments args)
    {
        DataSet help = this.helpData;
        for (int a = 0; a < args.size(); a++)
        {
            switch (help.getType(args.get(a)))
            {
                case STRING : return help.getString(args.get(a));
                case DATA_SET :
                {
                    help = help.getDataSet(args.get(a));
                    break;
                }
                default: return null;
            }
        }

        return help.contains("help") ?
                help.getString("help") :
                help.contains("summary") ?
                    help.getString("summary") :
                    null;
    }

    public boolean isHost(String hostName)
    {
        return this.environment.getDataSet("hostConfig")
                .contains(hostName);
    }

    public boolean isRunning()
    {
        return this.runnng;
    }

    public void close()
    {
        this.runnng=false;
    }

    public void save()
    {
        this.environment.put("saved",new Date());
        DataWriter dw = null;
        try
        {
            dw = new DataWriter(this.envFile);
            dw.write(this.environment);
        }
        catch (IOException ex)
        {
            ex.printStackTrace(System.out);
        }
        finally
        {
            if (dw != null)
                try {
                    dw.close();
            }
            catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }

    }

    public void load()
    {
        this.hostConfig = new ArrayDataSet();
        this.helpData = loadFile(this.helpFile);
        this.environment = loadFile(this.envFile);
        this.environment.put("loaded",new Date());
        if (!this.environment.contains("hostConfig"))
        {
            this.environment.put("hostConfig", new ArrayDataSet());
        }

        if (!this.environment.contains("autoLoad"))
        {
            this.environment.put("autoLoad", false);
        }
        if (!this.environment.contains("keepHost"))
        {
            this.environment.put("keepHost", false);
        }
        if (!this.environment.contains("hostName") || !this.environment.getBoolean("keepHost"))
        {
            this.environment.put("hostName", "");
        }
        this.setPrompt(this.environment.getString("hostName"));

        for (Session session : this.sessionList.values())
        {
            session.close();
        }
        this.sessionList.clear();
    }

    public void setHost(String hostName, String hostFile)
    {
        this.environment.getDataSet("hostConfig")
                .put(hostName,
                        new ArrayDataSet()
                            .put("hostFile",hostFile)
                            .put("status","?")
                            .put("updated", new Date())
        );
    }

    private void setPrompt(String hostName)
    {
        if (hostName.isEmpty())
        {
            this.prompt = NO_HOST_PROMPT;
        }
        else
        {
            this.prompt = hostName + PROMPT_SUFFIX;
        }
    }

    private DataSet loadConfig(String block, String config, File configFile)
    {
        // get the data either cached or from the file
        DataSet blockDs;
        if (!this.hostConfig.contains(block))
        {
            this.hostConfig.put(block, new ArrayDataSet() );
        }
        blockDs = this.hostConfig.getDataSet(block);
        if (!blockDs.contains(config))
        {
            try
            {
                blockDs.put(config,
                        new DataReader(configFile).read());
            }
            catch (IOException ex)
            {

            }

        }
        return blockDs.getDataSet(config);
    }

    private DataSet loadFile(File file)
    {
        if (file.exists())
        {
            try
            {
                return new DataReader(file).read();
            }
            catch (IOException ex)
            {
                ex.printStackTrace(System.out);
            }
        }
        return new ArrayDataSet();
    }
}
