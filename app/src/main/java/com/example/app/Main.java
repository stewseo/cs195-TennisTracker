package com.example.app;

import com.example.list.LinkedList;

import static com.example.app.MessageUtils.getMessage;
import static com.example.utilities.StringUtils.join;
import static com.example.utilities.StringUtils.split;

public class Main {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        System.out.println(join(tokens));
    }
}
