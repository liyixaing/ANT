package com.example.administrator.mode.Pojo;

import java.util.List;

public class getZhongChouReleaseRecordBean {

    /**
     * code : 1
     * message : 成功
     * data : [{"changeTime":1547867846,"duration":10,"itemId":8,"amount":2400,"itemName":"ds","id":19,"itemDesc":"asfd","userId":137136,"status":1},{"changeTime":1547867780,"duration":10,"itemId":7,"amount":600,"itemName":"ds","id":17,"itemDesc":"asfd","userId":137136,"status":1}]
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
         * changeTime : 1547867846
         * duration : 10
         * itemId : 8
         * amount : 2400
         * itemName : ds
         * id : 19
         * itemDesc : asfd
         * userId : 137136
         * status : 1
         */

        private int changeTime;
        private int duration;
        private int itemId;
        private double amount;
        private String itemName;
        private int id;
        private String itemDesc;
        private int userId;
        private int status;

        public int getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(int changeTime) {
            this.changeTime = changeTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
