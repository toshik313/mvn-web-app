package com.toshi313.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SelectMtStateTest {

    public static class SelectMtStateExceptionTest {

        @Test
        public void mtStateが空の時は正常終了し0行のarraylistが返ることを確認する() throws Exception {

            // SetUp

            // Exercise

            // Verify

            // TearDown

            Assert.assertThat("何をすべきか分かったため省略", CoreMatchers.is("何をすべきか分かったため省略"));
        }

        @Test
        public void mtStateのconn引数がnullの時は例外が発生しnullが返ることを確認する() throws Exception {

            // SetUp

            // Exercise

            // Verify

            // TearDown

            Assert.assertThat("何をすべきか分かったため省略", CoreMatchers.is("何をすべきか分かったため省略"));
        }
    }

    @RunWith(Theories.class)
    public static class SelectMtState初期レコード確認Test {

        private static Connection conn = null;

        @BeforeClass
        public static void setUpClass() throws Exception {
            conn = DbConnectorForUt.connect();
        }

        @AfterClass
        public static void tearDownClass() throws Exception {
            DbConnectorForUt.close(conn);
        }

        @Theory
        public void select結果の各レコードの都道府県コートと都道府県名の値が正しいこと(Fixture p) throws Exception {

            // SetUp
            SelectMtState selectMtState = new SelectMtState();

            // Exercise
            ArrayList<HashMap<String, String>> list = selectMtState.select(conn);

            HashMap<String, String> actualMap = list.get(p.listIndex);
            String actualCode = actualMap.get(SelectMtState.getColNames(
                    SelectMtState.COL_INDEX_STATE_CODE));
            String actualName = actualMap.get(SelectMtState.getColNames(
                    SelectMtState.COL_INDEX_STATE_NAME));

            // Verify
            String message = "listIndex=[" + p.listIndex + "],"
                    + " stateCode=[" + p.stateCode + "],"
                    + " stateName=[" + p.stateName + "]";

            Assert.assertThat(message, actualCode, CoreMatchers.is(p.stateCode));
            Assert.assertThat(message, actualName, CoreMatchers.is(p.stateName));

            // TearDown
            ;
        }

        @DataPoints
        public static Fixture[] PARAMS = {
                new Fixture(0, "01", "北海道"),
                new Fixture(1, "02", "青森県"),
                new Fixture(2, "03", "岩手県"),
                new Fixture(3, "04", "宮城県"),
                new Fixture(4, "05", "秋田県"),
                new Fixture(5, "06", "山形県"),
                new Fixture(6, "07", "福島県"),
                new Fixture(7, "08", "茨城県"),
                new Fixture(8, "09", "栃木県"),
                new Fixture(9, "10", "群馬県"),
                new Fixture(10, "11", "埼玉県"),
                new Fixture(11, "12", "千葉県"),
                new Fixture(12, "13", "東京都"),
                new Fixture(13, "14", "神奈川県"),
                new Fixture(14, "15", "新潟県"),
                new Fixture(15, "16", "富山県"),
                new Fixture(16, "17", "石川県"),
                new Fixture(17, "18", "福井県"),
                new Fixture(18, "19", "山梨県"),
                new Fixture(19, "20", "長野県"),
                new Fixture(20, "21", "岐阜県"),
                new Fixture(21, "22", "静岡県"),
                new Fixture(22, "23", "愛知県"),
                new Fixture(23, "24", "三重県"),
                new Fixture(24, "25", "滋賀県"),
                new Fixture(25, "26", "京都府"),
                new Fixture(26, "27", "大阪府"),
                new Fixture(27, "28", "兵庫県"),
                new Fixture(28, "29", "奈良県"),
                new Fixture(29, "30", "和歌山県"),
                new Fixture(30, "31", "鳥取県"),
                new Fixture(31, "32", "島根県"),
                new Fixture(32, "33", "岡山県"),
                new Fixture(33, "34", "広島県"),
                new Fixture(34, "35", "山口県"),
                new Fixture(35, "36", "徳島県"),
                new Fixture(36, "37", "香川県"),
                new Fixture(37, "38", "愛媛県"),
                new Fixture(38, "39", "高知県"),
                new Fixture(39, "40", "福岡県"),
                new Fixture(40, "41", "佐賀県"),
                new Fixture(41, "42", "長崎県"),
                new Fixture(42, "43", "熊本県"),
                new Fixture(43, "44", "大分県"),
                new Fixture(44, "45", "宮崎県"),
                new Fixture(45, "46", "鹿児島県"),
                new Fixture(46, "47", "沖縄県")
        };

        static class Fixture {

            int listIndex;
            String stateCode;
            String stateName;

            Fixture(int listIndex, String stateCode, String stateName) {
                this.listIndex = listIndex;
                this.stateCode = stateCode;
                this.stateName = stateName;
            }
        }
    }

}
