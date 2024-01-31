import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TestMain {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Scanner s = new Scanner(System.in);

        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Insert Data");
            System.out.println("2. Select Data");
            System.out.println("3. Select All Data");
            System.out.println("4. Update Data");
            System.out.println("5. Delete Data");
            System.out.print("Enter your choice: ");

            choice = s.nextInt();

            switch (choice) {
                case 1:
                    insertData(session);
                    break;
                case 2:
                    showData(session);
                    break;
                case 3:
                    showAll(session);
                    break;
                case 4:
                    updateData(session);
                    break;
                case 5:
                    deleteData(session);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);

        session.close();
        factory.close();
        s.close();
    }

    private static void deleteData(Session session) {
        Scanner s = new Scanner(System.in);
        Transaction txn = session.beginTransaction();

        System.out.println("Enter the id whose records you want to delete:");
        Doctor doctor = session.get(Doctor.class, s.nextInt());

        session.delete(doctor);
        txn.commit();
        System.out.println("Data deleted successfully");
    }

    private static void updateData(Session session) {
        Scanner s = new Scanner(System.in);
        Transaction txn = session.beginTransaction();

        System.out.println("Enter the id whose records you want to update:");
        Doctor doctor = session.get(Doctor.class, s.nextInt());

        System.out.println("Enter name and salary:");
        String name = s.next();
        int salary = s.nextInt();
        doctor.setDname(name);
        doctor.setSalary(salary);

        session.update(doctor);
        txn.commit();
        System.out.println("Data updated successfully");
    }

    private static void showAll(Session session) {
        List<Doctor> doctors = session.createQuery("from Doctor", Doctor.class).getResultList();

        for (Doctor doctor : doctors) {
            System.out.println(doctor.getDid() + "\t" + doctor.getDname() + "\t" + doctor.getSalary());
        }
    }

    private static void showData(Session session) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter id to get details:");
        Doctor doctor = session.get(Doctor.class, s.nextInt());
        System.out.println(doctor);
    }

    private static void insertData(Session session) {
        Transaction txn = session.beginTransaction();
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the Doctor id, name, and salary:");
        int id = s.nextInt();
        String name = s.next();
        int salary = s.nextInt();

        Doctor doctor = new Doctor();
        doctor.setDid(id);
        doctor.setDname(name);
        doctor.setSalary(salary);

        session.save(doctor);
        txn.commit();

        System.out.println("Data inserted successfully");
    }
}
