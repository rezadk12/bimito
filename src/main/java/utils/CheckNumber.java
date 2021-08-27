package utils;

import java.util.regex.Pattern;

public class CheckNumber {
    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
    public static boolean IsAllNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        return strNum.indexOf('9')==-1?false:strNum.indexOf('8')==-1?false:strNum.indexOf('7')==-1?false:strNum.indexOf('6')==-1?false:strNum.indexOf('5')==-1?false:strNum.indexOf('4')==-1?false:strNum.indexOf('3')==-1?false:strNum.indexOf('2')==-1?false:strNum.indexOf('1')==-1?false:true;
    }
}
