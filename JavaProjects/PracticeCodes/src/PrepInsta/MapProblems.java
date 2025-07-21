package PrepInsta;

import java.util.*;

public class MapProblems {
    public static void main(String[] args) {

        // ✅ Question: Count the frequency of each character in a string
        // 📥 Input: "banana"
        // 📤 Output: {b=1, a=3, n=2}
        String word = "banana";
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            charFrequencyMap.put(word.charAt(i), charFrequencyMap.getOrDefault(word.charAt(i), 0) + 1);
        }
        System.out.println("Character frequencies in '" + word + "': " + charFrequencyMap);

        // ✅ Question: Count frequency of each word in a sentence
        // 📥 Input: "the cat and the dog"
        // 📤 Output: {the=2, cat=1, and=1, dog=1}
        String sentence = "the cat and the dog";
        String[] words = sentence.split(" ");
        HashMap<String, Integer> wordFrequencyMap = new HashMap<>();
        System.out.println("Input sentence: " + sentence);
        for (String wordItem : words) {
            wordFrequencyMap.put(wordItem, wordFrequencyMap.getOrDefault(wordItem, 0) + 1);
        }
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println("Word: " + entry.getKey() + ", Count: " + entry.getValue());
        }
        System.out.println("Complete word frequency map: " + wordFrequencyMap);

        // ✅ Question: Print words that occur more than once
        // 📥 Input: "java python java c c java"
        // 📤 Output: [java, c]
        String[] inputWords = "java python java c c java".split(" ");
        System.out.println("Input words: " + Arrays.toString(inputWords));
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        for (String wordItem : inputWords) {
            wordCountMap.put(wordItem, wordCountMap.getOrDefault(wordItem, 0) + 1);
        }
        System.out.println("Word frequency map: " + wordCountMap);

        List<String> duplicateWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicateWords.add(entry.getKey());
            }
        }
        System.out.println("Words occurring more than once: " + duplicateWords);

        // ✅ Question: Find the first non-repeating character
        // 📥 Input: "aabbcdde"
        // 📤 Output: c
        String inputString = "aabbcdde";
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // Count frequency of each character
        for (char ch : inputString.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Find the first character with frequency 1
        for (char ch : inputString.toCharArray()) {
            if (charCountMap.get(ch) == 1) {
                System.out.println("First non-repeating character in '" + inputString + "': " + ch);
                break;
            }
        }

        // ✅ Question: Swap keys and values in a map
        // 📥 Input: {1=A, 2=B, 3=C}
        // 📤 Output: {A=1, B=2, C=3}
        Map<Integer, String> originalKeyValueMap = new HashMap<>();
        originalKeyValueMap.put(1, "A");
        originalKeyValueMap.put(2, "B");
        originalKeyValueMap.put(3, "C");

        // Map to hold swapped keys and values
        Map<String, Integer> swappedKeyValueMap = new HashMap<>();

        // Swapping keys and values
        for (Map.Entry<Integer, String> entry : originalKeyValueMap.entrySet()) {
            swappedKeyValueMap.put(entry.getValue(), entry.getKey());
        }

        // Output the maps
        System.out.println("Original map: " + originalKeyValueMap);
        System.out.println("Swapped map: " + swappedKeyValueMap);

        // ✅ Question: Merge two maps by adding values for common keys
        // 📥 Input: map1 = {a=100, b=200}, map2 = {a=300, c=400}
        // 📤 Output: {a=400, b=200, c=400}
        Map<Character, Integer> baseMap = new HashMap<>();
        baseMap.put('a', 100);
        baseMap.put('b', 200);

        // Map to store values to be added
        Map<Character, Integer> additionalValuesMap = new HashMap<>();
        additionalValuesMap.put('a', 300);
        additionalValuesMap.put('b', 400);

        // Compute and merge values from additionalValuesMap into baseMap
        baseMap.compute('a', (key, value) -> value + additionalValuesMap.getOrDefault('a', 0));
        baseMap.compute('b', (key, value) -> value + additionalValuesMap.getOrDefault('b', 0));

        // Print the merged map
        System.out.println("Merged map: " + baseMap);

        // ✅ Question: Sort a map by values
        // 📥 Input: {b=2, a=1, c=3}
        // 📤 Output: {a=1, b=2, c=3}
        Map<Character, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put('b', 2);
        unsortedMap.put('a', 1);
        unsortedMap.put('c', 3);

        // Convert entry set to a list
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(unsortedMap.entrySet());

        // Sort list by value
        entryList.sort(Map.Entry.comparingByValue());

        // Create a LinkedHashMap to maintain sorted order
        Map<Character, Integer> sortedValueMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : entryList) {
            sortedValueMap.put(entry.getKey(), entry.getValue());
        }

        // Output the sorted map
        System.out.println("Map sorted by values: " + sortedValueMap);

        // ✅ Question: Count each digit in a number
        // 📥 Input: 122333
        // 📤 Output: {1=1, 2=2, 3=3}
        String numberString = String.valueOf(122333);
        Map<Character, Integer> digitFrequencyMap = new HashMap<>();
        for (Character ch : numberString.toCharArray()) {
            digitFrequencyMap.put(ch, digitFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        System.out.println("Digit frequencies in '" + numberString + "': " + digitFrequencyMap);

        // ✅ Question: Count frequency of characters ignoring case
        // 📥 Input: "Apple"
        // 📤 Output: {a=1, p=2, l=1, e=1}
        String inputWord = "Apple".toLowerCase();
        Map<Character, Integer> charCountOrderedMap = new LinkedHashMap<>();
        for (Character ch : inputWord.toCharArray()) {
            charCountOrderedMap.put(ch, charCountOrderedMap.getOrDefault(ch, 0) + 1);
        }
        System.out.println("Character frequencies in '" + inputWord + "' (ordered): " + charCountOrderedMap);

        // Note: Commented sorting code is retained as per request
//        Instead of using the below code for sorting we can do the sorting by using LinkedHashMap or TreeMap
//        List<Map.Entry<Character,Integer>> entryList1=new ArrayList<>(charCountOrderedMap.entrySet());
//        entryList1.sort(Map.Entry.comparingByKey());
//
//        Map<Character,Integer> sortedMap1=new LinkedHashMap<>();
//        for (Map.Entry<Character,Integer> entry:entryList1){
//            sortedMap1.put(entry.getKey(),entry.getValue());
//        }
//        System.out.println(sortedMap1);
    }
}