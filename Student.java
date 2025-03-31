package StudentWithList;
import java.io.Serializable;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private double grade;

    // Constructor
    public Student(String name, int rollNumber, double grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public double getGrade() {
        return grade;
    }

    // Setter method to update grade
    public void setGrade(double grade) {
        this.grade = grade;
    }

    // Display student details
    public void displayStudent() {
        System.out.println("Roll No: " + rollNumber + " | Name: " + name + " | Grade: " + grade);
    }
}

