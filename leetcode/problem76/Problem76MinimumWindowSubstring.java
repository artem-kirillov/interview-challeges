import java.util.*;

/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class Problem76MinimumWindowSubstring {
    //    S = "ADOBECODEBANC"
//    T = "ABC"
//    Minimum window is "BANC".
    public String minWindow(String s, String t) {
        String window = null;
        final Queue<Character> curWindow = new LinkedList<>();

        final Map<Character, Integer> expectedCounts = Collections.unmodifiableMap(buildIndex(t));
        final Map<Character, Integer> curCounts = buildIndex(t);
        final Map<Character, Integer> statCounts = new HashMap<>();

        int endIndex = s.length();
        while (endIndex > 0 && !expectedCounts.containsKey(s.charAt(endIndex - 1))) {
            endIndex -= 1;
        }

        for (final Character ch : s.substring(0, endIndex).toCharArray()) {
            if (curWindow.isEmpty() && !expectedCounts.containsKey(ch)) {
                continue;
            }

            if (expectedCounts.containsKey(ch)) {
                incr(statCounts, ch);
            }

            while (!curWindow.isEmpty() && (
                    !expectedCounts.containsKey(curWindow.peek()) ||
                            statCounts.getOrDefault(curWindow.peek(), 0) > expectedCounts.get(curWindow.peek()))) {
                decr(statCounts, curWindow.remove());
            }

            curWindow.add(ch);
            decr(curCounts, ch);

            if (curCounts.isEmpty() && (window == null || curWindow.size() < window.length())) {
                window = toStr(curWindow);
            }
        }

        return window == null ? "" : window;
    }

    private static Map<Character, Integer> buildIndex(String t) {
        Map<Character, Integer> index = new HashMap<>();
        for (char ch : t.toCharArray()) {
            index.put(ch, index.getOrDefault(ch, 0) + 1);
        }
        return index;
    }

    private static void incr(Map<Character, Integer> index, Character key) {
        index.put(key, index.getOrDefault(key, 0) + 1);
    }

    private static void decr(Map<Character, Integer> index, Character key) {
        if (!index.containsKey(key)) {
            return;
        }
        int newCount = index.get(key) - 1;
        if (newCount > 0) {
            index.put(key, newCount);
        } else {
            index.remove(key);
        }
    }

    private static String toStr(Collection<Character> chars) {
        StringBuilder builder = new StringBuilder(chars.size());
        for (char x : chars) {
            builder.append(x);
        }
        return builder.toString();
    }
}
