package com.toshi313.dao;

//import static org.hamcrest.CoreMatchers.*;
//import static org.junit.Assert.*;

import com.toshi313.common.PropertyInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;

@RunWith(Enclosed.class)
public class DbConnectorTest {

    @RunWith(Theories.class)
    public static class ConnectJndiExceptionTest {

        @DataPoints
        public static String[] NULL_PARAMS = { "jndi_driver", "jndi_datasource" };

        @Theory
        public void propertyファイルから取得したjndi設定値がNullの時はエラーログが出力されること(String param) throws Exception {

            // SetUp
            final String expected = "jndiDriverまたはjndiDatasourceの取得に失敗しました。";

            final ArrayList<String> actual_list = new ArrayList<String>();

            DbConnector sut = new DbConnector();

            // PropertyInfoのstub作成（getValue(key)メソッドのkeyがparamの時nullを返す)
            PropertyInfo propInfoStub = new PropertyInfo() {

                @Override
                public String getValue(String key) {

                    if (key.equals(param)) {
                        return null;
                    }

                    return super.getValue(key);
                }
            };

            // LoggerのSpy作成（error(msg, e)メソッドのmsgをspyする）
            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }
            }).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));

            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            // テスト対象にPropertyInfoのstubを登録してgetConnection実行
            Connection conn = sut.getConnection(propInfoStub);

            // Verify
            if (actual_list.size() == 0) {
                Assert.fail("logger.error()は呼ばれませんでした。");
            } else {
                Assert.assertThat(actual_list.get(actual_list.size() - 1),
                        CoreMatchers.containsString(expected));
            }

            // TearDown
            sut.close(conn);
        }
    }

    @RunWith(Theories.class)
    public static class ConnectJdbcExceptionTest {

        @DataPoints
        public static String[] NULL_PARAMS = { "db_url", "db_user", "db_password" };

        @Theory
        public void propertyファイルから取得したjdbc設定値がNullの時はエラーログが出力されること(String param) throws Exception {

            // SetUp
            final String expected = "dbUrlまたはdbUserまたはdbPasswordの取得に失敗しました。";

            final ArrayList<String> actual_list = new ArrayList<String>();

            DbConnector sut = new DbConnector();

            // PropertyInfoのstub作成（getValue(key)メソッドのkeyがparamの時nullを返す)
            PropertyInfo propInfoStub = new PropertyInfo() {

                @Override
                public String getValue(String key) {

                    if (key.equals(param)) {
                        return null;
                    }

                    return super.getValue(key);
                }
            };

            // LoggerのSpy作成（error(msg, e)メソッドのmsgをspyする）
            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }
            }).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));

            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            // テスト対象にPropertyInfoのstubを登録してgetConnection実行
            Connection conn = sut.getConnection(propInfoStub);

            // Verify
            if (actual_list.size() == 0) {
                Assert.fail("logger.error()は呼ばれませんでした。");
            } else {
                Assert.assertThat(actual_list.get(actual_list.size() - 1),
                        CoreMatchers.containsString(expected));
            }

            // TearDown
            sut.close(conn);
        }
    }

    public static class CloseTest {

        @Test
        public void closeでconnがnullの時はerrorlogが出力されること() throws Exception {

            // Set Up
            final String expected = "DB接続のclose処理に失敗しました。";
            final ArrayList<String> actual_list = new ArrayList<String>();

            // LoggerのSpy作成（error(msg, e)メソッドのmsgをspyする
            DbConnector sut = new DbConnector();

            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }
            }).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));

            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            sut.close(null);

            // Verify
            if (actual_list.size() == 0) {
                Assert.fail("logger.error()は呼ばれませんでした。");
            } else {
                Assert.assertThat(actual_list.get(actual_list.size() - 1),
                        CoreMatchers.containsString(expected));
            }

            // TearDown
            ;
        }

        @Test
        public void closeが正常終了時はinfologが出力されること() throws Exception {

            // Set Up
            final String expected = "DB接続のclose処理に成功しました。";
            final ArrayList<String> actual_list = new ArrayList<String>();

            // LoggerのSpy作成（error(msg, e)メソッドのmsgをspyする
            DbConnector sut = new DbConnector();
            Connection conn = sut.getConnection(PropertyInfo.getInstance());

            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }
            }).when(spy).info(Mockito.anyString());

            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            sut.close(conn);

            // Verify
            if (actual_list.size() == 0) {
                Assert.fail("logger.error()は呼ばれませんでした。");
            } else {
                Assert.assertThat(actual_list.get(1), CoreMatchers.containsString(expected));
            }

            // TearDown
            ;
        }
    }

    public static class CommitRollbackTest {

        @Before
        public void setUp() throws Exception {

            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.getConnection(PropertyInfo.getInstance());

            try (Statement stmt = conn.createStatement()) {

                stmt.execute("drop table if exists sc_mvn_web_app.tb_ut_temp");
                stmt.execute("create table sc_mvn_web_app.tb_ut_temp (col1 varchar not null)");
                stmt.close();
                conn.commit();
            }

            dbConn.close(conn);
        }

        @After
        public void tearDown() throws Exception {

            DbConnector dbConn = new DbConnector();
            Connection conn = dbConn.getConnection(PropertyInfo.getInstance());

            try (Statement stmt = conn.createStatement()) {

                stmt.execute("drop table if exists sc_mvn_web_app.tb_ut_temp");
                stmt.close();
                conn.commit();
            }
            dbConn.close(conn);
        }

        private void setTestData(Connection conn, String testData) throws Exception {

            String sql = "insert into sc_mvn_web_app.tb_ut_temp values(?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, testData);
                ps.execute();
                ps.close();
            }
        }

        private String getTestData(Connection conn) throws Exception {

            String retData = null;

            try (Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("select col1 from sc_mvn_web_app.tb_ut_temp");
                if (rs.next()) {
                    retData = rs.getString("col1");
                }
                stmt.close();
            }

            return retData;
        }

        @Test
        public void connectがDB接続後insertのrollbackができること() throws Exception {

            // SetUp
            final String testData = "this is test record and rollback test";

            DbConnector sut = new DbConnector();
            Connection conn1 = sut.getConnection(PropertyInfo.getInstance());

            this.setTestData(conn1, testData);
            String testCheckValue = getTestData(conn1);
            if (!testCheckValue.equals(testData)) {
                throw new Exception("このテストで登録したテストデータの値が不正です。"
                        + "登録したテストデータ=[" + testCheckValue + "],"
                        + " 取得したテストデータ=[" + testData + "]");
            }
            conn1.rollback();
            sut.close(conn1);

            // Exercise
            Connection conn2 = sut.getConnection(PropertyInfo.getInstance());
            String actual = getTestData(conn2);

            // Verify
            Assert.assertThat(actual, CoreMatchers.nullValue());

            // TearDown
            sut.close(conn2);
        }

        @Test
        public void connectがDB接続後insertのcommitができること() throws Exception {

            // SetUp
            final String testData = "this is test record and commit test";
            final String expected = testData;

            DbConnector sut = new DbConnector();
            Connection conn = sut.getConnection(PropertyInfo.getInstance());
            this.setTestData(conn, testData);
            String testCheckValue = getTestData(conn);
            if (!testCheckValue.equals(testData)) {
                throw new Exception("このテストで登録したテストデータの値が不正です。"
                        + "登録したテストデータ=[" + testCheckValue + "],"
                        + " 取得したテストデータ=[" + testData + "]");
            }
            conn.commit();
            sut.close(conn);

            // Exercise
            conn = sut.getConnection(PropertyInfo.getInstance());
            String actual = getTestData(conn);

            // Verify
            Assert.assertThat(actual, CoreMatchers.is(expected));

            // TearDown
            sut.close(conn);
        }
    }
}
