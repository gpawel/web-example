package com.qamission.example.config;

import com.qamission.example.utils.ResourceUtils;

public class Config {

    private static Config config = null;

    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    public static Config reload() {
        config = null;
        return getConfig();
    }


    private String rootPath;
    private String resourcesPath;
    private String chromeDriverPath;
    private String env;
    private String userName;
    private String userPassword;


    private long pauseInTest;

    private Config() {
        env = System.getProperty("env","");
        if (env == null) {
            System.err.println("env is not provided. Please set -Denv=<folder> what is /resources/env/<folder> ");
            System.exit(-1);
        }

        rootPath = System.getProperty("ROOT",System.getProperty("user.dir"));
        resourcesPath = rootPath+ "/resources";

        String defaultProps = resourcesPath + "/etc";
        String envProps = resourcesPath + "/env/"+env;
        readProperties(defaultProps);
        readProperties(envProps);
        setUpSuiteVariables();
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public long getPauseInTest() {
        return pauseInTest;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    private void readProperties(String propPath) {
        ResourceUtils.loadProperties(propPath);
    }

    private void setUpSuiteVariables() {
        chromeDriverPath = resourcesPath+"/drivers/"+System.getProperty("chromeDriverExec");
        pauseInTest = Long.parseLong(System.getProperty("PAUSE_IN_TEST_MILLS"));
        userName = System.getProperty("pc_user_name");
        userPassword = System.getProperty("pc_user_password");
    }


}

