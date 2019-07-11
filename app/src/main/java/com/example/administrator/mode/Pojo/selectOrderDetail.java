package com.example.administrator.mode.Pojo;

public class selectOrderDetail {

    /**
     * code : 1
     * message : 成功
     * data : {"harvestScore":199.8,"orderNo":"111","statusDesc":"未发货","payTime":1544497339,"ant":978.2,"shopName":"ANT-SHOP","userId":131426,"sendTime":null,"receiveTime":null,"postage":0,"totalAmount":-222,"payType":0,"phone":"13500000000","createTime":1544433524,"cancleTime":1544436000,"worldCode":"86","payment":-222,"shopId":2,"shopKey":"b64ab4b8124e2c5d43d52d9c05a6f992","username":"131426","status":1}
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
         * harvestScore : 199.8
         * orderNo : 111
         * statusDesc : 未发货
         * payTime : 1544497339
         * ant : 978.2
         * shopName : ANT-SHOP
         * userId : 131426
         * sendTime : null
         * receiveTime : null
         * postage : 0
         * totalAmount : -222
         * payType : 0
         * phone : 13500000000
         * createTime : 1544433524
         * cancleTime : 1544436000
         * worldCode : 86
         * payment : -222
         * shopId : 2
         * shopKey : b64ab4b8124e2c5d43d52d9c05a6f992
         * username : 131426
         * status : 1
         */

        private double harvestScore;
        private String orderNo;
        private String statusDesc;
        private int payTime;
        private double ant;
        private String shopName;
        private int userId;
        private Object sendTime;
        private Object receiveTime;
        private int postage;
        private double totalAmount;
        private int payType;
        private String phone;
        private int createTime;
        private int cancleTime;
        private String worldCode;
        private double payment;
        private int shopId;
        private String shopKey;
        private String username;
        private int status;

        public double getHarvestScore() {
            return harvestScore;
        }

        public void setHarvestScore(double harvestScore) {
            this.harvestScore = harvestScore;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getStatusDesc() {
            return statusDesc;
        }

        public void setStatusDesc(String statusDesc) {
            this.statusDesc = statusDesc;
        }

        public int getPayTime() {
            return payTime;
        }

        public void setPayTime(int payTime) {
            this.payTime = payTime;
        }

        public double getAnt() {
            return ant;
        }

        public void setAnt(double ant) {
            this.ant = ant;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getSendTime() {
            return sendTime;
        }

        public void setSendTime(Object sendTime) {
            this.sendTime = sendTime;
        }

        public Object getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(Object receiveTime) {
            this.receiveTime = receiveTime;
        }

        public int getPostage() {
            return postage;
        }

        public void setPostage(int postage) {
            this.postage = postage;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public int getCancleTime() {
            return cancleTime;
        }

        public void setCancleTime(int cancleTime) {
            this.cancleTime = cancleTime;
        }

        public String getWorldCode() {
            return worldCode;
        }

        public void setWorldCode(String worldCode) {
            this.worldCode = worldCode;
        }

        public double getPayment() {
            return payment;
        }

        public void setPayment(double payment) {
            this.payment = payment;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopKey() {
            return shopKey;
        }

        public void setShopKey(String shopKey) {
            this.shopKey = shopKey;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
