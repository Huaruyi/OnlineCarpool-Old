package com.hry.algorithm;

public class Cost {
    public static Double totalPrice(Double distance){
        Double money = 0.0;
        if (distance <= 3.0){
            money = 9.0;
        }else if (distance <= 30.0){ //每公里1.9;
            money = (distance - 3.0) * 1.9 + 9.0;
        }else { //30公里以上加收50%长途费  也就是每公里2.8
            money = 37*1.9 + 9.0 + (distance-30) * 2.8;
        }
        return money;
    }

    public static Double one(Double money,Integer percent){
        return money;
    }
}
