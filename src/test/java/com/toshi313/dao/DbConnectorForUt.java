package com.toshi313.dao;

import com.toshi313.common.PropertyInfo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectorForUt {

    public static Connection connect() throws Exception {

        Class.forName("org.postgresql.Driver");

        PropertyInfo prop_info = PropertyInfo.getInstance();

        Connection conn = DriverManager.getConnection(
                prop_info.getValue(PropertyInfo.DB_URL),
                prop_info.getValue(PropertyInfo.DB_USER),
                prop_info.getValue(PropertyInfo.DB_PASSWORD));

        return conn;
    }

    public static void close(Connection conn) throws Exception {

        conn.close();

        return;
    }
}
