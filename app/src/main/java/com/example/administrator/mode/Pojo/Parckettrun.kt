package com.example.administrator.mode.Pojo

class Parckettrun {
    /**
     * code : 1
     * message : success
     * data : [{"id":5,"userId":100012,"type":0,"contributor":100018,"contributorCoin":0,"baseCoin":0,"baseAmount":80,"contributorAmount":1.6,"status":0,"createTime":1535524590,"claimTime":null,"expireTime":1535558400,"orderId":3,"version":1535524590900}]
     */

    var code: Int = 0
    var message: String? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 5
         * userId : 100012
         * type : 0
         * contributor : 100018
         * contributorCoin : 0
         * baseCoin : 0
         * baseAmount : 80
         * contributorAmount : 1.6
         * status : 0
         * createTime : 1535524590
         * claimTime : null
         * expireTime : 1535558400
         * orderId : 3
         * version : 1535524590900
         */

        var id: Int = 0
        var userId: Int = 0
        var type: Int = 0
        var contributor: Int = 0
        var contributorCoin: Int = 0
        var baseCoin: Int = 0
        var baseAmount: Double? =null
        var contributorAmount: Double? = null
        var status: Int = 0
        var createTime: Int = 0
        var claimTime: Int? = null
        var expireTime: Int = 0
        var orderId: Int = 0
        var version: Long = 0
    }
}
