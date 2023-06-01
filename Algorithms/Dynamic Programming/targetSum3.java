import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class targetSum3 {
    public static ArrayList<Integer> bestSum(int target, int[] arr) {
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;

        ArrayList<Integer> list = null, prevList = null;

        for (int num : arr) {
            if (num == 0)
                continue;

            list = bestSum(target - num, arr);

            if (list != null)
                list.add(num);

            if ((list == null) || (prevList != null && prevList.size() <= list.size() - 1))
                list = prevList;

            prevList = list;
        }

        return list;
    }

    public static ArrayList<Integer> bestSum_memoized(int target, int[] arr,
            HashMap<Integer, ArrayList<Integer>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;

        ArrayList<Integer> list = null, prevList = null;

        for (int num : arr) {
            if (num == 0)
                continue;

            list = bestSum(target - num, arr);

            if (list != null)
                list.add(num);

            if ((list == null) || (prevList != null && prevList.size() <= list.size() - 1))
                list = prevList;

            prevList = list;
        }

        memo.put(target, list);
        return list;
    }

    /*
     * same logic as howSum, with the only difference that an additional check
     * needs to be done, to make sure you are choosing the solution with smallest
     * length
     */
    public static ArrayList<Integer> bestSum_tab(int target, int[] arr) {
        ArrayList<ArrayList<Integer>> tab = new ArrayList<>();

        for (int i = 0; i <= target; i++)
            tab.add(null);

        tab.set(0, new ArrayList<>());

        for (int i = 0; i <= target; i++) {
            if (tab.get(i) != null) {
                for (int num : arr) {
                    if (i + num < tab.size()) {
                        if (tab.get(i + num) == null || tab.get(i).size() + 1 < tab.get(i + num).size()) {
                            tab.set(i + num, new ArrayList<>(tab.get(i)));
                            tab.get(i + num).add(num);
                        }
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
        res = bestSum(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nBest Sum Normal: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = bestSum_memoized(target, arr, new HashMap<>());
        runtime = System.nanoTime() - start;
        System.out.print("\nBest Sum Memoized: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = bestSum_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nBest  Sum Tabulation: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");
    }
}
