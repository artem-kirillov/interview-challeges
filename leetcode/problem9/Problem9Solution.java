/**
 * https://leetcode.com/problems/palindrome-number/description/
 */
class Problem9Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int l = 1;
        int r = power(x);
        while (l < r) {
            if (x / l % 10 != x / r % 10) return false;
            l *= 10;
            r /= 10;
        }
        return true;
    }
    private static int power(final int x) {
        int p = 1;
        while (x / p >= 10) p *= 10;
        return p;
    }
}
