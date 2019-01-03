package com.example.administrator.mode.Pojo

class prturn {
    /**
     * code : 1
     * message : success
     * data : {"user_ant":"10.0","lt_earnings":"0.00000000","static_earnings":"0.00000000","user_score":"10.0","introduce_earnings":"0.0","userId":"100012","username":"aasdf"}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * user_ant : 10.0
         * lt_earnings : 0.00000000
         * static_earnings : 0.00000000
         * user_score : 10.0
         * introduce_earnings : 0.0
         * userId : 100012
         * username : aasdf
         */
        var redPacketCount: String? = null
        var user_ant: String? = null
        var lt_earnings: String? = null
        var static_earnings: String? = null
        var user_score: String? = null
        var introduce_earnings: String? = null
        var lg_earnings: String? = null
        var userId: String? = null
        var username: String? = null
        var isVip: String? = null
        var isMerchant: String? = null
    }
}
