/*
 * =============================================================================
 * Lexa - Property of William Norman-Walker
 * -----------------------------------------------------------------------------
 * JavaProcessLauncher.java
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

package lexa.core.controller.launcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class JavaProcessLauncher {

    private JavaProcessLauncher() {}

    public static int launch(Class launchClass, boolean wait, String ... args) throws IOException,
                                               InterruptedException
    {
        return launch(launchClass.getCanonicalName(), wait, args);
    }
    public static int launch(String className, boolean wait, String ... args)
            throws IOException,
            InterruptedException
    {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +
                File.separator + "bin" +
                File.separator + "java";
        String classpath =  System.getProperty("user.home") +
                File.separator + "Documents" +
                File.separator + "lexa" +
                File.separator + "core" +
                File.separator + "bin";
                //System.getProperty("java.class.path");
        //String className = launchClass.getCanonicalName();

        List<String> processArguments = new ArrayList();
        processArguments.add(javaBin);
        processArguments.add("-cp");
        processArguments.add(classpath);
        processArguments.add(className);
        processArguments.addAll(Arrays.asList(args));
        //ProcessBuilder builder = new ProcessBuilder(
        //        javaBin, "-cp", classpath, className);
        ProcessBuilder builder = new ProcessBuilder(processArguments);
        Process process = builder.start();
        if (!wait)
        {
            return 0;
        }
        process.waitFor();
        return process.exitValue();
    }

}