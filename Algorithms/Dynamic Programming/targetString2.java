import java.util.HashMap;
import java.util.Scanner;

public class targetString2 {
    public static int countConstruct(String target, String[] arr) {
        if (target.equals(""))
            return 1;

        int count = 0;

        for (String word : arr) {
            if (target.startsWith(word)) { // check if the word is prefix in the target array
                count += countConstruct(target.substring(word.length()), arr);
            }
        }

        return count;
    }

    public static int countConstruct_memoized(String target, String[] arr,
            HashMap<String, Integer> memo) {
        if (memo.containsKey(target))
            return memo.get(target);
        if (target.equals(""))
            return 1;

        int count = 0;

        for (String word : arr) {
            if (target.startsWith(word)) {
                count += countConstruct(target.substring(word.length()), arr);
            }
        }

        memo.put(target, count);
        return count;
    }

    public static int countConstruct_tab(String target, String[] arr) {
        int[] tab = new int[target.length() + 1];
        tab[0] = 1;

        for (int i = 0; i <= target.length(); i++) {
            if (tab[i] != 0) {
                for (String word : arr) {
                    if (i + word.length() <= target.length()
                            && target.substring(i, i + word.length()).equals(word)) {
                        tab[i + word.length()] += tab[i];
                    }
                }
            }
        }

        return tab[target.length()];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Target: ");
        String target = sc.next();
        System.out.print("\nEnter Array Size: ");
        int size = sc.nextInt();

        String[] arr = new String[size];
        long start;
        int noOfWays;
        double runtime;

        System.out.print("\nEnter Array Values seperated by space: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.next();
        }

        sc.close();

        start = System.nanoTime();
        noOfWays = countConstruct(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nCount Construct Normal: " + noOfWays + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        noOfWays = countConstruct_memoized(target, arr, new HashMap<String, Integer>());
        runtime = System.nanoTime() - start;
        System.out.print("\nCount Construct Memoized: " + noOfWays + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        noOfWays = countConstruct_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nCount Construct Tabulation: " + noOfWays + "; Time Taken: " + runtime + " ns");
    }
}
