package com.example.administrator.mode.Pojo;

public class buyZhongChouBean {

    /**
     * code : 1
     * message : 成功
     * data : {"costSourceCoin":1200,"gainTargetCoin":600}
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
         * costSourceCoin : 1200
         * gainTargetCoin : 600
         */

        private double costSourceCoin;
        private double gainTargetCoin;

        public double getCostSourceCoin() {
            return costSourceCoin;
        }

        public void setCostSourceCoin(double costSourceCoin) {
            this.costSourceCoin = costSourceCoin;
        }

        public double getGainTargetCoin() {
            return gainTargetCoin;
        }

        public void setGainTargetCoin(double gainTargetCoin) {
            this.gainTargetCoin = gainTargetCoin;
        }
    }
}
