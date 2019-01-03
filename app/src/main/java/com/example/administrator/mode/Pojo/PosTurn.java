package com.example.administrator.mode.Pojo;

import java.util.List;

public class PosTurn {

    /**
     * code : 1
     * message : success
     * data : {"total":0,"size":0,"history_pos_earnings":23.52902298,"lists":[{"id":3496,"userId":100123,"userName":"渣小渣6","userPhone":"13000000007","changeTime":1537430288,"changeAmount":11.74340807,"changeDesc":"POS权益","changeType":3,"createTime":1537343786,"orderId":1393,"version":null}],"totalPage":0,"yestoday_pos_earnings":11.74340807}
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
         * total : 0
         * size : 0
         * history_pos_earnings : 23.52902298
         * lists : [{"id":3496,"userId":100123,"userName":"渣小渣6","userPhone":"13000000007","changeTime":1537430288,"changeAmount":11.74340807,"changeDesc":"POS权益","changeType":3,"createTime":1537343786,"orderId":1393,"version":null}]
         * totalPage : 0
         * yestoday_pos_earnings : 11.74340807
         */

        private int total;

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

        private double score;
        private double ant;
        private int size;
        private double history_pos_earnings;
        private int totalPage;
        private double yestoday_pos_earnings;
        private List<ListsBean> lists;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public double getHistory_pos_earnings() {
            return history_pos_earnings;
        }

        public void setHistory_pos_earnings(double history_pos_earnings) {
            this.history_pos_earnings = history_pos_earnings;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public double getYestoday_pos_earnings() {
            return yestoday_pos_earnings;
        }

        public void setYestoday_pos_earnings(double yestoday_pos_earnings) {
            this.yestoday_pos_earnings = yestoday_pos_earnings;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * id : 3496
             * userId : 100123
             * userName : 渣小渣6
             * userPhone : 13000000007
             * changeTime : 1537430288
             * changeAmount : 11.74340807
             * changeDesc : POS权益
             * changeType : 3
             * createTime : 1537343786
             * orderId : 1393
             * version : null
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
        }
    }
}
