import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in); // scanner for input
        int choice = 0;

        do {
            System.out.println("\n--- TO-DO LIST MENU ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark as Completed");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Safe input for menu
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a valid number!");
                sc.next(); // clear invalid input
                continue;
            }

            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();
                    tasks.add("[ ] " + task);
                    System.out.println("Task added!");
                    break;

                case 2:
                    System.out.println("\nYour Tasks:");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter task number to delete: ");

                    if (sc.hasNextInt()) {
                        int del = sc.nextInt();
                        if (del > 0 && del <= tasks.size()) {
                            tasks.remove(del - 1);
                            System.out.println("Task deleted!");
                        } else {
                            System.out.println("Invalid number!");
                        }
                    } else {
                        System.out.println("Enter valid number!");
                        sc.next();
                    }
                    break;

                case 4:
                    System.out.print("Enter task number to mark completed: ");

                    if (sc.hasNextInt()) {
                        int comp = sc.nextInt();
                        if (comp > 0 && comp <= tasks.size()) {
                            String t = tasks.get(comp - 1);
                            tasks.set(comp - 1, t.replace("[ ]", "[X]"));
                            System.out.println("Task marked as completed!");
                        } else {
                            System.out.println("Invalid number!");
                        }
                    } else {
                        System.out.println("Enter valid number!");
                        sc.next();
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}