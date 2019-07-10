package com.toshi313;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    com.toshi313.common.CommonUTAllTests.class,
    com.toshi313.dao.DaoUTAllTests.class,
    com.toshi313.servlet.ServletUTAllTests.class
    })
public class UTAllTests {

}
