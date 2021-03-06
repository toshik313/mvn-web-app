package com.toshi313.dao;

import com.toshi313.common.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectMtState {

    private Logger logger = LoggerFactory.getLogger(SelectMtState.class);

    private static final String SELECT_SQL = "select"
            + " a.state_code,"
            + " a.state_name,"
            + " a.update_datetime"
            + " from sc_mvn_web_app.mt_state a"
            + " order by a.state_code";

    private static final String[] COL_NAMES = { "state_code", "state_name", "update_datetime" };

    public static final int COL_INDEX_STATE_CODE = 0;
    public static final int COL_INDEX_STATE_NAME = 1;
    public static final int COL_INDEX_UPDATE_DATETIME = 2;

    public static String getColNames(int index) {

        if (index >= 0 && index < COL_NAMES.length - 1) {
            return COL_NAMES[index];
        }

        return "";
    }

    public ArrayList<HashMap<String, String>> select(Connection conn) {

        final String Method_Overview = "都道府県マスタの検索処理";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SELECT_SQL);

            logger.debug(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview
                    + ":SELECT_SQL=[" + SELECT_SQL + "]");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    for (String colname : COL_NAMES) {

                        String val = rs.getString(colname);
                        if (val == null) {
                            val = "";
                        }
                        map.put(colname, val);
                    }
                    list.add(map);
                }
            }

            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + "に成功しました。");

        } catch (Exception e) {

            list = null;
            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview + "に失敗しました。",
                    e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                            + ":" + Method_Overview
                            + "で、Statementのcloseに失敗しました。", e);
                }
            }
        }
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");

        return list;
    }
}
