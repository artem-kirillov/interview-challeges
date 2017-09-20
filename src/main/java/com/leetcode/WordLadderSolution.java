package com.leetcode;

/**
 * https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadderSolution {
    public int ladderLength(String beginWord, String endWord, java.util.List<String> wordList) {
        final java.util.Set<String> dict = new java.util.HashSet<>(wordList.size());
        dict.addAll(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        final java.util.Queue<String> toVisit = new java.util.ArrayDeque<>();
        toVisit.add(beginWord);

        int dist = 1;
        while (!toVisit.isEmpty()) {
            final int num = toVisit.size();
            for (int i = 0; i < num; i++) {
                final String word = toVisit.poll();
                if (word.equals(endWord)) {
                    return dist;
                }
                java.util.Set<String> nextWords = getNextWords(word, dict);
                toVisit.addAll(nextWords);
            }
            dist += 1;
        }
        return 0;
    }

    private java.util.Set<String> getNextWords(String word, java.util.Set<String> dict) {
        dict.remove(word);
        java.util.Set<String> nextWords = new java.util.HashSet<>();
        for(int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            final char letter = chars[i];
            for (int c = 'a'; c < 'a' + 26; c++) {
                chars[i] = (char) c;
                String w = new String(chars);
                if (dict.contains(w)) {
                    nextWords.add(w);
                    dict.remove(w);
                }
                chars[i] = letter;
            }
        }
        return nextWords;
    }
}
