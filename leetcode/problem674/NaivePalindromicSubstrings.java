/**
 * https://leetcode.com/problems/palindromic-substrings
 */
public class NaivePalindromicSubstrings {
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i + j >= s.length()) break;
                if (j == 1 || isPalindromic(s, i, i + j)) res++;
            }
        }
        return res;
    }

    private static boolean isPalindromic(final String s, final int start, final int end) {
        int l = start, r = end;
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}
