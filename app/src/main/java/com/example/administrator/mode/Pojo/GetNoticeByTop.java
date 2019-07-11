package com.example.administrator.mode.Pojo;

import java.util.List;

public class GetNoticeByTop {

    /**
     * code : 1
     * message : 成功
     * data : [{"createTime":"1543999270","category":"-1","title":"a","noticeId":"9","content":"aaa"},{"createTime":"1543999269","category":"-1","title":"a","noticeId":"8","content":"aaa"},{"createTime":"1543999266","category":"-1","title":"a","noticeId":"7","content":"aaa"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createTime : 1543999270
         * category : -1
         * title : a
         * noticeId : 9
         * content : aaa
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
