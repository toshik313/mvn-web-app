package com.toshi313.servlet;

import com.toshi313.common.PropertyInfo;
import com.toshi313.common.Util;
import com.toshi313.dao.DbConnector;
import com.toshi313.dao.SelectMtState;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowStateList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(ShowStateList.class);

    private static DbConnector dbConn = new DbConnector();
    private static SelectMtState selectMtState = new SelectMtState();

    public static void setDbConnector(DbConnector dbConn) {
        ShowStateList.dbConn = dbConn;
    }

    public static void setSelectMtState(SelectMtState selectMtState) {
        ShowStateList.selectMtState = selectMtState;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String Method_Overview = "都道府県一覧取得Servlet";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":START");

        ArrayList<HashMap<String, String>> mtStateList = null;

        Connection conn = dbConn.getConnection(PropertyInfo.getInstance());
        if (conn == null) {

            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview
                    + ":DBConnecter.connect() is null.");
            request.setAttribute("ERROR_MESSAGE", "DB接続に失敗しました。");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
            return;
        } else {

            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview
                    + ":DBConnecter.connect() is success.");

            mtStateList = selectMtState.select(conn);
            dbConn.close(conn);
        }

        if (mtStateList == null) {

            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview
                    + ":mt_state_list is null.");
            request.setAttribute("ERROR_MESSAGE", "都道府県マスタの取得に失敗しました。");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
            return;
        } else {
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                    + ":" + Method_Overview
                    + ":mtStateList.size()=[" + mtStateList.size() + "]");
        }

        request.setAttribute("mtStateList", mtStateList);
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + Method_Overview
                + ":request.setAttribute(\"mt_state_list\", mt_state_list) done");

        request.getRequestDispatcher("/ShowStateList.jsp").forward(request, response);
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview
                + ":request.getRequestDispatcher(\"/ShowStateList.jsp\")"
                + ".forward(request, response) done");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName()
                + ":" + Method_Overview + ":END");
    }
}
