package com.example.administrator.mode.Pojo;

import java.util.List;

public class NodeTurn {

    /**
     * code : 1
     * message : success
     * data : {"yestoday_node_score_earnings":0,"score":4842.61257,"total":0,"yestoday_node_ant_earnings":0,"history_node_ant_earnings":20.00914186,"size":0,"lists":[{"id":4240,"userId":100123,"userName":"渣小渣6","userPhone":"13000000007","changeTime":1537715380,"changeAmount":0.997,"changeDesc":"T1社区","changeType":7,"createTime":1537637459,"orderId":1977,"version":null,"isAnt":1}],"totalPage":0,"ant":28162.27130814,"history_node_score_earnings":0}
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
         * yestoday_node_score_earnings : 0
         * score : 4842.61257
         * total : 0
         * yestoday_node_ant_earnings : 0
         * history_node_ant_earnings : 20.00914186
         * size : 0
         * lists : [{"id":4240,"userId":100123,"userName":"渣小渣6","userPhone":"13000000007","changeTime":1537715380,"changeAmount":0.997,"changeDesc":"T1社区","changeType":7,"createTime":1537637459,"orderId":1977,"version":null,"isAnt":1}]
         * totalPage : 0
         * ant : 28162.27130814
         * history_node_score_earnings : 0
         */

        private double yestoday_node_score_earnings;
        private double score;
        private int total;
        private double yestoday_node_ant_earnings;
        private double history_node_ant_earnings;
        private int size;
        private int totalPage;
        private double ant;
        private double history_node_score_earnings;
        private List<ListsBean> lists;

        public double getYestoday_node_score_earnings() {
            return yestoday_node_score_earnings;
        }

        public void setYestoday_node_score_earnings(double yestoday_node_score_earnings) {
            this.yestoday_node_score_earnings = yestoday_node_score_earnings;
        }

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

        public double getYestoday_node_ant_earnings() {
            return yestoday_node_ant_earnings;
        }

        public void setYestoday_node_ant_earnings(double yestoday_node_ant_earnings) {
            this.yestoday_node_ant_earnings = yestoday_node_ant_earnings;
        }

        public double getHistory_node_ant_earnings() {
            return history_node_ant_earnings;
        }

        public void setHistory_node_ant_earnings(double history_node_ant_earnings) {
            this.history_node_ant_earnings = history_node_ant_earnings;
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

        public double getHistory_node_score_earnings() {
            return history_node_score_earnings;
        }

        public void setHistory_node_score_earnings(double history_node_score_earnings) {
            this.history_node_score_earnings = history_node_score_earnings;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * id : 4240
             * userId : 100123
             * userName : 渣小渣6
             * userPhone : 13000000007
             * changeTime : 1537715380
             * changeAmount : 0.997
             * changeDesc : T1社区
             * changeType : 7
             * createTime : 1537637459
             * orderId : 1977
             * version : null
             * isAnt : 1
             */

            private int id;
            private int userId;
            private String userName;
            private String userPhone;
            private int changeTime;
            private double changeAmount;
            private String changeDesc;
            private int changeType;
            private int createTime;
            private int orderId;
            private String version;
            private int isAnt;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public int getChangeTime() {
                return changeTime;
            }

            public void setChangeTime(int changeTime) {
                this.changeTime = changeTime;
            }

            public double getChangeAmount() {
                return changeAmount;
            }

            public void setChangeAmount(double changeAmount) {
                this.changeAmount = changeAmount;
            }

            public String getChangeDesc() {
                return changeDesc;
            }

            public void setChangeDesc(String changeDesc) {
                this.changeDesc = changeDesc;
            }

            public int getChangeType() {
                return changeType;
            }

            public void setChangeType(int changeType) {
                this.changeType = changeType;
            }

            public int getCreateTime() {
                return createTime;
            }

            public void setCreateTime(int createTime) {
                this.createTime = createTime;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public int getIsAnt() {
                return isAnt;
            }

            public void setIsAnt(int isAnt) {
                this.isAnt = isAnt;
            }
        }
    }
}
