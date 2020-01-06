package com.example.administrator.mode.creatorprivatekey;

import android.util.Log;

import org.web3j.crypto.*;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

public class MessageSignUtils {

    public static String Sign( Credentials credentials, String message )
    {
        Sign.SignatureData data = Sign.signPrefixedMessage(message.getBytes(), credentials.getEcKeyPair());
        Log.e("签名串", "0x" + Numeric.toHexStringNoPrefix(data.getR()) + Numeric.toHexStringNoPrefix(data.getS()) + Numeric.toHexStringNoPrefix( BigInteger.valueOf(data.getV())));
        return "0x" + Numeric.toHexStringNoPrefix(data.getR()) + Numeric.toHexStringNoPrefix(data.getS()) + Numeric.toHexStringNoPrefix( BigInteger.valueOf(data.getV()));
    }

    public static String RecoverAddress( String originString, String signature ) throws RuntimeException
    {
        if ( signature.length() != 132  )
        {
            throw new RuntimeException("签名串的长度不正确，包含0x字符应该为132个字节。");
        }

        String R = signature.substring( 2, 66 );
        String S = signature.substring( 66, 130);
        String V = signature.substring( 130,132);

        ECDSASignature ecdsaSignature = new ECDSASignature( Numeric.toBigInt(R), Numeric.toBigInt(S) );

        int i = Numeric.toBigInt(V).intValue() - 27;

        return "0x" + Keys.getAddress(Sign.recoverFromSignature( i, ecdsaSignature, getEthereumMessageHash(originString.getBytes())));

    }

    static byte[] getEthereumMessagePrefix(int messageLength) {
        return "\u0019Ethereum Signed Message:\n".concat(String.valueOf(messageLength)).getBytes();
    }

    static byte[] getEthereumMessageHash(byte[] message) {
        byte[] prefix = getEthereumMessagePrefix(message.length);
        byte[] result = new byte[prefix.length + message.length];
        System.arraycopy(prefix, 0, result, 0, prefix.length);
        System.arraycopy(message, 0, result, prefix.length, message.length);
        return Hash.sha3(result);
    }

}
