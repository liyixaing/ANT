package com.example.administrator.mode.Pojo;

import java.util.List;

public class getMyZhongChouBuyRecordBean {

    /**
     * code : 1
     * message : 成功
     * data : [{"id":69,"userId":137136,"itemId":8,"targetAmount":600,"rate":0.5,"sourceAmount":1200,"createTime":1547817303}]
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
         * id : 69
         * userId : 137136
         * itemId : 8
         * targetAmount : 600
         * rate : 0.5
         * sourceAmount : 1200
         * createTime : 1547817303
         */

        private int id;
        private int userId;
        private int itemId;
        private double targetAmount;
        private double rate;
        private double sourceAmount;
        private int createTime;

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

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public double getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(double targetAmount) {
            this.targetAmount = targetAmount;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getSourceAmount() {
            return sourceAmount;
        }

        public void setSourceAmount(double sourceAmount) {
            this.sourceAmount = sourceAmount;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }
    }
}
