import java.util.List;
import java.util.ArrayList;

public class Zalgorithm {
    // function to compute Z values
    public int[] computeZValues(String S) {
        int[] Zvalues = new int[S.length()];
        int left = 0, right = 0;

        for (int i = 1; i < S.length(); i++) {
            if (i > right) {

                left = right = i;

                while (right < S.length() && S.charAt(right - left) == S.charAt(right))
                    right++;

                Zvalues[i] = right - left;
                right--;

            }

            else {
                int startIdx = i - left;

                // if the zvalue < Zbox's width, then just blindly copy
                if (Zvalues[startIdx] < left - right + 1)
                    Zvalues[i] = Zvalues[startIdx];
                else {
                    left = i;

                    while (right < S.length() && S.charAt(right - left) == S.charAt(right))
                        right++;

                    Zvalues[i] = right - left;
                    right--;
                }
            }

        }

        return Zvalues;
    }

    public List<Integer> Zalg(String text, String pattern) {
        String S = pattern + "$" + text;
        List<Integer> res = new ArrayList<>();

        // compute Z values
        int Zvalues[] = computeZValues(S);

        // find the positions of repetitions
        for (int i = 0; i < S.length(); i++) {
            if (Zvalues[i] == pattern.length())
                res.add(i - pattern.length() - 1);
        }

        return res;

    }

    public static void main(String[] args) {
        String text = "aaabcxyzaaaabczabbaaaaaabc";
        String pattern = "aaabc";

        Zalgorithm classObj = new Zalgorithm();
        List<Integer> res = classObj.Zalg(text, pattern);

        if (res.size() == 0)
            System.out.println("No Match Found !!");
        else
            for (int i : res)
                System.out.println(i);
    }
}
