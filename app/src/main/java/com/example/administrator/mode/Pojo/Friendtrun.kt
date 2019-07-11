package com.example.administrator.mode.Pojo

import com.google.gson.annotations.SerializedName

class Friendtrun {
    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null
    class DataBean {
        /**
         * total : 2
         * yesterday_introduce_earnings : 0
         * list : [{"score":20,"createTime":1535454373,"phone":"13028812993","ant":80,"successIntroduce":0,"childsNodes":1,"id":100017,"parentNode":100012,"username":"aasdf12dfddd","realname":null}]
         */
        var total: Int = 0
        var yesterday_introduce_earnings: Double? = null
        var list: List<ListBean>? = null

        class ListBean {
            var score: Double? = null
            var createTime: Int = 0
            var phone: String? = null
            var ant: Double? = null
            var successIntroduce: Int = 0
            var childsNodes: Int = 0
            var id: Int = 0
            var parentNode: Int = 0
            var username: String? = null
            var realname: String? = null
        }
    }

}


