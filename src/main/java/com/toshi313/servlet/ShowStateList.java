package com.toshi313.servlet;

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

import com.toshi313.common.PropertyInfo;
import com.toshi313.common.Util;
import com.toshi313.dao.DBConnector;
import com.toshi313.dao.SelectMtState;


public class ShowStateList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Logger logger = LoggerFactory.getLogger(ShowStateList.class);

    private DBConnector db_conn;
    private SelectMtState select_mt_state;

    public ShowStateList() {
        this.db_conn = new DBConnector();
        this.select_mt_state = new SelectMtState();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String METHOD_OVERVIEW = "都道府県一覧取得Servlet";
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":START");

        ArrayList<HashMap<String, String>> mt_state_list = null;

        Connection conn = this.db_conn.getConnection(PropertyInfo.getInstance());
        if(conn == null) {

            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":DBConnecter.connect() is null.");
            request.setAttribute("ERROR_MESSAGE", "DB接続に失敗しました。");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
            return;
        } else {

            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":DBConnecter.connect() is success.");

            mt_state_list = this.select_mt_state.select(conn);
            this.db_conn.close(conn);
        }

        if(mt_state_list == null) {

            logger.error(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":mt_state_list is null.");
            request.setAttribute("ERROR_MESSAGE", "都道府県マスタの取得に失敗しました。");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
            return;
        } else {
            logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":mt_state_list.size()=[" + mt_state_list.size() + "]");
        }

        request.setAttribute("mt_state_list", mt_state_list);
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":request.setAttribute(\"mt_state_list\", mt_state_list) done");

        request.getRequestDispatcher("/ShowStateList.jsp").forward(request, response);
        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":request.getRequestDispatcher(\"/ShowStateList.jsp\").forward(request, response) done");

        logger.info(Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ":" + METHOD_OVERVIEW + ":END");
    }
}
