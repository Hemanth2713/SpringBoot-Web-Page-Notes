//package PrepInsta;
//
//import java.util.*;
//
//public class ArrayListProblems {
//
//
//    public static void main(String[] args) {
//        List<String> list1 = new ArrayList<>();
//        list1.add("Alice");
//        list1.add("Bob");
//        list1.add("David");
//        list1.add("Alice");
//        list1.add("Charlie");
//        list1.add("David");
//        list1.add("Eva");
//        System.out.println("List 1 Elements : " + list1);
//
//        list1.remove(2);
//        System.out.println("List after removing the specific index " + 2 + " value is : " + list1);
//
//        System.out.println("Check if the Sai exists in the list or not : " + list1.contains("Sai"));
//
//        list1.set(1, "Hemanth");
//        System.out.println("After changing the value at position 1 : " + list1);
//
//        Iterator<String> itr = list1.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());
//
//        Set<String> set = new HashSet<>(list1);
//        list1.clear();
//        list1.addAll(set);
//        System.out.println("After removing all the duplicates in an ArrayList : " + list1);
//
//        HashMap<Integer, Integer> hm = new HashMap<>();
//
//        ArrayList<Integer> numbers = new ArrayList<>();
//        numbers.add(10);
//        numbers.add(20);
//        numbers.add(10);
//        numbers.add(80);
//        numbers.add(30);
//        numbers.add(20);
//        numbers.add(60);
//        numbers.add(30);
//        numbers.add(50);
//        numbers.add(100);
//
//        Collections.sort(numbers);
//        System.out.println("After sorting the numbers (ascending) : " + numbers);
//
//        Collections.sort(numbers, Collections.reverseOrder());
//        //We can implement the same above implementation as  numbers.sort(Collections.reverseOrder());
//
//        System.out.println("After sorting the elements in reverse order (descending) : " + numbers);
//        //We can also implement the sorting by using the comparator
//
//        numbers.sort(Comparator.naturalOrder());
//        System.out.println("Sorted again in natural order using Comparator.naturalOrder(): " + numbers);
//
//        numbers.sort(Comparator.reverseOrder());
//        System.out.println("Sorted again in reverse order using Comparator.reverseOrder(): " + numbers);
//
//        List<Integer> uniqueList=new ArrayList<>();
//        for(int num:numbers){
//            if(!uniqueList.contains(num)){
//                uniqueList.add(num);
//            }
//        }
//        Collections.sort(uniqueList);
//        System.out.println(uniqueList);
//        System.out.println("Second Highest number form the Numbers List is : "+uniqueList.get(uniqueList.size()-2));
//
//        List<Integer> nums1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
//        List<Integer> nums2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
//
//        // Union: combine both lists, remove duplicates using Set
//        Set<Integer> unionSet = new HashSet<>();
//        unionSet.addAll(nums1);
//        unionSet.addAll(nums2);
//        System.out.println("After performing the union: " + unionSet);
//
//        // Intersection: find common elements
//        //nums1.retainAll(nums2); //We can implement this one also for intersection
//        List<Integer> intersection = new ArrayList<>(nums1); // Copy of nums1
//        intersection.retainAll(nums2); // Retains only elements also in nums2
//        System.out.println("After performing the intersection: " + intersection);
//
//        List<Integer> arr=new ArrayList<>(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7));
//        List<Integer> slidingWindow=new ArrayList<>();
//        int k=3;
//        for(int i=0;i<=arr.size()-k;i++){
//            slidingWindow.add(Collections.max(arr.subList(i,i+k)));
//        }
//        System.out.println(slidingWindow);
//
//        List<Student> students = new ArrayList<>(
//                Arrays.asList(
//                        new Student(85,"Alice"),
//                        new Student(75,"Bob"),
//                        new Student(95,"Charlie")
//                )
//        );
//
//        //We can also add the new student in this format as well
//        Student s1=new Student(88,"Sai");
//        students.add(s1);
//
//        // Sort by id in ascending order
//        students.sort(Comparator.comparing(student -> student.id));
//
//        System.out.println("Sorted by ID:");
//        students.forEach(student -> System.out.println(student.id+" "+student.name));
//
//        students.sort(Comparator.comparing(Student::getId).reversed());
//
//
//        System.out.println("Sorting by ID in Reverse Order : ");
//        System.out.println(students);
////        students.forEach(student -> System.out.println(student.id+" "+student.name));
//
//        List<Integer> list2 = Arrays.asList(1, 2, 1, 3, 2, 1, 1);
//        System.out.println("Input list: " + list2);
//        System.out.println("The Sub Array Count : " + subArraySum(list2, 4));
//
//        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
//        List<Integer> l2 = Arrays.asList(4, 5, 6);
//        List<Integer> l3 = Arrays.asList(7, 8, 9);
//
//        l1.addAll(l2);
//        l1.addAll(l3);
//
//        System.out.println(l1);
//
//    }
//    public  static  int subArraySum(List<Integer> arr,int k){
//        int count=0;
//        for(int i=0;i<arr.size();i++){
//            int sum=0;
//            for(int j=i;j<arr.size();j++){
//                sum+=arr.get(j);
//                if(sum==k){
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//
//}
//class Student {
//    int id;
//    String name;
//
//    public Student(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//        public String toString() {
//            return "Student{id=" + id + ", name='" + name + "'}";
//        }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
package PrepInsta;

import java.util.*;

public class ArrayListProblems {

    public static void main(String[] args) {
        /**
         * 1. Add Elements
         * Input: ["Alice", "Bob", "Charlie", "David", "Eva"]
         * Output:
         * Alice
         * Bob
         * Charlie
         * David
         * Eva
         */
        List<String> list1 = new ArrayList<>();
        list1.add("Alice");
        list1.add("Bob");
        list1.add("Charlie");
        list1.add("David");
        list1.add("Eva");
        System.out.println("List 1 Elements : " + list1);

        /**
         * 2. Remove Element at Specific Index
         * Input: ["Red", "Green", "Blue", "Yellow"], index = 2
         * Output: ["Red", "Green", "Yellow"]
         */
        list1.clear(); // Clear list1 to reuse for this question
        list1.add("Red");
        list1.add("Green");
        list1.add("Blue");
        list1.add("Yellow");
        list1.remove(2);
        System.out.println("List after removing the specific index " + 2 + " value is : " + list1);

        /**
         * 3. Search for Element
         * Input: ["Apple", "Banana", "Mango"], search = "Orange"
         * Output: Element found: false
         */
        list1.clear(); // Clear list1 to reuse for this question
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Mango");
        System.out.println("Check if the Orange exists in the list or not : " + list1.contains("Orange"));

        /**
         * 4. Update Element
         * Input: ["Apple", "Banana", "Mango"], update index 1 to "Blackberry"
         * Output: ["Apple", "Blackberry", "Mango"]
         */
        list1.set(1, "Blackberry");
        System.out.println("After changing the value at position 1 : " + list1);

        /**
         * 5. Print All Using Loop
         * Input: ["One", "Two", "Three"]
         * Output:
         * One
         * Two
         * Three
         */
        list1.clear(); // Clear list1 to reuse for this question
        list1.add("One");
        list1.add("Two");
        list1.add("Three");
        Iterator<String> itr = list1.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());

        /**
         * 6. Remove Duplicates
         * Input: [1, 2, 3, 2, 4, 1, 5]
         * Output: [1, 2, 3, 4, 5]
         */
        list1.clear(); // Clear list1 to reuse for this question
        list1.add("Alice");
        list1.add("Bob");
        list1.add("David");
        list1.add("Alice");
        list1.add("Charlie");
        list1.add("David");
        list1.add("Eva");
        System.out.println("List 1 Elements : " + list1);

        list1.remove(2);
        System.out.println("List after removing the specific index " + 2 + " value is : " + list1);

        System.out.println("Check if the Sai exists in the list or not : " + list1.contains("Sai"));

        list1.set(1, "Hemanth");
        System.out.println("After changing the value at position 1 : " + list1);

        itr = list1.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());

        Set<String> set = new HashSet<>(list1);
        list1.clear();
        list1.addAll(set);
        System.out.println("After removing all the duplicates in an ArrayList : " + list1);

        /**
         * 7. Find Frequency of Elements
         * Input: [10, 20, 10, 10, 30, 20, 30]
         * Output:
         * 10: 3
         * 20: 2
         * 30: 2
         */
        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(10);
        numbers.add(10);
        numbers.add(30);
        numbers.add(20);
        numbers.add(30);
        for (Integer num : numbers) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        /**
         * 8. Reverse ArrayList
         * Input: [5, 10, 15, 20]
         * Output: [20, 15, 10, 5]
         */
        numbers.clear(); // Clear numbers to reuse for this question
        numbers.add(5);
        numbers.add(10);
        numbers.add(15);
        numbers.add(20);
        Collections.reverse(numbers);
        System.out.println("After reversing the ArrayList : " + numbers);

        /**
         * 9. Sort in Ascending/Descending
         * Input: [3, 1, 4, 5, 2]
         * Output:
         * Ascending: [1, 2, 3, 4, 5]
         * Descending: [5, 4, 3, 2, 1]
         */
        numbers.clear(); // Clear numbers to reuse for this question
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(5);
        numbers.add(2);
        Collections.sort(numbers);
        System.out.println("After sorting the numbers (ascending) : " + numbers);

        Collections.sort(numbers, Collections.reverseOrder());
        //We can implement the same above implementation as  numbers.sort(Collections.reverseOrder());
        System.out.println("After sorting the elements in reverse order (descending) : " + numbers);
        //We can also implement the sorting by using the comparator

        numbers.sort(Comparator.naturalOrder());
        System.out.println("Sorted again in natural order using Comparator.naturalOrder(): " + numbers);

        numbers.sort(Comparator.reverseOrder());
        System.out.println("Sorted again in reverse order using Comparator.reverseOrder(): " + numbers);

        /**
         * 10. Find Second Largest
         * Input: [10, 20, 30, 40, 50]
         * Output: Second largest: 40
         */
        numbers.clear(); // Clear numbers to reuse for this question
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        List<Integer> uniqueList = new ArrayList<>();
        for (int num : numbers) {
            if (!uniqueList.contains(num)) {
                uniqueList.add(num);
            }
        }
        Collections.sort(uniqueList);
        System.out.println(uniqueList);
        System.out.println("Second Highest number form the Numbers List is : " + uniqueList.get(uniqueList.size() - 2));

        /**
         * 11. Intersection of Two Lists
         * Input:
         * List1: [1, 2, 3, 4]
         * List2: [3, 4, 5, 6]
         * Output: [3, 4]
         */
        List<Integer> nums1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> nums2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        // Union: combine both lists, remove duplicates using Set
        Set<Integer> unionSet = new HashSet<>();
        unionSet.addAll(nums1);
        unionSet.addAll(nums2);
        System.out.println("After performing the union: " + unionSet);

        // Intersection: find common elements
        //nums1.retainAll(nums2); //We can implement this one also for intersection
        List<Integer> intersection = new ArrayList<>(nums1); // Copy of nums1
        intersection.retainAll(nums2); // Retains only elements also in nums2
        System.out.println("After performing the intersection: " + intersection);

        /**
         * 12. Union of Two Lists
         * Input:
         * List1: [1, 2, 3]
         * List2: [3, 4, 5]
         * Output: [1, 2, 3, 4, 5]
         */
        nums1.clear(); // Clear nums1 to reuse for this question
        nums1.addAll(Arrays.asList(1, 2, 3));
        nums2.clear(); // Clear nums2 to reuse for this question
        nums2.addAll(Arrays.asList(3, 4, 5));
        unionSet.clear(); // Clear unionSet to reuse
        unionSet.addAll(nums1);
        unionSet.addAll(nums2);
        System.out.println("After performing the union: " + unionSet);

        /**
         * 13. Remove Even Numbers
         * Input: [1, 2, 3, 4, 5, 6]
         * Output: [1, 3, 5]
         */
        numbers.clear(); // Clear numbers to reuse for this question
        numbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(num -> num % 2 == 0);
        System.out.println("After removing even numbers: " + numbers);

        /**
         * 14. Custom Sorting (Students by Marks Desc)
         * Input:
         * [
         *   Student("Alice", 85),
         *   Student("Bob", 75),
         *   Student("Charlie", 95)
         * ]
         * Output:
         * Charlie - 95
         * Alice - 85
         * Bob - 75
         */
        List<Integer> arr = new ArrayList<>(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7));
        List<Integer> slidingWindow = new ArrayList<>();
        int k = 3;
        for (int i = 0; i <= arr.size() - k; i++) {
            slidingWindow.add(Collections.max(arr.subList(i, i + k)));
        }
        System.out.println(slidingWindow);

        List<Student> students = new ArrayList<>(
                Arrays.asList(
                        new Student(85, "Alice"),
                        new Student(75, "Bob"),
                        new Student(95, "Charlie")
                )
        );
        students.sort(Comparator.comparing(Student::getId).reversed());
        System.out.println("Sorting by ID in Reverse Order : ");
        students.forEach(student -> System.out.println(student.getName() + " - " + student.getId()));

        /**
         * 15. Group Anagrams
         * Input: ["bat", "tab", "cat", "act", "tac"]
         * Output:
         * [
         *   [bat, tab],
         *   [cat, act, tac]
         * ]
         */
        List<String> words = Arrays.asList("bat", "tab", "cat", "act", "tac");
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            anagramGroups.computeIfAbsent(sortedWord, key -> new ArrayList<>()).add(word);
        }
        System.out.println("Anagram groups: " + anagramGroups.values());

        /**
         * 16. Sliding Window Maximum (k = 3)
         * Input: [1, 3, -1, -3, 5, 3, 6, 7]
         * Output: [3, 3, 5, 5, 6, 7]
         */
        arr = new ArrayList<>(Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7));
        slidingWindow = new ArrayList<>();
        k = 3;
        for (int i = 0; i <= arr.size() - k; i++) {
            slidingWindow.add(Collections.max(arr.subList(i, i + k)));
        }
        System.out.println(slidingWindow);

        /**
         * 17. Count Subarrays That Sum to K
         * Input: [1, 2, 3], K = 3
         * Output: 2
         * Explanation: Subarrays [1,2] and [3] both sum to 3
         */
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        System.out.println("Input list: " + list2);
        System.out.println("The Sub Array Count : " + subArraySum(list2, 3));

        /**
         * 18. Flatten a Nested ArrayList
         * Input: [[1, 2], [3, 4], [5]]
         * Output: [1, 2, 3, 4, 5]
         */
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );
        List<Integer> flattenedList = new ArrayList<>();
        for (List<Integer> subList : nestedList) {
            flattenedList.addAll(subList);
        }
        System.out.println("Flattened list: " + flattenedList);

        // Original code continued
        //We can also add the new student in this format as well
        Student s1 = new Student(88, "Sai");
        students.add(s1);

        // Sort by id in ascending order
        students.sort(Comparator.comparing(student -> student.id));

        System.out.println("Sorted by ID:");
        students.forEach(student -> System.out.println(student.id + " " + student.name));

        students.sort(Comparator.comparing(Student::getId).reversed());

        System.out.println("Sorting by ID in Reverse Order : ");
        System.out.println(students);
//        students.forEach(student -> System.out.println(student.id+" "+student.name));

        List<Integer> list2_2 = Arrays.asList(1, 2, 1, 3, 2, 1, 1);
        System.out.println("Input list: " + list2_2);
        System.out.println("The Sub Array Count : " + subArraySum(list2_2, 4));

        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        List<Integer> l3 = Arrays.asList(7, 8, 9);

        l1.addAll(l2);
        l1.addAll(l3);

        System.out.println(l1);
    }

    public static int subArraySum(List<Integer> arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            int sum = 0;
            for (int j = i; j < arr.size(); j++) {
                sum += arr.get(j);
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}

class Student {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}