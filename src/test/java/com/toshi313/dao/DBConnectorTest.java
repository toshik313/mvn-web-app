package com.toshi313.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
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

import com.toshi313.common.PropertyInfo;

@RunWith(Enclosed.class)
public class DBConnectorTest {

    @RunWith(Theories.class)
    public static class ConnectJNDIExceptionTest {


        @DataPoints
        public static String NULL_PARAMS[] = {
                "jndi_driver",
                "jndi_datasource"
        };


        @Theory
        public void propertyファイルから取得したjndi設定値がNullの時はエラーログが出力されること(String param) throws Exception {

            // SetUp
            final String expected = "jndi_driverまたはjndi_datasourceの取得に失敗しました。";

            final ArrayList<String> actual_list = new ArrayList<String>();


            DBConnector sut = new DBConnector();

            // PropertyInfoのstub作成（getValue(key)メソッドのkeyがparamの時nullを返す)
            PropertyInfo prop_info_stub = new PropertyInfo() {

                @Override
                public String getValue(String key) {

                    if(key.equals(param)) {
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
                }}).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));


            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            // テスト対象にPropertyInfoのstubを登録してgetConnection実行
            Connection conn = sut.getConnection(prop_info_stub);

            // Verify
            if(actual_list.size() == 0) {
                fail("logger.error()は呼ばれませんでした。");
            } else {
                assertThat(actual_list.get(actual_list.size()-1), containsString(expected));
            }

            // TearDown
            sut.close(conn);
        }
    }


    @RunWith(Theories.class)
    public static class ConnectJDBCExceptionTest {

        @DataPoints
        public static String NULL_PARAMS[] = {
                "db_url",
                "db_user",
                "db_password"
        };


        @Theory
        public void propertyファイルから取得したjdbc設定値がNullの時はエラーログが出力されること(String param) throws Exception {

            // SetUp
            final String expected = "db_urlまたはdb_userまたはdb_passwordの取得に失敗しました。";

            final ArrayList<String> actual_list = new ArrayList<String>();


            DBConnector sut = new DBConnector();

            // PropertyInfoのstub作成（getValue(key)メソッドのkeyがparamの時nullを返す)
            PropertyInfo prop_info_stub = new PropertyInfo() {

                @Override
                public String getValue(String key) {

                    if(key.equals(param)) {
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
                }}).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));


            // テスト対象にloggerのspyを登録
            sut.logger = spy;

            // Exercise
            // テスト対象にPropertyInfoのstubを登録してgetConnection実行
            Connection conn = sut.getConnection(prop_info_stub);

            // Verify
            if(actual_list.size() == 0) {
                fail("logger.error()は呼ばれませんでした。");
            } else {
                assertThat(actual_list.get(actual_list.size()-1), containsString(expected));
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
            DBConnector sut = new DBConnector();

            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }}).when(spy).error(Mockito.anyString(), Mockito.any(Exception.class));

            // テスト対象にloggerのspyを登録
            sut.logger = spy;


            // Exercise
            sut.close(null);


            // Verify
            if(actual_list.size() == 0) {
                fail("logger.error()は呼ばれませんでした。");
            } else {
                assertThat(actual_list.get(actual_list.size()-1), containsString(expected));
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
            DBConnector sut = new DBConnector();
            Connection conn = sut.getConnection(PropertyInfo.getInstance());

            Logger spy = Mockito.spy(sut.logger);
            Mockito.doAnswer(new Answer<Void>() {

                @Override
                public Void answer(InvocationOnMock invocation) throws Throwable {
                    // TODO 自動生成されたメソッド・スタブ
                    actual_list.add(invocation.getArgument(0));
                    invocation.callRealMethod();
                    return null;
                }}).when(spy).info(Mockito.anyString());

            // テスト対象にloggerのspyを登録
            sut.logger = spy;


            // Exercise
            sut.close(conn);


            // Verify
            if(actual_list.size() == 0) {
                fail("logger.error()は呼ばれませんでした。");
            } else {
                assertThat(actual_list.get(1), containsString(expected));
            }

            // TearDown
            ;
        }
    }



    public static class CommitRollbackTest {


        @Before
        public void setUp() throws Exception {

            DBConnector db_conn = new DBConnector();
            Connection conn = db_conn.getConnection(PropertyInfo.getInstance());

            Statement stmt = conn.createStatement();
            stmt.execute("drop table if exists sc_mvn_web_app.tb_ut_temp");
            stmt.execute("create table sc_mvn_web_app.tb_ut_temp (col1 varchar not null)");
            stmt.close();

            conn.commit();
            db_conn.close(conn);
        }


        @After
        public void tearDown() throws Exception {

            DBConnector db_conn = new DBConnector();
            Connection conn = db_conn.getConnection(PropertyInfo.getInstance());

            Statement stmt = conn.createStatement();
            stmt.execute("drop table if exists sc_mvn_web_app.tb_ut_temp");
            stmt.close();

            conn.commit();
            db_conn.close(conn);
        }


        private void setTestData(Connection conn, String test_data) throws Exception {

            Statement stmt = conn.createStatement();
            stmt.execute("insert into sc_mvn_web_app.tb_ut_temp values('" + test_data + "')");
            stmt.close();
        }


        private String getTestData(Connection conn) throws Exception {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select col1 from sc_mvn_web_app.tb_ut_temp");

            String ret_data = null;
            if(rs.next()) {
                ret_data = rs.getString("col1");
            }
            stmt.close();

            return ret_data;
        }


        @Test
        public void connectがDB接続後insertのrollbackができること() throws Exception {

            // SetUp
            String test_data = "this is test record and rollback test";
            String expected = null;


            DBConnector sut = new DBConnector();
            Connection conn = sut.getConnection(PropertyInfo.getInstance());
            this.setTestData(conn, test_data);
            String test_check_value = getTestData(conn);
            if(!test_check_value.equals(test_data)) {
                throw new Exception("このテストで登録したテストデータの値が不正です。登録したテストデータ=[" + test_check_value + "], 取得したテストデータ=[" + test_data + "]");
            }
            conn.rollback();
            sut.close(conn);


            // Exercise
            conn = sut.getConnection(PropertyInfo.getInstance());
            String actual = getTestData(conn);

            // Verify
            assertThat(actual, is(expected));

            // TearDown
            sut.close(conn);
        }


        @Test
        public void connectがDB接続後insertのcommitができること() throws Exception {

            // SetUp
            String test_data = "this is test record and commit test";
            String expected = test_data;

            DBConnector sut = new DBConnector();
            Connection conn = sut.getConnection(PropertyInfo.getInstance());
            this.setTestData(conn, test_data);
            String test_check_value = getTestData(conn);
            if(!test_check_value.equals(test_data)) {
                throw new Exception("このテストで登録したテストデータの値が不正です。登録したテストデータ=[" + test_check_value + "], 取得したテストデータ=[" + test_data + "]");
            }
            conn.commit();
            sut.close(conn);

            // Exercise
            conn = sut.getConnection(PropertyInfo.getInstance());
            String actual = getTestData(conn);

            // Verify
            assertThat(actual, is(expected));

            // TearDown
            sut.close(conn);
        }
    }
}
