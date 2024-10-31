package coid.bcafinance.pcmtugasakhir;

import coid.bcafinance.pcmtugasakhir.security.BcryptImpl;

public class Test123 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        // paul123
        //  98u71982379ht9
        //  98u71982379ht9 + Key (AES)
        //  98u71982379ht9
//        System.out.println("$2a$11$.HryliSpcSd/8Ml3nOSwI.KAMXle9Vfn9JcVrzghOpSRR7AHGEnNu".length());
//        iwan.oktara
//        B56o06$cuOnm
//        System.out.println(BcryptImpl.hash("B56o06$cuOnm"));
        //$2a$11$tufIYYfNzqT.3nTy4OSsEeqw8tO8cHj5tbz41J1jVln4XiQk0uliu--->
//        System.out.println(BcryptImpl.verifyHash("$2a$11$tufIYYfNzqT.3nTy4OSsEeqw8tO8cHj5tbz41J1jVln4XiQk0uliu","$2a$11$SqZ/hmbftTv2mRBy95L2yuvc17s2z5Qzpb8QWeFRnEOAVW6lElliO"));;
        System.out.println(BcryptImpl.hash("Paul$123poll.chihuy"));
    }
}