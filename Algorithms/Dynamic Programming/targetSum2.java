import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class targetSum2 {
    public static ArrayList<Integer> howSum(int target, int[] arr) {
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;

        ArrayList<Integer> list;

        for (int num : arr) {
            if (num == 0)
                continue;

            if ((list = howSum(target - num, arr)) != null) {
                list.add(num);
                return list;
            }
        }

        return null;
    }

    public static ArrayList<Integer> howSum_memoized(int target, int[] arr, HashMap<Integer, ArrayList<Integer>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;

        ArrayList<Integer> list;

        for (int num : arr) {
            if (num == 0)
                continue;

            if ((list = howSum(target - num, arr)) != null) {
                list.add(num);
                memo.put(target - num, list);
                return list;
            }
        }

        memo.put(target, null);
        return null;
    }

    /*
     * Same intuition as "canSum" problem but instead of using boolean values, we
     * can use Arrays
     */
    public static ArrayList<Integer> howSum_tab(int target, int[] arr) {
        ArrayList<ArrayList<Integer>> tab = new ArrayList<>();

        for (int i = 0; i <= target; i++) // fill the array with indices <= target as "ArrayList" is empty
            tab.add(null);

        tab.set(0, new ArrayList<>()); // set the index "0" to empty array as target==0 is itself a solution

        for (int i = 0; i <= target; i++) {
            if (tab.get(i) != null) { // make sure the solution for number i present
                for (int num : arr) {
                    if (i + num < tab.size()) { // make sure sum of (i + num) is within range of target
                        tab.set(i + num, new ArrayList<>(tab.get(i)));
                        tab.get(i + num).add(num);
                    }
                }
            }
        }

        return tab.get(target);
    }

    public static void printList(ArrayList<Integer> list) {
        if (list == null) {
            System.out.println("ArrayList object is null\n");
            return;
        }

        System.out.print("[");

        for (int num : list)
            System.out.print(num + ",");

        System.out.print("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Target: ");
        int target = sc.nextInt();
        System.out.print("\nEnter Array Size: ");
        int size = sc.nextInt();

        int[] arr = new int[size];
        long start;
        double runtime;
        ArrayList<Integer> res;

        System.out.print("\nEnter Array Values seperated by space: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        sc.close();

        start = System.nanoTime();
        res = howSum(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nHow Sum Normal: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = howSum_memoized(target, arr, new HashMap<>());
        runtime = System.nanoTime() - start;
        System.out.print("\nHow Sum Memoized: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = howSum_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nHow Sum Tabulation: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");
    }
}
