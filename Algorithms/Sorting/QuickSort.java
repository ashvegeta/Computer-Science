public class QuickSort {
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int[] randomArr(int size, int min, int max) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++)
            arr[i] = generateRandomNo(min, max);

        return arr;
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r], i = l - 1;

        for (int j = l; j <= r - 1; j++) {
            if (arr[j] <= pivot) { // if element is less than pivot, push it to the "leftmost possible" side.
                i++;
                swap(arr, i, j); // swap the element with the "leftmost possible" side.
            }
        }

        swap(arr, i + 1, r); // place the pivot in right place i.e b/w elements lower and higher than pivot.

        return i + 1;
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int pivot = partition(arr, l, r); // calculate the pivote element

            sort(arr, l, pivot - 1); // sort everything before pivot
            sort(arr, pivot + 1, r); // sort everything after pivot
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
