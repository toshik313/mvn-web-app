package com.toshi313.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toshi313.common.PropertyInfo;
import com.toshi313.common.Util;

public class DBConnector {

    public Logger logger = LoggerFactory.getLogger(DBConnector.class);


    public Connection getConnection(PropertyInfo prop_info) {

        final String METHOD_OVERVIEW = "DB接続の取得処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":START");

        Connection conn = null;
        try {
            String jndi_driver = prop_info.getValue(PropertyInfo.JNDI_DRIVER_KEY);
            String jndi_datasource = prop_info.getValue(PropertyInfo.JNDI_DATASOURCE_KEY);

            if(jndi_driver == null || jndi_datasource == null) {
                throw new IllegalStateException("jndi_driverまたはjndi_datasourceの取得に失敗しました。"
                        + "jndi_driver=[" + jndi_driver + "], jndi_datasource=[" + jndi_datasource + "]");
            }

            Class.forName(jndi_driver);

            try {

                InitialContext cxt = new InitialContext();
                DataSource ds = (DataSource)cxt.lookup(jndi_datasource);
                conn = ds.getConnection();
            } catch(Exception e) {

                logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":JNDIによる接続に失敗したため、JDBCによる接続を試みます。", e);

                String db_url = prop_info.getValue(PropertyInfo.DB_URL);
                String db_user = prop_info.getValue(PropertyInfo.DB_USER);
                String db_password = prop_info.getValue(PropertyInfo.DB_PASSWORD);

                if(db_url == null || db_user == null || db_password == null) {
                    throw new IllegalStateException("db_urlまたはdb_userまたはdb_passwordの取得に失敗しました。"
                            + "db_url=[" + db_url + "], db_user=[" + db_user + "], db_password=[非公開]");
                }

                conn = DriverManager.getConnection(db_url, db_user, db_password);
            }

            conn.setAutoCommit(false);

        } catch (Exception e) {
            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + "に失敗しました。" + e.getMessage(), e);
        }

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":END");

        return conn;
    }


    public void close(Connection conn) {

        final String METHOD_OVERVIEW = "DB接続のclose処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":START");

        try {
            conn.close();
            logger.info(Util.getClassName() + "." + Util.getMethodName() + ":" + METHOD_OVERVIEW + "に成功しました。");
        } catch (Exception e) {
            logger.error(Util.getClassName() + "." + Util.getMethodName() + ":" + METHOD_OVERVIEW + "に失敗しました。" + e.getMessage(), e);
        }
        logger.info(Util.getClassName() + "." + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":END");

        return;
    }
}
