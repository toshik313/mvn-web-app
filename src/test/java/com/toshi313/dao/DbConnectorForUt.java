package com.toshi313.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectorForUt {

    public static Connection connect() throws Exception {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/db_mvn_web_app",
                "u_mvn_web_app",
                "313toshiyuki");

        return conn;
    }

    public static void close(Connection conn) throws Exception {

        conn.close();

        return;
    }
}
