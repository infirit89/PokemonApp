package com.test.app.globals;

public class Globals {

    public static int getPageLimit() {
        return pageLimit;
    }

    public static void setPageLimit(int inPageLimit) {
        pageLimit = inPageLimit;
    }

    private static int pageLimit = 5;
}
