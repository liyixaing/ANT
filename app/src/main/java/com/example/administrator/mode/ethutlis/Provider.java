package com.example.administrator.mode.ethutlis;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.geth.JsonRpc2_0Geth;
import org.web3j.protocol.geth.response.PersonalEcRecover;
import java.io.IOException;
import java.math.BigInteger;

public class Provider {

    enum ConnectType {
        Unkown,
        RPC,
        Windows_IPC,
        Uinx_IPC
    }

    /// 节点连接状态
    enum  ConnectState {
        /// 未知状态，即默认值
        Unkown,

        /// 已经连接
        Connectioned,

        /// 连接已断开
        Disconnected,
    }

    /// 节点标识符
    String          identifier;

    /// 节点连接类型
    ConnectType     type;

    /// 节点连接参数
    String          param;

    /// 节点连接状态
    ConnectState    state;

    /// web3j连接实例
    Web3j           web3j;

    Web3jService    service;

    Provider( String identifier, ConnectType type, String param )
    {
        this.identifier = identifier;
        this.type = type;
        this.param = param;
        this.state = ConnectState.Unkown;
    }

    public boolean isValid() {
        return web3j != null && state == ConnectState.Connectioned;
    }

    public String ECRecoverAddress( byte[] hexMessage, String signedMessag ) throws ServiceExpection
    {
        try {

            JsonRpc2_0Geth gethRPC = new JsonRpc2_0Geth(service);

            PersonalEcRecover ecRecover = gethRPC.personalEcRecover(
                    "0x" + BytesUtils.bytesToHexString(hexMessage),
                    signedMessag ).send();

            return ecRecover.getRecoverAccountId();

        }
        catch (IOException ioe)  {

            throw new ServiceExpection(-1001, ioe.getMessage());
        }
    }

    public String ECRecoverAddress( String hexMessage, String signedMessage ) throws ServiceExpection
    {
        return ECRecoverAddress( hexMessage.getBytes(), signedMessage );
    }

    public boolean VerifySignature( String hexMessage, String signedMessage, String address ) throws ServiceExpection {

        String recoverAddress = ECRecoverAddress(hexMessage, signedMessage);

        return recoverAddress.equals(address);
    }

    public String getIdentifier() {
        return identifier;
    }

    public ConnectType getType() {
        return type;
    }

    public String getParam() {
        return param;
    }

    public ConnectState getState() {
        return state;
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public Web3jService getService() {
        return service;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setType(ConnectType type) {
        this.type = type;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public void setState(ConnectState state) {
        this.state = state;
    }

    public void setService(Web3jService service) {
        this.service = service;
    }

    public BigInteger getSenderEtherBalance( String address ) throws IOException {
        return this.web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
    }
}
