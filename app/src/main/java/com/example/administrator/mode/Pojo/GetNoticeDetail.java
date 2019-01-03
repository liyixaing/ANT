package com.example.administrator.mode.Pojo;

public class GetNoticeDetail {


    /**
     * code : 1
     * message : success
     * data : {"createTime":"1549928800","category":"1","title":"sdfas","noticeId":"1","content":"Everyone who cares about ANficate ce.\\n\\n ANT\\n NOV 02, 2018"}
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
         * createTime : 1549928800
         * category : 1
         * title : sdfas
         * noticeId : 1
         * content : Everyone who cares about ANficate ce.\n\n ANT\n NOV 02, 2018
         */

        private String createTime;
        private String category;
        private String title;
        private String noticeId;
        private String content;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
