package com.example.administrator.mode.Pojo

class Balanceturn {
    /**
     * code : 1
     * message : success
     * data : {"ant_convert_max_value":"10000","score":10,"ant_convert_rate":"5","ant":10,"ant_convert_min_value":"10"}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * ant_convert_max_value : 10000
         * score : 10
         * ant_convert_rate : 5
         * ant : 10
         * ant_convert_min_value : 10
         */

        var ant_convert_max_value: String? = null
        var score: Double? = null
        var ant_convert_rate: String? = null
        var ant: Double? = null
        var ant_convert_min_value: String? = null
    }
}
