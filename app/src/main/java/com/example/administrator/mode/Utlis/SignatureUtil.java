package com.example.administrator.mode.Utlis;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;


public class SignatureUtil {
    public static String key = "30820278020100300d06092a864886f70d0101010500048202623082025e02010002818100adcdb7aa7905c15d8b46e30cf778dbce02285f104ea241da1d023246a437e239dd790051f8f0e4673a975093038f1929a123c49f8caae2a930ee6936f8eab6cfed7fd12687b81ff213701faf3f27a4dcf303e00baf7acd06fa5c97aec4ccf540b66b4e118a8ae7d1ff0d1c6584dbbd48099d38bac9f52a15b68d02a451f7cd330203010001028181008e217f233f98ff4a5a4d7bfbec9cbf53b6dc00f1bafc61d1d68b274631a129b59c72095affb32f7563aabe4f9b4778925c9e7d7ae47412abbdc219f4c4965d2c4c06718a0d0e61ea33ea410956532cfa07f1c099044a4785f4086f957e16cf103baa56d715df68d5ce9906309c807e6f67a32363a613516e26bdabfac7806dd1024100f4a7153558154560731a0798bb33050785b38c29bc6fb4b64e6d2543ebaad171c4a5b4fc0def2f7952bad78802699d7e3b0e45c474bfc13c3ebc72fdec754f8f024100b5dd65f350498b7966623dc989b6b8e554308a8d7235f6293310a582415e3984a03184f0522b8d23f8617e4366dea2bedbc22b44036b0b50c9bc33186af0961d02400b0f9ecad99fa49256c1df840d3aa24893c7e9575db8a32ff6ddc2688eb81b5e0c68fd84c3d08391f5a692e6084de41bf7e98e47f28b54de376be7bb093cc88b024100af1fd700899b88570aa427635edd39067c1f920bcaa2f3bb9094c30bbeee80ec996595e1e4fe53170422bd772223d90b788a6d273d3362de14a4c97f05db4e71024100f429a6466036dbe4468f6e8731e3b0cbc69f2a9fd46750b9097b788e17904ff341576190e3110163f55e6faf0789cf063022dd30b2187097bec21a68323abc01";
    public static byte[] decodeHex(final String data) throws DecoderException {
        return decodeHex(data.toCharArray());
    }
    public static String encodeHexString(final byte[] data) {
        return new String(encodeHex(data));
    }
    public static byte[] decodeHex(final char[] data) throws DecoderException {

        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new DecoderException("Odd number of characters.");
        }

        final byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }
    protected static int toDigit(final char ch, final int index) throws DecoderException {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }
    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }
    private static final char[] DIGITS_LOWER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final char[] DIGITS_UPPER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }
    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
    public static String signtureByPrivateKey( String target  ){
        try{
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(decodeHex(key));
            KeyFactory keyFactory = KeyFactory.getInstance( "RSA" );
            PrivateKey privateKey = keyFactory.generatePrivate( encodedKeySpec );
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign( privateKey );
            signature.update( target.getBytes() );
            byte[] result = signature.sign();
            return encodeHexString(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}