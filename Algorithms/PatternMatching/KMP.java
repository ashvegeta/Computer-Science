import java.util.ArrayList;
import java.util.List;

public class KMP {
    // build the array which gives the "longest proper prefix which is also a
    // suffix"
    // For ex: consider string : a a b a a a. here "aa" is the longest prefix which
    // is also the suffix.
    public int[] BuildLPS(String pattern) {
        if (pattern.length() == 0)
            return new int[] {};

        int lps[] = new int[pattern.length()];
        lps[0] = 0;
        int i = 1, j = 0;

        // iterate through the characters of the patterns to build the lps array
        while (i < pattern.length()) {
            // increment both pointers if character matches (basically a prefix which is
            // also a suffix was found)
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
                i++;
            }

            // else, reset j or increment i accordingly to next window
            else {
                if (j != 0)
                    j = lps[j - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public List<Integer> KMPSearch(String text, String pattern) {
        List<Integer> res = new ArrayList<>();
        int i = 0, j = 0; // pointers for text and pattern
        int lps[] = BuildLPS(text); // compute lps array

        while (text.length() - i >= pattern.length() - j) { // basically checking if the remaining length of the text is
                                                            // more than the pattern
            // if the character is a match
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            // if a total pattern match is found, add it to the result list and update the
            // j's new
            // position
            if (j == pattern.length()) {
                res.add(i - j);
                j = lps[j - 1];
            }

            // if the character is a mismatch
            else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        String text = "aaabcxyzaaaabczabbaaaaaabc";
        String pattern = "aaabc";

        KMP classObj = new KMP();
        List<Integer> res = classObj.KMPSearch(text, pattern);

        if (res.size() == 0)
            System.out.println("No Match Found !!");
        else
            for (int i : res)
                System.out.println(i);
    }
}
