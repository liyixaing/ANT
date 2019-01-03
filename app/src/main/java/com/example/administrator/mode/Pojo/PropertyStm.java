package com.example.administrator.mode.Pojo;

public class PropertyStm {
    /**
     * code : 1
     * message : success
     * data : {"score":-100,"phone":"13000000007","orderId":69,"createTime":1537950708,"ant":100,"worldCode":"86","tradeTypeDesc":"DPOS","userId":100123,"username":"渣小渣6"}
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
         * score : -100
         * phone : 13000000007
         * orderId : 69
         * createTime : 1537950708
         * ant : 100
         * worldCode : 86
         * tradeTypeDesc : DPOS
         * userId : 100123
         * username : 渣小渣6
         */

        private double score;
        private String phone;
        private Long orderId;
        private int createTime;
        private double ant;
        private String worldCode;
        private String tradeTypeDesc;
        private int userId;
        private String username;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public double getAnt() {
            return ant;
        }

        public void setAnt(double ant) {
            this.ant = ant;
        }

        public String getWorldCode() {
            return worldCode;
        }

        public void setWorldCode(String worldCode) {
            this.worldCode = worldCode;
        }

        public String getTradeTypeDesc() {
            return tradeTypeDesc;
        }

        public void setTradeTypeDesc(String tradeTypeDesc) {
            this.tradeTypeDesc = tradeTypeDesc;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
