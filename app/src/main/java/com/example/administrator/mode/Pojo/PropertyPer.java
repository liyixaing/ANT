package com.example.administrator.mode.Pojo;

public class PropertyPer {
    /**
     * code : 1
     * message : success
     * data : {"fees":0,"fromWorldCode":"86","fromChangeAnt":-400,"orderId":74,"toPhone":"13899999999","toUsername":"100164","toChangeScore":40,"fromChangeScore":360,"fromRealname":null,"toRealname":null,"fromPhone":"13000000007","toChangeAnt":360,"rate":0,"createTime":1537949350,"toWorldcode":"86","tradeTypeDesc":"用户转账","fromUsername":"渣小渣6","coin":"余额"}
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
         * fees : 0
         * fromWorldCode : 86
         * fromChangeAnt : -400
         * orderId : 74
         * toPhone : 13899999999
         * toUsername : 100164
         * toChangeScore : 40
         * fromChangeScore : 360
         * fromRealname : null
         * toRealname : null
         * fromPhone : 13000000007
         * toChangeAnt : 360
         * rate : 0
         * createTime : 1537949350
         * toWorldcode : 86
         * tradeTypeDesc : 用户转账
         * fromUsername : 渣小渣6
         * coin : 余额
         */

        private double fees;
        private String fromWorldCode;
        private double fromChangeAnt;
        private Long orderId;
        private String toPhone;
        private String toUsername;
        private double toChangeScore;
        private double fromChangeScore;
        private String fromRealname;
        private String toRealname;
        private String fromPhone;
        private double toChangeAnt;
        private double rate;
        private int createTime;
        private String toWorldcode;
        private String tradeTypeDesc;
        private String fromUsername;
        private String coin;

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        private String memo;

        public double getFees() {
            return fees;
        }

        public void setFees(double fees) {
            this.fees = fees;
        }

        public String getFromWorldCode() {
            return fromWorldCode;
        }

        public void setFromWorldCode(String fromWorldCode) {
            this.fromWorldCode = fromWorldCode;
        }

        public double getFromChangeAnt() {
            return fromChangeAnt;
        }

        public void setFromChangeAnt(double fromChangeAnt) {
            this.fromChangeAnt = fromChangeAnt;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public String getToPhone() {
            return toPhone;
        }

        public void setToPhone(String toPhone) {
            this.toPhone = toPhone;
        }

        public String getToUsername() {
            return toUsername;
        }

        public void setToUsername(String toUsername) {
            this.toUsername = toUsername;
        }

        public double getToChangeScore() {
            return toChangeScore;
        }

        public void setToChangeScore(double toChangeScore) {
            this.toChangeScore = toChangeScore;
        }

        public double getFromChangeScore() {
            return fromChangeScore;
        }

        public void setFromChangeScore(double fromChangeScore) {
            this.fromChangeScore = fromChangeScore;
        }

        public String getFromRealname() {
            return fromRealname;
        }

        public void setFromRealname(String fromRealname) {
            this.fromRealname = fromRealname;
        }

        public String getToRealname() {
            return toRealname;
        }

        public void setToRealname(String toRealname) {
            this.toRealname = toRealname;
        }

        public String getFromPhone() {
            return fromPhone;
        }

        public void setFromPhone(String fromPhone) {
            this.fromPhone = fromPhone;
        }

        public double getToChangeAnt() {
            return toChangeAnt;
        }

        public void setToChangeAnt(double toChangeAnt) {
            this.toChangeAnt = toChangeAnt;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public String getToWorldcode() {
            return toWorldcode;
        }

        public void setToWorldcode(String toWorldcode) {
            this.toWorldcode = toWorldcode;
        }

        public String getTradeTypeDesc() {
            return tradeTypeDesc;
        }

        public void setTradeTypeDesc(String tradeTypeDesc) {
            this.tradeTypeDesc = tradeTypeDesc;
        }

        public String getFromUsername() {
            return fromUsername;
        }

        public void setFromUsername(String fromUsername) {
            this.fromUsername = fromUsername;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }
    }
}
