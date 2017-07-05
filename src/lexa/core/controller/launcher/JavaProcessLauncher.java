/*==============================================================================
 * Lexa - Property of William Norman-Walker
 *------------------------------------------------------------------------------
 * JavaProcessLauncher.java
 *------------------------------------------------------------------------------
 * Author:  William Norman-Walker
 * Created: June 2017
 *==============================================================================
 */
package lexa.core.controller.launcher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class JavaProcessLauncher {

    private JavaProcessLauncher() {}

    public static Process launch(Class launchClass, boolean wait, String ... args)
        throws IOException,
            InterruptedException,
            ClassNotFoundException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException
    {
        return launch(launchClass.getCanonicalName(), wait, args);
    }
    public static synchronized Process launch(String className, boolean wait, String ... args)
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
                File.separator + "bin" +
                File.separator + "*";

        List<String> processArguments = new ArrayList();
        processArguments.add(javaBin);
        processArguments.add("-cp");
        processArguments.add(classpath);
        processArguments.add(className);
        processArguments.addAll(Arrays.asList(args));

        ProcessBuilder builder = new ProcessBuilder(processArguments);
        builder.inheritIO();
        Process process = builder.start();

        if (wait)
        {
            process.waitFor();
        }
        else
        {
            // allow it time to at least start
            process.waitFor(250, TimeUnit.MILLISECONDS);
        }

        return process;
    }


    public static Process exec(String className, String ... args) throws IOException,
                                               InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +
                File.separator + "bin" +
                File.separator + "java";
        String classpath = System.getProperty("java.class.path");

        ProcessBuilder builder = new ProcessBuilder(
                javaBin, "-cp", classpath, className);

        return builder.start();
    }
}