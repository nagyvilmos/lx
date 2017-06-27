/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * Arguments.java
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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william
 */
class Arguments {
    protected final List<String> arguments;
    private final Arguments parent;
    private final int size;
    Arguments()
    {
        this(new ArrayList());
    }
    Arguments(String arguments)
    {
        this.parent=null;
        // create an array:
        this.arguments = new ArrayList();
        int start = -1;
        boolean inQuotes = false;
        for (int pos = 0;
                pos < arguments.length();
                pos++)
        {
            if (inQuotes)
            {
                if (arguments.charAt(pos) == '"')
                {
                    this.arguments.add(arguments.substring(start,pos));
                    inQuotes=false;
                }
            }
            else
            {
                switch (arguments.charAt(pos))
                {
                    case '"' :
                    {
                        inQuotes=true;
                        start=pos+1;
                        break;
                    }
                    case ' ' :
                    case '\t' :
                    case '\n' :
                    {
                        if (start >-1)
                        {
                            this.arguments.add(arguments.substring(start,pos));
                            start=-1;
                        }
                        break;
                    }
                    default :
                    {
                        if (start == -1)
                        {
                            start=pos;
                        }
                    }
                }
            }
        }
        if (inQuotes)
        {
            // the command cannot be split
            this.arguments.clear();
            start=0;
        }
        if (start >-1)
        {
            this.arguments.add(arguments.substring(start,arguments.length()));
        }
        this.size = this.arguments.size();
    }

    Arguments(List<String> arguments)
    {
        this.parent=null;
        this.arguments=arguments;
        this.size = this.arguments.size();
    }

    Arguments(Arguments parent)
    {
        this.parent = parent;
        this.arguments=null;
        this.size = this.parent.size() > 0 ?
                this.parent.size()-1 : 0;
    }

    boolean isEmpty()
    {
        return (this.size == 0);
    }

    String get(int arg)
    {
        if (this.parent != null)
        {
            return this.parent.get(arg + 1);
        }
        if (arg < 0 || arg >= this.size())
        {
            return ""; // blank string NOT null
        }
        return this.arguments.get(arg);
    }

    int size()
    {
        return this.size;
    }

    @Override
    public String toString()
    {
        switch (this.size())
        {
            case 0 : return "<args>";
            case 1 : return this.get(0);
        }
        StringBuilder ts = new StringBuilder();

        for (int a = 0; a < this.size(); a++)
        {
            String arg = this.get(a);
            ts.append(' ');
            if (arg.contains(" ") || arg.isEmpty())
            {
                ts.append('"');
                ts.append(arg);
                ts.append('"');
            }
            else
            {
                ts.append(arg);
            }
        }
        return ts.toString();
    }

    static Arguments insert(Arguments arguments, int pos, String insert)
    {
        List<String> args = new ArrayList(arguments.arguments);
        args.add(pos, insert);

        return new Arguments(args);
    }
}
