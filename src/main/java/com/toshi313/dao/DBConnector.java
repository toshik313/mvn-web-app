package com.toshi313.dao;

import com.toshi313.common.PropertyInfo;
import com.toshi313.common.Util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnector {

    public Logger logger = LoggerFactory.getLogger(DbConnector.class);

    public Connection getConnection(PropertyInfo propInfo) {

        final String Method_Overview = "DB接続の取得処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        Connection conn = null;
        try {
            String jndiDriver = propInfo.getValue(PropertyInfo.JNDI_DRIVER_KEY);
            String jndiDatasource = propInfo.getValue(PropertyInfo.JNDI_DATASOURCE_KEY);

            if (jndiDriver == null || jndiDatasource == null) {
                throw new IllegalStateException("jndiDriverまたはjndiDatasourceの取得に失敗しました。"
                        + "jndiDriver=[" + jndiDriver + "],"
                        + " jndiDatasource=[" + jndiDatasource + "]");
            }

            Class.forName(jndiDriver);

            try {

                InitialContext cxt = new InitialContext();
                DataSource ds = (DataSource) cxt.lookup(jndiDatasource);
                conn = ds.getConnection();
            } catch (Exception e) {

                logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                        + ":" + Method_Overview + ":JNDIによる接続に失敗したため、JDBCによる接続を試みます。", e);

                String dbUrl = propInfo.getValue(PropertyInfo.DB_URL);
                String dbUser = propInfo.getValue(PropertyInfo.DB_USER);
                String dbPassword = propInfo.getValue(PropertyInfo.DB_PASSWORD);

                if (dbUrl == null || dbUser == null || dbPassword == null) {
                    throw new IllegalStateException("dbUrlまたはdbUserまたはdbPasswordの取得に失敗しました。"
                            + "dbUrl=[" + dbUrl + "],"
                            + " dbUser=[" + dbUser + "], dbPassword=[非公開]");
                }

                conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }

            conn.setAutoCommit(false);

        } catch (Exception e) {
            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + "に失敗しました。"
                    + e.getMessage(), e);
        }

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");

        return conn;
    }

    public void close(Connection conn) {

        final String Method_Overview = "DB接続のclose処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        try {
            conn.close();
            logger.info(Util.getClassName() + "." + Util.getMethodName()
                    + ":" + Method_Overview + "に成功しました。");
        } catch (Exception e) {
            logger.error(Util.getClassName() + "." + Util.getMethodName()
                    + ":" + Method_Overview + "に失敗しました。"
                    + e.getMessage(), e);
        }
        logger.info(Util.getClassName() + "." + Util.getMethodName()
                + ":" + Method_Overview + ":END");

        return;
    }
}
