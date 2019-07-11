package com.example.administrator.mode.Pojo

class Bantrun {

    /**
     * code : 1
     * message : success
     * data : [{"id":441,"userId":100012,"userName":"aasdf","userPhone":null,"changeTime":1535541626,"changeAmount":10,"changeDesc":"红包奖励","changeType":4,"createTime":1535541626,"orderId":13,"version":1535541626212}]
     */

    var code: Int = 0
    var message: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 441
         * userId : 100012
         * userName : aasdf
         * userPhone : null
         * changeTime : 1535541626
         * changeAmount : 10
         * changeDesc : 红包奖励
         * changeType : 4
         * createTime : 1535541626
         * orderId : 13
         * version : 1535541626212
         */

        var id: Int = 0
        var userId: Int = 0
        var userName: String? = null
        private var userPhone: String? = null
        var changeTime: Int = 0
        var changeAmount: Double? = null
        var changeDesc: String? = null
        var changeType: Int = 0
        var createTime: Int = 0
        var orderId: Long = 0
        var version: Long = 0

        fun getUserPhone(): Any? {
            return userPhone
        }

        fun setUserPhone(userPhone: String) {
            this.userPhone = userPhone
        }
    }
}
