package com.hhzmy.bean;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class ZhuCe {

    /**
     * code : 200
     * message_code : 9998
     * result : {"user_logon_token":"8AIRqn5ZkweYKzPb13041205068","user_name":"用户5068","user_phone":"13041205068","user_start":"2016-12-07 11:20:08","user_status":0}
     */

    private String code;
    private String message_code;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage_code() {
        return message_code;
    }

    public void setMessage_code(String message_code) {
        this.message_code = message_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * user_logon_token : 8AIRqn5ZkweYKzPb13041205068
         * user_name : 用户5068
         * user_phone : 13041205068
         * user_start : 2016-12-07 11:20:08
         * user_status : 0
         */

        private String user_logon_token;
        private String user_name;
        private String user_phone;
        private String user_start;
        private int user_status;

        public String getUser_logon_token() {
            return user_logon_token;
        }

        public void setUser_logon_token(String user_logon_token) {
            this.user_logon_token = user_logon_token;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_start() {
            return user_start;
        }

        public void setUser_start(String user_start) {
            this.user_start = user_start;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }
    }
}
