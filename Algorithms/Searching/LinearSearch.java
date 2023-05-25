import java.lang.Math;

public class LinearSearch {
    public static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int[] randomArr(int size, int min, int max) {

        int[] arr = new int[size];

        for (int i = 0; i < size; i++)
            arr[i] = generateRandomNo(min, max);

        return arr;
    }

    public static void search(int[] arr, int num) {

        for (int i = 0; i < arr.length; i++)
            if (arr[i] == num) {
                System.out.println(num + " found at index " + i);
                return;
            }

        System.out.println(num + " does not exist");
    }

    public static void main(String[] args) {
        int[] arr = randomArr(10, 0, 1000);
        int no = arr[arr.length / 2];

        search(arr, no);
        search(arr, generateRandomNo(1000, 1100));

    }
}
