package com.celesky.jdk.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * @desc: 异常处理
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfExceptionTest {
    public static void main(String[] args) {
        CompletableFuture<Integer> f0 = CompletableFuture
                .supplyAsync(()->7/1)
                .thenApply(r->r*10)
                .exceptionally(e->{
                    e.printStackTrace();
                    return 0;
                })
                .whenComplete((t,u)-> System.out.println("t = " + t+" u = "+u))
                .handle((x,y)-> {
                    System.out.println("x = " + x);
                    System.out.println("y = " + y);
                    return x;
                });
        System.out.println(f0.join());
    }
}
