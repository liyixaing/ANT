package com.example.administrator.mode.Pojo;

import java.util.List;

public class aa {

    /**
     * code : 1
     * message : success
     * data : {"score":3883.8987243,"total":0,"yestoday_dpos_ant_earnings":120,"size":0,"lists":[{"score":540,"provider":null,"orderId":25,"ant":60,"id":3431,"time":1537343704,"userId":100123,"desc":""}],"totalPage":0,"ant":1316.1012757,"history_dpos_score_earnings":120,"history_dpos_ant_earnings":120,"yestoday_dpos_score_earnings":1080}
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
         * score : 3883.8987243
         * total : 0
         * yestoday_dpos_ant_earnings : 120
         * size : 0
         * lists : [{"score":540,"provider":null,"orderId":25,"ant":60,"id":3431,"time":1537343704,"userId":100123,"desc":""}]
         * totalPage : 0
         * ant : 1316.1012757
         * history_dpos_score_earnings : 120
         * history_dpos_ant_earnings : 120
         * yestoday_dpos_score_earnings : 1080
         */

        private double score;
        private int total;
        private double yestoday_dpos_ant_earnings;
        private int size;
        private int totalPage;
        private double ant;
        private double history_dpos_score_earnings;
        private double history_dpos_ant_earnings;
        private double yestoday_dpos_score_earnings;
        private List<ListsBean> lists;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public double getYestoday_dpos_ant_earnings() {
            return yestoday_dpos_ant_earnings;
        }

        public void setYestoday_dpos_ant_earnings(double yestoday_dpos_ant_earnings) {
            this.yestoday_dpos_ant_earnings = yestoday_dpos_ant_earnings;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public double getAnt() {
            return ant;
        }

        public void setAnt(double ant) {
            this.ant = ant;
        }

        public double getHistory_dpos_score_earnings() {
            return history_dpos_score_earnings;
        }

        public void setHistory_dpos_score_earnings(double history_dpos_score_earnings) {
            this.history_dpos_score_earnings = history_dpos_score_earnings;
        }

        public double getHistory_dpos_ant_earnings() {
            return history_dpos_ant_earnings;
        }

        public void setHistory_dpos_ant_earnings(double history_dpos_ant_earnings) {
            this.history_dpos_ant_earnings = history_dpos_ant_earnings;
        }

        public double getYestoday_dpos_score_earnings() {
            return yestoday_dpos_score_earnings;
        }

        public void setYestoday_dpos_score_earnings(double yestoday_dpos_score_earnings) {
            this.yestoday_dpos_score_earnings = yestoday_dpos_score_earnings;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * score : 540
             * provider : null
             * orderId : 25
             * ant : 60
             * id : 3431
             * time : 1537343704
             * userId : 100123
             * desc :
             */

            private double score;
            private String provider;
            private int orderId;
            private double ant;
            private int id;
            private int time;
            private int userId;
            private String desc;

            public Double getScore() {
                return score;
            }

            public void setScore(Double score) {
                this.score = score;
            }

            public String getProvider() {
                return provider;
            }

            public void setProvider(String provider) {
                this.provider = provider;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public double getAnt() {
                return ant;
            }

            public void setAnt(double ant) {
                this.ant = ant;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}
