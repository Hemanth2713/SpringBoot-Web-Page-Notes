package PrepInsta;

import java.util.*;

public class Map2Problems {
    public static void main(String[] args) {

        // ✅ Anagram check
        String firstString = "listen";
        String secondString = "silent";
        if (isAnagram(firstString, secondString)) {
            System.out.println("Are Anagrams: true");
        } else {
            System.out.println("Are Anagrams: false");
        }

        // ✅ Character with highest frequency
        String frequencyString = "successes";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (Character ch : frequencyString.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        System.out.print("Characters with max frequency: ");
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() >= maxFrequency) {
                System.out.print(entry.getKey() + " ");
            }
        }
        System.out.println();

        // ✅ Separate Even and Odd numbers using Map
        List<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Map<String, List<Integer>> evenOddMap = new HashMap<>();
        evenOddMap.put("Even", new ArrayList<>());
        evenOddMap.put("Odd", new ArrayList<>());

        for (int num : numberList) {
            if ((num & 1) == 0) {
                evenOddMap.get("Even").add(num);
            } else {
                evenOddMap.get("Odd").add(num);
            }
        }
        System.out.println("Even and Odd Map: " + evenOddMap);

        // ✅ Count vowels in each word
        String[] words = "beautiful day".split(" ");
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
        Map<Character, Integer> vowelCountMap = new HashMap<>();

        for (String word : words) {
            for (Character ch : word.toCharArray()) {
                if (vowels.contains(ch)) {
                    vowelCountMap.put(ch, vowelCountMap.getOrDefault(ch, 0) + 1);
                }
            }
        }
        System.out.println("Vowel Frequencies: " + vowelCountMap);

        char[] word = "keyboard".toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        // Store character with its last index
        for (int i = 0; i < word.length; i++) {
            map.put(word[i], i);
        }

        // Convert map entries to list for sorting
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort the list by value (index)
        entryList.sort(Map.Entry.comparingByValue());

        // Print sorted map
        for (Map.Entry<Character, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    public static boolean isAnagram(String s1, String s2) {
        // Step 1: If lengths differ, not anagrams
        if (s1.length() != s2.length()) return false;

        Map<Character, Integer> charCountMap = new HashMap<>();

        // Step 2: Count characters in first string
        for (char ch : s1.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Step 3: Subtract counts using second string
        for (char ch : s2.toCharArray()) {
            if (!charCountMap.containsKey(ch)) return false;
            charCountMap.put(ch, charCountMap.get(ch) - 1);
            if (charCountMap.get(ch) < 0) return false;
        }

        return true; // All counts matched
    }
}
