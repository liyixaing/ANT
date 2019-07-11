package com.example.administrator.mode.Pojo;

import java.util.List;

public class GetState {

    /**
     * code : 1
     * message : 成功
     * data : [{"countryCode":"86","name":"中国","id":53,"provinces":[{"areaCode":"71","areaDesc":"台湾省","id":12036,"citys":[{"areaCode":"90","areaDesc":"基隆市","id":9136},{"areaCode":"90","areaDesc":"新竹市","id":9137},{"areaCode":"90","areaDesc":"嘉义市","id":9138},{"areaCode":"06","areaDesc":"桃园市","id":9123},{"areaCode":"05","areaDesc":"台南市","id":9086},{"areaCode":"04","areaDesc":"台中市","id":9057},{"areaCode":"03","areaDesc":"新北市","id":9028},{"areaCode":"02","areaDesc":"高雄市","id":8990},{"areaCode":"01","areaDesc":"台北市","id":8978}]}]}]
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
         * countryCode : 86
         * name : 中国
         * id : 53
         * provinces : [{"areaCode":"71","areaDesc":"台湾省","id":12036,"citys":[{"areaCode":"90","areaDesc":"基隆市","id":9136},{"areaCode":"90","areaDesc":"新竹市","id":9137},{"areaCode":"90","areaDesc":"嘉义市","id":9138},{"areaCode":"06","areaDesc":"桃园市","id":9123},{"areaCode":"05","areaDesc":"台南市","id":9086},{"areaCode":"04","areaDesc":"台中市","id":9057},{"areaCode":"03","areaDesc":"新北市","id":9028},{"areaCode":"02","areaDesc":"高雄市","id":8990},{"areaCode":"01","areaDesc":"台北市","id":8978}]}]
         */

        private String countryCode;
        private String name;
        private int id;
        private List<ProvincesBean> provinces;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ProvincesBean> getProvinces() {
            return provinces;
        }

        public void setProvinces(List<ProvincesBean> provinces) {
            this.provinces = provinces;
        }

        public static class ProvincesBean {
            /**
             * areaCode : 71
             * areaDesc : 台湾省
             * id : 12036
             * citys : [{"areaCode":"90","areaDesc":"基隆市","id":9136},{"areaCode":"90","areaDesc":"新竹市","id":9137},{"areaCode":"90","areaDesc":"嘉义市","id":9138},{"areaCode":"06","areaDesc":"桃园市","id":9123},{"areaCode":"05","areaDesc":"台南市","id":9086},{"areaCode":"04","areaDesc":"台中市","id":9057},{"areaCode":"03","areaDesc":"新北市","id":9028},{"areaCode":"02","areaDesc":"高雄市","id":8990},{"areaCode":"01","areaDesc":"台北市","id":8978}]
             */

            private String areaCode;
            private String areaDesc;
            private int id;
            private List<CitysBean> citys;

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getAreaDesc() {
                return areaDesc;
            }

            public void setAreaDesc(String areaDesc) {
                this.areaDesc = areaDesc;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<CitysBean> getCitys() {
                return citys;
            }

            public void setCitys(List<CitysBean> citys) {
                this.citys = citys;
            }

            public static class CitysBean {
                /**
                 * areaCode : 90
                 * areaDesc : 基隆市
                 * id : 9136
                 */

                private String areaCode;
                private String areaDesc;
                private int id;

                public String getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(String areaCode) {
                    this.areaCode = areaCode;
                }

                public String getAreaDesc() {
                    return areaDesc;
                }

                public void setAreaDesc(String areaDesc) {
                    this.areaDesc = areaDesc;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
