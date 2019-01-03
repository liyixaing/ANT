package com.example.administrator.mode.Pojo

class Loginturn {


    /**
     * code : 1
     * message : success
     * data : {"introducer":100000,"isVerified":null,"sex":null,"updateTime":1535549988,"avatar":null,"token":"42e0ee35-ca4b-4496-8b32-d78f28d8d9d0","tokenExprie":1535787669,"phone":"13028812988","createTime":1535449040,"worldCode":"86","id":100012,"age":null,"email":null,"username":"aasdf","status":1}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * introducer : 100000
         * isVerified : null
         * sex : null
         * updateTime : 1535549988
         * avatar : null
         * token : 42e0ee35-ca4b-4496-8b32-d78f28d8d9d0
         * tokenExprie : 1535787669
         * phone : 13028812988
         * createTime : 1535449040
         * worldCode : 86
         * id : 100012
         * age : null
         * email : null
         * username : aasdf
         * status : 1
         */

        var introducer: Int = 0
        var isVerified: Any? = null
        var sex: Any? = null
        var updateTime: Int = 0
        var avatar: String? = null
        var token: String? = null
        var tokenExprie: Int = 0
        var phone: String? = null
        var createTime: Int = 0
        var worldCode: String? = null
        var id: Int = 0
        var age: Any? = null
        var email: Any? = null
        var username: String? = null
        var status: Int = 0
    }
}
