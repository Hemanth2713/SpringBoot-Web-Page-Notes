//package PrepInsta;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class LinkedListProblems {
//    public static void main(String[] args) {
//        LinkedList<Integer> list1 = new LinkedList<>();
//
//        // Adding elements
//        list1.add(10);
//        list1.add(4);
//        list1.addAll(Arrays.asList(5, 7, 96));
//        list1.addLast(55);
//        list1.addFirst(44);
//        list1.offer(1);
//        list1.offerLast(66);
//        list1.offerFirst(33);
//        list1.push(77);
//        list1.add(4);
//        System.out.println("Index of first occurrence of 4: " + list1.indexOf(4));
//        System.out.println("Index of last occurrence of 4: " + list1.lastIndexOf(4));
//
//
//        // Print initial list
//        System.out.println("Initial LinkedList after all additions: " + list1);
//
//        // Peeking elements (does not remove them)
//        System.out.println("Peek (first element): " + list1.peek());
//        System.out.println("Peek First: " + list1.peekFirst());
//        System.out.println("Peek Last: " + list1.peekLast());
//
//        // Removing by index
//        list1.remove(1);  // Removes element at index 1
//        System.out.println("After remove(1) [removes element at index 1]: " + list1);
//
//        // Removing by value
//        list1.remove((Integer) 7); // Removes first occurrence of value 7
//        System.out.println("After remove((Integer) 7) [removes value 7]: " + list1);
//
//        // Remove last
//        list1.removeLast();
//        System.out.println("After removeLast(): " + list1);
//
//        // Remove first
//        list1.removeFirst();
//        System.out.println("After removeFirst(): " + list1);
//
//        // Poll (removes head/first)
//        list1.poll();
//        System.out.println("After poll() [removes first element]: " + list1);
//
//        // Pop (removes first, behaves like stack)
//        list1.pop();
//        System.out.println("After pop() [removes first element]: " + list1);
//
//        // Poll last
//        list1.pollLast();
//        System.out.println("After pollLast() [removes last element]: " + list1);
//
//        // Poll first
//        list1.pollFirst();
//        System.out.println("After pollFirst() [removes first element]: " + list1);
//
//        // Final state of the list
//        System.out.println("Final LinkedList: " + list1);
//
//
//
//        // Creating a LinkedList with duplicate values
//        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5,2,1,7, 8, 6, 7, 3));
//
//        // Print original list
//        System.out.println("Original List: " + list2);
//
//        // Sort the list in reverse order
//        list2.sort(Comparator.reverseOrder());
//        System.out.println("List in Reverse Order: " + list2);
//
//        // ---------------------------------------
//        // Method 1: Using Set and Collections.frequency()
//        // This method avoids duplicate counting by processing each number once using Set
//        System.out.println("\nMethod 1: Frequency using Collections.frequency()");
//
//        Set<Integer> uniqueSet = new HashSet<>(list2); // Ensures each number is processed only once
//        for (Integer num : uniqueSet) {
//            int count = Collections.frequency(list2, num); // Built-in frequency counter
//            System.out.println(num + " occurs " + count + " time(s)");
//        }
//
//        // ---------------------------------------
//        // Method 2: Using HashMap and getOrDefault()
//        // This method builds a frequency map manually
//        System.out.println("\nMethod 2: Frequency using HashMap");
//
//        Map<Integer, Integer> countMap = new HashMap<>();
//        for (int num : list2) {
//            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
//        }
//
//        // Print the frequency map
//        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
//            System.out.println(entry.getKey() + " occurs " + entry.getValue() + " time(s)");
//        }
//
//        LinkedList<Integer> list3 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5,2,1,7, 8, 6, 7, 3));
//
//        // Print size of the list
//        System.out.println("List Size: " + list3.size());
//
//        // Get and print the middle element
//        int middleIndex = list3.size() / 2;
//        System.out.println("Middle Element (at index " + middleIndex + "): " + list3.get(middleIndex));
//
//        // Print elements at even positions (index 0, 2, 4, ...)
//        System.out.println("Elements at Even Positions (index 0, 2, 4...):");
//        for (int i = 0; i < list3.size(); i += 2) {
//            System.out.print(list3.get(i) + " ");
//        }
//        System.out.println();
//
//
////        System.out.println("Maximum Element : "+list3.get(Collections.max(list3)));
////        System.out.println("Minimum Element : "+list3.get(Collections.min(list3)));
//        System.out.println("Maximum Element: " + Collections.max(list3));
//        System.out.println("Minimum Element: " + Collections.min(list3));
//        int evenSum=0,oddSum=0;
//        for (int i=0;i< list3.size();i++){
//            if((i&1)==0){
//                evenSum+=list3.get(i);
//            } else if ((i&1)==1) {
//                oddSum+=list3.get(i);
//            }
//        }
//
//        int ternaryEvenSum=0,ternaryOddSum=0;
//        for (int i = 0; i < list3.size(); i++) {
//            int value = list3.get(i);
//            // Use ternary operator to decide where to add
//            ternaryEvenSum += (i % 2 == 0) ? value : 0;
//            ternaryOddSum += (i % 2 != 0) ? value : 0;
//        }
//        System.out.println("Even Sum + "+evenSum+" Odd Sum : "+oddSum);
//
//        LinkedList<Integer> list4 = new LinkedList<>(Arrays.asList(10, 20, 30, 8, 6, 23, 40));
//
//        // Insert 25 after 20
//        int index = list4.indexOf(20);
//        list4.add(index + 1, 25);
//
//        // Print updated list
//        System.out.println(list4);
//
//        // Check if head node is 10
//        System.out.println("Check if a Given Value is at the Head: " + list4.getFirst().equals(10));
//
//        // Count numbers greater than target using a loop
//        int target = 20, count = 0;
//        for (int num : list4) {
//            if (num > target) {
//                count++;
//            }
//        }
//        System.out.println("The number of numbers greater than target (loop): " + count);
//
//        // Count using Streams
//        System.out.println("The number of numbers greater than target (stream): " +
//                list4.stream().filter(num -> num > target).count());
//
//        System.out.println("Original List: " + list4);
//        System.out.println("Delete Last Node : " + list4.pollLast());
//        System.out.println("List after Deleting the Last Node : " + list4);
//        int index1=list4.indexOf(20);
//        list4.set(index1,90);
//        System.out.println(list4);
//    }
//}
package PrepInsta;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedListProblems {
    public static void main(String[] args) {
        // Question 1: Print All Elements of a Linked List
        // Input: Head → 10 → 20 → 30 → null
        // Output: 10 20 30
        // Answer: Print the entire LinkedList using System.out.println(list1)
        LinkedList<Integer> list1 = new LinkedList<>();

        // Adding elements
        list1.add(10);
        list1.add(4);
        list1.addAll(Arrays.asList(5, 7, 96));
        list1.addLast(55);
        list1.addFirst(44);
        list1.offer(1);
        list1.offerLast(66);
        list1.offerFirst(33);
        list1.push(77);
        list1.add(4);

        // Question 2: Find the Length of a Linked List
        // Input: Head → 5 → 15 → 25 → 35 → null
        // Output: 4
        // Answer: Use list1.size() to get the length
        System.out.println("List Size: " + list1.size());

        // Question 3: Find the Sum of All Elements
        // Input: Head → 1 → 3 → 5 → 7 → 9 → null
        // Output: 25
        // Answer: Calculate sum using a loop
        int sum = 0;
        for (int num : list1) {
            sum += num;
        }
        System.out.println("Sum of all elements: " + sum);

        // Question 4: Search for an Element
        // Input: Head → 2 → 4 → 6 → 8 → null, Search = 6
        // Output: Found
        // Answer: Use contains() or indexOf() to check if an element exists
        System.out.println("Index of first occurrence of 4: " + list1.indexOf(4));
        System.out.println("Index of last occurrence of 4: " + list1.lastIndexOf(4));
        System.out.println("Contains 66: " + list1.contains(66));

        // Question 5: Insert at the End
        // Input: List: 1 → 2 → 3 → null, Insert: 4
        // Output: 1 → 2 → 3 → 4 → null
        // Answer: Use addLast() or offerLast() to insert at the end
        // (Already done above with list1.addLast(55) and list1.offerLast(66))
        System.out.println("Initial LinkedList after all additions: " + list1);

        // Question 6: Insert at a Given Position
        // Input: List: 10 → 20 → 30 → 40, X = 25, P = 2
        // Output: 10 → 20 → 25 → 30 → 40
        // Answer: Use add(index, value) to insert at position P
        list1.add(2, 25);
        System.out.println("After inserting 25 at position 2: " + list1);

        // Question 7: Delete the First Occurrence of a Value
        // Input: List: 1 → 3 → 5 → 3 → 7, X = 3
        // Output: 1 → 5 → 3 → 7
        // Answer: Use remove((Integer) value) to delete the first occurrence
        // Removing by index
        list1.remove(1);  // Removes element at index 1
        System.out.println("After remove(1) [removes element at index 1]: " + list1);

        // Removing by value
        list1.remove((Integer) 7); // Removes first occurrence of value 7
        System.out.println("After remove((Integer) 7) [removes value 7]: " + list1);

        // Question 8: Count Occurrences of a Value
        // Input: List: 1 → 2 → 3 → 2 → 4 → 2, X = 2
        // Output: 3
        // Answer: Use Collections.frequency() or HashMap to count occurrences
        // Creating a LinkedList with duplicate values
        LinkedList<Integer> list2 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 2, 1, 7, 8, 6, 7, 3));

        // Print original list
        System.out.println("Original List: " + list2);

        // Sort the list in reverse order
        list2.sort(Comparator.reverseOrder());
        System.out.println("List in Reverse Order: " + list2);

        // ---------------------------------------
        // Method 1: Using Set and Collections.frequency()
        // This method avoids duplicate counting by processing each number once using Set
        System.out.println("\nMethod 1: Frequency using Collections.frequency()");

        Set<Integer> uniqueSet = new HashSet<>(list2); // Ensures each number is processed only once
        for (Integer num : uniqueSet) {
            int count = Collections.frequency(list2, num); // Built-in frequency counter
            System.out.println(num + " occurs " + count + " time(s)");
        }

        // ---------------------------------------
        // Method 2: Using HashMap and getOrDefault()
        // This method builds a frequency map manually
        System.out.println("\nMethod 2: Frequency using HashMap");

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : list2) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Print the frequency map
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " occurs " + entry.getValue() + " time(s)");
        }

        // Question 9: Find the Middle Element
        // Input: List: 11 → 22 → 33 → 44 → 55
        // Output: 33
        // Answer: Use list3.get(list3.size() / 2) to get the middle element
        LinkedList<Integer> list3 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 2, 1, 7, 8, 6, 7, 3));

        // Print size of the list
        System.out.println("List Size: " + list3.size());

        // Get and print the middle element
        int middleIndex = list3.size() / 2;
        System.out.println("Middle Element (at index " + middleIndex + "): " + list3.get(middleIndex));

        // Question 10: Check if List is Empty
        // Input: List: null
        // Output: Empty
        // Answer: Use isEmpty() to check if the list is empty
        System.out.println("Is list3 empty? " + list3.isEmpty());

        // Question 11: Print Elements at Even Positions
        // Input: List: 10 → 20 → 30 → 40 → 50 → 60
        // Output: 10 30 50
        // Answer: Iterate with step 2 to print elements at even indices
        System.out.println("Elements at Even Positions (index 0, 2, 4...):");
        for (int i = 0; i < list3.size(); i += 2) {
            System.out.print(list3.get(i) + " ");
        }
        System.out.println();

        // Question 12: Find Maximum Element
        // Input: List: 2 → 14 → 8 → 21 → 5
        // Output: 21
        // Answer: Use Collections.max()
        // Question 13: Find Minimum Element
        // Input: List: 10 → 7 → 18 → 2 → 5
        // Output: 2
        // Answer: Use Collections.min()
        System.out.println("Maximum Element: " + Collections.max(list3));
        System.out.println("Minimum Element: " + Collections.min(list3));

        // Question 14: Sum of Even and Odd Nodes Separately
        // Input: List: 1 → 2 → 3 → 4 → 5
        // Output: Even Sum = 6, Odd Sum = 9
        // Answer: Sum elements at even and odd indices separately
        int evenSum = 0, oddSum = 0;
        for (int i = 0; i < list3.size(); i++) {
            if ((i & 1) == 0) {
                evenSum += list3.get(i);
            } else if ((i & 1) == 1) {
                oddSum += list3.get(i);
            }
        }

        int ternaryEvenSum = 0, ternaryOddSum = 0;
        for (int i = 0; i < list3.size(); i++) {
            int value = list3.get(i);
            // Use ternary operator to decide where to add
            ternaryEvenSum += (i % 2 == 0) ? value : 0;
            ternaryOddSum += (i % 2 != 0) ? value : 0;
        }
        System.out.println("Even Sum + " + evenSum + " Odd Sum : " + oddSum);

        // Question 15: Check if a Given Value is at the Head
        // Input: List: 100 → 200 → 300, Check: 100
        // Output: Yes
        // Answer: Use getFirst().equals(value) to check the head
        LinkedList<Integer> list4 = new LinkedList<>(Arrays.asList(10, 20, 30, 8, 6, 23, 40));
        System.out.println("Check if a Given Value is at the Head: " + list4.getFirst().equals(10));

        // Question 16: Add a Node After a Given Value
        // Input: List: 10 → 20 → 30 → 40, Insert 25 after 20
        // Output: 10 → 20 → 25 → 30 → 40
        // Answer: Use indexOf() and add() to insert after a value
        int index = list4.indexOf(20);
        list4.add(index + 1, 25);

        // Print updated list
        System.out.println("After inserting 25 after 20: " + list4);

        // Question 17: Count Nodes Greater Than X
        // Input: List: 3 → 12 → 5 → 20 → 8, X = 10
        // Output: 2
        // Answer: Use a loop or stream to count nodes greater than X
        int target = 20, count = 0;
        for (int num : list4) {
            if (num > target) {
                count++;
            }
        }
        System.out.println("The number of numbers greater than target (loop): " + count);

        // Count using Streams
        System.out.println("The number of numbers greater than target (stream): " +
                list4.stream().filter(num -> num > target).count());

        // Question 18: Delete Last Node
        // Input: List: 11 → 22 → 33 → 44
        // Output: 11 → 22 → 33
        // Answer: Use pollLast() to delete the last node
        System.out.println("Original List: " + list4);
        System.out.println("Delete Last Node: " + list4.pollLast());
        System.out.println("List after Deleting the Last Node: " + list4);

        // Question 19: Replace a Value in the List
        // Input: List: 1 → 3 → 5 → 3 → 7, Replace 3 with 9
        // Output: 1 → 9 → 5 → 3 → 7
        // Answer: Use set(index, value) to replace a value
        int index1 = list4.indexOf(20);
        list4.set(index1, 90);
        System.out.println("After replacing 20 with 90: " + list4);

        // Question 20: Get Value at a Specific Index
        // Input: List: 100 → 200 → 300 → 400, k = 2
        // Output: 300
        // Answer: Use get(index) to retrieve value at specific index
        int k = 2;
        System.out.println("Value at index " + k + ": " + list4.get(k));

        // Additional operations from original code
        // Peeking elements (does not remove them)
        System.out.println("Peek (first element): " + list1.peek());
        System.out.println("Peek First: " + list1.peekFirst());
        System.out.println("Peek Last: " + list1.peekLast());

        // Remove operations
        // Removes the element at the specified index (1) and returns it
        list1.remove(1);  // Removes element at index 1
        // Prints the list after removing the element at index 1
        System.out.println("After remove(1) [removes element at index 1]: " + list1);
        // Removes the first occurrence of the specified value (7) and returns true if found
        list1.remove((Integer) 7); // Removes first occurrence of value 7
        // Prints the list after removing the first occurrence of 7
        System.out.println("After remove((Integer) 7) [removes value 7]: " + list1);
        // Removes and returns the last element of the list
        list1.removeLast();
        // Prints the list after removing the last element
        System.out.println("After removeLast(): " + list1);
        // Removes and returns the first element of the list
        list1.removeFirst();
        // Prints the list after removing the first element
        System.out.println("After removeFirst(): " + list1);
        // Removes and returns the first element (head) of the list, or null if empty
        list1.poll();
        // Prints the list after polling the first element
        System.out.println("After poll() [removes first element]: " + list1);
        // Removes and returns the first element, throws an exception if the list is empty
        list1.pop();
        // Prints the list after popping the first element
        System.out.println("After pop() [removes first element]: " + list1);
        // Removes and returns the last element of the list, or null if empty
        list1.pollLast();
        // Prints the list after polling the last element
        System.out.println("After pollLast() [removes last element]: " + list1);
        // Removes and returns the first element of the list, or null if empty
        list1.pollFirst();
        // Prints the list after polling the first element
        System.out.println("After pollFirst() [removes first element]: " + list1);
        // Prints the final state of list1 after all remove operations
        System.out.println("Final LinkedList: " + list1);
    }
}