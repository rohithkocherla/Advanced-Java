package org.example;

import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final SessionFactory factory =
            new Configuration().configure().buildSessionFactory();

    // CREATE
    public static void saveStudent(String name, int age) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = new Student(name, age);
            session.persist(s);
            tx.commit();
            System.out.println("✅ Student Saved Successfully!");
        }
    }

    // READ BY ID
    public static void getStudent(int id) {
        try (Session session = factory.openSession()) {
            Student s = session.get(Student.class, id);
            if (s != null)
                System.out.println(s);
            else
                System.out.println("❌ Student not found!");
        }
    }

    // READ ALL
    public static void getAllStudents() {
        try (Session session = factory.openSession()) {
            List<Student> list =
                    session.createQuery("from Student", Student.class).list();

            if (list.isEmpty())
                System.out.println("No records found.");
            else
                list.forEach(System.out::println);
        }
    }

    // UPDATE NAME ONLY
    public static void updateName(int id, String newName) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = session.get(Student.class, id);

            if (s != null) {
                s.setName(newName);
                tx.commit();
                System.out.println("✅ Name Updated Successfully!");
            } else {
                System.out.println("❌ Student not found!");
            }
        }
    }

    // UPDATE AGE ONLY
    public static void updateAge(int id, int newAge) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = session.get(Student.class, id);

            if (s != null) {
                s.setAge(newAge);
                tx.commit();
                System.out.println("✅ Age Updated Successfully!");
            } else {
                System.out.println("❌ Student not found!");
            }
        }
    }

    // DELETE
    public static void deleteStudent(int id) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student s = session.get(Student.class, id);

            if (s != null) {
                session.remove(s);
                System.out.println("✅ Student Deleted Successfully!");
            } else {
                System.out.println("❌ Student not found!");
            }

            tx.commit();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Get Student by ID");
            System.out.println("3. Get All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    saveStudent(name, age);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    getStudent(id);
                    break;

                case 3:
                    getAllStudents();
                    break;

                case 4:
                    System.out.println("\nWhat do you want to update?");
                    System.out.println("1. Update Name");
                    System.out.println("2. Update Age");
                    System.out.print("Enter choice: ");
                    int updateChoice = sc.nextInt();

                    System.out.print("Enter Student ID: ");
                    int updateId = sc.nextInt();

                    sc.nextLine(); // clear buffer

                    if (updateChoice == 1) {
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        updateName(updateId, newName);
                    }
                    else if (updateChoice == 2) {
                        System.out.print("Enter New Age: ");
                        int newAge = sc.nextInt();
                        updateAge(updateId, newAge);
                    }
                    else {
                        System.out.println("Invalid update choice!");
                    }

                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    int deleteId = sc.nextInt();
                    deleteStudent(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 6);

        factory.close();
        sc.close();
    }
}


