package com.test.app.globals;

import com.test.app.db.AppDatabase;

public class Globals {

    public static int getPageLimit() {
        return pageLimit;
    }

    public static void setPageLimit(int inPageLimit) {
        pageLimit = inPageLimit;
    }

    public static AppDatabase AppDatabase;

    private static int pageLimit = 8;
}
