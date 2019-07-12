package com.toshi313.servlet;

import com.toshi313.common.Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Initializer implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(Initializer.class);

    public void contextInitialized(ServletContextEvent event) {
        //Tomcat起動時処理
        final String Method_Overview = "Tomcat起動時処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");
    }

    public void contextDestroyed(ServletContextEvent event) {
        //Tomcatシャットダウン時処理
        final String Method_Overview = "Tomcatシャットダウン時処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");
    }
}
