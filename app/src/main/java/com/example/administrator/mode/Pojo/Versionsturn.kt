package com.example.administrator.mode.Pojo

class Versionsturn {

    /**
     * code : 1
     * message : success
     * data : {"id":1,"type":0,"versionNumber":"1.0.0","versionUrl":"http://imtt.dd.qq.com/16891/BE8B862DEA886ABC337548028B043687.apk?fsname=com.fengshun.comics.kumanhu_6.2.4_624.apk&csr=2097&_track_d99957f7=91167418-5952-4bfa-82b1-81ddccaf3e50","createTime":null,"packetSize":10,"sizeUnit":"MB","releaseNotes":"1. 新增转账功能/n /n 2.xxx","status":1}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * id : 1
         * type : 0
         * versionNumber : 1.0.0
         * versionUrl : http://imtt.dd.qq.com/16891/BE8B862DEA886ABC337548028B043687.apk?fsname=com.fengshun.comics.kumanhu_6.2.4_624.apk&csr=2097&_track_d99957f7=91167418-5952-4bfa-82b1-81ddccaf3e50
         * createTime : null
         * packetSize : 10
         * sizeUnit : MB
         * releaseNotes : 1. 新增转账功能/n /n 2.xxx
         * status : 1
         */

        var id: Int = 0
        var type: Int = 0
        var versionNumber: String? = null
        var versionUrl: String? = null
        var webUrl: String? = null
        var createTime: Int = 0
        var packetSize: Int = 0
        var sizeUnit: String? = null
        var releaseNotes: String? = null
        var status: Int = 0
    }
}
