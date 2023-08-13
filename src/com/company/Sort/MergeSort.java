package com.company.Sort;

public class MergeSort {
    public static void mergeIntSortUp(int[] arr) {
        int arrLength = arr.length;
        if (arrLength == 1) return;

        int mid = arrLength / 2;
        int[] l = new int[mid];
        int[] r = new int[arrLength - mid];

        for (int i = 0; i < mid; i++)
            l[i] = arr[i];
        for (int j = 0; j < arrLength - mid; j++)
            r[j] = arr[j + mid];

        mergeIntSortUp(l);
        mergeIntSortUp(r);
        mergeIntUp(arr, l, r);
    }

    public static void mergeIntUp(int[] arr, int[] l, int[] r) {
        int leftLength = l.length;
        int rightLength = r.length;

        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < leftLength && j < rightLength) {
            if (l[i] < r[j]) {
                arr[idx] = l[i];
                i++;
                idx++;
            } else {
                arr[idx] = r[j];
                j++;
                idx++;
            }
        }
        for (int ll = i; ll < leftLength; ll++)
            arr[idx++] = l[ll];
        for (int rr = j; rr < rightLength; rr++)
            arr[idx++] = r[rr];
    }

    public static void mergeIntSortDown(int[] arr) {
        int arrLength = arr.length;
        if (arrLength == 1) return;

        int mid = arrLength / 2;
        int[] l = new int[mid];
        int[] r = new int[arrLength - mid];

        for (int i = 0; i < mid; i++)
            l[i] = arr[i];
        for (int j = 0; j < arrLength - mid; j++)
            r[j] = arr[j + mid];

        mergeIntSortDown(l);
        mergeIntSortDown(r);
        mergeIntDown(arr, l, r);
    }

    public static void mergeIntDown(int[] arr, int[] l, int[] r) {
        int leftLength = l.length;
        int rightLength = r.length;

        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < leftLength && j < rightLength) {
            if (l[i] > r[j]) {
                arr[idx] = l[i];
                i++;
                idx++;
            } else {
                arr[idx] = r[j];
                j++;
                idx++;
            }
        }
        for (int ll = i; ll < leftLength; ll++)
            arr[idx++] = l[ll];
        for (int rr = j; rr < rightLength; rr++)
            arr[idx++] = r[rr];
    }

    public static void mergeStringSortUp(String[] arr) {
        int arrLength = arr.length;
        if (arrLength < 2) return;

        int mid = arrLength / 2;
        String[] l = new String[mid];
        String[] r = new String[arrLength - mid];

        for (int i = 0; i < mid; i++)
            l[i] = arr[i];
        for (int j = 0; j < arrLength - mid; j++)
            r[j] = arr[j + mid];

        mergeStringSortUp(l);
        mergeStringSortUp(r);
        mergeStringUp(arr, l, r);
    }

    public static void mergeStringUp(String[] arr, String[] l, String[] r) {
        int leftLength = l.length;
        int rightLength = r.length;

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i2 >= rightLength || (i1 < leftLength && l[i1].compareToIgnoreCase(r[i2]) < 0)) {
                arr[i] = l[i1];
                i1++;
            } else {
                arr[i] = r[i2];
                i2++;
            }
        }
    }

    public static void mergeStringSortDown(String[] arr) {
        int arrLength = arr.length;
        if (arrLength < 2) return;

        int mid = arrLength / 2;
        String[] l = new String[mid];
        String[] r = new String[arrLength - mid];

        for (int i = 0; i < mid; i++)
            l[i] = arr[i];
        for (int j = 0; j < arrLength - mid; j++)
            r[j] = arr[j + mid];

        mergeStringSortDown(l);
        mergeStringSortDown(r);
        mergeStringDown(arr, l, r);
    }

    public static void mergeStringDown(String[] arr, String[] l, String[] r) {
        int leftLength = l.length;
        int rightLength = r.length;

        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i2 >= rightLength || (i1 < leftLength && l[i1].compareToIgnoreCase(r[i2]) > 0)) {
                arr[i] = l[i1];
                i1++;
            } else {
                arr[i] = r[i2];
                i2++;
            }
        }
    }
}