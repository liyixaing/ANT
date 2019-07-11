package com.example.administrator.mode.Pojo;

public class KeyAddressBean {


    private String walletName;
    private String Keystore;
    private String address;
    //userPrivatelyKey
    private String name;
    private String mnemonic;
    private String userName;
    private String userId;
    //pwd
    private String userPrivatelyKey;

    public String getUserPrivatelyKey() {
        return userPrivatelyKey;
    }

    public void setUserPrivatelyKey(String userPrivatelyKey) {
        this.userPrivatelyKey = userPrivatelyKey;
    }
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    private String userHead;
    public String getKeystore() {
        return Keystore;
    }

    public void setKeystore(String keystore) {
        Keystore = keystore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

}
