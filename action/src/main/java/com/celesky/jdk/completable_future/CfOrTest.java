package com.celesky.jdk.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @desc: 如何使用applyToEither()方法来描述一个OR汇聚关系。
 * CompletionStage接口里面描述OR汇聚关系，主要是applyToEither、acceptEither和runAfterEither系列的接口，
 * 这些接口的区别也是源自fn、consumer、action这三个核心参数不同
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfOrTest {
    public static void main(String[] args) {
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = 5;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = 4;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

        System.out.println(f3.join());
    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
