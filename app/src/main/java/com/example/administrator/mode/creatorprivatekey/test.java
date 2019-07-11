package com.example.administrator.mode.creatorprivatekey;

import com.example.administrator.mode.ethutlis.ProviderManager;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

public class test {

    public static void test()
    {
        Web3j web3j = ProviderManager.DefaultManager().GetBalancingNode().getWeb3j();

        try {
            EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();

            block.getBlock().getGasUsed();

        }
        catch (Exception e)
        {

        }
    }
}
