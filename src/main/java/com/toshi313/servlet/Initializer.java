package com.toshi313.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toshi313.common.Util;

public class Initializer implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(Initializer.class);

    public void contextInitialized(ServletContextEvent event) {
        //Tomcat起動時処理
        final String METHOD_OVERVIEW = "Tomcat起動時処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":START");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":END");
    }

    public void contextDestroyed(ServletContextEvent event) {
        //Tomcatシャットダウン時処理
        final String METHOD_OVERVIEW = "Tomcatシャットダウン時処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":START");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":END");
    }
}
