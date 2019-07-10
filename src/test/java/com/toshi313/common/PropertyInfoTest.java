package com.toshi313.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PropertyInfoTest {


    @Test
    public void getInstanceメソッドが正しいInstanceを返すこと() throws Exception {

        // SetUp
        PropertyInfo expected = PropertyInfo.getInstance();

        // Exercise
        PropertyInfo actual = PropertyInfo.getInstance();

        // Verify
        assertThat(actual, is(sameInstance(expected)));

        // TearDown
        ;
    }


    @Theory
    public void getValueメソッドが正しい値を返すこと(Fixture p) throws Exception {

        // SetUp
        ;

        // Exercise
        String actual = PropertyInfo.getInstance().getValue(p.key);

        // Verify
        String message = "key=[" + p.key + "], value=[" + p.value + "]";
        assertThat(message, actual, is(p.value));

        // TearDown
        ;
    }

    @DataPoints
    public static Fixture[] PARAMS = {
            new Fixture("jndi_driver", "org.postgresql.Driver"),
            new Fixture("jndi_datasource", "java:/comp/env/jdbc/postgres"),
            new Fixture("db_url", "jdbc:postgresql://localhost:5432/db_mvn_web_app"),
            new Fixture("db_user", "u_mvn_web_app"),
            new Fixture("db_password", "313toshiyuki")
    };

    static class Fixture {
        String key;
        String value;

        Fixture(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
