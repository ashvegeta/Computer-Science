import java.util.Scanner;

public class targetSum {
    public static boolean canSum(int target, int[] arr) {
        if (target == 0)
            return true;
        if (target < 0)
            return false;

        for (int num : arr) {
            if (num == 0)
                continue;

            if (canSum(target - num, arr) == true)
                return true;
        }

        return false;
    }

    public static boolean canSum_memoized(int target, int[] arr, boolean[] memo) {
        if (target < 0)
            return false;
        if (memo[target])
            return true;
        if (target == 0)
            return true;

        int remainder;

        for (int num : arr) {
            if (num == 0)
                continue;

            remainder = target - num;

            if (canSum(remainder, arr) == true) {
                memo[remainder] = true;
                return true;
            }
        }

        return false;
    }

    /*
     * Intuition behind tabulation:
     * 
     * tab[i]=true, means number i exists in the array or i can derived from the
     * existing numbers in the array
     * 
     * ex: target = 7, arr = [5, 3, 4], tab = [T,F,F,F,F,F,F,F]
     * if tab[0] = T, then tab[0+5] = tab[0+3] = tab[0+7] = True, which means
     * 5 derived from (0+5)
     * 4 -> (0,4)
     * 3 -> (0,3)
     * Indices --------> 0 1 2 3 4 5 6 7
     * 1st iteration -> [T,F,F,T,T,T,F,F]
     * 4nd iteration -> [T,F,F,T,T,T,T,F]
     * 5th iteration -> [T,F,F,T,T,T,T,T]
     */

    public static boolean canSum_tab(int target, int[] arr) {
        boolean[] tab = new boolean[target + 1];
        tab[0] = true;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i]) {
                for (int j = 0; j < arr.length; j++)
                    if (i + arr[j] < tab.length)
                        tab[i + arr[j]] = true;
            }
        }

        return tab[target];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Target: ");
        int target = sc.nextInt();
        System.out.print("\nEnter Array Size: ");
        int size = sc.nextInt();

        int[] arr = new int[size];
        long start;
        boolean cansum;
        double runtime;

        System.out.print("\nEnter Array Values seperated by space: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        sc.close();

        start = System.nanoTime();
        cansum = canSum(target, arr);
        runtime = System.nanoTime() - start;
        System.out.println("\nCan Sum Normal: " + cansum + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        cansum = canSum_memoized(target, arr, new boolean[target + 1]);
        runtime = System.nanoTime() - start;
        System.out.println("\nCan Sum Memoized: " + cansum + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        cansum = canSum_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.println("\nCan Sum Tabulation: " + cansum + "; Time Taken: " + runtime + " ns");
    }
}
