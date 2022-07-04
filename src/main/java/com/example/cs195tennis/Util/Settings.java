package com.example.cs195tennis.Util;

import java.util.List;

public class Settings {

    public static boolean isNumber(List<String> list) {
        if (list.isEmpty()) {
            return false;
        }
        for (String s : list) {

            int last = s.length() - 1;

            if (s.hashCode() == 0) {
                return false;
            }
            if (s.charAt(0) < 48 || s.charAt(0) > 57) {
                return false;
            }
            if (s.charAt(last) < 48 || s.charAt(last) > 57) {
                return false;
            }
        }
        return true;
    }



}
