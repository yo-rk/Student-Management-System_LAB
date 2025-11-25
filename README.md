# 🎓 **Student Management System**

# 📘 *Java Programming Project*

**Name:** Yashieta Sethi
**Roll No.:** 2401010187
**Course Name:** Java Programming
**Programme:** B.Tech CSE CORE
**Session:** 2025–26

---

## 📝 **Description**

A console-based **Student Management System** implemented in a **single Java file**, demonstrating advanced **Object-Oriented Programming** concepts:

* `abstract` class
* `final` class and `final` method
* **Inheritance** & **Method Overriding**
* **Method Overloading**
* **Interfaces (CRUD operations)**
* **Abstract class for common behavior**
* **Collections (HashMap, List, Comparator)**
* **Menu-driven program with validation**

The system allows adding, deleting, updating, searching, and viewing student records, along with sorting students by marks.

---

## ✨ **Features**

* ➕ Add New Student (Roll No, Name, Email, Course, Marks)
* ❌ Delete Student by Roll Number
* ♻️ Update Student Details (Course / Marks)
* 🔍 Search Student by Roll Number
* 📋 View All Students (sorted by Roll No)
* 📊 View Students Sorted by **Marks (High → Low)**
* 🧮 Automatic Grade Calculation (`A`, `B`, `C`, `D`, `F`) based on marks
* 📌 Sample Data Preloaded (Roll 101, 102)
* 🧾 Overloaded display method with extra note (e.g., “Research Area: AI”)
* 🔔 Final class + final method demo (`FinalNotice`)
* 🧹 `finalize()` method demonstration message before garbage collection (non-deterministic)
* 🎛️ Clean, menu-driven console interface

---

## 🧠 **Concepts Used**

### 🔹 Abstract Class

* `abstract class Person`

  * Fields: `name`, `email`
  * Abstract method: `displayInfo()`
  * Implemented by `Student`.

### 🔹 Inheritance & Method Overriding

* `class Student extends Person`

  * Overrides `displayInfo()` to print full student details.

### 🔹 Method Overloading

* `displayInfo()`
* `displayInfo(String note)` → overloaded to show extra information like research area.

### 🔹 Final Class & Final Method

* `final class FinalNotice`

  * `public final void show()` → prints:
    `"This is a final method in a final class."`

### 🔹 Interface & CRUD Operations

* `interface RecordActions`

  * `addStudent(Student s)`
  * `deleteStudent(int rollNo)`
  * `updateStudent(int rollNo, String course, Double marks)`
  * `searchStudent(int rollNo)`
  * `viewAllStudents()`

### 🔹 Abstract Manager Class

* `abstract class AbstractManager`

  * Provides a default `viewAllStudents()` method.
* `class StudentManager extends AbstractManager implements RecordActions`

  * **Overrides** `viewAllStudents()` with actual implementation.

### 🔹 Collections & Sorting

* Uses `HashMap<Integer, Student>` to store students by roll number.
* Converts to `List<Student>` for:

  * Viewing all students (sorted by roll number).
  * Sorting by marks (descending).

### 🔹 Exception Handling & Input Handling

* Uses `try–catch` for numeric input (`NumberFormatException`).
* Graceful error messages when roll number not found or input is invalid.

### 🔹 finalize() Method

* `protected void finalize()` in `Student`

  * Prints: `"Finalize method called before object is garbage collected."`
  * Demonstrates `finalize()` (non-deterministic call by GC).

---

## 🎓 **Grade Calculation Logic**

* `marks ≥ 85` → **Grade A**
* `70 ≤ marks < 85` → **Grade B**
* `55 ≤ marks < 70` → **Grade C**
* `40 ≤ marks < 55` → **Grade D**
* `marks < 40` → **Grade F**

Grade is automatically updated whenever marks are changed.

---

## ▶️ **How to Run**

### 1️⃣ Save the file as:

```bash
StudentManagementSystem.java
```

### 2️⃣ Compile the program:

```bash
javac StudentManagementSystem.java
```

### 3️⃣ Run the program:

```bash
java StudentManagementSystem
```

---

## 🧪 **Menu Options (Runtime)**

When you run the program, it first:

* Loads **sample students** (Roll 101, 102)
* Demonstrates:

  * `displayInfo()`
  * Overloaded `displayInfo("Research Area: AI")`
  * `FinalNotice.show()`

Then it shows the **Student Management Menu**:

1️⃣ Add Student
2️⃣ Delete Student by Roll No
3️⃣ Update Student (course/marks)
4️⃣ Search Student by Roll No
5️⃣ View All Students
6️⃣ View Students Sorted by Marks (desc)
0️⃣ Exit

---

## ✅ **Conclusion**

This project is a compact but complete demonstration of:

* **Abstract Class + Inheritance**
* **Method Overriding & Overloading**
* **Interface-based CRUD Design**
* **Final Class & Final Method**
* **Finalize Method Concept**
* **Collections + Sorting Logic**
* **Menu-driven Java Application**

It is highly suitable for **OOP-focused Java assignments, practical exams, and viva demonstrations**, especially where the teacher expects **abstract class, interface, overriding, overloading, final, and collections** in a single integrated program.
