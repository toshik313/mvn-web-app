package com.toshi313.common;

public class Util {


    public static final String CM_SEP = "#";

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName() + "()";
    }

}
