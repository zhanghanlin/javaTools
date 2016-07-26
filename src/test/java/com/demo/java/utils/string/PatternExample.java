package com.demo.java.utils.string;

public class PatternExample {

    public static void main(String[] args) {
        String simple = "123.456";
        System.out.println(PatternUtils.matchNum(simple));
        System.out.println(PatternUtils.matchInteger(simple, 0));
        System.out.println(PatternUtils.matchInteger(simple, 1));
    }
}
