package com.example.administrator.mode.Pojo;

public class GetRedBagMessage {

    /**
     * code : 1
     * message : 成功
     * data : {"ant_red_envolope_territory_ant_min_value":"10","ant_red_envolope_territory_ant_max_value":"100000","ant_red_envolope_territory_score_max_value":"100000","ant_red_envolope_territory_expire_minute":"100","ant_red_envolope_territory_personal_llimit":"10","ant_red_envolope_territoty_score_min_value":"10","red_envolope_memo_length_limit":20,"red_envolope_distance":10000}
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
         * ant_red_envolope_territory_ant_min_value : 10
         * ant_red_envolope_territory_ant_max_value : 100000
         * ant_red_envolope_territory_score_max_value : 100000
         * ant_red_envolope_territory_expire_minute : 100
         * ant_red_envolope_territory_personal_llimit : 10
         * ant_red_envolope_territoty_score_min_value : 10
         * red_envolope_memo_length_limit : 20
         * red_envolope_distance : 10000
         */

        private String ant_red_envolope_territory_ant_min_value;
        private String ant_red_envolope_territory_ant_max_value;
        private String ant_red_envolope_territory_score_max_value;
        private String ant_red_envolope_territory_expire_minute;
        private String ant_red_envolope_territory_personal_llimit;
        private String ant_red_envolope_territoty_score_min_value;
        private int red_envolope_memo_length_limit;
        private int red_envolope_distance;
        public String getAnt_red_envolope_territory_ant_min_value() {
            return ant_red_envolope_territory_ant_min_value;
        }

        public void setAnt_red_envolope_territory_ant_min_value(String ant_red_envolope_territory_ant_min_value) {
            this.ant_red_envolope_territory_ant_min_value = ant_red_envolope_territory_ant_min_value;
        }

        public String getAnt_red_envolope_territory_ant_max_value() {
            return ant_red_envolope_territory_ant_max_value;
        }

        public void setAnt_red_envolope_territory_ant_max_value(String ant_red_envolope_territory_ant_max_value) {
            this.ant_red_envolope_territory_ant_max_value = ant_red_envolope_territory_ant_max_value;
        }

        public String getAnt_red_envolope_territory_score_max_value() {
            return ant_red_envolope_territory_score_max_value;
        }

        public void setAnt_red_envolope_territory_score_max_value(String ant_red_envolope_territory_score_max_value) {
            this.ant_red_envolope_territory_score_max_value = ant_red_envolope_territory_score_max_value;
        }

        public String getAnt_red_envolope_territory_expire_minute() {
            return ant_red_envolope_territory_expire_minute;
        }

        public void setAnt_red_envolope_territory_expire_minute(String ant_red_envolope_territory_expire_minute) {
            this.ant_red_envolope_territory_expire_minute = ant_red_envolope_territory_expire_minute;
        }

        public String getAnt_red_envolope_territory_personal_llimit() {
            return ant_red_envolope_territory_personal_llimit;
        }

        public void setAnt_red_envolope_territory_personal_llimit(String ant_red_envolope_territory_personal_llimit) {
            this.ant_red_envolope_territory_personal_llimit = ant_red_envolope_territory_personal_llimit;
        }

        public String getAnt_red_envolope_territoty_score_min_value() {
            return ant_red_envolope_territoty_score_min_value;
        }

        public void setAnt_red_envolope_territoty_score_min_value(String ant_red_envolope_territoty_score_min_value) {
            this.ant_red_envolope_territoty_score_min_value = ant_red_envolope_territoty_score_min_value;
        }

        public int getRed_envolope_memo_length_limit() {
            return red_envolope_memo_length_limit;
        }

        public void setRed_envolope_memo_length_limit(int red_envolope_memo_length_limit) {
            this.red_envolope_memo_length_limit = red_envolope_memo_length_limit;
        }

        public int getRed_envolope_distance() {
            return red_envolope_distance;
        }

        public void setRed_envolope_distance(int red_envolope_distance) {
            this.red_envolope_distance = red_envolope_distance;
        }
    }
}
