package com.celesky.jvm.bytecode;

import lombok.Data;

/**
 * @desc:
 * @author: panqiong
 * @date: 2020-02-20
 */
@Data
public class Base {
    private int age;

    public void process(){
        System.out.println("process");
    }

}
