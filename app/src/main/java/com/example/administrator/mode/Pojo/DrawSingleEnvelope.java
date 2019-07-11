package com.example.administrator.mode.Pojo;

public class DrawSingleEnvelope {
    /**
     * code : 1
     * message : 成功
     * data : {"score":0,"ant":10}
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
         * score : 0
         * ant : 10
         */

        private double score;
        private double ant;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public double getAnt() {
            return ant;
        }

        public void setAnt(double ant) {
            this.ant = ant;
        }
    }
}
