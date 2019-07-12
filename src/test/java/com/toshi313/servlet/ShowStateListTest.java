package com.toshi313.servlet;

import com.toshi313.dao.DbConnector;
import com.toshi313.dao.DbConnectorForUt;
import com.toshi313.dao.SelectMtState;

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

public class ShowStateListTest {

    @Mock(name = "request")
    private HttpServletRequest requestMock;

    @Mock(name = "response")
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher rdMock;

    //    @Mock(name = "dbConn")
    //    private DbConnector dbConnMock;
    //
    //    @Mock(name = "selectMtState")
    //    private SelectMtState selectMtStateMock;

    @InjectMocks
    private ShowStateList sut = new ShowStateList();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dbConnectorのgetConnectionがnullの時はerrorメッセージをセットしfowardすることを確認する() throws Exception {

        // SetUp
        final String expectedPage = "/Error.jsp";

        DbConnector dbConnStub = new DbConnector() {
            @Override
            public Connection getConnection(com.toshi313.common.PropertyInfo propInfo) {
                return null;
            }
        };
        ShowStateList.setDbConnector(dbConnStub);

        Mockito.when(requestMock.getRequestDispatcher(Mockito.anyString())).thenReturn(rdMock);
        Mockito.doNothing().when(rdMock).forward(requestMock, responseMock);

        // Exercise
        this.sut.doPost(requestMock, responseMock);

        // Verify
        Mockito.verify(requestMock,
                Mockito.times(1)).setAttribute("ERROR_MESSAGE", "DB接続に失敗しました。");
        Mockito.verify(requestMock, Mockito.never()).setAttribute("mtStateList", null);
        Mockito.verify(requestMock, Mockito.times(1)).getRequestDispatcher(expectedPage);

        // TearDown
        ShowStateList.setDbConnector(new DbConnector());
    }

    @Test
    public void selectMtStateのselect結果がnullの時はerrorメッセージをセットfowardすることを確認する() throws Exception {

        // SetUp
        final String expectedPage = "/Error.jsp";

        SelectMtState selectMtStateMockStub = new SelectMtState() {
            @Override
            public ArrayList<HashMap<String, String>> select(Connection conn) {
                return null;
            }
        };
        ShowStateList.setSelectMtState(selectMtStateMockStub);

        Mockito.when(requestMock.getRequestDispatcher(
                Mockito.anyString())).thenReturn(rdMock);
        Mockito.doNothing().when(rdMock).forward(requestMock, responseMock);

        // Exercise
        this.sut.doPost(requestMock, responseMock);

        // Verify
        Mockito.verify(requestMock, Mockito.times(1)).setAttribute(
                "ERROR_MESSAGE",
                "都道府県マスタの取得に失敗しました。");
        Mockito.verify(requestMock, Mockito.never()).setAttribute("mtState_list", null);
        Mockito.verify(requestMock, Mockito.times(1)).getRequestDispatcher(expectedPage);

        // TearDown
        ShowStateList.setSelectMtState(new SelectMtState());
    }

    @Test
    public void selectMtStateのselect結果がnull以外の時はfowardすることを確認する() throws Exception {

        // SetUp
        final String expectedPage = "/ShowStateList.jsp";

        Connection conn = DbConnectorForUt.connect();

        DbConnector dbConnStub = new DbConnector() {

            @Override
            public Connection getConnection(com.toshi313.common.PropertyInfo propInfo) {
                return conn;
            }
        };
        ShowStateList.setDbConnector(dbConnStub);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        SelectMtState selectMtStateMockStub = new SelectMtState() {

            @Override
            public ArrayList<HashMap<String, String>> select(Connection conn) {
                return list;
            }
        };
        ShowStateList.setSelectMtState(selectMtStateMockStub);

        Mockito.when(requestMock.getRequestDispatcher(Mockito.anyString())).thenReturn(rdMock);
        Mockito.doNothing().when(rdMock).forward(requestMock, responseMock);

        // Exercise
        this.sut.doPost(requestMock, responseMock);

        // Verify
        Mockito.verify(requestMock, Mockito.never()).setAttribute(
                Mockito.eq("ERROR_MESSAGE"), Mockito.anyString());
        Mockito.verify(requestMock, Mockito.times(1)).setAttribute("mtStateList", list);
        Mockito.verify(requestMock, Mockito.times(1)).getRequestDispatcher(expectedPage);

        // TearDown
        ShowStateList.setDbConnector(new DbConnector());
        ShowStateList.setSelectMtState(new SelectMtState());
    }
}
