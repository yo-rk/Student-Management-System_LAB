import java.util.*;
import java.io.*;


abstract class Person {
    protected String name;
    protected String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();
}

/* ===========================
   Final class with final method
   (to match the assignment sample output)
   =========================== */
final class FinalNotice {
    public final void show() {
        System.out.println("This is a final method in a final class.");
    }
}


class Student extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private int rollNo;
    private String course;
    private double marks;
    private String grade;

    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    // Overridden abstract method from Person
    @Override
    public void displayInfo() {
        System.out.println("Student Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.printf("Marks: %.2f\n", marks);
        System.out.println("Grade: " + grade);
        System.out.println();
    }

    public void displayInfo(String note) {
        System.out.println("Student Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        if (note != null && !note.isEmpty()) {
            System.out.println(note);
        }
        System.out.println();
    }

    private String computeGrade(double marks) {
        if (marks >= 85) return "A";
        if (marks >= 70) return "B";
        if (marks >= 55) return "C";
        if (marks >= 40) return "D";
        return "F";
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.grade = computeGrade(marks);
    }

    // finalize -- called by GC (non-deterministic). Included to match example output.
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Finalize method called before object is garbage collected.");
        } finally {
            super.finalize();
        }
    }

    @Override
    public String toString() {
        return String.format("Roll: %d | Name: %s | Email: %s | Course: %s | Marks: %.2f | Grade: %s",
                             rollNo, name, email, course, marks, grade);
    }
}

interface RecordActions {
    boolean addStudent(Student s);
    boolean deleteStudent(int rollNo);
    boolean updateStudent(int rollNo, String course, Double marks);
    Student searchStudent(int rollNo);
    List<Student> viewAllStudents();
}

/* ===========================
   AbstractManager - to show method overriding in StudentManager
   (StudentManager will override viewAllStudents())
   =========================== */
abstract class AbstractManager {
    // default behavior (can be overridden)
    public List<Student> viewAllStudents() {
        System.out.println("AbstractManager: default viewAllStudents (no data).");
        return new ArrayList<>();
    }
}

/* ===========================
   StudentManager:
   - extends AbstractManager (overrides viewAllStudents)
   - implements RecordActions (CRUD) using HashMap
   =========================== */
class StudentManager extends AbstractManager implements RecordActions {
    private final Map<Integer, Student> studentMap; // key: rollNo

    public StudentManager() {
        this.studentMap = new HashMap<>();
    }

    // Add student, prevent duplicate roll numbers
    @Override
    public boolean addStudent(Student s) {
        if (s == null) return false;
        int roll = s.getRollNo();
        if (studentMap.containsKey(roll)) {
            System.out.println("Add failed: Duplicate roll number " + roll);
            return false;
        }
        studentMap.put(roll, s);
        return true;
    }

    @Override
    public boolean deleteStudent(int rollNo) {
        if (!studentMap.containsKey(rollNo)) {
            System.out.println("Delete failed: roll number " + rollNo + " not found.");
            return false;
        }
        studentMap.remove(rollNo);
        return true;
    }

    // Update course and/or marks (marks nullable)
    @Override
    public boolean updateStudent(int rollNo, String course, Double marks) {
        Student s = studentMap.get(rollNo);
        if (s == null) {
            System.out.println("Update failed: roll number " + rollNo + " not found.");
            return false;
        }
        if (course != null && !course.isEmpty()) s.setCourse(course);
        if (marks != null) s.setMarks(marks);
        return true;
    }

    @Override
    public Student searchStudent(int rollNo) {
        return studentMap.get(rollNo);
    }

    // Overrides AbstractManager.viewAllStudents() — demonstrates method overriding
    @Override
    public List<Student> viewAllStudents() {
        List<Student> list = new ArrayList<>(studentMap.values());
        // sort by rollNo for consistent output
        list.sort(Comparator.comparingInt(Student::getRollNo));
        // print brief listing
        System.out.println("----- All Students (" + list.size() + ") -----");
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println("--------------------------------");
        return list;
    }

    // Extra helper: return list sorted by marks descending
    public List<Student> sortByMarksDescending() {
        List<Student> list = new ArrayList<>(studentMap.values());
        list.sort((a, b) -> Double.compare(b.getMarks(), a.getMarks()));
        return list;
    }

    // For demonstration: load sample data
    public void loadSampleData() {
        addStudent(new Student(101, "Ankit", "ankit@mail.com", "B.Tech", 78.5));
        addStudent(new Student(102, "Riya", "riya@mail.com", "M.Tech", 91.0));
    }
}

/* ===========================
   Main class with menu & sample run
   =========================== */
public class StudentManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        // Load sample data to match expected output example
        manager.loadSampleData();

        // Print example outputs similar to assignment
        System.out.println();
        // Display student 101 using normal displayInfo()
        Student s1 = manager.searchStudent(101);
        if (s1 != null) s1.displayInfo();

        // Display student 102 using normal displayInfo()
        Student s2 = manager.searchStudent(102);
        if (s2 != null) {
            // demonstrate overloaded display method with extra note
            s2.displayInfo("Research Area: AI");
        }

        // Demonstrate overloaded display method note explicitly
        System.out.println("[Note] Overloaded display method:");
        if (s1 != null) s1.displayInfo(); // overloaded version could be used; showing simple output here

        // Show final notice & finalize demonstration (non-deterministic)
        FinalNotice fn = new FinalNotice();
        fn.show();

        // Start interactive menu for further operations
        runMenu();
    }

    private static void runMenu() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    handleAdd();
                    break;
                case "2":
                    handleDelete();
                    break;
                case "3":
                    handleUpdate();
                    break;
                case "4":
                    handleSearch();
                    break;
                case "5":
                    manager.viewAllStudents(); // overridden method
                    break;
                case "6":
                    handleSortByMarks();
                    break;
                case "0":
                    System.out.println("Exiting. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Student Management Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student by Roll No");
        System.out.println("3. Update Student (course/marks)");
        System.out.println("4. Search Student by Roll No");
        System.out.println("5. View All Students");
        System.out.println("6. View Students Sorted by Marks (desc)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void handleAdd() {
        try {
            System.out.print("Enter roll no (int): ");
            int roll = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter name: ");
            String name = sc.nextLine().trim();
            System.out.print("Enter email: ");
            String email = sc.nextLine().trim();
            System.out.print("Enter course: ");
            String course = sc.nextLine().trim();
            System.out.print("Enter marks (double): ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            Student s = new Student(roll, name, email, course, marks);
            if (manager.addStudent(s)) {
                System.out.println("Student added successfully.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid numeric input. Cancelled add operation.");
        }
    }

    private static void handleDelete() {
        try {
            System.out.print("Enter roll no to delete: ");
            int roll = Integer.parseInt(sc.nextLine().trim());
            if (manager.deleteStudent(roll)) {
                System.out.println("Student deleted successfully.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid roll number.");
        }
    }

    private static void handleUpdate() {
        try {
            System.out.print("Enter roll no to update: ");
            int roll = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter new course (or blank to skip): ");
            String course = sc.nextLine().trim();
            System.out.print("Enter new marks (or blank to skip): ");
            String marksInput = sc.nextLine().trim();
            Double marks = null;
            if (!marksInput.isEmpty()) marks = Double.parseDouble(marksInput);
            if (manager.updateStudent(roll, course.isEmpty() ? null : course, marks)) {
                System.out.println("Student updated successfully.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid numeric input. Update cancelled.");
        }
    }

    private static void handleSearch() {
        try {
            System.out.print("Enter roll no to search: ");
            int roll = Integer.parseInt(sc.nextLine().trim());
            Student s = manager.searchStudent(roll);
            if (s != null) {
                s.displayInfo();
            } else {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid roll number.");
        }
    }

    private static void handleSortByMarks() {
        List<Student> sorted = manager.sortByMarksDescending();
        System.out.println("----- Students sorted by marks (high -> low) -----");
        for (Student s : sorted) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------");
    }
}
