public class HeapSort {
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

    static void printArr(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
    }

    static void heapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2 * i + 1, right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) // dont touch the last n elements, as they are already sorted
            largest = left;

        if (right < n && arr[right] > arr[largest]) // dont touch the last n elements, as they are already sorted
            largest = right;

        if (largest != i) {
            swap(arr, i, largest); // swap if the children nodes are bigger than parent
            heapify(arr, largest, n); // then continue heapify-ing
        }
    }

    static void sort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) // heapify from leaves, as heapify is top-down algorithm
            heapify(arr, i, n);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i); // swap first and ith element
            heapify(arr, 0, i); // then hapify everything before ith element
        }
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
