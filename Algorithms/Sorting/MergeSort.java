
public class MergeSort {
    public static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int[] randomArr(int size, int min, int max) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++)
            arr[i] = generateRandomNo(min, max);

        return arr;
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // len of 1st sorted sub-array
        int n2 = r - m; // len of 2nd sorted sub-array
        int i, j, k;
        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        for (i = 0; i < n1; i++)
            arr1[i] = arr[l + i]; // l -> start idx of sub-arr

        for (i = 0; i < n2; i++)
            arr2[i] = arr[m + 1 + i]; // m + 1 -> start idx of 2nd sub-arr

        i = j = 0;
        k = l;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                arr[k] = arr1[i];
                i++;
            }

            else {
                arr[k] = arr2[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = arr1[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = arr2[j];
            j++;
            k++;
        }
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;

            sort(arr, l, mid);
            sort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    static void printArr(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
    }

    public static void main(String[] args) {
        int[] arr = randomArr(10, 0, 100);

        System.out.print("\nunsorted array: ");
        printArr(arr);

        sort(arr, 0, arr.length - 1);

        System.out.print("\nsorted array: ");
        printArr(arr);
    }
}
