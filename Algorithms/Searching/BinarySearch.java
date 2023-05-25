import java.util.Arrays;

public class BinarySearch {
    static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    static int[] randomArr(int size, int min, int max) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++)
            arr[i] = generateRandomNo(min, max);

        return arr;
    }

    static void BinarySearch_IT(int[] arr, int num) {
        int low = 0, high = arr.length - 1, mid;

        while (low < high) {
            mid = low + (high - low) / 2;

            if (arr[mid] == num) {
                System.out.println(num + " found at index " + mid);
                return;
            }

            if (num > arr[mid])
                low = mid + 1;
            else
                high = mid;
        }

        System.out.println(num + " does not exist");
    }

    static void BinarySearch_REC(int[] arr, int low, int high, int no) {
        if (low >= high) {
            System.out.println(no + " does not exist");
            return;
        }

        int mid = low + (high - low) / 2;

        if (arr[mid] == no) {
            System.out.println(no + " found at index " + mid);
            return;
        }

        if (no > arr[mid])
            BinarySearch_REC(arr, mid + 1, high, no);
        else
            BinarySearch_REC(arr, low, mid, no);

    }

    public static void main(String[] args) {
        int[] arr = randomArr(100, 0, 1000);
        int num = arr[generateRandomNo(0, 99)];
        Arrays.sort(arr);

        BinarySearch_REC(arr, 0, arr.length - 1, num);
        BinarySearch_REC(arr, 0, arr.length - 1, generateRandomNo(1000, 1200));

        BinarySearch_IT(arr, num);
        BinarySearch_IT(arr, generateRandomNo(1000, 1200));
    }
}
