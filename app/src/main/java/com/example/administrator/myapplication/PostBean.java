package com.example.administrator.myapplication;

public class PostBean {


    /**
     * Content : {"EmployeeName":"","Page":1,"PageSize":20}
     * Head : {"Language":null,"pws":"F7D73952C7FC4B889E7DD3F9B9893CC0","uid":"F7D73952C7FC4B889E7DD3F9B9893CC0"}
     */

    public ContentBean Content;
    public HeadBean Head;

    public static class ContentBean {
        /**
         * EmployeeName :
         * Page : 1
         * PageSize : 20
         */

        public String EmployeeName;
        public int Page;
        public int PageSize;
    }

    public static class HeadBean {
        /**
         * Language : null
         * pws : F7D73952C7FC4B889E7DD3F9B9893CC0
         * uid : F7D73952C7FC4B889E7DD3F9B9893CC0
         */

        public Object Language;
        public String pws;
        public String uid;
    }
}
