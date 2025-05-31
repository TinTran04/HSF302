package hsf302.jpa.gui;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;
import hsf302.jpa.service.StudentServiceImpl;
import hsf302.jpa.service.SubjectServiceImpl;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class MainProgram {
    private static StudentServiceImpl studentService = new StudentServiceImpl();
    private static SubjectServiceImpl subjectService = new SubjectServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("==========MENU===========");
            System.out.println("1. Create Student");
            System.out.println("2. View students details");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");


            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    viewStudentDetails();
                    break;
                case 3:
                    updateStudentById();
                    break;
                case 4:
                    deleteStudentById();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);


    }

    public static void createStudent() {
        System.out.println("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Student Birth Year: ");
        int birthYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter Student Email: ");
        String email = scanner.nextLine();

        System.out.println("Enter Student Password: ");
        String password = scanner.nextLine();

        Student student = new Student(name, birthYear, email, password);

        System.out.println("How many subjects to add for this student? ");
        int subjectCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < subjectCount; i++) {
            System.out.println("Enter Subject #" + (i + 1) + " Name: ");
            String subjectName = scanner.nextLine();

            System.out.println("Enter Subject #" + (i + 1) + " Code: ");
            String subjectCode = scanner.nextLine();

            Subject subject = new Subject(subjectName, subjectCode);
            subject.setStudent(student);
            student.getSubjects().add(subject);
        }

        studentService.createStudent(student);

        System.out.println("Student created successfully with ID: " + student.getId());
        System.out.println("Total subjects added: " + student.getSubjects().size());
    }


    public static void viewStudentDetails() {
        System.out.println("Enter Student ID to view details: ");
        Long studentId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        System.out.println("Student Details:");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Birth Year: " + student.getBirthYear());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Subjects: ");
        for (Subject subject : student.getSubjects()) {
            System.out.println("- " + subject.getName() + " (" + subject.getCode() + ")");
        }
    }

    public static void updateStudentById() {
        System.out.println("Enter Student ID to update: ");
        Long studentId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return;
        }

        System.out.println("Enter new Name (leave blank to skip): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.println("Enter new Birth Year (leave blank to skip): ");
        String birthYearInput = scanner.nextLine();
        if (!birthYearInput.isEmpty()) {
            student.setBirthYear(Integer.parseInt(birthYearInput));
        }

        System.out.println("Enter new Email (leave blank to skip): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            student.setEmail(email);
        }

        System.out.println("Enter new Password (leave blank to skip): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) {
            student.setPassword(password);
        }

        System.out.println("Do you want to update subjects? (Y/N): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            List<Subject> subjects = student.getSubjects();
            System.out.println("Current subjects:");
            for (int i = 0; i < subjects.size(); i++) {
                Subject sub = subjects.get(i);
                System.out.printf("%d. %s (%s)%n", i + 1, sub.getName(), sub.getCode());
            }

            System.out.println("Enter subject number to edit (or 0 to skip editing): ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx > 0 && idx <= subjects.size()) {
                Subject sub = subjects.get(idx - 1);
                System.out.println("New name for this subject (leave blank to keep): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) sub.setName(newName);

                System.out.println("New code for this subject (leave blank to keep): ");
                String newCode = scanner.nextLine();
                if (!newCode.isEmpty()) sub.setCode(newCode);
                subjectService.updateSubject(sub);

                System.out.println("Subject updated successfully.");
            }

            subjects = subjectService.getSubjectsByStudentId(student.getId());


            System.out.println("Enter subject number to remove (or 0 to skip): ");
            int removeIdx = scanner.nextInt();
            scanner.nextLine();

            if (removeIdx > 0 && removeIdx <= subjects.size()) {
                Subject subToRemove = subjects.get(removeIdx - 1);

                subjectService.deleteSubject(subToRemove);

                subjects.remove(removeIdx - 1);

                System.out.println("Subject removed successfully.");
            }


        }

        studentService.updateStudentById(studentId, student);
        System.out.println("Student updated successfully.");
    }

    public static void deleteStudentById() {
        System.out.println("Enter Student ID to delete: ");
        Long studentId = scanner.nextLong();
        scanner.nextLine(); // Consume newline
        studentService.deleteStudentById(studentId);
        System.out.println("Student deleted successfully.");
    }
}