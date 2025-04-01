public class AdvancedJava {
    public static void main(String[] args) {
        // Check if exactly five arguments are provided
        if (args.length != 5) {
            System.out.println("Error: Please provide exactly five numbers.");
            System.exit(1);
        }

        // Parse the command-line arguments to integers
        int[] numbers = new int[5];
        try {
            for (int i = 0; i < args.length; i++) {
                numbers[i] = Integer.parseInt(args[i]); // Convert String to Integer
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: All arguments must be valid integers.");
            System.exit(1); // Exit if any of the arguments are not integers
        }

        // Create a thread to print the unsorted numbers
        Thread unsortedThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.print("Unsorted Numbers: ");
                for (int number : numbers) {
                    System.out.print(number + " ");
                }
                System.out.println();
            }
        });

        // Create a thread to print the sorted numbers
        Thread sortedThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Bubble Sort to sort the numbers
                int[] sortedNumbers = numbers.clone();  // Clone the original array for sorting
                bubbleSort(sortedNumbers);  // Sort using Bubble Sort
                System.out.print("Sorted Numbers: ");
                for (int number : sortedNumbers) {
                    System.out.print(number + " ");
                }
                System.out.println();
            }
        });

        // Start the unsorted thread first to ensure it prints first
        unsortedThread.start();

        // Wait for unsorted thread to complete before starting the sorted thread
        try {
            unsortedThread.join(); // Ensures unsorted thread completes before the sorted thread
        } catch (InterruptedException e) {
            System.out.println("Error: Thread interruption occurred.");
        }

        // Start the sorted thread after the unsorted thread finishes
        sortedThread.start();

        // Wait for sorted thread to finish
        try {
            sortedThread.join();
        } catch (InterruptedException e) {
            System.out.println("Error: Thread interruption occurred.");
        }

        // Calculate and display the sum of the numbers
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println("Sum of Numbers: " + sum);
    }

    // Bubble Sort implementation
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
