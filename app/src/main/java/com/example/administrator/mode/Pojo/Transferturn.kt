package com.example.administrator.mode.Pojo

class Transferturn {

    /**
     * code : 1
     * message : success
     * data : {"toId":100015,"toRealName":null,"amount":300,"fees":30,"actualArriveTime":null,"toUsername":"aasdf12dfd","toPhone":"13028812991","actualAmount":330,"predictArriveTime":1535817600,"fromRealname":null,"fromPhone":"13028812988","transfer_time":1535801516,"toWorldCode":"86","rate":0.1,"tradeId":2,"fromUsername":"aasdf","coin":1,"fromWordCode":"86"}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {

        var toId: Int = 0
        var toRealName: String? = null
        var amount: Double? = null
        var fees: Double? = null
        var actualArriveTime: String? = null
        var toUsername: String? = null
        var toPhone: String? = null
        var actualAmount: Double? = null
        var predictArriveTime: Int = 0
        var fromRealname: String? = null
        var fromPhone: String? = null
        var transfer_time: Int = 0
        var toWorldCode: String? = null
        var rate: Double = 0.toDouble()
        var tradeId: Int = 0
        var fromUsername: String? = null
        var coin: Int = 0
        var fromWordCode: String? = null
    }
}
