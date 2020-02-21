package com.celesky.jdk.completable_future;

import java.util.concurrent.CompletableFuture;

/**
 * @desc: CompletionStage接口里面描述串行关系，主要是thenApply、thenAccept、thenRun和thenCompose这四个系列的接口。
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfThenTest {

    public static void main(String[] args) {
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> "Hello World")      //①
                        .thenApply(s -> s + " QQ")  //②
                        .thenApply(String::toUpperCase);//③
        //输出结果
        System.out.println(f0.join());

    }
}
