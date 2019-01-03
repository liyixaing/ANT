package com.example.administrator.mode.Utlis;

public  class Encryption {


    public static int fibonacciSequenceByRecursion( int n){
        if( n < 1 ) return 0;
        if( n == 1 || n == 2 )
            return 1;
        return fibonacciSequenceByRecursion( n - 1 ) + fibonacciSequenceByRecursion( n - 2 );
    }

    public static int fibonacciSequenceByDynamicPlanning( int n){
        if( n < 1 ) return 0;
        if( n == 1 || n == 2 )
            return 1;
        int[] DP = new int[ n +1 ];

        DP[ 1 ] = 1;
        DP[ 2 ] = 1;

        for( int i= 3; i <= n ; i++){
            DP[ i ] = DP[ i -1 ] + DP[ i - 2 ];
        }
        return DP[ n ];
    }

    public static String getSecretKeyByFibonacciSequence( String target){
        if(target.isEmpty()) return "";

        StringBuffer sb= new StringBuffer();

        int n = 1;
        int index = fibonacciSequenceByDynamicPlanning( n );
        while ( index -1 < target.length() ){
            sb.append(target.charAt( index - 1 ));
            index = fibonacciSequenceByRecursion( ++n );
        }
        return sb.toString();
    }

    public static boolean verifyTokenFromShopTransmit( String redisToken , String fakeToken){

        String secretKey = getSecretKeyByFibonacciSequence( redisToken );
        return fakeToken.equals( MD5Util.MD5(MD5Util.MD5( redisToken ) + secretKey)) ;
    }

    public static String generateFakeTokenToShop( String realToken){
        String secretKey = getSecretKeyByFibonacciSequence( realToken );
        return MD5Util.MD5(MD5Util.MD5( realToken ) + secretKey) ;
    }


   /* public static void main(String[] args ){
//        for( int i =1 ; i< 20 ; i++ ){
//            System.out.print( fibonacciSequenceByRecursion( i ) + " ");
//        }
//        System.out.println( " --------------------------------- ");
//        for( int i =1 ; i< 20 ; i++ ){
//            System.out.print( fibonacciSequenceByDynamicPlanning( i ) + " ");
//        }

        String realToken = "2678edc14c5bc55868bd38d42759489a";
        String fakeToken = "73a8c1c674b869b4d8c9f28dc5d6c389";

        String s1 = generateFakeTokenToShop( realToken );
        System.out.println( s1);

        System.out.println( verifyTokenFromShopTransmit(realToken , fakeToken));

        //System.out.println( MD5Util.MD5("100000:SCAC共享商城"));
        System.out.println( generateFakeTokenToShop( realToken ));
    }
*/

}
