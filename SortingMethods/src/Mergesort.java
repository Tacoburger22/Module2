import java.util.Arrays;

/**
 * Use with objects.
 * Isaac Weiss
 * 9/19/2020
 */

public class Mergesort {

    public static void main(String[] args) {
        Integer[] array = {66,67,20,86,55,74,11,91,43,47};
        mergeSort(array);
        System.out.print(Arrays.toString(array));
    }

    public static <T> void mergeSort(Comparable<T>[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    public static <T> void mergeSort(Comparable<T>[] a, int left, int right) {
        if (right == left) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        merge(a, left, mid, right);
    }

    public static <T> void merge(Comparable<T>[] a, int left, int mid, int right) {
        Comparable<T>[] aux = Arrays.copyOf(a, a.length);
        
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > right) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo((T) aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            } 
        }
    }


}
