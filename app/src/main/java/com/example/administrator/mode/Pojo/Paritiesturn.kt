package com.example.administrator.mode.Pojo

class Paritiesturn {
    /**
     * code : 1
     * message : success
     * data : {"score":10,"ant_transfer_min_value":"100","ant_transfer_rate":"0.1","ant_transfer_max_value":"10000","ant":10}
     */

    var code: Int = 0
    var message: String? = null
    var data: DataBean? = null

    class DataBean {
        /**
         * score : 10.0
         * ant_transfer_min_value : 100
         * ant_transfer_rate : 0.1
         * ant_transfer_max_value : 10000
         * ant : 10.0
         */

        var score: Double? = null
        var ant_transfer_min_value: String? = null
        var ant_transfer_rate: String? = null
        var ant_transfer_max_value: String? = null
        var ant: Double? = null
    }
}
