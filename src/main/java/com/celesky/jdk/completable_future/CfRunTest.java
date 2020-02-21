package com.celesky.jdk.completable_future;

/**
 * @desc: 提交初步任务
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfRunTest {
    /**
     * //使用默认线程池
     *         static CompletableFuture<Void>
     *         runAsync(Runnable runnable)
     *         static <U> CompletableFuture<U>
     *                 supplyAsync(Supplier<U> supplier)
     * //可以指定线程池
     *         static CompletableFuture<Void>
     *         runAsync(Runnable runnable, Executor executor)
     *         static <U> CompletableFuture<U>
     *                 supplyAsync(Supplier<U> supplier, Executor executor)
     * @param args
     */
    public static void main(String[] args) {

    }
}
