package com.example.administrator.mode.Pojo;

public class SystemUPTurn {

    /**
     * code : 1
     * message : 服务器正在维护
     * data : {"state":"stop","openingtime":1539129600}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * state : stop
         * openingtime : 1539129600
         */

        private String state;
        private String info;

        private int openingtime;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getOpeningtime() {
            return openingtime;
        }

        public void setOpeningtime(int openingtime) {
            this.openingtime = openingtime;
        }
    }
}
