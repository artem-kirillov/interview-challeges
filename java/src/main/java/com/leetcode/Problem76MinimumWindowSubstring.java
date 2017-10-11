package com.leetcode;

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
        Queue<Character> curWindow = new ArrayDeque<>();

        Map<Character, Integer> expectedCounts = Collections.unmodifiableMap(buildIndex(t));
        Map<Character, Integer> curCounts = buildIndex(t);
        Map<Character, Integer> statCounts = new HashMap<>();

        int endIndex = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (expectedCounts.containsKey(s.charAt(i))) {
                endIndex = Math.min(endIndex, i + 1);
                break;
            }
        }

        for (char ch : s.substring(0, endIndex).toCharArray()) {
            if (curWindow.isEmpty() && !expectedCounts.containsKey(ch)) {
                continue;
            }

            if (expectedCounts.containsKey(ch)) {
                incr(statCounts, ch);
            }

            if (!curWindow.isEmpty() && !curCounts.containsKey(ch) && curWindow.peek().equals(ch)) {
                Character head = curWindow.remove();
                decr(statCounts, head);
                if (statCounts.getOrDefault(head, 0) < expectedCounts.get(head)) {
                    incr(curCounts, head);
                }

                padWindow(curWindow, expectedCounts);

                while (!curWindow.isEmpty() && !curCounts.containsKey(curWindow.peek()) && statCounts.getOrDefault(curWindow.peek(), 0) > expectedCounts.get(curWindow.peek())) {
                    decr(statCounts, curWindow.remove());
                    padWindow(curWindow, expectedCounts);
                }
            }

            curWindow.add(ch);
            decr(curCounts, ch);

            if (curCounts.isEmpty()) {
                if (window == null || curWindow.size() < window.length()) {
                    window = toStr(curWindow);
                }
            }
        }

        return window == null ? "" : window;
    }

    private static void padWindow(Queue<Character> curWindow, Map<Character, Integer> expectedCounts) {
        while (!curWindow.isEmpty() && !expectedCounts.containsKey(curWindow.peek())) {
            curWindow.remove();
        }
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
