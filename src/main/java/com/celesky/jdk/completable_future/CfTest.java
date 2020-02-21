package com.celesky.jdk.completable_future;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desc:
 * @author: panqiong
 * @date: 2020-01-30
 */
public class CfTest {
    static ExecutorService executor = Executors.newFixedThreadPool(10);
    static HashSet safeSet = new HashSet();
    public static void main(String[] args) {
        List<String> markKeyList = new ArrayList<>();
        markKeyList.add("123456");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        markKeyList.add("6541223");
        getDupilcatedMobiles(markKeyList);

        executor.shutdown();
    }
    public static Set<String> getDupilcatedMobiles(List<String> markKeyList) {

        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture[] cfs = markKeyList.stream()
                .map(markKey ->
                        CompletableFuture
                                .supplyAsync(() -> getFromRedis(markKey), executor)
                                .exceptionally(e->{
                                    e.printStackTrace();
                                    return null;
                                })
                                .whenComplete((s, e) -> {
                                    System.out.println("e = " + e);
                                    safeSet.add(s);
                                })
                ).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).join();
        return safeSet;
    }

    private static String getFromRedis(String markKey) {
        return markKey;
    }
}
