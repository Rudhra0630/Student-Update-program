package StudentWithList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainCLass {
    private static ArrayList<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile(); // Load existing data

        while (true) {
            System.out.println("\nStudent Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Grade");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudentGrade();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to add a student
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();

        students.add(new Student(name, rollNumber, grade));
        System.out.println("Student added successfully!");
    }

    // Method to view all students
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\nStudent Records:");
        for (Student s : students) {
            s.displayStudent();
        }
    }

    // Method to update student grade
    private static void updateStudentGrade() {
        System.out.print("Enter roll number of student to update: ");
        int rollNumber = scanner.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.print("Enter new grade: ");
                double newGrade = scanner.nextDouble();
                s.setGrade(newGrade);
                System.out.println("Grade updated successfully!");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Method to delete a student
    private static void deleteStudent() {
        System.out.print("Enter roll number of student to delete: ");
        int rollNumber = scanner.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                students.remove(s);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Method to search a student by name or roll number
    private static void searchStudent() {
        System.out.println("Search by: 1. Name  2. Roll Number");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(name)) {
                    s.displayStudent();
                    return;
                }
            }
            System.out.println("No student found with name " + name);
        } else if (choice == 2) {
            System.out.print("Enter roll number: ");
            int rollNumber = scanner.nextInt();

            for (Student s : students) {
                if (s.getRollNumber() == rollNumber) {
                    s.displayStudent();
                    return;
                }
            }
            System.out.println("No student found with roll number " + rollNumber);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    // Save student data to file
    private static void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load student data from file
    private static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data.");
        }
    }
}

