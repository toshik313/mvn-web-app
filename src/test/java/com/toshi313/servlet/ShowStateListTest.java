package com.toshi313.servlet;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.toshi313.dao.DBConnector;
import com.toshi313.dao.DBConnectorForUT;
import com.toshi313.dao.SelectMtState;


public class ShowStateListTest {

    @Mock(name="request")
    private HttpServletRequest request_mock;

    @Mock(name="response")
    private HttpServletResponse response_mock;

    @Mock
    private RequestDispatcher rd_mock;

    @Mock(name="db_conn")
    private DBConnector db_conn_mock;

    @Mock(name="select_mt_state")
    private SelectMtState select_mt_state_mock;

    @InjectMocks
    private ShowStateList sut = new ShowStateList();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void DBConnectorのgetConnectionがnullの時はerrorメッセージをセットしfowardすることを確認する() throws Exception {

        // SetUp
        String expected_page = "/Error.jsp";

        Mockito.when(this.db_conn_mock.getConnection(Mockito.any(com.toshi313.common.PropertyInfo.class))).thenReturn(null);
        Mockito.when(this.select_mt_state_mock.select(Mockito.any(java.sql.Connection.class))).thenReturn(null);
        Mockito.when(request_mock.getRequestDispatcher(Mockito.anyString())).thenReturn(rd_mock);
        Mockito.doNothing().when(rd_mock).forward(request_mock, response_mock);

        // Exercise
        this.sut.doPost(request_mock, response_mock);

        // Verify
        Mockito.verify(request_mock, Mockito.times(1)).setAttribute("ERROR_MESSAGE", "DB接続に失敗しました。");
        Mockito.verify(request_mock, Mockito.never()).setAttribute("mt_state_list", null);
        Mockito.verify(request_mock, Mockito.times(1)).getRequestDispatcher(expected_page);

        // TearDown
        ;
    }


    @Test
    public void SelectMtStateのselect結果がnullの時はerrorメッセージをセットfowardすることを確認する() throws Exception {

        // SetUp
        String expected_page = "/Error.jsp";

        Connection conn = DBConnectorForUT.connect();
        Mockito.when(this.db_conn_mock.getConnection(Mockito.any(com.toshi313.common.PropertyInfo.class))).thenReturn(conn);
        Mockito.when(this.select_mt_state_mock.select(Mockito.any(java.sql.Connection.class))).thenReturn(null);
        Mockito.when(request_mock.getRequestDispatcher(Mockito.anyString())).thenReturn(rd_mock);
        Mockito.doNothing().when(rd_mock).forward(request_mock, response_mock);

        // Exercise
        this.sut.doPost(request_mock, response_mock);

        // Verify
        Mockito.verify(request_mock, Mockito.times(1)).setAttribute("ERROR_MESSAGE", "都道府県マスタの取得に失敗しました。");
        Mockito.verify(request_mock, Mockito.never()).setAttribute("mt_state_list", null);
        Mockito.verify(request_mock, Mockito.times(1)).getRequestDispatcher(expected_page);

        // TearDown
        ;
    }



    @Test
    public void SelectMtStateのselect結果がnull以外の時はfowardすることを確認する() throws Exception {

        // SetUp
        String expected_page = "/ShowStateList.jsp";

        Connection conn = DBConnectorForUT.connect();
        Mockito.when(this.db_conn_mock.getConnection(Mockito.any(com.toshi313.common.PropertyInfo.class))).thenReturn(conn);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        Mockito.when(this.select_mt_state_mock.select(Mockito.any(java.sql.Connection.class))).thenReturn(list);

        Mockito.when(request_mock.getRequestDispatcher(Mockito.anyString())).thenReturn(rd_mock);
        Mockito.doNothing().when(rd_mock).forward(request_mock, response_mock);

        // Exercise
        this.sut.doPost(request_mock, response_mock);

        // Verify
        Mockito.verify(request_mock, Mockito.never()).setAttribute(Mockito.eq("ERROR_MESSAGE"), Mockito.anyString());
        Mockito.verify(request_mock, Mockito.times(1)).setAttribute("mt_state_list", list);
        Mockito.verify(request_mock, Mockito.times(1)).getRequestDispatcher(expected_page);

        // TearDown
        ;
    }
}
