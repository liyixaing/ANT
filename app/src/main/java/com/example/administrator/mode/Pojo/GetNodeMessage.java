package com.example.administrator.mode.Pojo;

public class GetNodeMessage {

    /**
     * code : 1
     * message : success
     * data : {"insertPointId":100132,"introducerWorldCode":"86","introduceRealname":null,"phone":"13000001001","introduce":100123,"childsNode":0,"introduceUsername":"渣小渣6","worldCode":"86","avatar":null,"introducePhone":"13000000007","username":"小蚂蚁","realname":null}
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
         * insertPointId : 100132
         * introducerWorldCode : 86
         * introduceRealname : null
         * phone : 13000001001
         * introduce : 100123
         * childsNode : 0
         * introduceUsername : 渣小渣6
         * worldCode : 86
         * avatar : null
         * introducePhone : 13000000007
         * username : 小蚂蚁
         * realname : null
         */

        private int insertPointId;
        private String introducerWorldCode;
        private String introduceRealname;
        private String phone;
        private int introduce;
        private int childsNode;
        private String introduceUsername;
        private String worldCode;
        private String avatar;
        private String introducePhone;
        private String username;
        private String realname;

        public int getInsertPointId() {
            return insertPointId;
        }

        public void setInsertPointId(int insertPointId) {
            this.insertPointId = insertPointId;
        }

        public String getIntroducerWorldCode() {
            return introducerWorldCode;
        }

        public void setIntroducerWorldCode(String introducerWorldCode) {
            this.introducerWorldCode = introducerWorldCode;
        }

        public String getIntroduceRealname() {
            return introduceRealname;
        }

        public void setIntroduceRealname(String introduceRealname) {
            this.introduceRealname = introduceRealname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getIntroduce() {
            return introduce;
        }

        public void setIntroduce(int introduce) {
            this.introduce = introduce;
        }

        public int getChildsNode() {
            return childsNode;
        }

        public void setChildsNode(int childsNode) {
            this.childsNode = childsNode;
        }

        public String getIntroduceUsername() {
            return introduceUsername;
        }

        public void setIntroduceUsername(String introduceUsername) {
            this.introduceUsername = introduceUsername;
        }

        public String getWorldCode() {
            return worldCode;
        }

        public void setWorldCode(String worldCode) {
            this.worldCode = worldCode;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIntroducePhone() {
            return introducePhone;
        }

        public void setIntroducePhone(String introducePhone) {
            this.introducePhone = introducePhone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }
}
