/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.base.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class Config {
    public static final double REQUIRED_JDK = 1.6;

    private static String getConfigFileName(String command) {
        // default command is "start"
        if (command == null || command.trim().length() == 0) {
            command = "start";
        }
        // strip off the leading dash
        if (command.startsWith("-")) {
            command = command.substring(1);
        }
        // shutdown & status hack
        if (command.equalsIgnoreCase("shutdown")) {
            command = "start";
        } else if (command.equalsIgnoreCase("status")) {
            command = "start";
        }

        String configFileName = System.getProperty("easyfk.home",".");
        configFileName +="/core/base/config/"+ command + ".properties";
        return configFileName;
    }

    public static Config getInstance(String[] args) throws IOException {
        String firstArg = args.length > 0 ? args[0] : "";
        String configFileName = getConfigFileName(firstArg);
        Config result = new Config();
        result.readConfig(configFileName);
        return result;
    }

    public InetAddress adminAddress;
    public String adminKey;
    public int adminPort;
    //public String awtHeadless;
    public String baseConfig;
    public String baseDtd;
    public String baseJar;
    public String baseLib;
    public String commJar;
    public String containerConfig;
    public String instrumenterClassName;
    public String instrumenterFile;
    public List<String> loaders;
    public String logDir;
    public String easyfkHome;
    public boolean requireCommJar = false;
    public boolean requireToolsJar = false;
    public boolean shutdownAfterLoad = false;
    //public String splashLogo;
    public String testConfig;
    public String toolsJar;
    public boolean useShutdownHook = true;

    private String findSystemJar(Properties props, String javaVendor, String javaVersion, String jarName, boolean required) {
        String fileSep = System.getProperty("file.separator");
        String javaHome = System.getProperty("java.home");
        String errorMsg = "Unable to locate " + jarName + " - ";
        // String foundMsg = "Found " + jarName + " - ";
        String jarLoc = "lib" + fileSep + jarName;
        File tj = null;

        if ("tools.jar".equals(jarName) && javaVendor.startsWith("Apple")) {
            // tools.jar is always available in Apple's JDK implementation
            return null;
        }

        // check to see if it is in the EASYFK_HOME directory
        tj = new File(easyfkHome + fileSep + jarName);
        if (tj.exists()) {
            return null;
        }

        // check to see if it is in the base/lib directory
        tj = new File(baseLib + fileSep + jarName);
        if (tj.exists()) {
            return null;
        }

        // try to locate tools.jar from the properties file
        String jarProps = props.getProperty("java." + jarName, null);
        if (jarProps != null) {
            tj = new File(jarProps);
            if (!tj.exists()) {
                if (required) {
                    System.err.println(errorMsg + tj.getAbsolutePath());
                }
            } else {
                // System.out.println(foundMsg + tj.getAbsolutePath());
                return jarProps;
            }
        }

        // next check the JAVA_HOME lib dir
        tj = new File(javaHome + fileSep + jarLoc);
        if (!tj.exists()) {
            if (required) {
                System.err.println(errorMsg + tj.getAbsolutePath());
            }
        } else {
            // System.out.println(foundMsg + tj.getAbsolutePath());
            return tj.getAbsolutePath();
        }

        // next if we are a JRE dir check the parent dir
        String jreExt = fileSep + "jre";
        if (javaHome.toLowerCase().endsWith(jreExt)) {
            javaHome = javaHome.substring(0, javaHome.lastIndexOf(fileSep));
            tj = new File(javaHome + fileSep + jarLoc);
            if (!tj.exists()) {
                if (required) {
                    System.err.println(errorMsg + tj.getAbsolutePath());
                }
            } else {
                // System.out.println(foundMsg + tj.getAbsolutePath());
                return tj.getAbsolutePath();
            }
        }

        // special windows checking
        if (javaHome.toLowerCase().charAt(1) == ':') {
            String driveLetter = javaHome.substring(0, 2);
            String windowsPath = driveLetter + fileSep + "j2sdk" + javaVersion;
            tj = new File(windowsPath + fileSep + jarLoc);
            if (!tj.exists()) {
                if (required) {
                    System.err.println(errorMsg + tj.getAbsolutePath());
                }
            } else {
                // System.out.println(foundMsg + tj.getAbsolutePath());
                return tj.getAbsolutePath();
            }
        }

        if (required) {
            System.err.println("");
            System.err.println("Required library " + jarName + " could not be located.");
            System.err.println("Make sure you using Java2 SDK " + REQUIRED_JDK + "+ and NOT the JRE.");
            System.err.println("You may need to copy " + jarName + " into a loadable lib directory");
            System.err.println("(i.e. EASYFK_HOME or EASYFK_HOME/base/lib)");
            System.err.println("");
            System.exit(-1);
        }

        return null;
    }

    private String getEasyFKHomeProp(Properties props, String key, String def) {
        String value = System.getProperty(key);
        if (value != null)
            return value;
        return easyfkHome + "/" + props.getProperty(key, def);
    }

    private String getProp(Properties props, String key, String def) {
        String value = System.getProperty(key);
        if (value != null)
            return value;
        return props.getProperty(key, def);
    }

    private Properties getPropertiesFile(String config) throws IOException {
        InputStream propsStream = null;
        Properties props = new Properties();
        try {
            // first try classpath
            propsStream = getClass().getClassLoader().getResourceAsStream(config);
            if (propsStream != null) {
                props.load(propsStream);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            // next try file location
            File propsFile = new File(config);
            if (propsFile != null) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(propsFile);
                    if (fis != null) {
                        props.load(fis);
                    } else {
                        throw new FileNotFoundException();
                    }
                } catch (FileNotFoundException e2) {
                    // do nothing; we will see empty props below
                } finally {
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
        } finally {
            if (propsStream != null) {
                propsStream.close();
            }
        }

        // check for empty properties
        if (props.isEmpty()) {
            throw new IOException("Cannot load configuration properties : " + config);
        }
        return props;
    }

    public void initClasspath(Classpath classPath) throws IOException {
        // load tools.jar
        if (this.toolsJar != null) {
            classPath.addComponent(this.toolsJar);
        }
        // load comm.jar
        if (this.commJar != null) {
            classPath.addComponent(this.commJar);
        }
        // add EASYFK_HOME to class path & load libs
        classPath.addClasspath(this.easyfkHome);
        loadLibs(classPath, this.easyfkHome, false);
        // load the lib directory
        if (this.baseLib != null) {
            loadLibs(classPath, this.baseLib, true);
        }
        // load the base.jar
        if (this.baseJar != null) {
            classPath.addComponent(this.baseJar);
        }
        // load the base schema directory
        if (this.baseDtd != null) {
            classPath.addComponent(this.baseDtd);
        }
        // load the config directory
        if (this.baseConfig != null) {
            classPath.addComponent(this.baseConfig);
        }
        classPath.instrument(this.instrumenterFile, this.instrumenterClassName);
    }

    private void loadLibs(Classpath classPath, String path, boolean recurse) throws IOException {
        File libDir = new File(path);
        if (libDir.exists()) {
            File files[] = libDir.listFiles();
            for (File file: files) {
                String fileName = file.getName();
                if (file.isHidden()) {
                    continue;
                }
                // FIXME: filter out other files?
                if (file.isDirectory() && !"CVS".equals(fileName) && !".svn".equals(fileName) && recurse) {
                    loadLibs(classPath, file.getCanonicalPath(), recurse);
                } else if (fileName.endsWith(".jar") || fileName.endsWith(".zip")) {
                    classPath.addComponent(file);
                }
            }
        }
    }

    public void readConfig(String config) throws IOException {
        // check the java_version
        String javaVersion = System.getProperty("java.version");
        String javaVendor = System.getProperty("java.vendor");
        double version = Double.parseDouble(javaVersion.substring(0, javaVersion.indexOf(".") + 2));
        if (REQUIRED_JDK > version) {
            System.err.println("");
            System.err.println("Java Version - " + javaVendor + " " + javaVersion + " - is not supported by EasyFK.");
            System.err.println("Please install Java2 SDK " + REQUIRED_JDK + "+");
            System.err.println("");
            System.exit(-1);
        }

        Properties props = this.getPropertiesFile(config);
        System.out.println("Start.java using configuration file " + config);

        
        // set the easyfk.home
        if (easyfkHome == null) {
            easyfkHome = props.getProperty("easyfk.home", ".");
            // get a full path
            if (easyfkHome.equals(".")) {
                easyfkHome = System.getProperty("user.dir");
                easyfkHome = easyfkHome.replace('\\', '/');
                System.out.println("Set EASYFK_HOME to - " + easyfkHome);
            }
        }
        System.setProperty("easyfk.home", easyfkHome);

        // base config directory
        baseConfig = getEasyFKHomeProp(props, "easyfk.base.config", "core/base/config");

        // base schema directory
        baseDtd = getEasyFKHomeProp(props, "easyfk.base.schema", "core/base/dtd");

        // base lib directory
        baseLib = getEasyFKHomeProp(props, "easyfk.base.lib", "core/base/lib");

        // base jar file
        baseJar = getEasyFKHomeProp(props, "easyfk.base.jar", "core/base/build/lib/base.jar");

        // tools jar
        String reqTJ = getProp(props, "java.tools.jar.required", "false");
        requireToolsJar = "true".equalsIgnoreCase(reqTJ);
        toolsJar = this.findSystemJar(props, javaVendor, javaVersion, "tools.jar", requireToolsJar);

        // comm jar
        String reqCJ = getProp(props, "java.comm.jar.required", "false");
        requireCommJar = "true".equalsIgnoreCase(reqCJ);
        commJar = this.findSystemJar(props, javaVendor, javaVersion, "comm.jar", requireCommJar);

        // log directory
        logDir = getEasyFKHomeProp(props, "easyfk.log.dir", "runtime/logs");

        // container configuration
        containerConfig = getEasyFKHomeProp(props, "easyfk.container.config", "core/base/config/easyfk-containers.xml");

        // get the admin server info
        String serverHost = getProp(props, "easyfk.admin.host", "127.0.0.1");

        String adminPortStr = getProp(props, "easyfk.admin.port", "0");
        // set the admin key
        adminKey = getProp(props, "easyfk.admin.key", "NA");

        // create the host InetAddress
        adminAddress = InetAddress.getByName(serverHost);

        // parse the port number
        try {
            adminPort = Integer.parseInt(adminPortStr);
        } catch (Exception e) {
            adminPort = 0;
        }

        // set the Derby system home
        String derbyPath = getProp(props, "derby.system.home", "runtime/data/derby");
        System.setProperty("derby.system.home", derbyPath);

        // set the property to tell Log4J to use log4j.xml
        String log4jConfig = getProp(props, "log4j.configuration", "log4j.xml");

        // set the log4j configuration property so we don't pick up one inside jars by
        // mistake
        System.setProperty("log4j.configuration", log4jConfig);

        // check for shutdown hook
        if (System.getProperty("easyfk.enable.hook") != null && System.getProperty("easyfk.enable.hook").length() > 0) {
            useShutdownHook = "true".equalsIgnoreCase(System.getProperty("easyfk.enable.hook"));
        } else if (props.getProperty("easyfk.enable.hook") != null && props.getProperty("easyfk.enable.hook").length() > 0) {
            useShutdownHook = "true".equalsIgnoreCase(props.getProperty("easyfk.enable.hook"));
        }

        // check for auto-shutdown
        if (System.getProperty("easyfk.auto.shutdown") != null && System.getProperty("easyfk.auto.shutdown").length() > 0) {
            shutdownAfterLoad = "true".equalsIgnoreCase(System.getProperty("easyfk.auto.shutdown"));
        } else if (props.getProperty("easyfk.auto.shutdown") != null && props.getProperty("easyfk.auto.shutdown").length() > 0) {
            shutdownAfterLoad = "true".equalsIgnoreCase(props.getProperty("easyfk.auto.shutdown"));
        }

        // set AWT headless mode
        //awtHeadless = getProp(props, "java.awt.headless", null);
        //if (awtHeadless != null) {
        //    System.setProperty("java.awt.headless", awtHeadless);
        //}

        // get the splash logo
        //splashLogo = props.getProperty("easyfk.start.splash.logo", null);

        // set the property to tell Jetty to use 2.4 SessionListeners
        //System.setProperty("org.mortbay.jetty.servlet.AbstractSessionManager.24SessionDestroyed", "true");

        // set the default locale
        String localeString = props.getProperty("easyfk.locale.default");
        if (localeString != null && localeString.length() > 0) {
            String args[] = localeString.split("_");
            switch (args.length) {
                case 1:
                    Locale.setDefault(new Locale(args[0]));
                    break;
                case 2:
                    Locale.setDefault(new Locale(args[0], args[1]));
                    break;
                case 3:
                    Locale.setDefault(new Locale(args[0], args[1], args[2]));
            }
            System.setProperty("user.language", localeString);
        }

        // set the default time zone
        String tzString = props.getProperty("easyfk.timeZone.default");
        if (tzString != null && tzString.length() > 0) {
            TimeZone.setDefault(TimeZone.getTimeZone(tzString));
        }

        instrumenterClassName = getProp(props, "easyfk.instrumenterClassName", null);
        instrumenterFile = getProp(props, "easyfk.instrumenterFile", null);

        // loader classes
        loaders = new ArrayList<String>();
        int currentPosition = 1;
        while (true) {
            String loaderClass = props.getProperty("easyfk.start.loader" + currentPosition);
            if (loaderClass == null || loaderClass.length() == 0) {
                break;
            } else {
                loaders.add(loaderClass);
                currentPosition++;
            }
        }
    }

}
