package com.toshi313.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyInfo {

    private static Logger logger = LoggerFactory.getLogger(PropertyInfo.class);

    private static final String PROP_FNAME = "mvn-web-app";

    public static final String JNDI_DRIVER_KEY = "jndi_driver";
    public static final String JNDI_DATASOURCE_KEY = "jndi_datasource";
    public static final String DB_URL = "db_url";
    public static final String DB_USER = "db_user";
    public static final String DB_PASSWORD = "db_password";

    private static PropertyInfo PROP_DEF = null;
    private static final HashMap<String, String> PROP_MAP = new HashMap<String, String>();

    protected PropertyInfo() {

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":START");

        ResourceBundle bundle = ResourceBundle.getBundle(PROP_FNAME);

        Collections.list(bundle.getKeys()).forEach(key -> {
            String value = bundle.getString(key);
            PROP_MAP.put(key, value);

            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":[" + bundle.getBaseBundleName() + "]から、"
                    + "key=[" + key + "], value=[" + value + "]を取得しました。");
        });

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":END");
    }

    public static PropertyInfo getInstance() {

        final String Method_Overview = "インスタンス生成処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        if (PROP_DEF == null) {
            PROP_DEF = new PropertyInfo();
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + ":PROP_DEFのinstanceを生成しました。");
        } else {
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + ":PROP_DEFのinstanceは生成済みです。");
        }

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");

        return PROP_DEF;
    }

    public String getValue(String key) {

        final String Method_Overview = "値返却処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        String value = "";

        if (PROP_MAP.containsKey(key)) {
            value = PROP_MAP.get(key);
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":"
                    + Method_Overview + ":key=[" + key + "], value=[" + value + "]");
        } else {
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + ":mvn-web-appに[" + key + "]は存在しません。");
        }

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");
        return value;
    }
}
