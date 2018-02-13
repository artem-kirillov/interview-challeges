/**
 * https://leetcode.com/problems/palindromic-substrings
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        final int n = s.length();
        int res = 0;
        for (int pos = 0; pos < 2 * n - 1; pos++) {
            int left = pos / 2;
            int right = left + pos % 2;

            while (left >= 0 && right < n && s.charAt(left--) == s.charAt(right++)) res++;
        }
        return res;
    }
}
