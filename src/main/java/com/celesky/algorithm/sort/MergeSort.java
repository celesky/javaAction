package com.celesky.algorithm.sort;

/**
 * @desc:
 * @author: panqiong
 * @date: 2020-02-01
 */
public class MergeSort {
    static int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};


    /**
     * 两路归并算法，两个排好序的子序列合并为一个子序列
     */
    public static void merge(int[] a, int left, int mid, int right) {
        //辅助数组
        int[] tmp = new int[a.length];
        //p1、p2是检测指针，k是存放指针
        int p1 = left, p2 = mid + 1, k = left;

        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2]) {
                tmp[k++] = a[p1++];
            } else {
                tmp[k++] = a[p2++];
            }

        }
        //如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p1 <= mid) {
            tmp[k++] = a[p1++];
        }
        //同上
        while (p2 <= right) {
            tmp[k++] = a[p2++];
        }

        //复制回原素组
        for (int i = left; i <= right; i++) {
            a[i] = tmp[i];
        }

    }

    public static void mergeSort(int[] a, int start, int end) {
        //当子序列中只有一个元素时结束递归
        if (start < end) {
            //划分子序列
            int mid = (start + end) / 2;
            //对左侧子序列进行递归排序
            mergeSort(a, start, mid);
            //对右侧子序列进行递归排序
            mergeSort(a, mid + 1, end);
            //合并
            merge(a, start, mid, end);
        }
    }

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 50};
        mergeSort(a, 0, a.length - 1);
        System.out.println("排好序的数组：");
        for (int e : a) {
            System.out.print(e + " ");
        }

    }


}
