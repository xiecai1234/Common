package com.bjc.xcb.common.builder;

public class TestBuilder {
    public static void main(String args[]){
        User user = new User.Builder().
                setFirstName("Xie")
                .setAge(29)
                .build();

    }

}
