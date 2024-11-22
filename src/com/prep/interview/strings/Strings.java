package com.prep.interview.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Strings {

    public String longestPalindrome(String s) {
        int max = 0;
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j > i; j--) {
                String current = s.substring(i, j + 1);
                if (isPalindrome(current)) {
                    if (current.length() > max) {
                        longest = current;
                        max = current.length();
                    }
                }
            }
        }
        return longest;
    }

    public boolean isPalindrome(String s) {

            String word = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

            int last = word.length() - 1;
            for (int i = 0; i < word.length() / 2; i++) {
                if (word.charAt(i) != word.charAt(last)) {
                    return false;
                }
                last--;
            }
            return true;
    }


    public int longestSubstringWithoutRepeat(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int distinct = 0;

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (set.contains(current)) {
                while (set.contains(current)) {
                    set.remove(s.charAt(i - distinct));
                    distinct--;
                }
            }

            set.add(current);
            distinct++;

            if (distinct > max) {
                max = distinct;
            }
        }

        return max;

    }

    public int firstUnique(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    public boolean stringRotation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        String combined = s2 + s2;

        return isSubstring(combined, s1);
    }

    public boolean isSubstring(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        int s1_index = 0;
        int s2_index = 0;
        int s2_length = s2.length();

        for (int i = 0; i < s1.length(); i++) {
            char s1_curr = s1.charAt(s1_index);
            char s2_curr = s2.charAt(s2_index);
            if (s1_curr != s2_curr) {
                s1_index++;
                s2_length = s2.length();
                s2_index = 0;
            } else {
                s1_index++;
                s2_index++;
                s2_length--;
                if (s2_length == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public String compress(String string) {

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < string.length() - 1; i++) {
            char currentLetter = string.charAt(i);
            char nextLetter = string.charAt(i + 1);
            if (currentLetter == nextLetter) {
                counter++;
            } else {
                sb.append(currentLetter);
                sb.append(counter);
                counter = 1;
            }
        }

        sb.append(string.charAt(string.length() - 1));
        sb.append(counter);

        if (sb.toString().length() < string.length()) {
            return sb.toString();
        }

        return string;
    }

    public boolean oneAway(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        } else if (s1.length() == s2.length()) {
            return isOneEditAway(s1, s2);
        } else {
            if (s1.length() < s2.length()) {
                return isOneInsertionAway(s1,s2);
            } else {
                return isOneInsertionAway(s2, s1);
            }
        }
    }

    private boolean isOneEditAway(String s1, String s2) {
        int currentEdits = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                currentEdits++;
                if (currentEdits > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOneInsertionAway(String shorter, String longer) {
        int shortIndex = 0;
        int longIndex = 0;
        int currentEdits = 0;

        while (shortIndex < shorter.length() && longIndex < longer.length()) {
            if (shorter.charAt(shortIndex) != longer.charAt(longIndex)) {
                currentEdits++;
                longIndex++;
                if (currentEdits > 1) {
                    return false;
                }
            } else {
                shortIndex++;
                longIndex++;
            }
        }
        return true;
    }

    public boolean palindromePermutation(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int oddCounter = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                oddCounter++;
                if (oddCounter > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public String urlify(String s) {
        int trailingSpaces = checkTrailingSpaces(s);
        StringBuilder sb = new StringBuilder();
        int spaceCounter = 0;
        char[] s_charArray = s.toCharArray();

        for (int i = 0; i < s_charArray.length - trailingSpaces; i++) {
            if (s_charArray[i] == ' ' && spaceCounter == 0) {
                sb.append("%20");
                spaceCounter++;
            } else if (s_charArray[i] != ' ') {
                sb.append(s_charArray[i]);
                spaceCounter = 0;
            }
        }
        return sb.toString();
    }

    private int checkTrailingSpaces(String s) {
        int counter = 0;
        char[] s_charArray = s.toCharArray();
        for (int i = s_charArray.length - 1; i >= 0; i--) {
            if (s_charArray[i] == ' ') {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }

    public boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char currentLetter = s1.charAt(i);
            map.put(currentLetter, map.getOrDefault(currentLetter, 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            char currentLetter= s2.charAt(i);
            if (map.containsKey(currentLetter)) {
                int frequency = map.get(currentLetter);
                if (frequency == 0) {
                    return false;
                }
                map.put(currentLetter, frequency - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean checkPermutation_Solution_2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] s1_charArray = s1.toCharArray();
        char[] s2_charArray = s2.toCharArray();
        Arrays.sort(s1_charArray);
        Arrays.sort(s2_charArray);

        for (int i = 0; i < s1_charArray.length; i++) {
            if (s1_charArray[i] != s2_charArray[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean isUnique(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char currentLetter = s.charAt(i);
            if (map.containsKey(currentLetter)) {
                return false;
            }
            map.put(currentLetter, 1);
        }
        return true;
    }

    public boolean isUnique_Solution_2(String s) {
        char[] s_Array = s.toCharArray();
        Arrays.sort(s_Array);
        for (int i = 0; i < s_Array.length - 1; i++) {
            if (s_Array[i] == s_Array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
