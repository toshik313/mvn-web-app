package com.toshi313.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

    @Test
    public void getClassNameメソッドが正しいクラス名を返すこと() throws Exception {

        // SetUp
        String expected = "com.toshi313.common.UtilTest";

        // Exercise
        String actual = Util.getClassName();

        // Verify
        assertThat(actual, is(expected));

        // TearDown
        ;
    }

    @Test
    public void getMethodNameメソッドが正しいメソッド名を返すこと() throws Exception {

        // SetUp
        String expected = "getMethodNameメソッドが正しいメソッド名を返すこと()";

        // Exercise
        String actual = Util.getMethodName();

        // Verify
        assertThat(actual, is(expected));

        // TearDown
        ;
    }
}
