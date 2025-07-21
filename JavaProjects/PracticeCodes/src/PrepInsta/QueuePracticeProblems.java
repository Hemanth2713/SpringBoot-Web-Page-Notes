package PrepInsta;

import java.util.*;

public class QueuePracticeProblems {

    public static void main(String[] args) {

        // 1. Add Elements and Print Queue
        // Input: ["Alice", "Bob", "Charlie", "David"]
        // Output: [Alice, Bob, Charlie, David]
        Queue<String> queue1 = new LinkedList<>();
        queue1.add("Alice");
        queue1.add("Bob");
        queue1.add("Charlie");
        queue1.add("David");
        System.out.println("Queue 1: " + queue1);

        // 2. Poll Elements Until Empty
        // Input: ["Pizza", "Burger", "Sandwich"]
        // Output: Pizza
        //         Burger
        //         Sandwich
        //         Queue is empty
        Queue<String> queue2 = new LinkedList<>(List.of("Pizza", "Burger", "Sandwich"));
        while (!queue2.isEmpty()) {
            System.out.println(queue2.poll());
        }
        System.out.println("Queue is empty");

        // 3. Peek Front of the Queue
        // Input: ["Item1", "Item2", "Item3"]
        // Output: Front of queue: Item1
        Queue<String> queue3 = new LinkedList<>();
        queue3.add("Item1");
        queue3.add("Item2");
        queue3.add("Item3");
        System.out.println("Front of queue: " + queue3.peek());

        // 4. Count Elements in Queue
        // Input: ["Red", "Green", "Blue", "Yellow"]
        // Output: Queue size: 4
        Queue<String> queue4 = new LinkedList<>(List.of("Red", "Green", "Blue", "Yellow"));
        System.out.println("Queue size: " + queue4.size());

        // 5. Check if Queue is Empty
        // Input: ["Tom", "Jerry"]
        // Output:
        // Is queue empty? false
        // Polled: Tom
        // Polled: Jerry
        // Is queue empty? true
        Queue<String> queue5 = new LinkedList<>(List.of("Tom", "Jerry"));
        System.out.println("Is queue empty? " + queue5.isEmpty());
        System.out.println("Polled: " + queue5.poll());
        System.out.println("Polled: " + queue5.poll());
        System.out.println("Is queue empty? " + queue5.isEmpty());

        // 6. Add Elements, Remove One, Then Add More
        // Input: ["Person1", "Person2"] → remove one → add ["Person3", "Person4"]
        // Output: [Person2, Person3, Person4]
        Queue<String> queue6 = new LinkedList<>();
        queue6.add("Person1");
        queue6.add("Person2");
        queue6.poll(); // remove one
        queue6.add("Person3");
        queue6.add("Person4");
        System.out.println("Queue 6: " + queue6);

        // 7. Reverse Print Without Using Stack
        // Input: ["A", "B", "C", "D"]
        // Output: D C B A
        Queue<String> queue7 = new LinkedList<>(List.of("A", "B", "C", "D"));
        List<String> tempList = new ArrayList<>(queue7);
        Collections.reverse(tempList);
        for (String item : tempList) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 8. Serve Customers Based on Queue
        // Input: ["User1", "User2", "User3"]
        // Output:
        // Serving User1
        // Serving User2
        // Serving User3
        Queue<String> queue8 = new LinkedList<>(List.of("User1", "User2", "User3"));
        while (!queue8.isEmpty()) {
            System.out.println("Serving " + queue8.poll());
        }

        // 9. Last Element in Queue
        // Input: ["One", "Two", "Three"]
        // Output: Last element: Three
        Queue<String> queue9 = new LinkedList<>(List.of("One", "Two", "Three"));
        String last = null;
        for (String item : queue9) {
            last = item;
        }
        System.out.println("Last element: " + last);

        // 10. Add Only Unique Elements
        // Input: ["Apple", "Banana", "Apple", "Orange", "Banana"]
        // Output: [Apple, Banana, Orange]
        String[] inputs = {"Apple", "Banana", "Apple", "Orange", "Banana"};
        Set<String> seen = new LinkedHashSet<>();
        Queue<String> queue10 = new LinkedList<>();
        for (String item : inputs) {
            if (seen.add(item)) {
                queue10.add(item);
            }
        }
        System.out.println("Queue 10 (Unique): " + queue10);
    }
}
