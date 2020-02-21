package com.celesky.jdk.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @desc: 描述AND汇聚关系
 * CompletionStage接口里面描述AND汇聚关系，主要是thenCombine、thenAcceptBoth和runAfterBoth系列的接口，
 * 这些接口的区别也是源自fn、consumer、action这三个核心参数不同
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfAndTest {


    public static void main(String[] args) {
        //任务1：洗水壶->烧开水
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);

                    return "矿泉水";
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (f1Result, f2Result)->{
                    System.out.println("T1:拿到开水:"+ f1Result);
                    System.out.println("T1:拿到茶叶:" + f2Result);
                    System.out.println("T1:用"+f1Result+"泡"+f2Result);
                    return "上茶:" + f1Result+"泡的"+ f2Result;
                });
        //等待任务3执行结果
        System.out.println(f3.join());


    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }
}
