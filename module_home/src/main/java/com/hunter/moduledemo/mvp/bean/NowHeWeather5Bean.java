package com.hunter.moduledemo.mvp.bean;

/**
 * Created by Hunter on 2017/7/22.
 * 当前城市实时天气实体类
 */

public class NowHeWeather5Bean {

    private BasicBean basic;
    private NowBean now;
    private String status;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class BasicBean {
        /**
         * city : 成都
         * cnty : 中国
         */

        private String city;
        private String cnty;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }
    }

    public static class NowBean {
        /**
         * cond : {"code":"101","txt":"多云"}
         * tmp : 33
         */

        private CondBean cond;
        private String tmp;

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public static class CondBean {
            /**
             * code : 101
             * txt : 多云
             */

            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }
}
