public class SelectionSort {
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

    static void sort(int[] arr) {
        int min, idx;

        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            idx = -1;

            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    idx = j;
                    min = arr[idx];
                }
            }

            if (idx != -1)
                swap(arr, i, idx);

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

        sort(arr);

        System.out.print("\nsorted array: ");
        printArr(arr);
    }
}
