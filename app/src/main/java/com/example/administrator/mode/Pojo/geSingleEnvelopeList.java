package com.example.administrator.mode.Pojo;

import java.util.List;

public class geSingleEnvelopeList {
    /**
     * code : 1
     * message : 成功
     * data : [{"id":7,"sendId":131426,"acceptId":100000,"redEnvelopeType":2,"status":0,"distanceLimit":10000,"canDrawTime":1547177278,"expireTime":1547305200,"createTime":1547177278,"drawTime":null,"refundTime":null,"amountAnt":10,"amountScore":0,"memo":"大吉大利，恭喜发财！","longitude":null,"latitude":null,"updateTime":null,"noExpire":true}]
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
         * id : 7
         * sendId : 131426
         * acceptId : 100000
         * redEnvelopeType : 2
         * status : 0
         * distanceLimit : 10000
         * canDrawTime : 1547177278
         * expireTime : 1547305200
         * createTime : 1547177278
         * drawTime : null
         * refundTime : null
         * amountAnt : 10
         * amountScore : 0
         * memo : 大吉大利，恭喜发财！
         * longitude : null
         * latitude : null
         * updateTime : null
         * noExpire : true
         */

        private int id;
        private int sendId;
        private int acceptId;
        private int redEnvelopeType;
        private int status;
        private int distanceLimit;
        private int canDrawTime;
        private int expireTime;
        private int createTime;
        private Object drawTime;
        private Object refundTime;
        private double amountAnt;
        private double amountScore;
        private String memo;
        private Object longitude;
        private Object latitude;
        private Object updateTime;
        private boolean noExpire;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSendId() {
            return sendId;
        }

        public void setSendId(int sendId) {
            this.sendId = sendId;
        }

        public int getAcceptId() {
            return acceptId;
        }

        public void setAcceptId(int acceptId) {
            this.acceptId = acceptId;
        }

        public int getRedEnvelopeType() {
            return redEnvelopeType;
        }

        public void setRedEnvelopeType(int redEnvelopeType) {
            this.redEnvelopeType = redEnvelopeType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDistanceLimit() {
            return distanceLimit;
        }

        public void setDistanceLimit(int distanceLimit) {
            this.distanceLimit = distanceLimit;
        }

        public int getCanDrawTime() {
            return canDrawTime;
        }

        public void setCanDrawTime(int canDrawTime) {
            this.canDrawTime = canDrawTime;
        }

        public int getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(int expireTime) {
            this.expireTime = expireTime;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public Object getDrawTime() {
            return drawTime;
        }

        public void setDrawTime(Object drawTime) {
            this.drawTime = drawTime;
        }

        public Object getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(Object refundTime) {
            this.refundTime = refundTime;
        }

        public double getAmountAnt() {
            return amountAnt;
        }

        public void setAmountAnt(double amountAnt) {
            this.amountAnt = amountAnt;
        }

        public double getAmountScore() {
            return amountScore;
        }

        public void setAmountScore(double amountScore) {
            this.amountScore = amountScore;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isNoExpire() {
            return noExpire;
        }

        public void setNoExpire(boolean noExpire) {
            this.noExpire = noExpire;
        }
    }
}
