package com.example.administrator.mode.Pojo;

public class RedPacket {
    /**
     * code : 1
     * message : success
     * data : {"un_claim_amount":234.65056592,"id":1775,"current_personal_score":1781.9837219,"userId":100001}
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
         * un_claim_amount : 234.65056592
         * id : 1775
         * current_personal_score : 1781.9837219
         * userId : 100001
         */

        private double un_claim_amount;
        private int id;
        private double current_personal_score;
        private int userId;
        public double getUn_claim_amount() {
            return un_claim_amount;
        }

        public void setUn_claim_amount(double un_claim_amount) {
            this.un_claim_amount = un_claim_amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getCurrent_personal_score() {
            return current_personal_score;
        }

        public void setCurrent_personal_score(double current_personal_score) {
            this.current_personal_score = current_personal_score;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
