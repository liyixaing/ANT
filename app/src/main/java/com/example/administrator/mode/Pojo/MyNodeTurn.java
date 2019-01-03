package com.example.administrator.mode.Pojo;

import java.util.List;

public class MyNodeTurn {

    /**
     * code : 1
     * message : success
     * data : {"nodes":2,"lists":[{"introducer":100110,"parentNode":100110,"introducerPhone":"152****5555","avatar":null,"childs":4,"realname":null,"score":9114.06654159,"introducerWorldCode":"86","introducerRealname":null,"phone":"152****2220","worldCode":"86","id":100114,"introducerUsername":"小蚂蚁","username":"小蚂蚁"}]}
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
         * nodes : 2
         * lists : [{"introducer":100110,"parentNode":100110,"introducerPhone":"152****5555","avatar":null,"childs":4,"realname":null,"score":9114.06654159,"introducerWorldCode":"86","introducerRealname":null,"phone":"152****2220","worldCode":"86","id":100114,"introducerUsername":"小蚂蚁","username":"小蚂蚁"}]
         */

        private int nodes;
        private List<ListsBean> lists;

        public int getNodes() {
            return nodes;
        }

        public void setNodes(int nodes) {
            this.nodes = nodes;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * introducer : 100110
             * parentNode : 100110
             * introducerPhone : 152****5555
             * avatar : null
             * childs : 4
             * realname : null
             * score : 9114.06654159
             * introducerWorldCode : 86
             * introducerRealname : null
             * phone : 152****2220
             * worldCode : 86
             * id : 100114
             * introducerUsername : 小蚂蚁
             * username : 小蚂蚁
             */

            private int introducer;
            private int parentNode;
            private String introducerPhone;
            private String avatar;
            private int childs;
            private String realname;
            private double score;
            private String introducerWorldCode;
            private String introducerRealname;
            private String phone;
            private String worldCode;
            private int id;
            private String introducerUsername;
            private String username;

            public int getIntroducer() {
                return introducer;
            }

            public void setIntroducer(int introducer) {
                this.introducer = introducer;
            }

            public int getParentNode() {
                return parentNode;
            }

            public void setParentNode(int parentNode) {
                this.parentNode = parentNode;
            }

            public String getIntroducerPhone() {
                return introducerPhone;
            }

            public void setIntroducerPhone(String introducerPhone) {
                this.introducerPhone = introducerPhone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getChilds() {
                return childs;
            }

            public void setChilds(int childs) {
                this.childs = childs;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getIntroducerWorldCode() {
                return introducerWorldCode;
            }

            public void setIntroducerWorldCode(String introducerWorldCode) {
                this.introducerWorldCode = introducerWorldCode;
            }

            public String getIntroducerRealname() {
                return introducerRealname;
            }

            public void setIntroducerRealname(String introducerRealname) {
                this.introducerRealname = introducerRealname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getWorldCode() {
                return worldCode;
            }

            public void setWorldCode(String worldCode) {
                this.worldCode = worldCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIntroducerUsername() {
                return introducerUsername;
            }

            public void setIntroducerUsername(String introducerUsername) {
                this.introducerUsername = introducerUsername;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
