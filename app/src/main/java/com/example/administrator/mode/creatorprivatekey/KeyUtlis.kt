package com.example.administrator.mode.creatorprivatekey

import com.example.administrator.mode.Pojo.KeyAddressBean
import com.example.administrator.mode.Utlis.SharedPreferencesUtil

class KeyUtlis {
    companion object {
        fun isHaveKey(): Boolean {
            val getHash = SharedPreferencesUtil.getHashMapData("keyAddress", KeyAddressBean::class.java)
            if (getHash.size > 0)
                return true
            return false
        }

        fun HaveKey(keyAddressMap: HashMap<String, KeyAddressBean>) {
            SharedPreferencesUtil.putHashMapData("keyAddress", keyAddressMap)
        }

    }
}