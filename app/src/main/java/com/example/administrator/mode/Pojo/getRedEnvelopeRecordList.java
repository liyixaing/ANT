package com.example.administrator.mode.Pojo;

import java.util.List;

public class getRedEnvelopeRecordList {
    /**
     * code : 1
     * message : 成功
     * data : [{"id":45,"userId":120006,"changeAnt":113.7,"changeScore":0,"envelopeIds":"50","envelopeType":5,"changeTime":1547178029}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 45
         * userId : 120006
         * changeAnt : 113.7
         * changeScore : 0
         * envelopeIds : 50
         * envelopeType : 5
         * changeTime : 1547178029
         */

        private int id;
        private int userId;
        private double changeAnt;
        private double changeScore;
        private String envelopeIds;
        private int envelopeType;
        private int changeTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getChangeAnt() {
            return changeAnt;
        }

        public void setChangeAnt(double changeAnt) {
            this.changeAnt = changeAnt;
        }

        public double getChangeScore() {
            return changeScore;
        }

        public void setChangeScore(double changeScore) {
            this.changeScore = changeScore;
        }

        public String getEnvelopeIds() {
            return envelopeIds;
        }

        public void setEnvelopeIds(String envelopeIds) {
            this.envelopeIds = envelopeIds;
        }

        public int getEnvelopeType() {
            return envelopeType;
        }

        public void setEnvelopeType(int envelopeType) {
            this.envelopeType = envelopeType;
        }

        public int getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(int changeTime) {
            this.changeTime = changeTime;
        }
    }
}
