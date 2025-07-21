package PrepInsta;

import java.util.*;

public class SetProblems {
    public static void main(String[] args) {
        // Problem 1: Remove Duplicates from a List
        // Input: A list of integers: [1, 2, 2, 3, 4, 4, 5]
        // Output: A set of unique integers: [1, 2, 3, 4, 5]
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 4, 5));
        System.out.println("Original list1 (with duplicates): " + list1);

        LinkedHashSet<Integer> linkedHashSet1 = new LinkedHashSet<>(list1);
        System.out.println("LinkedHashSet (duplicates removed, insertion order maintained): " + linkedHashSet1);

        // Problem 2: Find Common Elements
        // Input: Set A: [1, 2, 3, 4], Set B: [3, 4, 5, 6]
        // Output: Common elements: [3, 4]
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));

        Set<Integer> intersection1 = new HashSet<>(set1);
        intersection1.retainAll(set2);
        System.out.println("Intersection of set1 and set2: " + intersection1);

        // Problem 3: Check if Element Exists
        // Input: Set: ["apple", "banana", "orange"], Element to check: "banana"
        // Output: true
        Set<String> fruits = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        System.out.println("Does fruits set contain 'banana'? " + fruits.contains("banana")); // true

        // Problem 4: Union of Two Sets
        // Input: Set A: [10, 20, 30], Set B: [20, 40, 50]
        // Output: Union: [10, 20, 30, 40, 50]
        // Note: Code uses [1, 2, 3, 4] and [3, 4, 5, 6] instead of [10, 20, 30] and [20, 40, 50]
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> set4 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        Set<Integer> union = new HashSet<>(set3);
        boolean unionResult = union.addAll(set4);
        System.out.println("Was the union of set3 and set4 successful? " + unionResult);
        System.out.println("Union result: " + union);

        // Problem 5: Difference of Two Sets
        // Input: Set A: [1, 2, 3, 4], Set B: [2, 4]
        // Output: Difference (A - B): [1, 3]
        // Note: Code uses [1, 2, 3, 4] and [3, 4, 5, 6] for demonstration
        Set<Integer> set5 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> set6 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        Set<Integer> difference1 = new HashSet<>(set5);
        difference1.removeAll(set6);
        System.out.println("Difference (set5 - set6): " + difference1);

        // Additional demonstration: Difference (set6 - set5)
        Set<Integer> difference2 = new HashSet<>(set6);
        difference2.removeAll(set5);
        System.out.println("Difference (set6 - set5): " + difference2);

        // Problem 6: Check Subset
        // Input: Set A: [1, 2, 3, 4], Set B: [2, 3]
        // Output: Is B a subset of A? true
        // Note: Code checks [5, 6] as a subset of set6
        System.out.println("Subset Check:");
        System.out.println("Is [5, 6] a subset of set6? " + set6.containsAll(Arrays.asList(5, 6)));

        // Problem 7: Remove Specific Elements
        // Input: Set: ["cat", "dog", "bird", "fish"], Elements to remove: ["dog", "fish"]
        // Output: Updated set: ["cat", "bird"]
        // Method 1: Using remove() multiple times
        Set<String> animals1 = new HashSet<>(Arrays.asList("cat", "dog", "bird", "fish"));
        System.out.println("Original set (animals1): " + animals1);

        animals1.remove("cat");
        animals1.remove("bird");

        System.out.println("Set after removing 'cat' and 'bird' using remove(): " + animals1);

        // Problem 7 (continued): Remove Specific Elements (alternative method)
        // Input: Set: ["cat", "dog", "bird", "fish"], Elements to remove: ["dog", "fish"]
        // Output: Updated set: ["cat", "bird"]
        // Method 2: Using removeAll() with a collection
        Set<String> animals2 = new HashSet<>(Arrays.asList("cat", "dog", "bird", "fish"));
        System.out.println("Original set (animals2): " + animals2);

        animals2.removeAll(Arrays.asList("cat", "bird")); // remove both at once

        System.out.println("Set after removing 'cat' and 'bird' using removeAll(): " + animals2);

        // Problem 8: Convert Array to Sorted Set
        // Input: Array: [5, 2, 8, 1, 9]
        // Output: Sorted set: [1, 2, 5, 8, 9]
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(5, 2, 8, 1, 9));

        // Sort the list in ascending order
        list2.sort(Comparator.naturalOrder());

        // Create a LinkedHashSet to maintain sorted insertion order
        LinkedHashSet<Integer> linkedHashSet2 = new LinkedHashSet<>(list2);

        // Output the result
        System.out.println("Sorted LinkedList: " + list2);
        System.out.println("LinkedHashSet from sorted list: " + linkedHashSet2);

        // Problem 9: Find Size of Set
        // Input: Set: ["red", "blue", "green", "red"]
        // Output: Size: 3
        LinkedList<String> list3 = new LinkedList<>(Arrays.asList("red", "blue", "green", "red"));
        Set<String> set = new HashSet<>(list3);
        System.out.println("Number of unique colors in the list: " + set.size());

        // Problem 10: Intersection of Three Sets
        // Input: Set A: [1, 2, 3, 4], Set B: [2, 3, 5, 6], Set C: [3, 6, 7]
        // Output: Intersection: [3]
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> setB = new HashSet<>(Arrays.asList(2, 3, 5, 6));
        Set<Integer> setC = new HashSet<>(Arrays.asList(3, 6, 7));

        // Create a copy of setA to retain original sets
        Set<Integer> intersection = new HashSet<>(setA);

        // Perform intersection with B and C
        intersection.retainAll(setB);
        intersection.retainAll(setC);

        System.out.println("Intersection of A ∩ B ∩ C: " + intersection);

        String sentence = "The quick brown fox jumps over the lazy dog";

        // Split by space
        String[] words = sentence.split(" ");

        // Use LinkedHashSet to maintain order
        Set<String> uniqueWords = new LinkedHashSet<>();

        for (String word : words) {
//            uniqueWords.add(word); // case-sensitive
            uniqueWords.add(word.toLowerCase());

        }

        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Number of unique words: " + uniqueWords.size());
    }
}