import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class targetString3 {
    public static ArrayList<ArrayList<String>> allConstruct(String target, String[] arr) {
        if (target.equals(""))
            return new ArrayList<>();

        ArrayList<ArrayList<String>> listOfCombs = null;

        for (String word : arr) {
            if (target.startsWith(word)) { // check if word is prefix in the target array
                ArrayList<ArrayList<String>> list = allConstruct(target.substring(word.length()), arr);

                if (list != null) {
                    if (list.size() == 0)
                        list.add(new ArrayList<>(Arrays.asList(word))); // if list is empty add new array with the word.
                    else
                        list.get(list.size() - 1).add(0, word); // add new word to the end of the array

                    if (listOfCombs == null) // if list is empty, that means the first solution set is being added
                        listOfCombs = list;
                    else
                        listOfCombs.addAll(list); // else, add the new solution set to the end of list
                }
            }
        }

        return listOfCombs;
    }

    public static ArrayList<ArrayList<String>> allConstruct_memoized(String target, String[] arr,
            HashMap<String, ArrayList<ArrayList<String>>> memo) {
        if (memo.containsKey(target))
            return memo.get(target);

        ArrayList<ArrayList<String>> listOfCombs = null;

        for (String word : arr) {
            if (target.startsWith(word)) {
                ArrayList<ArrayList<String>> list = allConstruct(target.substring(word.length()), arr);

                if (list != null) {
                    if (list.size() == 0)
                        list.add(new ArrayList<>(Arrays.asList(word)));
                    else
                        list.get(list.size() - 1).add(0, word);

                    if (listOfCombs == null)
                        listOfCombs = list;
                    else
                        listOfCombs.addAll(list);
                }
            }
        }

        memo.put(target, listOfCombs);
        return listOfCombs;
    }

    /*
     * Intution behind Tabulation - (Similar to tabulation in canConstruct and
     * countConstruct)
     * 
     * instead of storing boolean/count values we will store 2-d array of strings
     * 
     * Ex: target = "abcdef", arr = ["ab", "abc", "cd", "def", "abcd", "ef", "c"]
     * 
     * indices ----> 0 1 2 3 4 5 6
     * target -------> a b c d e f
     * tab --------> [[[]], [] , [] , [], [], [], []]
     * 
     * tab[i] --> list of combinations to produce subtarget target[0 to i]
     * tab[3] --> list of combinations to produce subtarget "abc" -> [[abc], [ab,c]]
     * 
     * iteration 1 -> [[[]], [[ab]], [[abc]], [[abcd]], [] , []]
     * iteration 2 -> [[[]], [[ab]], [[abc]], [[abcd]], [] , []]
     * iteration 3 -> [[[]], [[ab]], [[abc], [ab,c]], [[abcd], [ab,cd]], [] , []]
     * iteration 4 -> [[[]], [[ab]], [[abc], [ab,c]], [[abcd], [ab,cd]], [] , [[abc,
     * def], [ab,c,def]]]
     * iteration 5 -> [[[]], [[ab]], [[abc], [ab,c]], [[abcd], [ab,cd]], [] , [[abc,
     * def], [ab,c,def], [abcd,ef], [ab,cd,ef]]]
     */
    public static ArrayList<ArrayList<String>> allConstruct_tab(String target, String[] arr) {
        ArrayList<ArrayList<ArrayList<String>>> tab = new ArrayList<>();

        for (int i = 0; i <= target.length(); i++)
            tab.add(new ArrayList<>());

        tab.get(0).add(new ArrayList<>());
        int len;

        for (int i = 0; i <= target.length(); i++) {
            if (tab.get(i).size() != 0) {
                for (String word : arr) {
                    len = word.length();

                    if (i + len <= target.length() && target.substring(i, i + len).equals(word)) {
                        for (ArrayList<String> list : tab.get(i)) {
                            // create new arraylist, else it will be copied by reference
                            tab.get(i + len).add(new ArrayList<>(list));
                            tab.get(i + len).get(tab.get(i + len).size() - 1).add(word);
                        }
                    }
                }
            }
        }

        return tab.get(target.length());
    }

    public static void printList(ArrayList<ArrayList<String>> list) {
        if (list == null) {
            System.out.println("ArrayList object is null\n");
            return;
        }

        System.out.print(" [");

        for (ArrayList<String> soln : list) {
            System.out.print("[");
            for (String word : soln) {
                System.out.print(word + ", ");
            }
            System.out.print("], ");
        }

        System.out.print("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Target: ");
        String target = sc.next();
        System.out.print("\nEnter Array Size: ");
        int size = sc.nextInt();

        String[] arr = new String[size];
        long start;
        double runtime;
        ArrayList<ArrayList<String>> res;

        System.out.print("\nEnter Array Values seperated by space: ");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.next();
        }

        sc.close();

        start = System.nanoTime();
        res = allConstruct(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nAll Construct Normal: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = allConstruct_memoized(target, arr, new HashMap<>());
        runtime = System.nanoTime() - start;
        System.out.print("\nAll Construct Memoized: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");

        start = System.nanoTime();
        res = allConstruct_tab(target, arr);
        runtime = System.nanoTime() - start;
        System.out.print("\nAll Construct Tabulation: ");
        printList(res);
        System.out.print("; Time Taken: " + runtime + " ns");
    }
}
