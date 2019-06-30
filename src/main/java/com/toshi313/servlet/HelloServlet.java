package com.toshi313.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toshi313.common.Util;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        logger.info(Util.getClassName() + "-" + Util.getMethodName() + ":START");

        response.getWriter().append("Served at: ").append(request.getContextPath());

        logger.info(Util.getClassName() + "-" + Util.getMethodName() + ":END");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        logger.info(Util.getClassName() + "-" + Util.getMethodName() + ":START");

        doGet(request, response);

        logger.info(Util.getClassName() + "-" + Util.getMethodName() + ":END");
    }
}
