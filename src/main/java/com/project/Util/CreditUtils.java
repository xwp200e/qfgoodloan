package com.project.Util;

public class CreditUtils {

    private static CreditUtils creditUtils = new CreditUtils();

    public static CreditUtils getInstance(){
        return creditUtils;
    }

    public int getCredit(int score) {

        if (score < 60){
            return 0;
        }

        int i = 60;
        int x = 1;
        while (i <= 95){

             if (score < i + 5){
                 return x;
             }
             i+=5;
             x++;
        }
        return 0;

    }

}
