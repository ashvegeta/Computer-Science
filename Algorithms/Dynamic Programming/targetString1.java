import java.util.HashMap;
import java.util.Scanner;

public class targetString1 {
    /* helper functions overloaded with thieir counterparts */

    // public static boolean canConstruct(String targetString, String[] arr) {
    // return canConstruct(targetString, 0, arr);
    // }

    // public static boolean canConstruct_memoized(String targetString, String[]
    // arr) {
    // return canConstruct_memoized(targetString, 0, arr, new HashMap<String,
    // Boolean>());
    // }

    // public static boolean canConstruct_tab(String targetString, String[] arr) {
    // return canConstruct(targetString, 0, arr);
    // }

    /* alternate implementation using matched indices */

    // public static boolean canConstruct(String targetString, int matched, String[]
    // arr) {
    // if (matched == targetString.length())
    // return true;

    // for (String s : arr) {
    // if (s == "")
    // continue;

    // if (targetString.startsWith(s, matched) && canConstruct(targetString, matched
    // + s.length(), arr))
    // return true;
    // }

    // return false;
    // }

    public static boolean canConstruct(String targetString, String[] arr) {
        if (targetString.equals(""))
            return true;

        for (String s : arr) {
            if (s == "")
                continue;

            // check if word is prefix in the target array and if the remaining substring
            // can be constructed
            if (targetString.startsWith(s) && canConstruct(targetString.substring(s.length()), arr))
                return true;
        }

        return false;
    }

    /* alternate implementation using matched indices */

    // public static boolean canConstruct_memoized(String targetString, int matched,
    // String[] arr,
    // HashMap<String, Boolean> memo) {
    // String matchString = targetString.substring(matched);

    // if (memo.containsKey(matchString))
    // return memo.get(matchString);
    // if (matched == targetString.length())
    // return true;

    // for (String s : arr) {
    // if (s == "")
    // continue;

    // if (targetString.startsWith(s, matched)
    // && canConstruct_memoized(targetString, matched + s.length(), arr, memo)) {
    // memo.put(matchString, true);
    // return true;
    // }
    // }

    // memo.put(matchString, false);
    // return false;
    // }

    public static boolean canConstruct_memoized(String targetString, String[] arr, HashMap<String, Boolean> memo) {
        if (memo.containsKey(targetString))
            return memo.get(targetString);
        if (targetString.equals(""))
            return true;

        for (String s : arr) {
            if (s == "")
                continue;

            if (targetString.startsWith(s) &&
                    canConstruct_memoized(targetString.substring(s.length()), arr, memo)) {
                memo.put(targetString, true);
                return true;
            }
        }

        memo.put(targetString, false);
        return false;
    }

    /*
     * Intution behind tabulation - (similar to integer targete sum)
     * 
     * let targetString = "abcdef" ,
     * arr = ["ab", "abc", "cd", "def", "abcd"],
     * tab = [T,F,F,F,F,F,F]
     * 
     * tab[0] = true, denotes any target string can be generated by empty string;
     * tab[i]=true represents targetString.substring(0,i) is present in the
     * arr, or can be constructed from the words in the array
     * 
     * targetString ---> a b c d e f
     * tab ---------> [T,F,F,F,F,F,F]
     * 
     * targetString --> a,b,c,d,e,f
     * iteration 1 : [T,F,T,T,T,F,F]
     * iteration 2 : [T,F,T,T,T,F,F]
     * iteration 3 : [T,F,T,T,T,F,F]
     * iteration 4 : [T,F,T,T,T,F,T]
     * iteration 5 : [T,F,T,T,T,F,T]
     * iteration 6 : [T,F,T,T,T,F,T]
     */
    public static boolean canConstruct_tab(String targetString, String[] arr) {
        boolean[] tab = new boolean[targetString.length() + 1];
        tab[0] = true;

        for (int i = 0; i <= targetString.length(); i++) {
            if (tab[i]) {
                for (String word : arr) {
                    if (i + word.length() <= targetString.length()
                            && targetString.substring(i, i + word.length()).equals(word)) {
                        tab[i + word.length()] = true;
                    }
                }
            }
        }

        return tab[targetString.length()];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Target String: ");
        String target = sc.next();
        System.out.print("\nEnter Array Size: ");
        int size = sc.nextInt();

        String[] arr = new String[size];
        long start;
        double runtime;
        boolean canconstruct;

        System.out.print("\nEnter Array Values seperated by space: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.next();
        }

        sc.close();

        start = System.nanoTime();
        canconstruct = canConstruct(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nCan Construct Normal: " + canconstruct + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        canconstruct = canConstruct_memoized(target, arr, new HashMap<>());
        runtime = System.nanoTime() - start;
        System.out.print("\nCan Construct Memoized: " + canconstruct + "; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        canconstruct = canConstruct_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nCan Construct Tabulation: " + canconstruct + "; Time Taken: " + runtime + " ns");
    }
}