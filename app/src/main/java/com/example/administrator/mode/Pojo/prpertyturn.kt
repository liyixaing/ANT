package com.example.administrator.mode.Pojo

class prpertyturn {

    /**
     * code : 1
     * message : success
     * data : [{"id":1,"userId":100012,"userName":"xxx","changeAmount":0,"changeType":1,"changeTime":1535454960,"changeDesc":"asdfsadf","createTime":1535454960,"orderId":2,"version":1535454960000}]
     */

    var code: Int = 0
    var message: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 1
         * userId : 100012
         * userName : xxx
         * changeAmount : 0
         * changeType : 1
         * changeTime : 1535454960
         * changeDesc : asdfsadf
         * createTime : 1535454960
         * orderId : 2
         * version : 1535454960000
         */

        var id: Int = 0
        var userId: Int = 0
        var userName: String? = null
        var changeAmount: Double? = null
        var changeType: Int = 0
        var changeTime: Int = 0
        var changeDesc: String? = null
        var createTime: Int = 0
        var orderId: Long = 0
        var version: Long = 0
    }
}
