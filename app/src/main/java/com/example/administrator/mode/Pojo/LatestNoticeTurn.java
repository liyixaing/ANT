package com.example.administrator.mode.Pojo;

public class LatestNoticeTurn {

    /**
     * code : 1
     * message : success
     * data : {"createTime":1539918196,"id":1,"title":"sdf","content":"fsfdasdjfklasjdfkllas;jfdksas;fj","status":0}
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
         * createTime : 1539918196
         * id : 1
         * title : sdf
         * content : fsfdasdjfklasjdfkllas;jfdksas;fj
         * status : 0
         */

        private int createTime;
        private int id;
        private String title;
        private String content;
        private int status;

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
