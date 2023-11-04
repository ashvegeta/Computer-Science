public class CoutingSort {
    public static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int[] randomArr(int size, int min, int max) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++)
            arr[i] = generateRandomNo(min, max);

        return arr;
    }

    static void printArr(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
    }

    static void sort(int[] arr, int min, int max) {
        int[] count = new int[max - min + 1];
        int[] output = new int[arr.length];
        int base = -min; // so that indx does not go out of bound; usage -> count[arr[i] + base]

        for (int i = 0; i < arr.length; i++) // track count of each repetition of an element in original array
            count[arr[i] + base]++;

        for (int i = 1; i < count.length; i++) // count[i] -> idx of element i in sorted array
            count[i] += count[i - 1];

        for (int i = arr.length - 1; i >= 0; i--) // rev order so that previous elements get updated position
            output[--count[arr[i] + base]] = arr[i]; // take one repetition of that element, and reduce index

        for (int i = 0; i < arr.length; i++) // copy contents to output array
            arr[i] = output[i];
    }

    public static void main(String[] args) {
        int min = -25, max = 75;

        int[] arr = randomArr(10, min, max);

        System.out.print("\nunsorted array: ");
        printArr(arr);

        sort(arr, min, max);

        System.out.print("\nsorted array: ");
        printArr(arr);

    }
}
