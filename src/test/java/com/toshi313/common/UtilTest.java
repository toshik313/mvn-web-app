package com.toshi313.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

    @Test
    public void getClassNameメソッドが正しいクラス名を返すこと() {

        String expected = "com.toshi313.common.UtilTest";
        String actual = Util.getClassName();
        assertThat(actual, is(expected));
    }

    @Test
    public void getMethodNameメソッドが正しいメソッド名を返すこと() {

        String expected = "getMethodNameメソッドが正しいメソッド名を返すこと";
        String actual = Util.getMethodName();
        assertThat(actual, is(expected));
    }
}
