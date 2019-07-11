package com.example.administrator.mode.Pojo;

public class getZhongChouDetailBean {

    /**
     * code : 1
     * message : 成功
     * data : {"amount":200000,"buyedAmount":600,"successRatio":0.023505,"successAmount":5301,"itemDesc":"asfd","releaseDays":100,"targetCoinName":"ANT COIN","duration":10,"itemName":"ds","createTime":1547815397,"rate":2,"sourceCoinName":"ANT TOKEN","perPersonLimit":10000,"startTime":1547815599,"id":7,"endTime":1548679599,"status":4}
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
         * amount : 200000
         * buyedAmount : 600
         * successRatio : 0.023505
         * successAmount : 5301
         * itemDesc : asfd
         * releaseDays : 100
         * targetCoinName : ANT COIN
         * duration : 10
         * itemName : ds
         * createTime : 1547815397
         * rate : 2
         * sourceCoinName : ANT TOKEN
         * perPersonLimit : 10000
         * startTime : 1547815599
         * id : 7
         * endTime : 1548679599
         * status : 4
         */

        private double amount;
        private double buyedAmount;
        private double successRatio;
        private double successAmount;
        private String itemDesc;
        private int releaseDays;
        private String targetCoinName;
        private int duration;
        private String itemName;
        private int createTime;
        private double rate;
        private String sourceCoinName;
        private int perPersonLimit;
        private int startTime;
        private int id;
        private int endTime;
        private int status;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getBuyedAmount() {
            return buyedAmount;
        }

        public void setBuyedAmount(double buyedAmount) {
            this.buyedAmount = buyedAmount;
        }

        public double getSuccessRatio() {
            return successRatio;
        }

        public void setSuccessRatio(double successRatio) {
            this.successRatio = successRatio;
        }

        public double getSuccessAmount() {
            return successAmount;
        }

        public void setSuccessAmount(double successAmount) {
            this.successAmount = successAmount;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public void setItemDesc(String itemDesc) {
            this.itemDesc = itemDesc;
        }

        public int getReleaseDays() {
            return releaseDays;
        }

        public void setReleaseDays(int releaseDays) {
            this.releaseDays = releaseDays;
        }

        public String getTargetCoinName() {
            return targetCoinName;
        }

        public void setTargetCoinName(String targetCoinName) {
            this.targetCoinName = targetCoinName;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getSourceCoinName() {
            return sourceCoinName;
        }

        public void setSourceCoinName(String sourceCoinName) {
            this.sourceCoinName = sourceCoinName;
        }

        public int getPerPersonLimit() {
            return perPersonLimit;
        }

        public void setPerPersonLimit(int perPersonLimit) {
            this.perPersonLimit = perPersonLimit;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
