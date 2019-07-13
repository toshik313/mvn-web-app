package com.toshi313;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        com.toshi313.common.CommonUtAllTests.class,
        com.toshi313.dao.DaoUtAllTests.class,
        com.toshi313.servlet.ServletUtAllTests.class
})
public class UtAllTests {

}
