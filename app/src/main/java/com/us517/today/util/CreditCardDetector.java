package com.us517.today.util;

import java.util.ArrayList;

public class CreditCardDetector {
    ArrayList<String> listOfPattern = new ArrayList<>();

    //static String ptVisa = "^4[0-9]{1,}$";
    static String ptVisa = "^4[0-9]{0,}$";
    //static String ptMasterCard = "^5[1-5][0-9]{2,}$";
    static String ptMasterCard = "^5[1-5][0-9]{0,}$";

    static String ptAmeExp = "^3[47][0-9]{0,}$";

    static String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{0,}$";

    static String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{0,}$";

    static String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{0,}$";

    static String ptUnion = "^622[1-9][0-9]{0,}$";

    public static String getCardType(String cardNumber) {
        String type = "card";
        if (cardNumber.matches(ptVisa)) {type = "visa";}
        else if (cardNumber.matches(ptMasterCard)) {type = "mastercard";}
        else if (cardNumber.matches(ptAmeExp)) {type = "americanExpress";}
        else if (cardNumber.matches(ptDinClb)) {type = "dinclb";}
        else if (cardNumber.matches(ptDiscover)) {type = "discover";}
        else if (cardNumber.matches(ptJcb)) {type = "jcb";}
        else if (cardNumber.matches(ptUnion)) {type = "union";}
        if (cardNumber.length() > 16 && type != "union") {type = "card";}
        return type;
    }
}
